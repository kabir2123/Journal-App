<img width="629" height="157" alt="image" src="https://github.com/user-attachments/assets/cf8f18b9-016b-4bfa-9440-e3cc9101bfe0" />

# 📝 Journal App - Spring Boot REST API

A robust, production-ready journal application built with **Spring Boot**, **MongoDB**, and **RESTful API** principles. This project demonstrates advanced Java development skills including transaction management, data validation, and comprehensive error handling.

##  Features

### Core Functionality
- **User Management**: Complete CRUD operations for user accounts
- **Journal Entries**: Create, read, update, and delete personal journal entries
- **Data Integrity**: Atomic transactions ensuring data consistency
- **RESTful API**: Clean, intuitive REST endpoints following best practices
- **Error Handling**: Comprehensive error responses with appropriate HTTP status codes

### Technical Highlights
- **@Transactional Annotations**: Ensures atomicity across database operations
- **MongoDB Integration**: NoSQL database with Spring Data MongoDB
- **Lombok Integration**: Reduces boilerplate code
- **Spring Boot Auto-configuration**: Zero configuration deployment
- **Health Check Endpoint**: Application monitoring capability

##  Technology Stack

- **Backend**: Spring Boot 3.x
- **Database**: MongoDB
- **Build Tool**: Maven
- **Language**: Java 17+
- **Documentation**: Spring Boot Actuator
- **Dependencies**: Spring Data MongoDB, Spring Web, Lombok

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MongoDB (local or cloud instance)
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## ⚙️ Installation & Setup

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/journal-app.git
cd journal-app
```

### 2. Configure MongoDB
Ensure MongoDB is running on your system or update `application.properties` with your MongoDB connection string:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/journalapp
```

### 3. Build and Run
```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

##  API Documentation

### Base URL
```
http://localhost:8080
```

### User Management Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `GET` | `/user` | Get all users | - |
| `POST` | `/user` | Create a new user | `{"username": "john_doe", "password": "secure123"}` |
| `PUT` | `/user` | Update user information | `{"username": "john_doe", "password": "newpassword"}` |
| `DELETE` | `/user/{username}` | Delete user and all their journal entries | - |

### Journal Entry Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| `GET` | `/journal/{username}` | Get all journal entries for a user | - |
| `POST` | `/journal/{username}` | Create a new journal entry | `{"title": "My Day", "content": "Today was amazing..."}` |
| `GET` | `/journal/id/{entryId}` | Get a specific journal entry by ID | - |
| `PUT` | `/journal/id/{entryId}/{username}` | Update a journal entry | `{"title": "Updated Title", "content": "Updated content"}` |
| `DELETE` | `/journal/id/{entryId}` | Delete a journal entry | - |

### Health Check
| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/health-check` | Application health status |

## 🔧 Using the API with Postman

You can easily test and interact with the Journal App API using [Postman](https://www.postman.com/). Follow these steps:

### 1. Import Endpoints
- Open Postman and create a new collection (e.g., "Journal App API").
- Add requests for each endpoint as described below.

### 2. Example Requests

#### **Create a User**
- **Method:** POST
- **URL:** `http://localhost:8080/user`
- **Body:**
  - Select `raw` and `JSON` in the body tab
  - Example:
    ```json
    {
      "username": "john_doe",
      "password": "secure123"
    }
    ```

#### **Get All Users**
- **Method:** GET
- **URL:** `http://localhost:8080/user`

#### **Update a User**
- **Method:** PUT
- **URL:** `http://localhost:8080/user`
- **Body:**
  - Select `raw` and `JSON`
  - Example:
    ```json
    {
      "username": "john_doe",
      "password": "newpassword"
    }
    ```

#### **Delete a User**
- **Method:** DELETE
- **URL:** `http://localhost:8080/user/john_doe`

#### **Create a Journal Entry**
- **Method:** POST
- **URL:** `http://localhost:8080/journal/john_doe`
- **Body:**
  - Select `raw` and `JSON`
  - Example:
    ```json
    {
      "title": "My First Entry",
      "content": "Today I learned about Spring Boot and MongoDB integration."
    }
    ```

#### **Get All Journal Entries for a User**
- **Method:** GET
- **URL:** `http://localhost:8080/journal/john_doe`

#### **Get a Journal Entry by ID**
- **Method:** GET
- **URL:** `http://localhost:8080/journal/id/{entryId}`

#### **Update a Journal Entry**
- **Method:** PUT
- **URL:** `http://localhost:8080/journal/id/{entryId}/john_doe`
- **Body:**
  - Select `raw` and `JSON`
  - Example:
    ```json
    {
      "title": "Updated Title",
      "content": "Updated content"
    }
    ```

#### **Delete a Journal Entry**
- **Method:** DELETE
- **URL:** `http://localhost:8080/journal/id/{entryId}`

#### **Health Check**
- **Method:** GET
- **URL:** `http://localhost:8080/health-check`

### 3. Tips
- Make sure to set the `Content-Type` header to `application/json` for all POST and PUT requests.
- You can save all these requests in your Postman collection for easy reuse.
- Use environment variables in Postman for `base_url` if you want to switch between local and deployed environments.

##  Architecture

### Project Structure
```
journalApp/
├── src/main/java/com/example/journalApp/
│   ├── controller/          # REST API controllers
│   ├── entity/             # MongoDB document models
│   ├── repository/          # Data access layer
│   ├── service/            # Business logic layer
│   └── JournalAppApplication.java
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

### Key Design Patterns
- **Layered Architecture**: Controller → Service → Repository
- **Dependency Injection**: Spring's IoC container
- **Repository Pattern**: Data access abstraction
- **Transaction Management**: ACID compliance with @Transactional



