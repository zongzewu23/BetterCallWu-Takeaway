# Better Call Wu

## Overview
**Better Call Wu** is a Java-based food delivery management system that leverages modern frameworks and technologies to provide efficient and scalable solutions for managing orders, customers, and food items. Built with Spring Boot and MyBatis-Plus, the project incorporates a clean, modular architecture and ensures high performance and maintainability.

---

## Features
- **User Management**: Handle user authentication and authorization.
- **Order Management**: Efficiently manage orders with robust database transactions.
- **Food and Category Management**: Add, update, and delete food items and their categories.
- **Frontend-Backend Integration**: Seamlessly integrate a Vue.js-powered frontend with a Spring Boot backend.
- **API Support**: Expose RESTful APIs for various operations.

---

## Tech Stack

### Backend
- **Java 17**: Core programming language.
- **Spring Boot**: Framework for rapid development of RESTful web services.
- **MyBatis-Plus**: ORM framework to simplify database interactions.
- **Druid**: Database connection pool for monitoring and optimizing performance.
- **MySQL**: Relational database for storing application data.
- **Lombok**: Reduce boilerplate code for POJOs.
- **FastJSON**: Handle JSON serialization and deserialization.
- **Apache Commons Lang**: Utility library for Java.
- **JUnit**: Testing framework for unit and integration tests.

### Frontend
- **Vue.js**: Progressive JavaScript framework for building user interfaces.
- **Element-UI**: Component library for Vue.js to enhance user interface.
- **Axios**: HTTP client for front-end-backend communication.

### Tools
- **Maven**: Dependency and build management.
- **Postman**: API testing and debugging.
- **Git**: Version control.

---

## Project Structure
```
E:\~IDEAPROJECTS\BETTER_CALL_WU
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com.zongzewu.bettercallwu
│   │   │       ├───common
│   │   │       ├───config
│   │   │       ├───controller
│   │   │       ├───dto
│   │   │       ├───entity
│   │   │       ├───filter
│   │   │       ├───mapper
│   │   │       ├───service
│   │   │       │   └───impl
│   │   │       └───utils
│   │   └───resources
│   │       ├───backend
│   │       │   ├───api
│   │       │   ├───images
│   │       │   │   ├───404-images
│   │       │   │   ├───icons
│   │       │   │   └───login
│   │       │   ├───js
│   │       │   ├───page
│   │       │   ├───plugins
│   │       │   └───styles
│   │       └───front
│   └───test
│       └───java
├───target
└───pom.xml
```

---

## Getting Started

### Prerequisites
- **JDK 17**: Ensure Java Development Kit is installed.
- **MySQL**: Install and configure a MySQL server.
- **Maven**: Ensure Maven is installed for project dependency management.

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/zongzewu23/Better_Call_Wu.git
   ```
2. Navigate to the project directory:
   ```bash
   cd Better_Call_Wu
   ```
3. Configure `application.properties`:
    - Update MySQL database credentials.
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bettercallwu
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. Install dependencies:
   ```bash
   mvn clean install
   ```

5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

---

## API Documentation
API documentation will be auto-generated using Swagger.
To access the Swagger UI, navigate to:
```
http://localhost:8080/swagger-ui/
```

---

## Future Plans
- **Redis Integration**: Improve caching performance.
- **Dockerization**: Containerize the application for easy deployment.
- **Role-Based Access Control (RBAC)**: Enhance security.
- **Real-Time Notifications**: Implement using WebSocket.

---

## Contributing
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch (`git checkout -b feature/your-feature-name`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature-name`).
5. Open a pull request.

---

## License
This project is licensed under the MIT License. See the `LICENSE` file for details.
