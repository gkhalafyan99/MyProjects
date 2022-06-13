import json
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.decorators import login_required
from django.db import IntegrityError
from django.http import JsonResponse
from django.shortcuts import HttpResponse, HttpResponseRedirect, render
from django.urls import reverse
from django.views.decorators.csrf import csrf_exempt
from django.core.paginator import Paginator
import random

from .models import User, Question, Single, SingleLeaders, Double, DoubleLeaders

# Create your views here.
def index(request):

    if request.user.is_authenticated:
        return render(request, "quizzer/index.html")
    else:
        return HttpResponseRedirect(reverse("login")) 


def login_view(request):
    if request.method == "POST":

        # Attempt to sign user in
        username = request.POST["username"]
        password = request.POST["password"]
        user = authenticate(request, username=username, password=password)

        # Check if authentication successful
        if user is not None:
            login(request, user)
            return HttpResponseRedirect(reverse("index"))
        else:
            return render(request, "quizzer/login.html", {
                "message": "Invalid username and/or password."
            })
    else:
        return render(request, "quizzer/login.html")

@login_required()
def logout_view(request):
    logout(request)
    return HttpResponseRedirect(reverse("index"))


def register(request):
    if request.method == "POST":
        username = request.POST["username"]
        email = request.POST["email"]

        password = request.POST["password"]
        confirmation = request.POST["confirmation"]
        if password != confirmation:
            return render(request, "quizzer/register.html", {
                "message": "Passwords must match."
            })

        try:
            user = User.objects.create_user(username, email, password)
            user.save()

            leader = SingleLeaders(username=username)
            leader.save()

            leader = DoubleLeaders(username=username)
            leader.save()
        except IntegrityError:
            return render(request, "quizzer/register.html", {
                "message": "Username already taken."
            })
        login(request, user)
        return HttpResponseRedirect(reverse("index"))
    else:
        return render(request, "quizzer/register.html")


@login_required()
def math(request):
    
    randomList = random.sample(range(1, 31), 8)
    questions = Question.objects.filter(subject="math", id__in=randomList)
    questionNumbers = []
    for q in questions:
        questionNumbers.append(q.id)

    return render(request, "quizzer/singleGame.html", {
        "subject": "Math",
        "questions": questions,
        "questionNumbers": questionNumbers
    })


@login_required()
def history(request):
    
    randomList = random.sample(range(31, 61), 8)
    questions = Question.objects.filter(subject="history", id__in=randomList)
    questionNumbers = []
    for q in questions:
        questionNumbers.append(q.id)

    return render(request, "quizzer/singleGame.html", {
        "subject": "History",
        "questions": questions,
        "questionNumbers": questionNumbers
    })


@login_required()
def geography(request):
    
    randomList = random.sample(range(61, 91), 8)
    questions = Question.objects.filter(subject="geography", id__in=randomList)
    questionNumbers = []
    for q in questions:
        questionNumbers.append(q.id)

    return render(request, "quizzer/singleGame.html", {
        "subject": "Geography",
        "questions": questions,
        "questionNumbers": questionNumbers
    })


@login_required()
def computers(request):
    
    randomList = random.sample(range(91, 121), 8)
    questions = Question.objects.filter(subject="computers", id__in=randomList)
    questionNumbers = []
    for q in questions:
        questionNumbers.append(q.id)

    return render(request, "quizzer/singleGame.html", {
        "subject": "Computers",
        "questions": questions,
        "questionNumbers": questionNumbers
    })


@login_required()
def biology(request):
    
    randomList = random.sample(range(121, 151), 8)
    questions = Question.objects.filter(subject="biology", id__in=randomList)
    questionNumbers = []
    for q in questions:
        questionNumbers.append(q.id)

    return render(request, "quizzer/singleGame.html", {
        "subject": "Biology",
        "questions": questions,
        "questionNumbers": questionNumbers
    })


@login_required()
def literature(request):
    
    randomList = random.sample(range(151, 181), 8)
    questions = Question.objects.filter(subject="literature", id__in=randomList)
    questionNumbers = []
    for q in questions:
        questionNumbers.append(q.id)

    return render(request, "quizzer/singleGame.html", {
        "subject": "Literature",
        "questions": questions,
        "questionNumbers": questionNumbers
    })


@login_required()
def mixed(request):
    
    randomList = random.sample(range(1, 181), 8)
    questions = Question.objects.filter(id__in=randomList)
    questionNumbers = []
    for q in questions:
        questionNumbers.append(q.id)

    return render(request, "quizzer/singleGame.html", {
        "subject": "Mixed",
        "questions": questions,
        "questionNumbers": questionNumbers
    })


