<div align="center">

# 🚌 Bus Journey Automation Framework

### Selenium 4 · Java 21 · Cucumber BDD · TestNG · Maven · Extent Reports

**A production-style UI test automation framework validating the end-to-end bus search workflow on AbhiBus**

![Java](https://img.shields.io/badge/Java-21-orange)
![Selenium](https://img.shields.io/badge/Selenium-4.x-green)
![Cucumber](https://img.shields.io/badge/Cucumber-7.x-brightgreen)
![TestNG](https://img.shields.io/badge/TestNG-7.x-red)
![Maven](https://img.shields.io/badge/Build-Maven-blue)
![Status](https://img.shields.io/badge/Status-Active-success)

</div>

---

## 📌 Project Overview

This framework automates the **bus search flow** on [AbhiBus](https://www.abhibus.com) using **Selenium WebDriver 4**, structured with **Cucumber BDD** for readable, business-facing test scenarios and **TestNG** as the execution/runner engine.

The automated flow covers:

- Launching the AbhiBus application
- Entering the source city
- Entering the destination city
- Selecting today's travel date
- Verifying search results are displayed
- Validating the resulting URL contains the correct source and destination
- Validating that a minimum number of buses are returned in the results

The project is built to reflect how a real QA automation team would structure a framework — with clean separation of concerns, reusable utilities, structured logging, and HTML reporting — rather than a single monolithic test script.

---

##  Why This Project?

This project is built to demonstrate the **framework-level thinking**.

- **Maintainability** — Page Object Model keeps locators and page logic isolated from test/step logic, so UI changes require minimal edits.
- **Readability** — Cucumber Gherkin scenarios describe *what* is being tested in plain English, understandable by non-technical stakeholders.
- **Traceability** — Log4j2 logging + Extent Reports give a clear, shareable record of what happened during a run, including failures.
- **Reusability** — Common actions (waits, config reading, screenshots) live in a `utils` layer instead of being duplicated across step definitions.
- **Thread safety** — A `ThreadLocal`-based Driver Factory avoids driver-state collisions, laying the groundwork for future parallel execution.

In short: this repo is meant to show *how* I structure automation, not just that I can automate one form.

---

## 🚀 Technology Stack

| Category | Technology |
|---|---|
| Language | Java 21 |
| UI Automation | Selenium WebDriver 4.x |
| BDD Framework | Cucumber 7.x |
| Test Runner | TestNG 7.x |
| Build Tool | Maven |
| Driver Management | WebDriverManager |
| Logging | Log4j2 |
| Reporting | Extent Reports (Spark Reporter) |
| Version Control | Git & GitHub |

---

## 🏗 Framework Architecture

```
Feature File (Gherkin)
        │
        ▼
Step Definitions
        │
        ▼
Page Objects (POM)
        │
        ▼
Utilities (Wait / Config / Screenshot / Log)
        │
        ▼
Driver Factory (ThreadLocal)
        │
        ▼
Browser
```

Each layer has a single responsibility: feature files describe behavior, step definitions translate Gherkin to code, page objects encapsulate UI interaction, utilities provide shared helpers, and the driver factory manages the WebDriver lifecycle.

---

## 📁 Project Structure

```text
BusJourneyAutomation
│
├── src
│   └── test
│       ├── java
│       │   ├── drivers          # ThreadLocal DriverFactory & browser lifecycle
│       │   ├── hooks            # Cucumber @Before / @After hooks
│       │   ├── pages            # Page Object classes (locators + actions)
│       │   ├── reports          # Extent Report manager & listeners
│       │   ├── runners          # TestNG + Cucumber runner class
│       │   ├── stepdefinitions  # Gherkin step implementations
│       │   └── utils            # ConfigReader, WaitUtils, Log, ScreenshotUtil
│       │
│       └── resources
│           ├── config           # config.properties
│           ├── features         # .feature files (Gherkin scenarios)
│           └── log4j2.xml       # Logging configuration
│
├── pom.xml
├── README.md
└── .gitignore
```

### Package Responsibilities

| Package | Responsibility |
|---|---|
| `drivers` | ThreadLocal DriverFactory, browser initialization, driver teardown |
| `pages` | Page Object classes — locators and business/action methods |
| `stepdefinitions` | Maps Gherkin steps to page methods, holds assertions and logging |
| `hooks` | Cucumber lifecycle — browser setup and teardown per scenario |
| `reports` | Extent Report manager and report-generation hooks |
| `utils` | `ConfigReader`, `WaitUtils`, `Log`, `ScreenshotUtil` |

---

## 🎯 Design Pattern Used

- **Page Object Model (POM)** — separates UI locators/actions from test logic
- **Factory Pattern** — `DriverFactory` centralizes WebDriver creation
- **ThreadLocal Pattern** — isolates driver instances per thread for safe future scaling
- **Singleton-style Extent Manager** — ensures a single shared report instance per run
- **Utility Class Pattern** — static helper classes for waits, config, and logging

---

## ✅ Features Implemented

- Selenium 4 automation framework built from scratch
- Java + Maven project setup
- Cucumber BDD scenario authoring
- TestNG as the test runner/execution engine
- Thread-safe `DriverFactory` using `ThreadLocal<WebDriver>`
- `ConfigReader` utility for externalized configuration (`config.properties`)
- Explicit wait utilities (`WaitUtils`) to avoid flaky waits
- Log4j2-based structured logging
- Extent Spark HTML report generation
- Automatic screenshot capture on scenario failure
- URL validation after search execution
- Bus-count validation against a minimum threshold
- Clean, layered project structure following POM

---

## 🧪 Test Scenario

```gherkin
Feature: Bus Search on AbhiBus

  Scenario: Search buses from Pune to Bangalore
    Given user launches AbhiBus application
    When user enters source city as "Pune"
    And user enters destination city as "Bangalore"
    And user selects today's date
    Then search results should be displayed
    And URL should contain source and destination
    And minimum buses should be displayed
```

---

## 🔄 Execution Flow

```
Feature File
     ↓
TestNG + Cucumber Runner
     ↓
Hooks (browser setup)
     ↓
Step Definitions
     ↓
Page Objects
     ↓
Utilities
     ↓
Browser Execution
     ↓
Hooks (teardown + report/log flush)
```

---

## 📊 Reports

Every run generates an **Extent Spark HTML report** along with the standard **Cucumber HTML report**:

```
test-output/
    ExtentReport.html
```

The report includes:

- Scenario-level pass/fail status
- Step-by-step execution details
- Execution time and summary
- Embedded screenshot on failure

---

## 📝 Logging

**Log4j2** captures structured execution logs for every run, useful for debugging failures without re-running the suite.

Example log output:

```text
Application launched successfully.
Source City : Pune
Destination City : Bangalore
Today's date selected.
Search results displayed successfully.
Current URL : https://www.abhibus.com/bus_search/Pune/51/Bengaluru/7/01-07-2026/O
Bus Count : 50
```

---

## 📸 Screenshots on Failure

When a scenario fails:

1. `ScreenshotUtil` automatically captures the browser state
2. The screenshot is saved under `screenshots/`
3. It is embedded directly into the Extent Report for quick failure analysis

---

## 🖥 Console Output

Example console output from a successful `mvn clean test` run:

```text

Scenario: Search buses from Pune to Bangalore     # src/test/resources/features/busBooking.feature:3
Jul 01, 2026 5:17:23 PM org.openqa.selenium.devtools.CdpVersionFinder findNearestMatch
WARNING: Unable to find CDP implementation matching 149
Jul 01, 2026 5:17:23 PM org.openqa.selenium.chromium.ChromiumDriver lambda$new$5
WARNING: Unable to find version of CDP to use for 149.0.7827.197. You may need to include a dependency on a specific version of the CDP using something si
milar to `org.seleniumhq.selenium:selenium-devtools-v86:4.21.0` where the version ("v86") matches the version of the chromium-based browser you're using and the version number of the artifact is the same as Selenium's.
17:18:19 INFO  Log - Application launched successfully.
  Given user launches AbhiBus application         # stepdefinitions.BusBookingSteps.launchApplication()
17:18:25 INFO  Log - Source City : Pune
  When user enters source city as "Pune"          # stepdefinitions.BusBookingSteps.enterSourceCity(java.lang.String)
17:18:27 INFO  Log - Destination City : Bangalore
  And user enters destination city as "Bangalore" # stepdefinitions.BusBookingSteps.enterDestinationCity(java.lang.String)
17:18:37 INFO  Log - Today's date selected.
  And user selects today's date                   # stepdefinitions.BusBookingSteps.selectTodaysDate()
17:18:38 INFO  Log - Search results displayed successfully.
  Then search results should be displayed         # stepdefinitions.BusBookingSteps.verifySearchResults()
17:18:38 INFO  Log - Current URL : https://www.abhibus.com/bus_search/Pune/51/Bengaluru/7/01-07-2026/O
  And URL should contain source and destination   # stepdefinitions.BusBookingSteps.verifyURL()
17:18:39 INFO  Log - Bus Count : 38
  And minimum buses should be displayed           # stepdefinitions.BusBookingSteps.verifyBusCount()
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 93.60 s -- in runners.TestRunner
[INFO] 
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  01:48 min
[INFO] Finished at: 2026-07-01T17:18:43+05:30
[INFO] ------------------------------------------------------------------------

```

---

## ▶️ How to Execute

**1. Clone the repository**
```bash
git clone <repository-url>
```

**2. Navigate into the project**
```bash
cd BusJourneyAutomation
```

**3. Run the tests**
```bash
mvn clean test
```

**4. View the report**
Open `test-output/ExtentReport.html` in a browser after execution.

---

## 📦 Maven Commands

| Command | Purpose |
|---|---|
| `mvn clean` | Cleans previously generated build/output files |
| `mvn test` | Executes the test suite |
| `mvn clean test` | Cleans and runs the suite in one step |

---

## 📈 Future Enhancements

The following are planned next steps and are **not yet implemented** in the current codebase:

- Cross-browser execution (Chrome, Firefox, Edge)
- Parallel test execution
- CI/CD integration via GitHub Actions
- Jenkins pipeline integration
- Docker containerization for consistent execution environments
- Data-driven testing using external data sources (Excel/JSON)
- Runtime browser selection via configuration
- Allure reporting as an alternative to Extent Reports

---

## 👨‍💻 Author

**Siddharth Sable**

Automation Test Engineer

- GitHub: https://github.com/Siddharth-S25

---

## 📄 License

This project is intended for learning and portfolio purposes. Feel free to fork, explore, and adapt it for your own practice.