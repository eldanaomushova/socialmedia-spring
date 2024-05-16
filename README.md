# Social Media App - Chat with Friends and in Groups
### [https://docs.google.com/document/d/1nCK8bXBrHWJdGUOl_lhvsDOlve_C0GvpTFuM1Aaw_R4/edit?usp=sharing]
### [https://drive.google.com/file/d/1LIBG9o2JZxL2cSJIl5GRLIIl3CBB0lyZ/view?usp=sharing]
The Social Media Application is a web-based platform designed to help users to communicate with other users one to one and inside the groups with plenty of other users.
Users can sign up using their usernames and emails, providing them with access to a range of features for interacting with friends and communities.

## Features
* Sign Up: New users can create accounts by providing a unique username and a valid email address.
* Login: Registered users can log in to their accounts using their credentials.
* Groups: User can create new groups, and they should provide userId and userName as a creator's informations.
* Members of group: After creating a group it is possible to add other members to the group.
* Chatting: User can chat with each other, and it neads two user's information for communications.
* Update: People can change their usernames, group name, and message content as a customization of data

## Exceptions
Exceptions helps for smooth handling of errors and unexpected scenarios within the application. 
In the Social Media App, exceptions are used to gracefully manage errors and provide appropriate responses to users. 
It is beta tested, further will be added more exceptions

## Spring Boot tools
Spring Data JPA: For data access and persistence.
Spring MVC: For handling web requests and responses.
H2 Database: In-memory database for development and testing.
Postgres Database: As an alternative profile.
JUnit 5: For unit and integration testing.
Mockito: For mocking dependencies in tests.
Hibernate: For data validation.
Swagger: For providing tools for API.
OpenAPI: For providing access to a software application.

## Crud Methods
Develop REST controllers to expose CRUD operations (GET, POST, PUT, PATCH, DELETE) methods for User, Group, UserMessage, GroupMessage classes.
And (GET, POST, DELETE) for GroupMembers class. (Because, PUT and PATCH methods are not needed. It will make impossible to change data of users directly)

## Test Cases
Testing is an essential part of ensuring the reliability and stability of the Social Media App. The following test cases cover various aspects of the 
application to verify its functionality and behavior. Test cases were maden for:
* controleers
* repositories
* integration
* mappers
* servises
* exceptions

## Authorizaiton and authentication

Authentication Methods:
- OAuth: Github and GitHub authentication options, also Facebook, but it will work only after deploying the server.
- Traditional: Email-based registration and authentication.

Token-Based Authentication: Utilizes JWT for secure, stateless authentication.
Security Measures: Includes IP blacklisting to block suspicious IPs.
Email verification for registered users and send a verification email containing a verification link.
2-factor authentication: Email method, for sending code.

## How to Run the Application

To run the application locally, follow these steps:

1. Clone the repository to your local machine[https://github.com/eldanaomushova/socialmedia-spring.git]
2. Navigate to the root directory of the project.
3. Install the necessary dependencies using npm or yarn.
4. Start the backend server by running.
5. Access the application in your web browser at http://localhost:8080.

## Contributor
Omushova Eldana Com22b












