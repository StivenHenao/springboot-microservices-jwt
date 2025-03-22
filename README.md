# Microservices with Spring Boot, Eureka, and API Gateway

This project implements a microservices architecture using Spring Boot, Eureka Server, and API Gateway. The following microservices are included:

- **Eureka Server**: For service registration and discovery.
- **API Gateway**: For route management and filtering using JWT.
- **Identity Service**: Handles authentication and JWT token generation.
- **Orders Service**: Manages order processing.
---
<p align="center">
  <strong>Architecture</strong>
</p>

<p align="center">
  <img src="https://i.imgur.com/DDc4sVg.png" alt="Architecture Diagram">
</p>

---

## Endpoints and Usage

### 1. User Registration

Registers a new user in the Identity Service.

```sh
curl --location --request POST 'http://localhost:8083/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "user1",
    "password": "password123",
    "email": "test@eimail.com",
    "Role": "admin"
}'
```

### 2. Generate JWT Token

Retrieves a JWT token for authentication.

```sh
curl --location --request POST 'http://localhost:8083/auth/token' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "user1",
    "password": "password123"
}'
```

Example response:

```json
{"token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."}
```

### 3. Validate Token

Checks if a token is valid.

```sh
curl --location --request GET 'http://localhost:8083/auth/validate?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...'
```

### 4. Get All Orders (Authentication Required)

Retrieves all orders stored in the Orders Service.

```sh
curl --location --request GET 'http://localhost:8081/ordenes' \
--header 'Authorization: Bearer <TOKEN_OBTAINED>'
```

### 5. Get Order by ID (Authentication Required)

```sh
curl --location --request GET 'http://localhost:8081/ordenes/1' \
--header 'Authorization: Bearer <TOKEN_OBTAINED>'
```

## Services in Eureka

Once the microservices are running, they can be viewed in Eureka at `http://localhost:8761`. They should appear as follows:

| Application      | Status | URL              |
| ---------------- | ------ | ---------------- |
| API-GATEWAY      | UP     | `localhost:8081` |
| IDENTITY-SERVICE | UP     | `localhost:8083` |
| ORDERS           | UP     | `localhost:8082` |

## Configuration

Ensure the API Gateway is configured to route requests to the appropriate microservices and enforce JWT authentication filters.

## Database Configuration for Identity Service

In the `application.properties` file of the Identity Service, configure the database as follows:

```properties
spring.application.name=identity-service
spring.datasource.url=jdbc:mysql://localhost:3306/<DATABASE_NAME>
spring.datasource.username=<DB_USERNAME>
spring.datasource.password=<DB_PASSWORD>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

Replace `<DATABASE_NAME>`, `<DB_USERNAME>`, and `<DB_PASSWORD>` with your actual database details.

Make sure to create the corresponding database in MySQL before running the service.

## Technologies Used

- Java Spring Boot
- Spring Cloud Eureka
- Spring Security with JWT
- API Gateway
- MySQL
- Hibernate
- Maven

## How to Run

1. Start the Eureka Server:
   ```sh
   mvn spring-boot:run -f eureka-server/pom.xml
   ```
2. Start the API Gateway:
   ```sh
   mvn spring-boot:run -f api-gateway/pom.xml
   ```
3. Start the Identity Service:
   ```sh
   mvn spring-boot:run -f identity-service/pom.xml
   ```
4. Start the Orders Service:
   ```sh
   mvn spring-boot:run -f orders/pom.xml
   ```