@login_required()
def singleResult(request):
    if request.method == "POST":
        questionNumbers = []
        questionNumbers.append(int(request.POST["first"]))
        questionNumbers.append(int(request.POST["second"]))
        questionNumbers.append(int(request.POST["third"]))
        questionNumbers.append(int(request.POST["fourth"]))
        questionNumbers.append(int(request.POST["fifth"]))
        questionNumbers.append(int(request.POST["sixth"]))
        questionNumbers.append(int(request.POST["seventh"]))
        questionNumbers.append(int(request.POST["eighth"]))

        score = 0

        answers = []
        for i in range(1, 9):
            answers.append(request.POST.get(str(i)))


        for i in range(0, 8):
            answer = Question.objects.get(id=questionNumbers[i]).answer
            if answers[i] == answer:
                score += 1

        subject = Question.objects.get(id=questionNumbers[0]).subject
        single = Single(username=request.user, subject=subject, score=score)
        single.save()

        # Updating the leaderboard table
        if subject == "math":
            mathGainedScore = SingleLeaders.objects.get(username=request.user).mathGainedScore
            mathPossibleScore = SingleLeaders.objects.get(username=request.user).mathPossibleScore

            mathGainedScore += score
            mathPossibleScore += 8
            mathPercent = round((mathGainedScore / mathPossibleScore), 4)

            m = SingleLeaders.objects.get(username=request.user)
            m.mathGainedScore = mathGainedScore
            m.mathPossibleScore = mathPossibleScore
            m.mathPercent = mathPercent
            m.save()

        elif subject == "history":
            historyGainedScore = SingleLeaders.objects.get(username=request.user).historyGainedScore
            historyPossibleScore = SingleLeaders.objects.get(username=request.user).historyPossibleScore

            historyGainedScore += score
            historyPossibleScore += 8
            historyPercent = round((historyGainedScore / historyPossibleScore), 4)

            h = SingleLeaders.objects.get(username=request.user)
            h.historyGainedScore = historyGainedScore
            h.historyPossibleScore = historyPossibleScore
            h.historyPercent = historyPercent
            h.save()

        elif subject == "geography":
            geographyGainedScore = SingleLeaders.objects.get(username=request.user).geographyGainedScore
            geographyPossibleScore = SingleLeaders.objects.get(username=request.user).geographyPossibleScore

            geographyGainedScore += score
            geographyPossibleScore += 8
            geographyPercent = round((geographyGainedScore / geographyPossibleScore), 4)

            g = SingleLeaders.objects.get(username=request.user)
            g.geographyGainedScore = geographyGainedScore
            g.geographyPossibleScore = geographyPossibleScore
            g.geographyPercent = geographyPercent
            g.save()

        elif subject == "computers":
            computersGainedScore = SingleLeaders.objects.get(username=request.user).computersGainedScore
            computersPossibleScore = SingleLeaders.objects.get(username=request.user).computersPossibleScore

            computersGainedScore += score
            computersPossibleScore += 8
            computersPercent = round((computersGainedScore / computersPossibleScore), 4)

            c = SingleLeaders.objects.get(username=request.user)
            c.computersGainedScore = computersGainedScore
            c.computersPossibleScore = computersPossibleScore
            c.computersPercent = computersPercent
            c.save()

        elif subject == "biology":
            biologyGainedScore = SingleLeaders.objects.get(username=request.user).biologyGainedScore
            biologyPossibleScore = SingleLeaders.objects.get(username=request.user).biologyPossibleScore

            biologyGainedScore += score
            biologyPossibleScore += 8
            biologyPercent = round((biologyGainedScore / biologyPossibleScore), 4)

            b = SingleLeaders.objects.get(username=request.user)
            b.biologyGainedScore = biologyGainedScore
            b.biologyPossibleScore = biologyPossibleScore
            b.biologyPercent = biologyPercent
            b.save()

        elif subject == "literature":
            literatureGainedScore = SingleLeaders.objects.get(username=request.user).literatureGainedScore
            literaturePossibleScore = SingleLeaders.objects.get(username=request.user).literaturePossibleScore

            literatureGainedScore += score
            literaturePossibleScore += 8
            literaturePercent = round((literatureGainedScore / literaturePossibleScore), 4)

            l = SingleLeaders.objects.get(username=request.user)
            l.literatureGainedScore = literatureGainedScore
            l.literaturePossibleScore = literaturePossibleScore
            l.literaturePercent = literaturePercent
            l.save()

        elif subject == "mixed":
            mixedGainedScore = SingleLeaders.objects.get(username=request.user).mixedGainedScore
            mixedPossibleScore = SingleLeaders.objects.get(username=request.user).mixedPossibleScore

            mixedGainedScore += score
            mixedPossibleScore += 8
            mixedPercent = round((mixedGainedScore / mixedPossibleScore), 4)

            m = SingleLeaders.objects.get(username=request.user)
            m.mixedGainedScore = mixedGainedScore
            m.mixedPossibleScore = mixedPossibleScore
            m.mixedPercent = mixedPercent
            m.save()


        overallGainedScore = SingleLeaders.objects.get(username=request.user).overallGainedScore
        overallPossibleScore = SingleLeaders.objects.get(username=request.user).overallPossibleScore

        overallGainedScore += score
        overallPossibleScore += 8
        overallPercent = round((overallGainedScore / overallPossibleScore), 4)

        o = SingleLeaders.objects.get(username=request.user)
        o.overallGainedScore = overallGainedScore
        o.overallPossibleScore = overallPossibleScore
        o.overallPercent = overallPercent
        o.save()

        return render(request, "quizzer/singleResult.html", {
            "score": score
        })


