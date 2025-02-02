# nava2105/Spring-Security-JWT

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)

## Table of Contents
1. [General Info](#general-info)
2. [Features](#features)
3. [Technologies](#technologies)
4. [Installation](#installation)
5. [Usage](#usage)
6. [Endpoints](#endpoints)

---

## General Info
This project is a comprehensive **Spring Boot REST API** implementation that uses **Spring Security** with **JWT (JSON Web Tokens)** for authentication and authorization.

- **Key Features**:
  - User authentication via JWT.
  - Role-based access (e.g., `USER` and `ADMIN` roles).
  - REST endpoints for user registration, login, and role assignment.
  - Built-in security configurations, filters, and exception handling for enhanced security.
  - MySQL database integration for persisting users and roles.

Designed for secure APIs involving multiple layers of protection.

---

## Features
The project includes the following capabilities:
- **User Management**:
  - Registration of users with predefined roles.
  - Admin registration and assigning roles dynamically.
- **Authentication and Authorization**:
  - JWT generation during login to provide a secure communication token.
  - Validation of JWT tokens in subsequent authenticated requests.
- **Role-Based Access Control (RBAC)**:
  - `ADMIN` has extended permissions (e.g., role assignment).
  - `USER` has limited access.
- **Database Initialization**:
  - Pre-populated `USER` and `ADMIN` roles at application startup via initializer.
- **Swagger Integration**:
  - Interactive API documentation available under `/swagger-ui/index.html`.

---

## Technologies
Technologies used in this project:
- [Java](https://www.oracle.com/java/): Version 17 or higher
- [Spring Boot](https://spring.io/projects/spring-boot): 3.x
- [Spring Security](https://spring.io/guides/gs/securing-web/)
- [JWT (JSON Web Tokens)](https://jwt.io/)
- [MySQL](https://www.mysql.com/)
- [Maven](https://maven.apache.org/): Build tool
- [Apache Tomcat](https://tomcat.apache.org/): 10.1.x (Embedded)

---

## Installation
### Prerequisites
- **Java 17+**: Ensure that Java is installed and configured in your system.
- **MySQL**: Ensure a MySQL database is up and running with credentials that match the application properties.
- **Maven**: Ensure `mvn` is installed.

### Steps to Run the Application:
1. Clone this repository:
   ```bash
   git clone https://github.com/nava2105/Spring-Security-JWT.git
   cd Spring-Security-JWT
   ```

2. Configure the database:
  - Update the `application.properties` file with your MySQL credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ```

1. Run the application:
   ```bash
   mvn spring-boot:run
   ```

2. Access the application:
  - Swagger API Documentation: [http://localhost:90/swagger-ui/index.html](http://localhost:90/swagger-ui/index.html)
  - Token-protected endpoints: Use tools such as Postman or Curl.

---

## Usage
Once the application is up and running, you can interact with the API by performing proper requests:

### Register a User:
- Endpoint: `/api/auth/register`
- Payload:
  ```json
  {
    "username": "your_username",
    "password": "your_password"
  }
  ```

### Register an Admin (Admin Only):
- Endpoint: `/api/auth/register/admin`
- Headers:
  ```
  Authorization: Bearer your_jwt_token
  ```
- Payload:
  ```json
  {
    "username": "admin_username",
    "password": "admin_password" 
  }
  ```
  
### Login and Get JWT:
- Endpoint: `/api/auth/login`
- Payload:
  ```json
  {
    "username": "your_username",
    "password": "your_password"
  }
  ```
- Response:
  ```json
  {
    "accessToken": "your_jwt_token",
    "tokenType": "Bearer"
  }
  ```

### Validate Token:
- Endpoint: `/api/auth/password/update`
- Headers:
  ```
  Authorization: Bearer your_jwt_token
  ```
- Payload:
  ```json
  {
    "password": "new_password"
  }
  ```

### Get User Id From Token:
- Endpoint: `/api/auth/user_id/token`
- Headers:
  ```
  Authorization: Bearer your_jwt_token
  ```
- Payload:
  ```json
  {}
  ```

---

## Endpoints
Below is a comprehensive list of the endpoints included in the project:

### Public Endpoints (No Authentication Required)
- **Register User**
  - `POST /api/auth/register`  
    Registers a user with the default `USER` role.

- **Login**
  - `POST /api/auth/login`  
    Authenticates the user and returns a JWT.

---

### Protected Endpoints (Authentication Required)
- **Register Admin**
  - `POST /api/auth/register/admin`
  - Allows admin users to register new users with the `ADMIN` role.
  - Requires `ADMIN` privilege.

- **Assign Role**
  - `POST /api/auth/password/update`
  - Allows users to update their password.
  - Requires the JWT.

- **Get User Id From Token**
  - `GET /api/auth/user_id/token`
  - Allows other APIs to validate the validity of the token.
  - Requires the JWT.

---

## Environment Variables
Below is the structure for the `.env` file, which contains the environment variables for configuring the database:

| **Variable**      | **Description**                  | **Example**                             |
|-------------------|----------------------------------|-----------------------------------------|
| DATABASE_USERNAME | Username for the database        | root                                    |
| DATABASE_PASSWORD | Password for the database        | password?                               |
| DATABASE_URL      | JDBC URL for connecting to MySQL | jdbc:mysql://localhost:3307/table_users |
---

## Configuration and Notes
- **Application Properties**:
  - Located in the `src/main/resources/application.properties` file.
  - Ensure to set up database credentials correctly before running the app.

- **Security**:
  - `JWT_EXPIRATION_TOKEN` is defined (default: 600 seconds).
  - Modify as required in `SecurityConstants.java`.

---

## Database Schema
The project uses the following schema for roles and users:

### `user` Table:
| **Column** | **Type**       |
|------------|----------------|
| user_id    | Long (Primary) |
| userName   | String         |
| password   | String         |

### `role` Table:
| **Column** | **Type**       |
|------------|----------------|
| role_id    | Long (Primary) |
| name       | String         |

---


### `user_roles` Table:
| **Column** | **Type**       |
|------------|----------------|
| user_id    | Long (Primary) |
| role_id    | Long (Primary) |

---
