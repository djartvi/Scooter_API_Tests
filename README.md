# Scooter_API_Tests

Java 11 tests for web app http://qa-scooter.praktikum-services.ru.

Contains test for registration, login of courier, creating and working with orders.

All test couriers and orders are removed after each run.

## API Documentation
https://qa-scooter.praktikum-services.ru/docs.

## Maven Dependencies
JUnit 4, Rest Assured, Allure Rest Assured, Allure JUnit, GSON, Lombok, JavaFaker.

## Plugins
AntRun Plugin, Surefire, Allure, AspectJ weaver.

## Generate Allure report
Run `mvn clean test allure:serve` from project directory.
