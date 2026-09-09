# NezamInteractions

Automated UI interaction tests (Selenium + TestNG) for learning and demo purposes.

## Summary
This Maven-based Java project contains UI tests that exercise common web interactions (alerts, dropdowns, drag-and-drop, file upload, iframes, tables, etc.). It is intended as a personal test automation collection and a starting point for a QA automation portfolio.

## Prerequisites
- JDK 26
- Maven (3.x)
- A modern browser (Chrome/Edge/Firefox) and the corresponding WebDriver on PATH, or use a driver manager (e.g. WebDriverManager)

## Quick start
1. Open the project in IntelliJ IDEA.
2. Build and run all tests:

   mvn test

3. Run a single TestNG test class (example):

   mvn -Dtest=Alerts test

If your tests require a specific driver, ensure the driver executable (chromedriver/geckodriver) is available on PATH or configured by your test utilities.

## Project structure
- pom.xml — Maven configuration and dependencies
- src/test/java — Test classes (Alerts, DragAndDrop, Dropdown, DynamicElements, FileUpload, Iframe(s), Tables, AutomationUtils)
- src/test/resources — test fixtures and sample HTML pages

## Suggested visual improvements (to make the repository more presentable)
2. Include a short animated GIF or screenshot of tests running or a sample test report in `/docs` and reference it in README.
