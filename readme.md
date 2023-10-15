# Employee Management API with Spring Boot

## Overview

This is a simple Employee Management API built with Spring Boot. It allows you to manage employee data and offers basic authentication using a user and role system. This README provides an overview of how to set up and use the API.

## Features

- **Employee Management**: You can perform CRUD operations for employees, including creating, retrieving, updating, and deleting employee records.

- **User Authentication**: Secure your API with basic authentication. Users and roles are stored in a database.

- **User Management**: Admin role can manage user accounts, including registration and user profile management.

- **Role-Based Access Control**: Admin role can ssign roles to users to control their access to specific API endpoints.

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or later
- Apache Maven
- H2 Database (for development and testing)

### Setup

1. Clone this repository:
    git clone https://github.com/Nayak-Aishwary/api-demo.git

2. Navigate to the project directory:
    cd api-demo

3. Build the project:
    mvn clean install

4. Run the application:
    mvn spring-boot:run

The API will be accessible at `http://localhost:8082/api/v1/employees`.

### User Authentication

For testing the user authentication and role-based access control features, you can use the following example users:

- **User 1**
  - Username: admin@boot.com
  - Password: password123
  - Role: ADMIN

- **User 2**
  - Username: dev@boot.com
  - Password: password123
  - Role: USER

### API Endpoints

- `GET /api/v1/employees/getAll`: Retrieve all employees.
- `GET /api/v1/employees/getById/{employeeId}`: Retrieve an employee by ID.
- `GET /api/v1/employees/getByName/{empName}`: Search for employees by name.
- `POST /api/v1/employees/register`: Register a new employee.
- `DELETE /api/v1/employees/{employeeId}`: Delete an employee by ID.
- `PUT /api/v1/employees/update/{employeeId}`: Update an employee by ID.

- `GET /api/v1/roles/getAll`: Retrieve all roles.
- `GET /api/v1/roles/getById/{roleId}`: Retrieve a role by ID.
- `POST /api/v1/roles/register`: Register a new role.
- `DELETE /api/v1/roles/{roleId}`: Delete a role by ID.
- `PUT /api/v1/roles/update/{roleId}`: Update a role by ID.

- `GET /api/v1/users/getAll`: Retrieve all users.
- `GET /api/v1/users/getById/{userId}`: Retrieve a user by ID.
- `POST /api/v1/users/register`: Register a new user.
- `DELETE /api/v1/users/{userId}`: Delete a user by ID.
- `PUT /api/v1/users/update/{userId}`: Update a user by ID.

## Security and Logging

- **Spring Security**: Utilized for authentication and authorization.
- **AOP Logging**: Method invocations and responses are logged using Aspect-Oriented Programming (AOP).

Enhancing application security and monitoring.

## Swagger Documentation

You can explore the API documentation using Swagger UI. To access it, open the following URL in your web browser:

Swagger UI accessible at `http://localhost:8082/swagger-ui.html`

The Swagger UI provides an interactive interface for exploring and testing the available API endpoints. You can view details about the endpoints, submit requests, and see responses right from your browser.

## Testing

This application includes test cases to verify the functionality of the API and data persistence. The tests are located in the `jp.co.axa.apidemo` package and cover aspects like saving, retrieving, updating, and deleting employee records.

## Configuration

- Database settings and other configurations can be modified in the `application.properties` file.

### Create an Employee Example

**Request:**

- **HTTP Method:** POST
- **Endpoint:** `/api/v1/employees`
- **Request Body Example:**

```json
{
    "name": "Sachin Tendulkar",
    "salary": 75000,
    "department": "IT"
}