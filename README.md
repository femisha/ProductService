##### Getting Started

This Spring Boot project demonstrates various core functionalities and best practices for developing 
robust and scalable applications. The project covers the following features:

### Features

### RESTful Web Services
* Implementation of RESTful APIs using Spring Boot.
* Demonstrates CRUD operations and best practices for building RESTful web services.


### Spring Data JPA and Cardinality Mappings
* Integration with Spring Data JPA for database interactions.
* Demonstrates the use of various cardinality mappings such as One-to-One, One-to-Many, Many-to-One, and Many-to-Many.

### Database Versioning using flyway
* Automated database versioning using flyway
* Using Flyway helps ensure that database schema changes are applied consistently across different environments. 
* It reduces the chances of errors due to manual database updates and makes it easier to manage the lifecycle of database changes over time


### Calling Third-Party APIs Using RestTemplate
* Usage of Spring's RestTemplate to call external third-party APIs.
* Provides examples for GET, POST, PUT, and DELETE requests.


### Exception Handling
* Centralized exception handling using @ControllerAdvice.
* Demonstrates how to handle different types of exceptions and return meaningful error messages to the client.

### Springboot Actuators
* Used actuators to check insights on application's health, metrics, environment, and more.
* http://localhost:9090/actuator
* Enable endpoints using below property
* management.endpoints.web.exposure.include=*