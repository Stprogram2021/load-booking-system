Load Booking System

This is a backend system built using Spring Boot and PostgreSQL to manage Load and Booking operations for a transportation platform. It allows shippers to post loads and transporters to book those loads.

Tech Stack

- Java 17  
- Spring Boot  
- Spring Data JPA  
- PostgreSQL  
- Maven  
- Postman (for API testing)

Features

- Create, read, update, and delete load postings  
- Booking loads by transporters  
- Load status updates (Posted, Booked, Cancelled)  
- Booking status management (Pending, Accepted, Rejected)  
- Global exception handling  
- Logging for service operations  
- DTO-based request/response structure  
- Validation of user input using annotations  

Modules

1. Load Module  
   - POST /loads  
   - GET /loads/{id}  
   - PUT /loads/{id}  
   - DELETE /loads/{id}  
   - GET /loads?shipperId=&truckType=

2. Booking Module  
   - POST /bookings  
   - GET /bookings/{id}  
   - PUT /bookings/{id}  
   - DELETE /bookings/{id}  
   - GET /bookings?transporterId=

Setup Instructions

1. Clone the repository  
   `git clone https://github.com/Stprogram2021/load-booking-system`

2. Open in your IDE (e.g., Eclipse or IntelliJ)

3. Set up PostgreSQL database:  
   - DB Name: `load_booking_db`  
   - Username: `postgres`  
   - Password: `Danger`

4. Update `application.properties` if credentials are different

5. Build and run the project using Maven:  
   `mvn spring-boot:run`

6. Use Postman to test API endpoints

API Usage Example

Create a Load:
POST /loads  
```json
{
  "shipperId": "S001",
  "facility": {
    "loadingPoint": "Delhi",
    "unloadingPoint": "Mumbai",
    "loadingDate": "2025-04-06T10:00:00",
    "unloadingDate": "2025-04-07T18:00:00"
  },
  "productType": "Steel",
  "truckType": "Open",
  "noOfTrucks": 2,
  "weight": 2000,
  "comment": "Urgent delivery",
  "datePosted": "2025-04-05T12:00:00"
}
