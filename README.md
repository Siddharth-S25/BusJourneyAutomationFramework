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

---

## 🤔 Why This Project?

This project was built to demonstrate the **framework-level thinking** expected in real QA automation roles:

- **Maintainability** — Page Object Model keeps locators and page logic isolated from test/step logic, so UI changes require minimal edits.
- **Readability** — Cucumber Gherkin scenarios describe *what* is being tested in plain English, understandable by non-technical stakeholders.
- **Traceability** — Log4j2 logging + Extent Reports give a clear, shareable record of what happened during a run, including failures.
- **Reusability** — Common actions (waits, config reading, screenshots) live in a `utils` layer instead of being duplicated across step definitions.
- **Thread safety** — A `ThreadLocal`-based Driver Factory isolates driver instances per thread, enabling safe parallel execution across browsers.

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
Browser (Chrome / Firefox / Edge)
```

Each layer has a single responsibility: feature files describe behavior, step definitions translate Gherkin to code, page objects encapsulate UI interaction, utilities provide shared helpers, and the driver factory manages the WebDriver lifecycle across threads.

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
├── testng.xml       # Cross-browser parallel execution suite
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

## 🎯 Design Patterns Used

- **Page Object Model (POM)** — separates UI locators/actions from test logic
- **Factory Pattern** — `DriverFactory` centralizes WebDriver creation
- **ThreadLocal Pattern** — isolates driver instances per thread for safe parallel execution
- **Singleton-style Extent Manager** — ensures a single shared report instance per run
- **Utility Class Pattern** — static helper classes for waits, config, and logging

---

## ✅ Features Implemented

- Selenium 4 automation framework built from scratch
- Java + Maven project setup
- Cucumber BDD scenario authoring
- TestNG as the test runner/execution engine
- Thread-safe `DriverFactory` using `ThreadLocal<WebDriver>`
- Cross-browser execution — Chrome, Firefox, and Edge supported via `DriverFactory` switch
- Parallel execution — `testng.xml` runs multiple browser threads simultaneously, proven with 3 concurrent passing runs
- Browser selection via System property (`-Dbrowser=chrome/firefox/edge`) or `config.properties` default
- `ConfigReader` utility for externalized configuration
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
testng.xml (parallel suite) / TestNG + Cucumber Runner
     ↓
Hooks (browser setup per thread)
     ↓
Step Definitions
     ↓
Page Objects
     ↓
Utilities
     ↓
Browser Execution (parallel threads)
     ↓
Hooks (teardown + report/log flush)
```

---

## 📊 Reports

Every run generates an **Extent Spark HTML report** along with the standard **Cucumber HTML report**:

```
test-output/
    ExtentReport.html
target/
    cucumber-report.html
```

The Extent report includes:

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
Current URL : https://www.abhibus.com/bus_search/Pune/51/Bengaluru/7/03-07-2026/O
Bus Count : 54
```

---

## 📸 Screenshots on Failure

When a scenario fails:

1. `ScreenshotUtil` automatically captures the browser state
2. The screenshot is saved under `screenshots/`
3. It is embedded directly into the Extent Report for quick failure analysis

---

## 🖥 Console Output

Parallel execution — 3 threads running simultaneously, all passing:

```text
[INFO]  T E S T S
[INFO] Running TestSuite

Scenario: Search buses from Pune to Bangalore   # busBooking.feature:3
Scenario: Search buses from Pune to Bangalore   # busBooking.feature:3
Scenario: Search buses from Pune to Bangalore   # busBooking.feature:3

16:46:19 INFO  Log - Application launched successfully.
16:46:19 INFO  Log - Application launched successfully.
16:46:19 INFO  Log - Application launched successfully.
...
16:47:41 INFO  Log - Bus Count : 45
16:47:41 INFO  Log - Bus Count : 45
16:47:41 INFO  Log - Bus Count : 45

[INFO] Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 247.2 s
[INFO] BUILD SUCCESS
[INFO] Total time: 04:27 min
```

---

## ▶️ How to Execute

**1. Clone the repository**
```bash
git clone https://github.com/Siddharth-S25/BusJourneyAutomationFramework.git
```

**2. Navigate into the project**
```bash
cd BusJourneyAutomation
```

**3. Run full parallel cross-browser suite**
```bash
mvn clean test
```

**4. Run a single browser**
```bash
mvn clean test -Dbrowser=chrome
mvn clean test -Dbrowser=firefox
mvn clean test -Dbrowser=edge
```

**5. View the report**

Open `test-output/ExtentReport.html` in a browser after execution.

> **Note:** Firefox and Edge require those browsers to be installed locally.
> WebDriverManager downloads the matching driver binary automatically.

---

## 📦 Maven Commands

| Command | Purpose |
|---|---|
| `mvn clean` | Cleans previously generated build/output files |
| `mvn test` | Executes the full parallel suite via `testng.xml` |
| `mvn clean test` | Cleans and runs the suite in one step |
| `mvn clean test -Dbrowser=firefox` | Runs suite on a specific browser only |

---

## 📈 Future Enhancements

The following are planned next steps and are **not yet implemented** in the current codebase:

- Jenkins pipeline integration
- Docker containerization for consistent execution environments
- Data-driven testing using external data sources (Excel/JSON)
- Allure reporting as an alternative to Extent Reports
- Additional test scenarios (negative flows, edge cases)

---

## 👨‍💻 Author

**Siddharth Sable**

Automation Test Engineer

- GitHub: [github.com/Siddharth-S25](https://github.com/Siddharth-S25)

---

## 📄 License

This project is intended for learning and portfolio purposes. Feel free to fork, explore, and adapt it for your own practice.