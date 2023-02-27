# Film Query Application
This is a command-line application that interacts with a database to retrieve and display film data. The user is presented with a menu-based interface with the following options:

Look up a film by its id.
Look up a film by a search keyword.
Exit the application.
Getting Started
To get started with this application, clone the repository to your local machine and open the project in an IDE such as Eclipse or IntelliJ.

Prerequisites
You will need the following software installed on your machine to run this application:

Java JDK 8 or higher
MySQL server
MySQL Connector/J
Installing
Create a new MySQL database named "filmdb".
Run the "create-db.sql" script located in the "sql" directory to create the necessary tables and data.
Update the values in the "mysql.properties" file with your MySQL server connection details.
Build the project using Maven: mvn package
Run the application using the following command: java -jar target/FilmQueryApp.jar
Usage
When you run the application, you will be presented with a menu:






## Description 

markdown
Copy code
1. Look up film by id
2. Look up film by search keyword
3. Exit
To look up a film by id, select option 1 and enter the id of the film you wish to search for. If the film is found, its title, year, rating, description, language, and cast will be displayed. If the film is not found, a message will be displayed saying so.

To look up a film by search keyword, select option 2 and enter the keyword you wish to search for. If any matching films are found, a list of films with their title, year, rating, description, language, and cast will be displayed. If no matching films are found, a message will be displayed saying so.

To exit the application, select option 3.

## Technologies Used 
Built With
Java
SQL
MYSQL
Maven
Eclipse IDE

## Lessons Learned 

Becoming more experienced with languages SQL, Java and understanding of Maven.

Acknowledgments
This project was created as part of the Skill Distillery Full Stack Java Development program.




