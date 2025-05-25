# PriceComparatorMarket_Project
This application is a "Price Comparator - Market" system designed to track product prices and discounts across different stores.The system assists customers in determining the best value products by tracking historical prices, highlighting the best current discounts, and displaying value-per-unit comparisons. 

STRUCTURE:
The project follows a standard Spring Boot layered architecture:
->Models: These are JPA entity classes that map to database tables.
->DTOs (Data Transfer Objects): hese are simple Java classes that organize the data returned by the API.
->Controllers: These classes define REST API endpoints and process HTTP requests. 
->Repositories: These interfaces make advantage of Spring Data JPA to perform automatic CRUD activities and custom queries for each entity. 
->Services: This layer contains the core business logic of the application. 

REQUIREMENTS:
->Java 17+
->Spring Boot
->Spring Data JPA
->H2 In-Memory Database
->Lombok
->Swagger
->Maven

BUILD AND START THE APP:
mvn clean install
mvn spring-boot:run
Access the app: http://localhost:8080/swagger-ui/index.html

IMPLEMENTED FEATURES:
CSV Import: Imports product, price, and discount data from CSV files.
Daily Shopping Basket Monitoring: Analyze which store offers the lowest total cost for a group of items.
Best Discounts: Lists products with the highest current percentage discounts across all stores.
New Discounts: Lists discounts newly added in the last 24 hours based on fromDate.
Dynamic Price History: Provides historical price data for products, filtered by store, category, or brand.
Product Recommendations: Help users find better deals even when pack sizes differ.




