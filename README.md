# Playwright + TestNG + Cucumber Automation Framework

## Overview
This project is a modular, scalable, and maintainable test automation framework that supports Web and API testing using Playwright, TestNG and Cucumber tools.

## Project Structure
```
project-root/
├── src/
│ ├── main/java/com/
│ │ ├── browser/ # Browser setup for playwirght instances and  parallel execution
│ │ ├── config/ # Properties reader with owner library 
│ │ ├── data/ # Data provider class(es) faker, etc.
│ │ ├── pages/ # Related web pages
│ │ └── util/ # Common playwright methods and base class for inherited pages
│ └── test/java/
│ ├── runner/ # Cucumber runner class with TestNG ability
│ ├── step_definitions/ # Cucumber step files and Hooks class
│ └── resources/ # Cucumber feature files
├── pom.xml # Project dependencies
└── README.md
```
## Tech Stack

1. **Java JDK 17**: The programming language and runtime environment used to develop and execute the test automation framework.
2. **Playwright**: A powerful end-to-end testing library for automating browser interactions across Chromium, Firefox, and WebKit. Enables fast, reliable UI testing.
3. **Cucumber (BDD Framework)**: Facilitates Behavior-Driven Development by allowing test scenarios to be written in plain English using Gherkin syntax, bridging communication between technical and non-technical stakeholders.
4. **TestNG (Test Framework)**: A flexible testing framework used to organize, group, and run tests. Supports parallel execution, configuration methods, test suites, and more.
5. **Maven (Build Tool)**: Manages project dependencies, builds, and plugins. Ensures smooth integration and execution of the framework.
6. **Allure (Reporting)**: A flexible and lightweight test report framework that generates attractive, interactive reports with step-level details and attachments for better test visibility and debugging.

## Setup
-  Clone the repository
-  Install dependencies: `mvn clean install`

## Running Tests
Run all tests  
`mvn clean test`

Run specific TestGroup  
`mvn clean test -Dcucumber.filter.tags=${TestGroup}`

## Reporting
- Cucumber reports are automatically generated after test execution
- Allure reports are automatically generated after test execution
- To view the report: `allure serve target/allure-results`

