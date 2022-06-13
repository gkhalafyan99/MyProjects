from django.urls import path

from . import views

urlpatterns = [
    path("", views.index, name="index"),
    path("login", views.login_view, name="login"),
    path("logout", views.logout_view, name="logout"),
    path("register", views.register, name="register"),

    path("math", views.math, name="math"),
    path("history", views.history, name="history"),
    path("geography", views.geography, name="geography"),
    path("computers", views.computers, name="computers"),
    path("biology", views.biology, name="biology"),
    path("literature", views.literature, name="literature"),
    path("mixed", views.mixed, name="mixed"),

    path("singleHistory", views.singleHistory, name="singleHistory"),
    path("singleResult", views.singleResult, name="singleResult"),
    path("singleLeaderboard", views.singleLeaderboard, name="singleLeaderboard"),

    path("doubleIntro", views.doubleIntro, name="doubleIntro"),
    path("doubleFirstResult", views.doubleFirstResult, name="doubleFirstResult"),
    path("challenges", views.challenges, name="challenges"),
    path("doubleGameSecond", views.doubleGameSecond, name="doubleGameSecond"),
    path("doubleSecondResult", views.doubleSecondResult, name="doubleSecondResult"),

    path("doubleLeaderboard", views.doubleLeaderboard, name="doubleLeaderboard"),
    path("doubleHistory", views.doubleHistory, name="doubleHistory"),

    #API routes
    path("singleScores/<str:user>", views.singleScores, name="singleScores"),
    path("singleLeaders/<str:subject>", views.singleLeaders, name="singleLeaders"),
    path("doubleScores/<str:player1>/<str:player2>", views.doubleScores, name="doubleScores"),
]