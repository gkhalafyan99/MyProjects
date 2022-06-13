from django.contrib.auth.models import AbstractUser
from django.db import models

# Create your models here.
class User(AbstractUser):
    pass

class Question(models.Model):
    subject = models.CharField(max_length=20)
    question = models.CharField(max_length=300)
    a = models.CharField(max_length=50)
    b = models.CharField(max_length=50)
    c = models.CharField(max_length=50)
    d = models.CharField(max_length=50)
    answer = models.CharField(max_length=50)

    def __str__(self):
        return f"id: {self.pk}, subject: {self.subject}, question: {self.question}, a: {self.a}, b: {self.b}, c: {self.c}, d: {self.d}, answer: {self.answer} "



class Single(models.Model):
    username = models.CharField(max_length=300)
    subject = models.CharField(max_length=20)
    score = models.IntegerField()
    time = models.DateTimeField(auto_now_add=True)

    def serialize(self):
        return {
            "id": self.id,
            "username": self.username,
            "subject": self.subject,
            "score": self.score,
            "time": self.time.strftime("%b %-d %Y, %-I:%M %p")
        }

    def __str__(self):
        return f"id: {self.pk}, username: {self.username}, subject: {self.subject}, score: {self.score}, time: {self.time}"


class SingleLeaders(models.Model):
    username = models.CharField(max_length=300)

    overallGainedScore = models.IntegerField(default=0)
    overallPossibleScore = models.IntegerField(default=0)
    overallPercent = models.FloatField(default=0)

    mathGainedScore = models.IntegerField(default=0)
    mathPossibleScore = models.IntegerField(default=0)
    mathPercent = models.FloatField(default=0)

    historyGainedScore = models.IntegerField(default=0)
    historyPossibleScore = models.IntegerField(default=0)
    historyPercent = models.FloatField(default=0)

    geographyGainedScore = models.IntegerField(default=0)
    geographyPossibleScore = models.IntegerField(default=0)
    geographyPercent = models.FloatField(default=0)

    geographyGainedScore = models.IntegerField(default=0)
    geographyPossibleScore = models.IntegerField(default=0)
    geographyPercent = models.FloatField(default=0)

    computersGainedScore = models.IntegerField(default=0)
    computersPossibleScore = models.IntegerField(default=0)
    computersPercent = models.FloatField(default=0)

    biologyGainedScore = models.IntegerField(default=0)
    biologyPossibleScore = models.IntegerField(default=0)
    biologyPercent = models.FloatField(default=0)

    literatureGainedScore = models.IntegerField(default=0)
    literaturePossibleScore = models.IntegerField(default=0)
    literaturePercent = models.FloatField(default=0)

    mixedGainedScore = models.IntegerField(default=0)
    mixedPossibleScore = models.IntegerField(default=0)
    mixedPercent = models.FloatField(default=0)

    def serialize(self):
        return {
            "id": self.id,
            "username": self.username,
            "overallGainedScore": self.overallGainedScore,
            "overallPossibleScore": self.overallPossibleScore,
            "overallPercent": self.overallPercent,
            "mathGainedScore": self.mathGainedScore,
            "mathPossibleScore": self.mathPossibleScore,
            "mathPercent": self.mathPercent,
            "historyGainedScore": self.historyGainedScore,
            "historyPossibleScore": self.historyPossibleScore,
            "historyPercent": self.historyPercent,
            "geographyGainedScore": self.geographyGainedScore,
            "geographyPossibleScore": self.geographyPossibleScore,
            "geographyPercent": self.geographyPercent,
            "computersGainedScore": self.computersGainedScore,
            "computersPossibleScore": self.computersPossibleScore,
            "computersPercent": self.computersPercent,
            "biologyGainedScore": self.biologyGainedScore,
            "biologyPossibleScore": self.biologyPossibleScore,
            "biologyPercent": self.biologyPercent,
            "literatureGainedScore": self.literatureGainedScore,
            "literaturePossibleScore": self.literaturePossibleScore,
            "literaturePercent": self.literaturePercent,
            "mixedGainedScore": self.mixedGainedScore,
            "mixedPossibleScore": self.mixedPossibleScore,
            "mixedPercent": self.mixedPercent
        }

    def __str__(self):
        return f"id: {self.pk}, username: {self.username}, overallGainedScore: {self.overallGainedScore}, overallPossibleScore: {self.overallPossibleScore}, overallPercent: {self.overallPercent}, mathGainedScore: {self.mathGainedScore}, mathPossibleScore: {self.mathPossibleScore}, mathPercent: {self.mathPercent},historyGainedScore: {self.historyGainedScore}, historyPossibleScore: {self.historyPossibleScore}, historyPercent: {self.historyPercent}, geographyGainedScore: {self.geographyGainedScore}, geographyPossibleScore: {self.geographyPossibleScore}, geographyPercent: {self.geographyPercent}, computersGainedScore: {self.computersGainedScore}, computersPossibleScore: {self.computersPossibleScore}, computersPercent: {self.computersPercent}, biologyGainedScore: {self.biologyGainedScore}, biologyPossibleScore: {self.biologyPossibleScore}, biologyPercent: {self.biologyPercent}, literatureGainedScore: {self.literatureGainedScore}, literaturePossibleScore: {self.literaturePossibleScore}, literaturePercent: {self.literaturePercent}, mixedGainedScore: {self.mixedGainedScore}, mixedPossibleScore: {self.mixedPossibleScore}, mixedPercent: {self.mixedPercent}"



class Double(models.Model):
    player1 = models.CharField(max_length=300)
    player1score = models.IntegerField()
    player2 = models.CharField(max_length=300)
    player2score = models.IntegerField(default=0)
    q1 = models.IntegerField()
    q2 = models.IntegerField()
    q3 = models.IntegerField()
    q4 = models.IntegerField()
    q5 = models.IntegerField()
    q6 = models.IntegerField()
    q7 = models.IntegerField()
    q7 = models.IntegerField()
    q8 = models.IntegerField()
    q9 = models.IntegerField()
    q10 = models.IntegerField()
    winner = models.CharField(max_length=300, default="nobody")


    def serialize(self):
        return {
            "id": self.id,
            "player1": self.player1,
            "player1score": self.player1score,
            "player2": self.player2,
            "player2score": self.player2score,
            "q1": self.q1,
            "q2": self.q2,
            "q3": self.q3,
            "q4": self.q4,
            "q5": self.q5,
            "q6": self.q6,
            "q7": self.q7,
            "q8": self.q8,
            "q9": self.q9,
            "q10": self.q10,
            "winner": self.winner
        }

    def __str__(self):
        return f"id: {self.pk}, player1: {self.player1}, player1score: {self.player1score}, player2: {self.player2}, player2score: {self.player2score}, q1: {self.q1}, q2: {self.q2}, q3: {self.q3}, q4: {self.q4}, q5: {self.q5}, q6: {self.q6}, q7: {self.q7}, q8: {self.q8}, q9: {self.q9}, q10: {self.q10}, winner: {self.winner}"


class DoubleLeaders(models.Model):
    username = models.CharField(max_length=300)
    gainedScore = models.IntegerField(default=0)
    possibleScore = models.IntegerField(default=0)
    percent = models.FloatField(default=0)

   
    def serialize(self):
        return {
            "id": self.id,
            "username": self.username,
            "gainedScore": self.overallGainedScore,
            "possibleScore": self.overallPossibleScore,
            "percent": self.overallPercent
        }

    def __str__(self):
        return f"id: {self.pk}, username: {self.username}, gainedScore: {self.gainedScore}, possibleScore: {self.possibleScore}, percent: {self.percent}"
