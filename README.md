Load Booking System

A backend REST API application built using Spring Boot and PostgreSQL. This system allows shippers to post loads and transporters to book them. It includes features such as load status management, booking workflows, data validation, and logging.


Features

- Shippers can create and manage loads with complete facility details.
- Transporters can book loads with proposed rates and comments.
- Booking and Load status tracking using enums.
- Uses DTOs for clean API interaction.
- Integrated validation using Jakarta annotations.
- Exception handling through global handler.
- Logging via SLF4J for service-level insights.


Tech Stack

- Java 17
- Spring Boot
- PostgreSQL
- Maven
- Jakarta Persistence
- Postman (for API testing)


Project Structure

src/
 └── main/
     └── java/
         └── com.example.loadbooking/
             ├── controller/
             ├── dto/
             ├── exception/
             ├── model/
             ├── repo/
             ├── service/
             ├── utility/

---

Getting Started

Prerequisites:
- Java 17 or higher
- PostgreSQL
- Maven
- Postman

Steps to Run:

1. Clone the Repository
   git clone https://github.com/Stprogram2021/load-booking-system.git
   cd load-booking-system

2. Configure PostgreSQL Database
   Create a database named `load_booking_db`.

3. Update application.properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/load_booking_db  
   spring.datasource.username=postgres  
   spring.datasource.password=Danger  
   spring.jpa.hibernate.ddl-auto=update  

4. Build and Run the Project
   mvn clean install  
   mvn spring-boot:run  

5. Test API Endpoints using Postman

API Endpoints Overview

POST /loads  
GET /loads  
GET /loads/{id}  
PUT /loads/{id}  
DELETE /loads/{id}  

POST /bookings  
GET /bookings?transporterId={id}  
GET /bookings/{id}  
PUT /bookings/{id}  
DELETE /bookings/{id}  

Validation and Error Handling

- DTOs use annotations like @NotNull, @Size, etc.
- @ControllerAdvice handles exceptions globally.

Logging

- SLF4J used for info, warning, and error logs at service layer.

Contact

Developed by Stprogram2021  
Repository: https://github.com/Stprogram2021/load-booking-system  

License

This project is licensed under the MIT License.
