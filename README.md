# auth-playwright-java

UI automation project for authentication flows on  using Java, Playwright, JUnit 5, and Maven.

## Overview

This repository currently covers three main user flows:

- Register a new account with generated test data
- Log in with an existing account
- Log out from an authenticated session

The project follows a simple Page Object Model structure:

- `BaseTest` manages Playwright, browser, context, and page setup
- `BasePage` provides shared `click()` and `fill()` helpers
- `LoginPage`, `RegisterPage`, and `HomePage` contain page-specific actions and assertions
- JUnit test classes call page objects directly to execute each flow

## Tech Stack

- Java 21
- Maven
- Playwright for Java `1.53.0`
- JUnit 5 `5.10.2`

## Project Structure

```text
src
+- test
   +- java
   �  +- com/example/auth
   �     +- base
   �     �  +- BaseTest.java
   �     +- pages
   �     �  +- BasePage.java
   �     �  +- HomePage.java
   �     �  +- LoginPage.java
   �     �  +- RegisterPage.java
   �     +- tests
   �     �  +- LoginTest.java
   �     �  +- LogoutTest.java
   �     �  +- RegisterTest.java
   �     +- utils
   �        +- TestDataFactory.java
   +- resources
      +- config.properties
```

## Test Flows

### Register

`RegisterTest` uses `TestDataFactory` to generate:

- random username
- random password
- random phone number

Then it submits the registration form and waits for a success element.

### Login

`LoginTest` logs in with a fixed username and password, then verifies the deposit button is visible.

### Logout

`LogoutTest` logs in first, opens the profile menu, clicks logout, confirms the logout dialog, and verifies the sign-in button is visible again.

## Prerequisites

- Java 21 installed
- Maven installed and available in `PATH`
- Internet access to open the target site
- Playwright browser dependencies installed if your environment requires them

## Run Tests

Run all tests:

```bash
mvn test
```

Run one test class:

```bash
mvn -Dtest=LoginTest test
mvn -Dtest=RegisterTest test
mvn -Dtest=LogoutTest test
```

## Configuration

Current config file:

[`src/test/resources/config.properties`](/D:/auth-playwright-java/src/test/resources/config.properties)

Available properties:

```properties
baseUrl=//
headless=false
test.username=atcaha1
test.password=A1234567
```

## Link demo
- Link: https://drive.google.com/file/d/1hLOMbKf20Wkw8at8Vag887SygjPS71Fo/view?usp=sharing


