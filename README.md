# Library Management System

The Library Management System is a Spring Boot application designed to facilitate the management of books, patrons, and borrowing records in a library setting. It provides RESTful endpoints for performing CRUD operations on books and patrons, as well as borrowing and returning books.
## Features
- **User Management:**
  - Retrieve a list of all Users
  - Retrieve details of a specific user by ID
  - Retrieve details of a specific user by EMAIL
  - Add a new user to the library
  - Update an existing user's information
  - Remove a user from the system
- **Book Management:**
    - Retrieve a list of all books
    - Retrieve details of a specific book by ID
    - Add a new book to the library
    - Update an existing book's information
    - Remove a book from the library
- **Patron Management:**
    - Retrieve a list of all patrons
    - Retrieve details of a specific patron by ID
    - Add a new patron to the system
    - Update an existing patron's information
    - Remove a patron from the system
- **Borrowing Record Management:**
    - Allow a patron to borrow a book
    - Record the return of a borrowed book by a patron


## Technologies Used
- **Spring Boot**: Framework for building standalone, production-grade Spring applications.
- **Spring Data JPA**: Simplifies the development of data access layer by providing repository support.
- **MySQL Database**: Popular open-source relational database management system used for data storage.
- **Spring Security**: Provides authentication, authorization, and other security features for Spring applications.
- **JSON Web Token (JWT)**: A compact, URL-safe means of representing claims to be transferred between two parties.
- **Git**: Version control system used for tracking changes in the project codebase and collaboration among team members.
- **Maven**: Build automation tool for managing project dependencies and build lifecycle.


## Setup Instructions

Follow these steps to set up and run the Library Management System project on your local machine:


```shell
git clone https://github.com/Aamaka/libraryManagementSystem
````

## Development Environment Setup
To set up the development environment for this application, follow these steps:

1. Install Java Development Kit (JDK) version 17.

2. Install an Integrated Development Environment (IDE) such as IntelliJ IDEA or Eclipse.

3. Ensure that you have Apache Maven installed as the build tool.

## Running the Application Locally
To run the application locally, follow these steps:

1. Open the project in your chosen IDE.

2. Build the project using Maven to download the required dependencies.

````shell
   1.  cd library-management-system
   
   2.  mvn clean install
````
3. Configure the database connection details in the application's configuration file (application.properties).

````properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/libraryManagementSystem?createDatabaseIfNotExist=true
spring.datasource.username=your_username
spring.datasource.password=your_password
application.security.jwt.secret-key=your_secret_key
application.security.jwt.expiration=999999
application.security.jwt.refresh-token.expiration=999999
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
````
4. Start the application by running the main class (LibraryManagementSystemApplication.java) from your IDE.



## API Endpoints

You can interact with the Library Management System using the following API endpoints:

- **User Management Endpoints:**
  - `POST /api/users`: Add a new user to the library
  - `PUT /api/users/`: Update an existing user's information
  - `GET /api/users`: Retrieve a list of all user
  - `GET /api/users/{id}`: Retrieve details of a specific user by ID
  - `GET /api/users/{email}`: Retrieve details of a specific user by Email
  - `DELETE /api/users/{id}`: Remove a user from the system


- **Book Management Endpoints:**
   - `POST /api/books`: Add a new book to the library
   - `PUT /api/books/{id}`: Update an existing book's information
   - `GET /api/books`: Retrieve a list of all books
   - `GET /api/books/{id}`: Retrieve details of a specific book by ID
   - `DELETE /api/books/{id}`: Remove a book from the library

- **Patron Management Endpoints:**
   - `POST /api/patrons`: Add a new patron to the system
   - `PUT /api/patrons/{id}`: Update an existing patron's information
   - `GET /api/patrons`: Retrieve a list of all patrons
   - `GET /api/patrons/{id}`: Retrieve details of a specific patron by ID
   - `DELETE /api/patrons/{id}`: Remove a patron from the system

- **Borrowing Endpoints:**
    - `POST /api/borrow/{bookId}/patron/{patronId}`: Allow a patron to borrow a book
    - `PUT /api/return/{bookId}/patron/{patronId}`: Record the return of a borrowed book by a patron
