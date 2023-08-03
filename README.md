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

### User Profile
- Each user has a profile page where their information is displayed, including their username, first name, last name, date of birth, and bio.
- Users can view their own profile information.
- Users can view other users' profiles.

### Post Creation
- Logged-in users can create posts and view posts by other users.

### Follow and Unfollow Users
- Users can follow other users to see their posts in their feed.
- Users can unfollow users they are currently following.

### Security
- The application uses Spring Security to handle user authentication and authorization.
- Only authenticated users can access certain pages and perform specific actions.

### Reusable Templates
- The application uses Thymeleaf fragments to create reusable templates for its information, ensuring consistent design across multiple pages.

## Current Status

- User Registration and Login: ✅ Completed and tested.
- Home Page: ✅ Completed and tested.
- User Profile: ✅ Completed and tested.
- Post Creation: ✅ Completed and tested.
- Error Handling Page: Attempted
- Security: ✅ Completed and tested.
- Reusable Templates: ✅ Completed and tested.

## Contributing
Contributions to the project are welcome! Feel free to open issues and submit pull requests to help improve the application.

## Credits
I got help formatting this README.md file from ChatGPT.
Image by [Freepik](https://www.freepik.com/free-vector/fun-collection-silhouette-avatars_1292975.htm#query=profile%20placeholder&position=4&from_view=keyword&track=ais)
