  Quizzer

  My final project's name is Quizzer, it is a webpage wgere users can register and 
take quizes on 6 different subjects, or a mixed category, which gives questions 
from all teh subjects. The users also can challenge another users to have battles, 
to see who is the winner, gain points and become a leader.
  When visiting the index route the user is presented with all available cathegories
of the quizes that can be taken. And from the navigation bar the user can look at 
the leaderboards and game history in both single and double game categories. Also 
they can visit the "challenges" page to see whether there are any users who have 
challenged them to play a game against each other.
  Now let's describe the steps of making any application with the description of 
files included in each step:
  -> First I created Question model in models.py and manually via shell I wrote all
the questions of each category in the table.
  -> After it I created User model and then some templates` login.html, 
register.html, so the user would be able to register on the page, and those 
templates are connected with javascript files login.js and register.js which kepp
the submit button deactivated until the username written is at least 1 character 
long and the password is at least 6 characters long.
  -> After it some urls, views, models were created for single games, when the user
chooses each category with help of Python 8 random questions from that category are 
chosen from the database and presented to the user and when the user finishes the 
quiz the score is counted and added to the history of single games and also the 
user's score in the leaderboard table is updated, and after all this is done a 
template is being represented with the result of the user's quiz. Also if the user 
chooses mixed category 8 questions are randomly being chosen from the whole 
database. A timer is developed on each page with javascript, so when the ti
  -> After it two pages were developed, one for history of single games, one for
leaderboard table. With the help of Python again the necessary results are shown 
and with the help of Javascript and API calls, we can filter the given results 
without leaving the page.
  -> After the development of single game, I moved on to double game when one 
player can challenge another one. And when he makes the first move, 10 questions
are generated and they are saved, so the opponent later will answer the same 
questions after accepting the challenge which is being made when the first user
finishes the quiz.
  -> Again, like single games, when the second user finishes the double game the
winner is presented on the result page, but before it the result is added to double
games' history table and the leaderboard table is updated. Both history and 
leaderboard tabel pages are developed like single oages, again on the backend we
gain the necessary information and pass it to the template and on the history page
with the help of javascript we ahain can filter result for both players.
  -> The application is also mobile-responsive, media queries are used in styles.css
file to make it possible. Three sizes of screens were chosen, before 375px for 
mobile devices, 768px for tablets and bigger screens for laptops and personal 
computers.
  In my opinion the project is pretty distinct from other projects in the course, 
I think it is more complicated because it uses more difficult models, more difficult
queries and API calls are made and I think the most different and difficult part is
when one user is able to chalenge another one and all the questions are being saved.
Also a little more advanced thing was filtration with Javascript used in double game
leaderboard page, beacuse instead of one criteria it can be filtered based on the 
names of two players which hasn't been done before in the course. And the last requirement of the project that is met is mobile-responsivness, which is correctly
designed with the help of CSS.
 
  
  