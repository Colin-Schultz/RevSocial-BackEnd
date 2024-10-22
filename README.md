# RevSocial - Back End

## Project Description

In Revature's Social Network everyone is friends with everyone else. Users can register, login to the application,
and start sharing multimedia with everyone. Registered users are allowed to modify their personal information and 
upload their profile pictures. The application provides a search feature that allows users to search out friends and 
look at their profiles. Users are provided with a "feed", in which they can see what everyone is posting and like posts. 
Users can access and use the application via an interactive client-side single paged application that stores and 
retrieves multimedia using AWS S3 and consumes a RESTful web service that provides business logic and access to a database.

## Technologies Used

* Spring
* Java
* PostgreSQL
* Hibernate
* Log4j
* JUnit
* Agile-Scrum

## Features

 * As a user:
    * I can register a new account.
    * I can login/logout.
    * I can write a post.
    * I can like a post.
    * I can view all posts.
    * I can search other users and view their profile.
    * I can edit my profile: change my name and profile picture.
    * I can ask for a password reset and the application will send me an email on how to reset my password.

To-do list:
* Users can comment in posts.
* Users can add a YouTube link to their post - it should display YouTube API to display it.
* Users get notifications.

## Getting Started

1. Open up your terminal and create a new directory.
  - mkdir <directory_name>
2. Run the following command:
  - git clone https://github.com/matthewjunglee/RevSocial-BackEnd.git
3. Open up the project in a Java IDE.
4. Create a SQL database and change the url, username, and password in the applicationContext.xml file in src/main/webapp/WEB-INF to match your database.
5. Set up AWS SES. In com.network.email.ResetEmailConfig.java, replace the necessary information.
6. Go to https://github.com/matthewjunglee/RevSocial-FrontEnd and clone the front end.

## Contributors

* Kevin Bagnall
* Earnest Gibbs
* Matthew Lee
* Colin Schultz
