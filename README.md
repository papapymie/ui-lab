# Automated UI Testing with Selenium and Cypress (ui-lab)

คลังข้อมูลนี้จัดทำขึ้นสำหรับปฏิบัติการทดสอบส่วนต่อประสานกับผู้ใช้แบบอัตโนมัติ (Automated UI Testing) รายวิชา **ITDS362 Software Quality Assurance and Testing**

## เครื่องมือที่เลือกทำ (Chosen Tool Parts)
ในปฏิบัติการนี้ได้เลือกเครื่องมือ 2 ส่วนตามข้อกำหนด ได้แก่:
- **Part 2: Selenium WebDriver in Java** (`selenium-java/`)
- **Part 4: Cypress** (`cypress/`)

---

## รายละเอียดแต่ละโฟลเดอร์และคำสั่งรันชุดทดสอบ (Execution Commands)

### 1. `selenium-java/`
- **คำอธิบายโฟลเดอร์**: ประกอบด้วยชุดการทดสอบด้วย Selenium WebDriver 4.52.0 บนภาษา Java 21, JUnit Jupiter 5 (6.1.3) และบริหารจัดการด้วย Maven ครอบคลุมการทดสอบ Selenium Web Form และการทดสอบสถานการณ์ QuickPizza (QP-01) โดยออกแบบตามรูปแบบโครงสร้าง Page Object Model (POM) ผ่านคลาส `HomePage.java` และ `AdminPage.java`
- **คำสั่งรันการทดสอบ**:
  ```bash
  mvn test -Dheadless=true
  ```
  *(หมายเหตุ: ต้องมีการตั้งค่าตัวแปรสภาพแวดล้อม `CHROMEDRIVER` ชี้ไปยังที่อยู่ไฟล์ ChromeDriver binary)*

### 2. `cypress/`
- **คำอธิบายโฟลเดอร์**: ประกอบด้วยชุดการทดสอบ End-to-End ด้วย Cypress 16.2.1 บนภาษา JavaScript สำหรับทดสอบ Selenium Web Form (`web-form.cy.js`) และการทดสอบระบบ QuickPizza ตั้งแต่ขั้นตอนของลูกค้าจนถึงผู้ดูแลระบบ (`pizza-scenario.cy.js`) รองรับการดักจับตรวจสอบ Network Requests (`cy.intercept`), คำสั่งกำหนดเอง (`cy.adminLogin`), กลไกการรอ Element อัตโนมัติ ตลอดจนการบันทึกภาพถ่ายหน้าจอและวิดีโอการทดสอบ
- **คำสั่งรันการทดสอบ**:
  ```bash
  npx cypress run
  ```
  *(หรือใช้ `npx cypress run --browser chrome`)*
