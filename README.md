Sure! Here’s a detailed, well-structured, and nicely formatted `README.md` for your Java API automation framework using RestAssured, TestNG, Cucumber, and Extent Reports:

---

# API Automation Framework

## Overview

This project implements a comprehensive API automation framework designed to validate all critical functionalities of a RESTful API. The framework is built with **Java**, **RestAssured**, **TestNG**, and **Cucumber**, and features advanced reporting using **Extent Reports**. It supports scalable, maintainable, and reusable test automation for CRUD operations with detailed test reports and CI/CD integration.

---

## Table of Contents

* [Features](#features)
* [Project Structure](#project-structure)
* [Tools & Technologies](#tools--technologies)
* [Setup & Configuration](#setup--configuration)
* [How to Run Tests](#how-to-run-tests)
* [Reporting](#reporting)
* [Testing Strategy](#testing-strategy)
* [Challenges & Solutions](#challenges--solutions)
* [CI/CD Integration](#cicd-integration)
* [Future Improvements](#future-improvements)
* [Contributing](#contributing)
* [License](#license)

---

## Features

* Complete CRUD API test coverage (Create, Read, Update, Delete)
* Positive and negative test scenarios
* Request chaining to use output from one API call as input to another
* Environment-specific configuration via `config.properties`
* Integration with Cucumber for behavior-driven development (BDD) style tests
* TestNG as the test execution engine for flexible test management
* Advanced, detailed HTML reports powered by Extent Reports
* Listener-based logging for real-time test feedback
* CI/CD pipeline setup via Jenkins (or GitHub Actions, easily extendable)

---

## Project Structure

```
/project-root
├── src
│   ├── main
│   │   └── java/com/company/api/           # Main application code
│   │       ├── config/                      # Configuration manager
│   │       ├── models/                      # POJOs for API payloads
│   │       └── listeners/                   # TestNG listeners for reporting
│   └── test
│       ├── java/com/company/api/            # Test implementation
│       │   ├── runners/                      # Cucumber test runner
│       │   ├── stepdefinitions/             # Step definitions for Cucumber
│       │   └── tests/                        # TestNG test classes
│       └── resources/features/               # Gherkin feature files
├── config.properties                        # API endpoint and env configuration
├── pom.xml                                 # Maven dependencies and build config
├── testng.xml                              # TestNG suite config
├── Jenkinsfile                             # CI/CD pipeline script
├── .gitignore                              # Git ignore rules
└── README.md                               # Project documentation
```

---

## Tools & Technologies

| Tool/Library   | Purpose                                          |
| -------------- | ------------------------------------------------ |
| Java           | Programming language                             |
| RestAssured    | REST API testing library                         |
| TestNG         | Test framework for execution and assertions      |
| Cucumber       | BDD framework for defining feature files         |
| Extent Reports | Advanced test reporting and logging              |
| Maven          | Dependency and build management                  |
| Jenkins        | Continuous Integration and Deployment (optional) |
| Git            | Version control                                  |

---

## Setup & Configuration

1. **Prerequisites:**

   * Java 11+ installed and configured
   * Maven installed (`mvn` command available)
   * IDE of choice (IntelliJ, Eclipse, VSCode)
   * Jenkins (optional) for CI/CD

2. **Clone the Repository:**

   ```bash
   git clone https://your-repo-url.git
   cd your-project
   ```

3. **Configure API Endpoint:**
   Update the `config.properties` file:

   ```properties
   base.url=https://api.yourdomain.com
   environment=dev
   ```

4. **Install Dependencies:**

   ```bash
   mvn clean install
   ```

---

## How to Run Tests

* **Run all tests via Maven:**

  ```bash
  mvn test
  ```

* **Run tests using TestNG suite:**

  ```bash
  mvn test -DsuiteXmlFile=testng.xml
  ```

* **Run tests via IDE:**
  Execute `TestRunner.java` as a TestNG test or run feature files via Cucumber plugin.

---

## Reporting

* **Extent Reports:**
  After test execution, detailed HTML reports will be generated in the `extent-reports` folder.
  Open `extent-reports/report.html` in your browser to view a rich, interactive report showing:

  * Passed/Failed/Skipped tests
  * Detailed logs and screenshots (if any)
  * Test execution time and environment info

* **Cucumber Reports:**
  Cucumber also produces an HTML report in `target/cucumber-html-report.html` for BDD test overview.

---

## Testing Strategy

* **Modular Design:**
  Reusable POJOs and step definitions keep the framework clean and maintainable.

* **Comprehensive Test Coverage:**
  Covers all CRUD operations with both positive and negative test cases.

* **Request Chaining:**
  Uses data returned by one API to feed into subsequent API requests ensuring realistic flows.

* **Assertions:**
  Robust validation on HTTP status codes, response payloads, headers, and error messages.

* **Configuration Management:**
  Uses a properties file to easily switch between environments (dev, QA, prod).

---

## Challenges & Solutions

| Challenge                                   | Solution                                                                    |
| ------------------------------------------- | --------------------------------------------------------------------------- |
| Handling dynamic data between API calls     | Implemented request chaining via shared variables (e.g., `userId`) in tests |
| Reporting detailed logs with screenshots    | Integrated Extent Reports with TestNG listener for rich HTML reports        |
| Managing parallel execution and flaky tests | Configured TestNG with proper dependencies and isolation in tests           |
| CI/CD integration with Jenkins pipelines    | Created Jenkinsfile with stages for build, test, and report publication     |

---

## CI/CD Integration

* The `Jenkinsfile` includes:

  * Code checkout from Git
  * Maven build and test execution
  * JUnit report publishing
  * Extent report publishing as HTML artifact

* You can configure **GitHub Actions** or other CI tools similarly to run tests on each push or PR.

---

## Future Improvements

* Add data-driven testing with external CSV or JSON files
* Integrate API contract testing with tools like Pact
* Add automated performance testing hooks
* Implement parallel test execution and distributed testing
* Extend reporting with Slack/email notifications

---

## Contributing

Contributions are welcome! Please raise issues or pull requests for any improvements or bug fixes.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---
