# RabbitMQ Communication Services

### Introduction

This project consists of two Spring Boot applications, `Producer` and `Consumer`, that use RabbitMQ for inter-process communication. The `Producer` sends messages, and the `Consumer` receives and stores them in a PostgreSQL database.

### Technologies

This project uses the following technologies:

- **Java 22** - Latest long-term supported version.
- **Spring Boot 3.3.2** - Framework for building Java applications.
- **Lombok** - Library for reducing boilerplate code (constructors, getters, setters, etc.).
- **RabbitMQ** - Messaging broker for communication between services.
- **PostgreSQL** - Database for storing messages.
- **Maven** - Build tool for managing project dependencies and builds.
- **Docker** - Containerization tool for running the PostgreSQL database.
- **PgAdmin** - Tool for managing PostgreSQL databases.

### Setup and Configuration

#### RabbitMQ and PostgreSQL

- **Run RabbitMQ and PostgreSQL**: Use Docker Compose to start both services: `docker-compose up -d`.
- **Configure PgAdmin**: Open PgAdmin, create a new server with the following details:
    - Host: `localhost`
    - Port: `5432`
    - Maintenance database: `postgres`
    - Username: `yourUsername`
    - Password: `yourPassword`

#### Running the Applications

- **Start Producer**: Navigate to the `producer` directory and run `mvn spring-boot:run`.
- **Start Consumer**: Navigate to the `consumer` directory and run `mvn spring-boot:run`.

### Testing the API

- Use Postman to test the API:
    - Create a new POST request with the URL `http://localhost:8083/api/v1/send`.
    - Set the request body to raw JSON, e.g.:
      ```json
      {
          "name": "Luna Sardar",
          "email": "Luna.sardar@example.com"
      }
      ```
    - Click "Send" to submit the request and observe the message flow.

### Setup and Configuration

#### Setting Up RabbitMQ

1. **Install RabbitMQ**:
    - Download and install RabbitMQ from the [official RabbitMQ website](https://www.rabbitmq.com/download.html).
    - Follow the [installation instructions](https://www.rabbitmq.com/docs/installation.html) for your operating system.

2. **Run RabbitMQ**:
    - Use Docker Compose to start RabbitMQ:
      ```bash
      docker-compose up -d
      ```
    - Alternatively, you can start RabbitMQ manually if installed locally.

#### Configuring the Applications

1. **Configure RabbitMQ for Producer and Consumer Services**:
    - **Producer Service Configuration** (`src/main/resources/application.yml`):
      ```yaml
      spring:
        rabbitmq:
          host: localhost
          port: 5672
          username: guest
          password: guest
      ```

    - **Consumer Service Configuration** (`src/main/resources/application.yml`):
      ```yaml
      spring:
        rabbitmq:
          host: localhost
          port: 5672
          username: guest
          password: guest
 
        datasource:
          url: jdbc:postgresql://localhost:5432/consumer_db
          username: yourUsername
          password: yourPassword
 
        jpa:
          hibernate:
            ddl-auto: update
          show-sql: true
          properties:
            hibernate.dialect: org.hibernate.dialect.PostgreSQLDialect
      ```

2. **Configure PgAdmin**:
    - Open PgAdmin and set up a new server with:
        - **Host**: `localhost`
        - **Port**: `5432`
        - **Maintenance database**: `postgres`
        - **Username**: `yourUsername`
        - **Password**: `yourPassword`
    - Click "Save" to connect to the PostgreSQL database.
