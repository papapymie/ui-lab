# UI lab results

Tool parts that I chose: Part 2 (Selenium WebDriver in Java) and Part 4 (Cypress)

## Part 0
- Step 0.1, Chrome version: 158.0.8127.37 (Official Build) (arm64)
- Step 0.3, ChromeDriver version: ChromeDriver 158.0.8127.36 (14d6f648e8cd7ad750a4044200ce94d3321be260-refs/branch-heads/8127@{#1691})
- Step 0.4, locator of Pizza, Please!: `button[name="pizza-please"]` (or `//button[@name='pizza-please']`)
- Step 0.4, locator of Excluded tools: `select` (or `//select`)
- Step 0.4, locator of Must be vegetarian: `#default-checkbox` (or `id=default-checkbox`)
- Step 0.4, locator of Custom Pizza Name: `input#pizza-name` (or `//input[@id='pizza-name']`)
- Step 0.5, pizza name, tool and calories of the run by hand: Lab Pizza 20261121-084000, Tool: Scissors, Calories: 450

| Step | Actual result | Pass or fail |
|------|---------------|--------------|
| 1 | The heading reads "Looking to break out of your pizza routine?" | Pass |
| 2 | Clicked Advanced switch; Max Calories per Slice, Excluded tools, Must be vegetarian, and Custom Pizza Name appear | Pass |
| 3 | Max Calories set to 600, Knife excluded, Must be vegetarian checked, Custom Pizza Name set to "Lab Pizza 20261121-084000"; fields display these values | Pass |
| 4 | Recommendation card displayed with pizza name "Lab Pizza 20261121-084000", tool "Scissors" (not Knife), and 450 calories per slice (<= 600) | Pass |
| 5 | Clicked "Love it!" button without logging in; text "Please log in first." appeared | Pass |
| 6 | Navigated to /admin, signed in with username "admin" and password "admin"; admin dashboard showed "Latest pizza recommendations" | Pass |
| 7 | Found line in the recommendation list containing "Lab Pizza 20261121-084000 (tool=Scissors,...)" with the matching tool from Step 4 | Pass |

## Part 2: Selenium WebDriver in Java
- Step 2.4, the failure line for the wrong title:
  `FirstTest.webFormOpens:20 expected: <Web Form> but was: <Web form>`
- Step 2.15, QP01Test run 1, result and time: Pass, 12.86 s
- Step 2.15, QP01Test run 2, result and time: Pass, 17.66 s
- Step 2.20, full run 1, time and number of tests: 41.912 s, 5 tests (5 passed, 0 failed)
- Step 2.20, full run 2, time and number of tests: 55.351 s, 5 tests (5 passed, 0 failed)

## Part 4: Cypress
- node --version, npm --version and npx cypress version:
  node: v22.20.0, npm: 10.9.3, cypress: 16.2.1
- Step 4.3, failure message for the wrong title:
  `Timed out retrying after 10000ms: expected 'Web form' to equal 'Web Form'`
- Step 4.6, tool and calories in the answer of POST /api/pizza:
  Tool: Pizza cutter, Calories: 575 (payload limit maxCaloriesPerSlice: 600 validated via cy.intercept)
- Step 4.7, the line of the admin list that shows your pizza:
  `Lab Pizza 20261121-090910 (tool=Pizza cutter, ingredients_number=8)`
- Step 4.8, run 1, result, duration and pizza name:
  Pass, duration: 16s (total run: 19s), Pizza name: Lab Pizza 20261121-090825
- Step 4.8, run 2, result, duration and pizza name:
  Pass, duration: 18s (total run: 21s), Pizza name: Lab Pizza 20261121-090910
- Step 4.9 (optional), browser and result:
  Electron 146 (headless, bundled): Both specs passed (2 passing, 0 failing) in 21s. Chrome 158 (headless): Both specs passed when run individually (web-form.cy.js in 3s, pizza-scenario.cy.js in 14s).

## Part B
1. QuickPizza assigns the identical attribute `id="floating_filled"` to all three numerical input boxes ("Max Calories per Slice", "Min Number of Toppings", and "Max Number of Toppings"), violating HTML document standards which require unique element IDs. Because an ID selector like `#floating_filled` is ambiguous and matches multiple elements, tests cannot target the calories box by ID alone and must locate it through its adjacent label text using `//label[normalize-space()='Max Calories per Slice']/preceding-sibling::input` or `.siblings('input')`. A proper change to the page would be assigning unique IDs to each input, such as `id="max-calories-per-slice"`. This change helps testers by providing an unambiguous, resilient CSS selector (`#max-calories-per-slice`), making test locators faster, less brittle, and completely independent of label phrasing or layout changes.

2. This check is an **end-to-end data consistency oracle** (also known as a cross-feature consistency check). It compares the state produced by the user-facing customer workflow with the persisted state retrieved by the administrative interface. One fault in QuickPizza that this oracle would catch is a database persistence bug or backend race condition where the recommendation service generates an allowed tool (such as "Scissors" or "Pizza cutter") in memory and sends it to the frontend, but the database saving routine fails to record the tool properly, overwrites it with an excluded default tool (such as "Knife"), or associates the pizza with another user's session data.

3. To check that the pizza is strictly vegetarian, the test could verify both the network response and the visible ingredients list. In the Cypress test with `cy.intercept('POST', '/api/pizza')`, the test can assert `expect(response.body.vegetarian).to.be.true`, and iterate over `response.body.pizza.ingredients` to verify that each ingredient contains no meat. On the web page, the test would need an external oracle list of known non-vegetarian ingredients (e.g., pepperoni, sausage, ham, bacon, prosciutto) to assert that none of the bullet items under the "Ingredients:" heading match meat products. Alternatively, the page could be improved by providing an explicit indicator badge (such as `<span class="vegetarian-badge">Vegetarian</span>`) or a `data-vegetarian="true"` attribute on the recommendation card.

4. Selenium WebDriver in Java relied on explicit conditional waits via `WebDriverWait` and `ExpectedConditions` (e.g. `visibilityOfElementLocated`), which had to be specified manually for asynchronous events like Svelte component rendering and dropdown population. Cypress, on the other hand, handled waiting natively through automatic retries on queries (`cy.get()`, `cy.contains()`) up to a 10-second timeout, making test creation much faster and eliminating boilerplate wait statements. Writing QP-01 in Cypress was faster and more developer-friendly thanks to method chaining, built-in network interception (`cy.intercept`), and immediate feedback in the interactive test runner. Cypress also generated far more comprehensive reporting evidence, producing complete video recordings (`.mp4`) showing the test execution alongside the command log, whereas Selenium produced plain-text Surefire summaries and required custom screenshot utility methods.

5. I choose **Cypress** for the automated UI testing component of Project 2 for two key reasons demonstrated in this lab. First, Cypress provides powerful built-in network interception (`cy.intercept`), which allows tests to spy on backend API requests, validate JSON payloads, and assert response statuses directly without needing separate mock servers or proxy configurations. Second, Cypress features automatic synchronization and retry mechanisms that prevent flaky tests caused by asynchronous DOM changes, while automatically recording video evidence and screenshots of full test runs, directly fulfilling Project 2's submission and demonstration requirements.

6. If QuickPizza changes the locator for the calories box, in `QP01Test.java` the tester would have to manually find and update every line where the locator is used (such as the explicit wait on lines 36-38 and the input interaction on line 41), risking missing an occurrence or breaking test execution. In contrast, in `QP01PageObjectTest.java`, the test method itself never directly references the locator; it only invokes clean semantic actions like `home.showAdvanced()`, `home.setMaxCalories(600)`, and `home.maxCalories()`. The locator is encapsulated in a single constant inside `HomePage.java` (`private static final By MAX_CALORIES = ...`), so modifying that single variable in the Page Object automatically repairs all associated tests. This design pattern reduces maintenance overhead, prevents duplicate code, and isolates UI layout changes from business logic test scenarios.

7. QuickPizza runs across multiple backend server nodes, and session cookies are used to bind a browser session to the specific server instance where a customer's custom pizza recommendation is stored. Cypress strictly enforces test isolation by automatically wiping all cookies, local storage, and session data before each `it()` test block. If steps 6 and 7 were placed in a separate test block, Cypress would clear the session cookie before step 6, causing the browser to visit `/admin` with a fresh session that could be routed to a different backend server node. On that different server, the pizza created in step 4 would not exist in the administrative list, causing step 7 (`cy.contains('li', pizzaName)`) to fail with a timeout error. Keeping all steps in a single test block ensures cookie continuity across the entire end-to-end user journey.
