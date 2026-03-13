
# Streakify – Habit Tracking Application

### Overview

Streakify is a backend habit tracking application designed to help users build consistent habits and improve productivity by maintaining daily streaks. The system allows users to create habits, log their daily progress, and track their current and longest streaks.

The application provides a structured backend using Spring Boot and PostgreSQL, enabling efficient habit tracking and analytics through RESTful APIs. The project follows a layered architecture to ensure clean code organization, scalability, and maintainability.

---

# Tech Stack

* Java
* Spring Boot
* PostgreSQL
* JPA / Hibernate
* Maven
* Postman

---

# Setup Steps

### 1. Clone the repository

```
git clone https://github.com/femi-77/Streakify.git
cd Streakify
```


### 2. Create PostgreSQL Database

Create a database named:

```
streakify
```



### 3. Configure Database

Update **application.properties**

```
spring.datasource.url=jdbc:postgresql://localhost:5432/streakify
spring.datasource.username=postgres
spring.datasource.password=postgres

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```


### 4. Run the Application

```
mvn spring-boot:run
```

Server runs on:

```
http://localhost:8080
```

---

# Database Schema

### users

| Column   | Type                 |
| -------- | -------------------- |
| id       | BIGINT (Primary Key) |
| name     | VARCHAR              |
| email    | VARCHAR (Unique)     |
| password | VARCHAR              |

---

### habits

| Column     | Type                            |
| ---------- | ------------------------------- |
| id         | BIGINT (Primary Key)            |
| user_id    | BIGINT (Foreign Key → users.id) |
| habit_name | VARCHAR                         |
| frequency  | VARCHAR                         |

---

### habit_logs

| Column    | Type                             |
| --------- | -------------------------------- |
| id        | BIGINT (Primary Key)             |
| habit_id  | BIGINT (Foreign Key → habits.id) |
| date      | DATE                             |
| completed | BOOLEAN                          |

---

# Project Structure

```
Streakify
Streakify
│
├── .idea
│
├── .mvn
│   └── wrapper
│
├── database
│   └── streakify.sql
│
├── postman
│   └── Streakify.postman_collection.json
│
├── screenshot
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── Streakify
│   │   │               ├── controller
│   │   │               │   ├── DashboardController.java
│   │   │               │   ├── HabitController.java
│   │   │               │   ├── HabitLogController.java
│   │   │               │   ├── StreakController.java
│   │   │               │   └── UserController.java
│   │   │               │
│   │   │               ├── dto
│   │   │               │   ├── CurrentStreakItemDTO.java
│   │   │               │   ├── DashboardResponseDTO.java
│   │   │               │   ├── ErrorResponseDTO.java
│   │   │               │   ├── HabitLogGetDTO.java
│   │   │               │   ├── HabitLogRequestDTO.java
│   │   │               │   ├── HabitLogResponseDTO.java
│   │   │               │   ├── HabitRequestDTO.java
│   │   │               │   ├── HabitResponseDTO.java
│   │   │               │   ├── StreakResponseDTO.java
│   │   │               │   ├── UserRequestDTO.java
│   │   │               │   └── UserResponseDTO.java
│   │   │               │
│   │   │               ├── exception
│   │   │               │   ├── BadRequestException.java
│   │   │               │   ├── DuplicateResourceException.java
│   │   │               │   ├── EmailAlreadyExistsException.java
│   │   │               │   ├── GlobalExceptionHandler.java
│   │   │               │   └── ResourceNotFoundException.java
│   │   │               │
│   │   │               ├── model
│   │   │               │   ├── Habit.java
│   │   │               │   ├── HabitLog.java
│   │   │               │   └── User.java
│   │   │               │
│   │   │               ├── repository
│   │   │               │   ├── HabitLogRepository.java
│   │   │               │   ├── HabitRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               │
│   │   │               ├── service
│   │   │               │   ├── DashboardService.java
│   │   │               │   ├── HabitLogService.java
│   │   │               │   ├── HabitService.java
│   │   │               │   ├── StreakService.java
│   │   │               │   └── UserService.java
│   │   │               │
│   │   │               └── StreakifyApplication.java
│   │   │
│   │   └── resources
│   │       ├── static
│   │       ├── templates
│   │       └── application.properties
│   │
│   └── test
│
├── target
│
├── .gitattributes
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
└── README.md
```

---

## API Endpoints

### User APIs

* GET /users/{id}
* DELETE /users/{id}


### Habit APIs

