# Products Rest API
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)  ![H2 Database](https://img.shields.io/badge/H2%20Database-018bff?style=for-the-badge&logoColor=white) ![OpenApi](https://img.shields.io/badge/Docs-OpenAPI-success?style=for-the-badge&logo=swagger)

I developed a Rest API to manage books and their publishers and authors, built by using **Spring Boot and Java**, providing CRUD (Create, Read, Update, Delete) operations. This API allows to store book information, such as title, edition etc. it also supports the storage of publisher information, including name, country, city etc. Additionally, it supports the storage of author information like fullname, coutry, city etc.

I used some libraries for this Rest API such **Spring Web, Spring Data JPA, Validation, H2 Database and SpringDoc OpenAPI Starter WebMVC UI 2.3.0 (for the API documentation).**

## Database Config
For test this API, an external Database is not necessary because an embedded Database (H2 Database) was used with the following configuration properties:

- Name: books_db
- Username: sa
- Password:

## Development Tools
This Rest API was built with:

- Spring Boot version: 3.3.0
- Java version: 17

## System Class Diagram

![BooksClassDiagram](https://github.com/MarcosTulioSDLV/Health-Tech/assets/41268178/12bd5a46-43e5-4837-8177-3e4445f1a772)

