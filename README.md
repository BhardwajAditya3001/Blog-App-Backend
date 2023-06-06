Blog App Backend - Java Spring Boot with JWT Authorization

This repository contains the backend code for a blog application built using Java Spring Boot framework. It includes JWT (JSON Web Token) authorization for secure user authentication and authorization.

Key Features:
- User registration and login endpoints with password hashing for security.
- JWT generation and validation for user authentication and authorization.
- CRUD (Create, Read, Update, Delete) operations for blog posts and comments.
- User roles and permissions management.
- API endpoints for retrieving blog posts, creating new posts, updating existing posts, and deleting posts.
- API endpoints for adding comments to blog posts, updating comments, and deleting comments.
- Integration with a database (e.g., MySQL, PostgreSQL) for persistent storage.
- Unit tests for key components and functionality.

Tech Stack:
- Java 11
- Spring Boot 2.x
- Spring Security for JWT authentication and authorization.
- MySQL/PostgreSQL for database storage.
- JUnit and Mockito for unit testing.

Instructions:
1. Clone the repository: git clone <repository-url>
2. Build the project: ./gradlew build
3. Configure the database connection in application.properties.
4. Run the application: ./gradlew bootRun

Please refer to the README.md file for more detailed instructions on setting up the project and configuring the necessary dependencies.

Feel free to contribute to the project by creating pull requests with bug fixes or additional features.

