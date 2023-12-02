# ChâTop Application Backend

Chatop is a real estate rental company in a tourist area that offers an online portal for potential tenants to contact property owners.

## Installation and Execution

### Steps

1. Clone the repository to your local machine:
    ```bash
    git clone https://github.com/SandraVsn/Developpez-le-back-end-en-utilisant-Java-et-Spring.git
    ```

2. Navigate to the project directory:
    ```bash
    cd project_name
    ```

3. Compile and run the API with Maven:
    ```bash
    mvn spring-boot:run
    ```

The API will be accessible at http://localhost:9000.

## Database

### Installation

1. Ensure Docker and Docker Compose are installed on your machine.
2. Create a .env file with the necessary variables for the successful launch of the database.
3. Launch the MySQL container with the following command in the project directory:
    ```bash
    docker-compose up -d
    ```

   This will start a MySQL container based on the configuration defined in the `docker-compose.yml`.

## API Documentation (Swagger)

The API is documented with Swagger. Access the following URL to explore and test API endpoints:
- [Swagger UI](http://localhost:9000/swagger-ui/index.html)

## Technologies

- Spring Boot
- Hibernate
- Maven
- Java 17
- MySql
- Swagger
- Docker
- Docker Compose