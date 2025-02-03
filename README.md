# CreateAdminMicroservice

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
7. [Database Schema](#database-schema)
8. [Docker](#docker)

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
   

2. Configure the database:
  - Create a `.env` file in the root folder of the repository with the database credentials:
    ```properties 
    MYSQL_DATABASE=ptrainer_users
    MYSQL_USER=root
    MYSQL_PASSWORD=Mmnsin210606?
    SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/ptrainer_users?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    SPRING_DATASOURCE_USERNAME=root
    SPRING_DATASOURCE_PASSWORD=Mmnsin210606?
    DATABASE_URL=jdbc:mysql://localhost:3306/ptrainer_users?createDatabaseIfNotExist=true
    DATABASE_USERNAME=root
    DATABASE_PASSWORD=Mmnsin210606?
    ```

1. Run the application:
   ```bash
   mvn spring-boot:run
   ```

---

## Usage
Once the application is up and running, you can interact with the API by performing proper requests:

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

---

## Endpoints
Below is a comprehensive list of the endpoints included in the project:

### Protected Endpoints (Authentication Required)
- **Register Admin**
  - `POST /api/auth/register/admin`
  - Allows admin users to register new users with the `ADMIN` role.
  - Requires `ADMIN` privilege.

---

- **Security**:
  - `JWT_EXPIRATION_TOKEN` is defined (default: 30 minutes).
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

## Docker

To use the `docker-compose.yml` file, change the paths of the other microservices defined in it depending where you have it stored