@login_required()
def singleHistory(request):
    scores = Single.objects.all().order_by("-time")
    users = User.objects.all()

    return render(request, "quizzer/singleHistory.html", {
            "scores": scores,
            "users": users
        })


@login_required
def singleScores(request, user):

    if user == "All":
        scores = Single.objects.all().order_by("-time")
    else:
        scores = Single.objects.filter(username=user).order_by("-time")
    
    return JsonResponse([score.serialize() for score in scores], safe=False)
    
@login_required
def singleLeaderboard(request):
    leaders = SingleLeaders.objects.all().order_by("-overallPercent")
    subjects = ["math", "history", "geography", "computers", "biology", "literature", "mixed"]

    return render(request, "quizzer/singleLeaderboard.html", {
            "leaders": leaders,
            "subjects": subjects
        })


@login_required
def singleLeaders(request, subject):

    percent = "-" + subject + "Percent"
    leaders = SingleLeaders.objects.all().order_by(percent)

    return JsonResponse([leader.serialize() for leader in leaders], safe=False)


@login_required
def doubleIntro(request):
    if request.method == "POST":
        player1 = request.user
        player2 = request.POST["selectPlayer"]

        randomList = random.sample(range(1, 181), 10)
        questions = Question.objects.filter(id__in=randomList)
        questionNumbers = []
        for q in questions:
            questionNumbers.append(q.id)


        return render(request, "quizzer/doubleGameFirst.html", {
                "player1": player1,
                "player2": player2,
                "questions": questions,
                "questionNumbers": questionNumbers
            })
    
    else:
        users = User.objects.exclude(username=request.user)

        return render(request, "quizzer/doubleIntro.html", {
                "users": users
            })



@login_required()
def doubleFirstResult(request):
    if request.method == "POST":
        questionNumbers = []
        questionNumbers.append(int(request.POST["first"]))
        questionNumbers.append(int(request.POST["second"]))
        questionNumbers.append(int(request.POST["third"]))
        questionNumbers.append(int(request.POST["fourth"]))
        questionNumbers.append(int(request.POST["fifth"]))
        questionNumbers.append(int(request.POST["sixth"]))
        questionNumbers.append(int(request.POST["seventh"]))
        questionNumbers.append(int(request.POST["eighth"]))
        questionNumbers.append(int(request.POST["nineth"]))
        questionNumbers.append(int(request.POST["tenth"]))

        score = 0

        answers = []
        for i in range(1, 11):
            answers.append(request.POST.get(str(i)))


        for i in range(0, 10):
            answer = Question.objects.get(id=questionNumbers[i]).answer
            if answers[i] == answer:
                score += 1

        player1 = request.POST["player1"]
        player2 = request.POST["player2"]

        double = Double(player1=player1, player1score=score, player2=player2, q1=questionNumbers[0], q2=questionNumbers[1], q3=questionNumbers[2], q4=questionNumbers[3], q5=questionNumbers[4], q6=questionNumbers[5], q7=questionNumbers[6], q8=questionNumbers[7], q9=questionNumbers[8], q10=questionNumbers[9])
        double.save()

        return render(request, "quizzer/doubleResult.html", {
            "score": score
        })


