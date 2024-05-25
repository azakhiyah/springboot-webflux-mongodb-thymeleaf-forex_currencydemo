# Spring WebFlux and Thymeleaf Forex Currency Demo

This project demonstrates a Spring WebFlux application that integrates Thymeleaf for server-side rendering. It uses APILayer's Fixer API to fetch and display currency exchange rates with pagination support.

## Features

- Reactive programming with Spring WebFlux
- Server-side rendering with Thymeleaf
- Integration with APILayer's Fixer API
- Pagination for displaying exchange rates

## Prerequisites

- Java 11 or higher
- Maven 3.6.0 or higher
- An API key from APILayer for the Fixer API

## Getting Started

### Clone the Repository
git clone https://github.com/azakhiyah/springboot-webflux-mongodb-thymeleaf-forex_currencydemo.git
cd spring-webflux-thymeleaf-forex

# Configuration
1.API Key: Obtain an API key from APILayer's Fixer API by signing up on their website.
2.Application Properties: Add your API key to the application.properties file.
  api.key = your_key

# Build and Run the Application
mvn clean install
mvn spring-boot:run


