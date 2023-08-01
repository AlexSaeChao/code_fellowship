# Code Fellowship

Code Fellowship is a web application that allows users to sign up, log in, and log out.

## Getting Started

### Prerequisites
- Java 11 or higher
- Spring Boot 2.x
- Thymeleaf
- Postgres

### Installation
1. Clone the repository: `git clone https://github.com/AlexSaeChao/code_fellowship`
2. Navigate to the project directory: `cd code_fellowship`

### Configuration
- Set up your database configuration in `application.properties` file.

### Running the Application
- Run the Spring Boot application using your IDE
- Access the application in your browser at `http://localhost:8080/`

## Features

### User Registration and Login
- Users can sign up by providing a username, password, first name, last name, date of birth, and a bio.
- After successful registration, users can log in with their username and password, then log out.

### Home Page
- The home page displays a welcome message to all users.
- If a user is logged in, their username will be shown in the welcome message.

### Post Creation and Interaction
- Logged-in users can log out right now and can verify they are logged in by displaying their name above the header.
- Only authenticated users can log in and only see their name if they have registered and have signed in.

### Log Out
- Users can log out from the application.

## Current Status

- User Registration and Login: ✅ Completed and tested.
- Home Page: ✅ Completed and tested.
- Post Creation and Interaction: ✅ Completed and tested.
- Log Out: ✅ Completed and tested.

## Contributing
I got help formatting this README.md file from ChatGPT.