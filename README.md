# Automated UI Testing with Selenium and Cypress (ui-lab)

This repository contains the automated UI testing laboratory for **ITDS362 Software Quality Assurance and Testing**.

## Chosen Tool Parts
The two tool parts chosen for this lab are:
- **Part 2: Selenium WebDriver in Java** (`selenium-java/`)
- **Part 4: Cypress** (`cypress/`)

---

## Directory Overview and Execution Commands

### 1. `selenium-java/`
- **Description**: Contains Selenium WebDriver 4.52.0 tests using Java 21, JUnit Jupiter 5 (6.1.3), and Maven. It implements automated UI tests for the Selenium Web Form and the QuickPizza scenario (QP-01) following the Page Object Model (POM) pattern (`HomePage.java` and `AdminPage.java`).
- **Run Command**:
  ```bash
  mvn test -Dheadless=true
  ```
  *(Requires `CHROMEDRIVER` environment variable set to the ChromeDriver binary path)*

### 2. `cypress/`
- **Description**: Contains Cypress 16.2.1 end-to-end tests written in JavaScript for testing the Selenium Web Form (`web-form.cy.js`) and the QuickPizza end-to-end user scenario (`pizza-scenario.cy.js`). It includes network interception (`cy.intercept`), custom commands (`cy.adminLogin`), automatic retrying waits, and automatic video/screenshot capture.
- **Run Command**:
  ```bash
  npx cypress run --browser chrome
  ```
