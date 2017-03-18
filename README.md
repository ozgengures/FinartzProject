This application is a user repository. You can create, update, delete and get all users shortly is a CRUD application. 

Application uses Spring MVC,MongoDB,JUnit,Mockito,slf4j and maven.


Running the Application:

Open the Command Prompt
Go to the root project directory
Run the following maven commands 

mvn install
mvn jetty:run

also db configurations can be set with these parameters:
-Dmongo.uri=
-Dmongo.databaseName=

Go to the browser and enter the following URL:
http://localhost:8080/FinartzProject/