@login_required()
def challenges(request):
    challenges = Double.objects.filter(player2=request.user, winner="nobody")

    return render(request, "quizzer/challenges.html", {
        "challenges": challenges
    })


@login_required
def doubleGameSecond(request):
    if request.method == "POST":
        key = request.POST["key"]

        questionNumbers = []
        questionNumbers.append(Double.objects.get(id=key).q1)
        questionNumbers.append(Double.objects.get(id=key).q2)
        questionNumbers.append(Double.objects.get(id=key).q3)
        questionNumbers.append(Double.objects.get(id=key).q4)
        questionNumbers.append(Double.objects.get(id=key).q5)
        questionNumbers.append(Double.objects.get(id=key).q6)
        questionNumbers.append(Double.objects.get(id=key).q7)
        questionNumbers.append(Double.objects.get(id=key).q8)
        questionNumbers.append(Double.objects.get(id=key).q9)
        questionNumbers.append(Double.objects.get(id=key).q10)

        questions = Question.objects.filter(id__in=questionNumbers)

        player1 = Double.objects.get(id=key).player1
        player2 = Double.objects.get(id=key).player2

        return render(request, "quizzer/doubleGameSecond.html", {
                "player1": player1,
                "player2": player2,
                "questions": questions,
                "questionNumbers": questionNumbers,
                "key": key
            })


@login_required()
def doubleSecondResult(request):
    if request.method == "POST":
        questionNumbers = []
        questionNumbers.append(int(request.POST["first"]))
        questionNumbers.append(int(request.POST["second"]))
        questionNumbers.append(int(request.POST["third"]))
        questionNumbers.append(int(request.POST["fourth"]))
        questionNumbers.append(int(request.POST["fifth"]))
        questionNumbers.append(int(request.POST["sixth"]))
        questionNumbers.append(int(request.POST["seventh"]))
        questionNumbers.append(int(request.POST["eighth"]))
        questionNumbers.append(int(request.POST["nineth"]))
        questionNumbers.append(int(request.POST["tenth"]))

        score = 0

        answers = []
        for i in range(1, 11):
            answers.append(request.POST.get(str(i)))


        for i in range(0, 10):
            answer = Question.objects.get(id=questionNumbers[i]).answer
            if answers[i] == answer:
                score += 1


        key = request.POST["key"]

        double = Double.objects.get(id=key)

        player1 = double.player1
        player2 = double.player2

        double.player2score = score
        player1score = double.player1score

        winner = ""
        if score > player1score:
            double.winner = double.player2
            winner = double.player2
        elif score < player1score:
            double.winner = double.player1
            winner = double.player1
        else:
            double.winner = "DRAW"
            winner = "draw"
        
        double.save()


        #Updating the leaderboard table
        p1 = DoubleLeaders.objects.get(username=player1)
        p2 = DoubleLeaders.objects.get(username=player2)

        p1.possibleScore += 2
        p2.possibleScore += 2

        if score > player1score:
            p2.gainedScore += 2
        elif score < player1score:
            p1.gainedScore += 2
        else:
            p1.gainedScore += 1
            p2.gainedScore += 1

        p1.percent = round((p1.gainedScore / p1.possibleScore), 4)
        p2.percent = round((p2.gainedScore / p2.possibleScore), 4)

        p1.save()
        p2.save()

        return render(request, "quizzer/doubleFinal.html", {
            "score": score,
            "winner": winner
        })


@login_required()
def doubleHistory(request):
    scores = Double.objects.all().exclude(winner="nobody").order_by("-id")
    first = set()
    second = set()

    for score in scores:
        first.add(score.player1)
        second.add(score.player2)

    one = []
    two = []

    for f in first:
        one.append(f)

    for s in second:
        two.append(s)

    return render(request, "quizzer/doubleHistory.html", {
            "scores": scores,
            "first": one,
            "second": two
        })


@login_required
def doubleScores(request, player1, player2):

    if player1 == "All" and player2 == "All":
        scores = Double.objects.all().order_by("-id")
    elif player1 == "All":
        scores = Double.objects.filter(player2=player2).order_by("-id")
    elif player2 == "All":
        scores = Double.objects.filter(player1=player1).order_by("-id")
    else:
        scores = Double.objects.filter(player1=player1, player2=player2).order_by("-id")
    
    return JsonResponse([score.serialize() for score in scores], safe=False)



@login_required
def doubleLeaderboard(request):
    leaders = DoubleLeaders.objects.all().order_by("-percent")

    return render(request, "quizzer/doubleLeaderboard.html", {
            "leaders": leaders
        })


