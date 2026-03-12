# Streakify – Habit Tracking Application

## Overview

Streakify is a backend habit tracking application designed to help users build consistent habits and improve productivity by maintaining daily streaks. The system allows users to create habits, log their daily progress, and track their current and longest streaks.

The application provides a structured backend using **Spring Boot** and **PostgreSQL**, enabling efficient habit tracking and analytics through RESTful APIs. The project follows a layered architecture to ensure clean code organization, scalability, and maintainability.

---

# Features

* User registration and user management
* Habit creation and habit management
* Daily habit logging
* Current streak calculation
* Longest streak calculation
* Productivity dashboard analytics
* API validation
* Centralized global exception handling
* Clean layered backend architecture

---


## Technologies Used

* Java
* Spring Boot
* Spring Security
* JPA / Hibernate
* PostgreSQL
* Maven
* Postman
* Git
* GitHub



---
## System Architecture

The Streakify backend follows a layered architecture.

Client requests are handled by controllers, business logic is processed in the service layer, and database operations are managed by the repository layer using JPA/Hibernate.

```
Client (Postman / Frontend)
        |
        v
   REST Controller
        |
        v
     Service Layer
        |
        v
   Repository Layer
        |
        v
   PostgreSQL Database

```   
This structure ensures:

Separation of concerns

Clean code organization

Easy maintenance and scalability

# Setup Steps

Follow the steps below to run the Streakify backend application locally.

### 1. Clone the Repository

```
git clone https://github.com/yourusername/streakify.git
```

### 2. Navigate to the Project Directory

```
cd streakify
```

### 3. Install Project Dependencies

```
mvn clean install
```

This command downloads all required dependencies and builds the project.

### 4. Run the Spring Boot Application

```
mvn spring-boot:run
```

Once the application starts successfully, it will run at:

```
http://localhost:8080
```

You can now test the APIs using **Postman** or integrate them with a frontend application.

---

# Database Configuration & Schema

The application uses **PostgreSQL** as the database to store user details, habits, and habit logs.

## Database Setup

Create a database named **streakify** in PostgreSQL:

```id="1jq9rt"
CREATE DATABASE streakify;
```

## Configure application.properties

Update the database connection settings in:

```id="qf12go"
src/main/resources/application.properties
```

Add the following configuration:

```id="9u3hbt"
spring.datasource.url=jdbc:postgresql://localhost:5432/streakify
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

Ensure that the PostgreSQL server is running before starting the application.

---

# Database Schema

## users

| Column   | Data Type                  |
| -------- | -------------------------- |
| id       | BIGINT (Primary Key)       |
| name     | VARCHAR (Not Null)         |
| email    | VARCHAR (Unique, Not Null) |
| password | VARCHAR (Not Null)         |

---

## habits

| Column     | Data Type                       |
| ---------- | ------------------------------- |
| id         | BIGINT (Primary Key)            |
| user_id    | BIGINT (Foreign Key → users.id) |
| habit_name | VARCHAR (Not Null)              |
| frequency  | VARCHAR (Not Null)              |

---

## habit_logs

| Column    | Data Type                        |
| --------- | -------------------------------- |
| id        | BIGINT (Primary Key)             |
| habit_id  | BIGINT (Foreign Key → habits.id) |
| date      | DATE (Not Null)                  |
| completed | BOOLEAN (Not Null)               |


# Sample API Requests and Responses


---
## API Endpoints

### User APIs

POST /users
GET /users/{id}
DELETE /users/{id}

---

### Habit APIs

POST /habits
GET /users/{userId}/habits
DELETE /habits/{id}

---

### Habit Log APIs

POST /habits/{habitId}/logs
PUT /habits/{habitId}/logs/{date}
GET /habits/{habitId}/logs

---

### Streak API

GET /habits/{habitId}/streak

---

### Dashboard API

GET /users/{userId}/dashboard

Below are examples of some core APIs used in the Streakify application.
## 1. Create User

**Endpoint**

```
POST /api/users
```

**Request Body**

```json
{
  "name": "John",
  "email": "john@example.com",
  "password": "123456"
}
```

**Response**

```json
{
  "id": 1,
  "name": "John",
  "email": "john@example.com"
}
```

---

## 2. Create Habit

**Endpoint**

```
POST /api/habits
```

**Request Body**

```json
{
  "userId": 1,
  "habitName": "Exercise",
  "frequency": "Daily"
}
```

**Response**

```json
{
  "habitId": 1,
  "habitName": "Exercise",
  "message": "Habit created successfully"
}
```

---

## 3. Log Habit Completion

**Endpoint**

```
POST /api/logs
```

**Request Body**

```json
{
  "habitId": 1,
  "date": "2026-03-12",
  "completed": true
}
```

**Response**

```json
{
  "message": "Habit logged successfully"
}
```

---

# Screenshots

Screenshots help demonstrate how the application works and improve project documentation.
<img width="1075" height="788" alt="Screenshot 2026-03-11 171105" src="https://github.com/user-attachments/assets/7f98c181-f1a6-4cd2-bdd0-d9a67225b319" />


### Dashboard

```
![Dashboard](screenshots/dashboard.png)
```

### Habit Creation

```
![Create Habit](screenshots/create-habit.png)
```

### Habit Tracking

```
![Habit Tracking](screenshots/habit-tracking.png)
```

Place all screenshots inside a folder named:

```
screenshots
```

inside the repository.

---

# Project Structure

The project follows a **layered architecture**:

```
streakify
│
├── src
│   └── main
│       ├── java
│       │   └── com.example.streakify
│       │       │
│       │       ├── controller
│       │       │   ├── UserController.java
│       │       │   ├── HabitController.java
│       │       │   ├── HabitLogController.java
│       │       │   └── DashboardController.java
│       │       │
│       │       ├── service
│       │       │   ├── UserService.java
│       │       │   ├── HabitService.java
│       │       │   ├── HabitLogService.java
│       │       │   └── DashboardService.java
│       │       │
│       │       ├── repository
│       │       │   ├── UserRepository.java
│       │       │   ├── HabitRepository.java
│       │       │   └── HabitLogRepository.java
│       │       │
│       │       ├── entity
│       │       │   ├── User.java
│       │       │   ├── Habit.java
│       │       │   └── HabitLog.java
│       │       │
│       │       ├── dto
│       │       │   ├── UserDTO.java
│       │       │   ├── HabitDTO.java
│       │       │   └── ErrorResponseDTO.java
│       │       │
│       │       ├── exception
│       │       │   ├── GlobalExceptionHandler.java
│       │       │   └── ResourceNotFoundException.java
│       │       │
│       │       ├── config
│       │       │   └── SecurityConfig.java
│       │       │
│       │       └── StreakifyApplication.java
│       │
│       └── resources
│           ├── application.properties
│           └── static
│
├── screenshots
│   ├── dashboard.png
│   ├── create-habit.png
│   └── habit-tracking.png
│
├── pom.xml
└── README.md
```

This structure improves maintainability and separation of concerns.

---

# Author

Femi Sunil
Final Year Computer Science Student
Backend Developer – Spring Boot