* POST /habits
* GET /users/{userId}/habits
* DELETE /habits/{id}



### Habit Log APIs

* POST /habits/{habitId}/logs
* PUT /habits/{habitId}/logs/{date}
* GET /habits/{habitId}/logs



### Streak APIs

* GET /habits/{habitId}/streak



### Dashboard API

* GET /users/{userId}/dashboard
---

# Screenshots

### Create User

<img width="1075" height="788" alt="Screenshot 2026-03-11 171105" src="https://github.com/user-attachments/assets/327ea932-4cb3-4b93-bdf8-35a9a230f306" />


### Get User

<img width="1087" height="801" alt="Screenshot 2026-03-11 171600" src="https://github.com/user-attachments/assets/46e5b6d0-5b9b-41f9-8a21-496b92a76e4f" />


### Delete User

<img width="1092" height="835" alt="Screenshot 2026-03-11 171925" src="https://github.com/user-attachments/assets/73be4a68-5b1d-4d7d-8872-bc7d618450d5" />


### Create Habit

<img width="1097" height="815" alt="Screenshot 2026-03-11 172627" src="https://github.com/user-attachments/assets/31da0777-1024-4191-b594-2176567e9050" />


### Get User Habits

<img width="1085" height="948" alt="Screenshot 2026-03-11 173857" src="https://github.com/user-attachments/assets/8bd4ef1a-3d39-4d8d-8e6a-cfc1459be074" />


### Delete Habit
<img width="1089" height="749" alt="Screenshot 2026-03-11 175048" src="https://github.com/user-attachments/assets/ebc4a626-dc7d-4b29-afc3-46daaacdf06a" />


### Log Habit

Day 1
<img width="1079" height="798" alt="Screenshot 2026-03-12 205533" src="https://github.com/user-attachments/assets/5226d7df-85d9-426f-8291-0a281b13a6f8" />



Day 2
<img width="1081" height="833" alt="Screenshot 2026-03-12 205608" src="https://github.com/user-attachments/assets/12f9e03e-c0a3-4e5c-a3a1-e90be8b66846" />

Day 3
<img width="1103" height="894" alt="Screenshot 2026-03-12 205622" src="https://github.com/user-attachments/assets/ce1eec2b-4d4d-4931-9894-8165447d91de" />


### Fetch Streak

<img width="1095" height="858" alt="Screenshot 2026-03-12 122806" src="https://github.com/user-attachments/assets/8a393c6b-47c1-4347-a6d8-0dd87e49b947" />



### Dashboard

<img width="1092" height="923" alt="Screenshot 2026-03-12 124436" src="https://github.com/user-attachments/assets/c959a06f-b16d-4fa5-b365-1f0604d0651b" />


### Negative Cases

### Duplicate Email
<img width="1091" height="816" alt="Screenshot 2026-03-11 171346" src="https://github.com/user-attachments/assets/63dfaf22-b4a1-4696-942e-05a91ea7b69c" />

### Invalid Email
<img width="1099" height="822" alt="Screenshot 2026-03-11 171442" src="https://github.com/user-attachments/assets/d1f84aa3-8288-46c9-acc3-222c49dcb99b" />

### User Not Found
<img width="1082" height="879" alt="Screenshot 2026-03-11 171652" src="https://github.com/user-attachments/assets/cb67dc15-365a-418f-a1f8-c081e017867e" />

### User not found with habit
<img width="1094" height="891" alt="Screenshot 2026-03-11 174854" src="https://github.com/user-attachments/assets/2e98a9b8-c9a7-4493-98ad-651eda904ba4" />

### Delete non-existing habit
<img width="1094" height="829" alt="Screenshot 2026-03-12 102432" src="https://github.com/user-attachments/assets/09ee5553-2808-4968-aedc-6801c6974dc4" />


### Future Date Not Allowed
<img width="1092" height="895" alt="Screenshot 2026-03-12 104224" src="https://github.com/user-attachments/assets/c61e5bf5-9c47-4d39-952b-2cb9f836c69d" />

### Habit Not Found
<img width="1094" height="829" alt="Screenshot 2026-03-12 102432" src="https://github.com/user-attachments/assets/31f460dd-f807-40a9-a1a7-596e17b62e33" />

### Duplicate Log
<img width="1087" height="870" alt="Screenshot 2026-03-12 104355" src="https://github.com/user-attachments/assets/1bf08ff0-9195-4777-a3c5-c15a56461fb1" />


