# UI lab results

Tool parts that I chose: Part 2 (Selenium WebDriver in Java) และ Part 4 (Cypress)

## Part 0
- Step 0.1, Chrome version: 158.0.8127.37 (Official Build) (arm64)
- Step 0.3, ChromeDriver version: ChromeDriver 158.0.8127.36 (14d6f648e8cd7ad750a4044200ce94d3321be260-refs/branch-heads/8127@{#1691})
- Step 0.4, locator of Pizza, Please!: `button[name="pizza-please"]` (หรือ `//button[@name='pizza-please']`)
- Step 0.4, locator of Excluded tools: `select` (หรือ `//select`)
- Step 0.4, locator of Must be vegetarian: `#default-checkbox` (หรือ `id=default-checkbox`)
- Step 0.4, locator of Custom Pizza Name: `input#pizza-name` (หรือ `//input[@id='pizza-name']`)
- Step 0.5, pizza name, tool and calories of the run by hand: Lab Pizza 20261121-084000, Tool: Scissors, Calories: 450

| Step | Actual result (ผลลัพธ์จริงที่พบ) | Pass or fail |
|------|----------------------------------|--------------|
| 1 | ข้อความหัวเรื่องแสดงตรงตามที่ระบุ: "Looking to break out of your pizza routine?" | Pass |
| 2 | เมื่อคลิกเปิดสวิตช์ Advanced พบว่าช่องกรอก Max Calories per Slice, Excluded tools, Must be vegetarian และ Custom Pizza Name ปรากฏขึ้นมาบนหน้าเว็บอย่างถูกต้อง | Pass |
| 3 | ปรับค่า Max Calories เป็น 600, เลือก Excluded tool เป็น Knife, ติ๊กถูกช่อง Must be vegetarian และตั้งชื่อ Custom Pizza Name เป็น "Lab Pizza 20261121-084000" โดยทุกช่องแสดงค่าที่กรอกครบถ้วน | Pass |
| 4 | แสดงการ์ดแนะนำพิซซ่า โดยมีชื่อตรงกับ "Lab Pizza 20261121-084000", อุปกรณ์เป็น "Scissors" (ไม่ใช่ Knife ที่ถูกคัดออก) และมีพลังงาน 450 แคลอรีต่อชิ้น (ไม่เกิน 600 ตามเงื่อนไข) | Pass |
| 5 | เมื่อคลิกปุ่ม "Love it!" โดยที่ยังไม่ได้เข้าสู่ระบบ หน้าเว็บแสดงข้อความแจ้งเตือน "Please log in first." | Pass |
| 6 | เปิดหน้า `/admin` แล้วกรอก Username เป็น "admin" และ Password เป็น "admin" เพื่อเข้าสู่ระบบ หน้าเว็บแสดงหัวข้อ "Latest pizza recommendations" | Pass |
| 7 | ในตารางรายการแนะนำของ Admin พบรายการพิซซ่า "Lab Pizza 20261121-084000 (tool=Scissors,...)" ซึ่งมีชื่อและอุปกรณ์ตรงกับที่ระบบแนะนำใน Step 4 | Pass |

## Part 2: Selenium WebDriver in Java
- Step 2.4, the failure line for the wrong title:
  `FirstTest.webFormOpens:20 expected: <Web Form> but was: <Web form>`
- Step 2.15, QP01Test run 1, result and time: Pass, 12.86 วินาที
- Step 2.15, QP01Test run 2, result and time: Pass, 17.66 วินาที
- Step 2.20, full run 1, time and number of tests: 41.912 วินาที, ทั้งหมด 5 tests (ผ่าน 5, ไม่ผ่าน 0)
- Step 2.20, full run 2, time and number of tests: 55.351 วินาที, ทั้งหมด 5 tests (ผ่าน 5, ไม่ผ่าน 0)

## Part 4: Cypress
- node --version, npm --version and npx cypress version:
  node: v22.20.0, npm: 10.9.3, cypress: 16.2.1
- Step 4.3, failure message for the wrong title:
  `Timed out retrying after 10000ms: expected 'Web form' to equal 'Web Form'`
- Step 4.6, tool and calories in the answer of POST /api/pizza:
  Tool: Pizza cutter, Calories: 575 (โดยส่งค่า payload ตรวจสอบ maxCaloriesPerSlice: 600 สำเร็จผ่าน cy.intercept)
- Step 4.7, the line of the admin list that shows your pizza:
  `Lab Pizza 20261121-090910 (tool=Pizza cutter, ingredients_number=8)`
- Step 4.8, run 1, result, duration and pizza name:
  Pass, เวลาทดสอบสเปก: 16 วินาที (เวลารวมทั้งชุด: 19 วินาที), ชื่อพิซซ่า: Lab Pizza 20261121-090825
- Step 4.8, run 2, result, duration and pizza name:
  Pass, เวลาทดสอบสเปก: 18 วินาที (เวลารวมทั้งชุด: 21 วินาที), ชื่อพิซซ่า: Lab Pizza 20261121-090910
- Step 4.9 (optional), browser and result:
  Electron 146 (headless, ตัวเบราว์เซอร์ที่มาพร้อมกับ Cypress): ผ่านทั้ง 2 specs (ผ่าน 2, ไม่ผ่าน 0) ในเวลา 21 วินาที ส่วน Chrome 158 (headless): รันผ่านทั้ง 2 specs แบบรายไฟล์ (web-form.cy.js ใช้เวลา 3 วินาที และ pizza-scenario.cy.js ใช้เวลา 14 วินาที)

## Part B
1. สาเหตุที่ชุดการทดสอบต้องระบุตำแหน่งของช่อง Max Calories per Slice ผ่าน Label แทนที่จะใช้ id โดยตรง เนื่องจากหน้าเว็บ QuickPizza ใช้ attribute `id="floating_filled"` ซ้ำกันในกล่องข้อความตัวเลขทั้ง 3 ช่อง (ได้แก่ Max Calories per Slice, Min Number of Toppings และ Max Number of Toppings) ซึ่งขัดต่อมาตรฐานโครงสร้าง HTML ที่กำหนดให้ id ต้องไม่ซ้ำกันในหน้าเดียว การใช้ selector ผ่าน id จึงเกิดความกำกวมและชี้ไปยังช่องที่ไม่ถูกต้อง จึงต้องใช้การค้นหาข้อความบน Label แล้วย้อนกลับไปยัง input ข้างเคียง เช่น `//label[normalize-space()='Max Calories per Slice']/preceding-sibling::input` ในทางกลับกัน หากนักพัฒนาปรับปรุงหน้าเว็บโดยกำหนด id ให้เฉพาะเจาะจง เช่น `id="max-calories-per-slice"` จะช่วยให้ผู้ทดสอบสามารถเขียน locator ผ่าน CSS ID Selector (`#max-calories-per-slice`) ได้โดยตรง ซึ่งมีความรวดเร็ว มั่นคง ไม่เปราะบาง และไม่ได้รับผลกระทบเมื่อมีการเปลี่ยนคำข้อความบนหน้าเว็บหรือการแปลภาษา

2. การตรวจสอบใน Step 7 ของ QP-01 จัดเป็น **ความสอดคล้องของข้อมูลแบบครบวงจร (Consistency Oracle หรือ Cross-Feature Consistency Check)** ซึ่งทำหน้าที่ตรวจสอบว่าผลลัพธ์ที่ฝั่งผู้ใช้งาน (Customer) ได้รับ มีความถูกต้องและสอดคล้องตรงกันกับข้อมูลที่บันทึกและแสดงผลบนระบบหลังบ้านของผู้ดูแลระบบ (Admin) หรือไม่ ข้อบกพร่อง (Fault) สำคัญใน QuickPizza ที่การตรวจสอบนี้สามารถดักจับได้ คือ ปัญหาความผิดพลาดในการบันทึกข้อมูลลงฐานข้อมูล (Data Persistence Bug) หรือ Race Condition เช่น ระบบสร้างคำแนะนำและแสดง Tool เป็น "Pizza cutter" หรือ "Scissors" ให้ลูกค้าเห็นบนหน้าเว็บ แต่ฟังก์ชันบันทึกข้อมูลลงฐานข้อมูลทำงานผิดพลาดจนทำให้อุปกรณ์กลายเป็นค่า Default หรืออุปกรณ์ที่ถูกสั่งคัดออก (เช่น "Knife") รวมถึงกรณีที่ข้อมูลของผู้ใช้งานคนอื่นถูกบันทึกทับซ้อนกัน

3. ในกรณีที่ต้องการตรวจสอบให้แน่ใจว่าพิซซ่าที่สร้างขึ้นเป็นมังสวิรัติ (Vegetarian) อย่างแท้จริง สามารถทำได้โดยการดักจับ Network Response ผ่าน `cy.intercept('POST', '/api/pizza')` แล้วตรวจสอบค่า Assertion ว่า `expect(response.body.vegetarian).to.be.true` พร้อมทั้งวนลูปตรวจสอบส่วนผสมใน `response.body.pizza.ingredients` แต่หากต้องการตรวจสอบจากสิ่งที่แสดงบนหน้า UI ผู้ทดสอบจำเป็นต้องมีข้อมูลอ้างอิงภายนอก (Oracle List) ที่รวบรวมรายชื่อวัตถุดิบที่ไม่ใช่มังสวิรัติ (เช่น ไส้กรอก, เปปเปอโรนี, แฮม, เบคอน, โพรชุตโต) เพื่อนำมาตรวจสอบเทียบกับรายการส่วนผสมใต้หัวข้อ "Ingredients:" ว่าต้องไม่มีรายการใดตรงกับวัตถุดิบเนื้อสัตว์ หรืออีกวิธีหนึ่งคือขอให้นักพัฒนาเพิ่มการแสดงป้ายสัญลักษณ์บนหน้าเว็บ เช่น `<span class="badge">Vegetarian</span>` หรือ attribute `data-vegetarian="true"` บนการ์ดผลลัพธ์ เพื่อให้ชุดทดสอบสามารถ Assert ได้อย่างแม่นยำ

4. เมื่อเปรียบเทียบเครื่องมือทั้งสองตัว ในด้านการรอหน้าเว็บ Selenium WebDriver ในภาษา Java ต้องอาศัยการเขียนเงื่อนไขการรออย่างชัดเจน (Explicit Wait) ผ่าน `WebDriverWait` ร่วมกับ `ExpectedConditions` สำหรับทุกจุดที่มีการโหลดแบบ Asynchronous เช่น การรอให้อุปกรณ์ใน Dropdown ปรากฏ ในขณะที่ Cypress มีระบบรออัตโนมัติ (Built-in Retry Mechanism) ในคำสั่งอย่าง `cy.get()` และ `.should()` สูงสุดตามเวลา timeout ที่ตั้งไว้ จึงไม่ต้องเขียนโค้ดสั่งรอเอง ในด้านความเร็วในการพัฒนา Cypress ช่วยให้เขียนโค้ดทดสอบ QP-01 ได้เร็วกว่ามาก เพราะรองรับ Method Chaining และมีคำสั่ง `cy.intercept` ในตัว สำหรับการอ่านรายงานผล Cypress ให้ผลลัพธ์ที่เข้าใจง่ายและครอบคลุมที่สุด เนื่องจากบันทึกเป็นวิดีโอไฟล์ `.mp4` ที่แสดงหน้าจอการทำงานจริงคู่กับ Command Log ด้านข้าง ขณะที่ Selenium จะได้รายงานสรุปเป็นไฟล์ข้อความของ Surefire และต้องเขียนเมธอดจับภาพหน้าจอเพิ่มเติมเอง

5. สำหรับการทำ Automated UI Testing ในโครงงาน Project 2 ขอเลือกใช้ **Cypress** โดยมีเหตุผลสนับสนุนสำคัญ 2 ประการจากแลปนี้ ประการแรกคือ ความสามารถในการดักจับและตรวจสอบ Network Requests ด้วย `cy.intercept` ได้อย่างสะดวกรวดเร็ว ช่วยให้สามารถตรวจสอบได้ทั้ง Payload ฝั่ง Request และข้อมูล JSON ฝั่ง Response ตลอดจนการเก็บตัวแปรข้ามขั้นตอนได้ง่าย ซึ่งมีประโยชน์อย่างยิ่งสำหรับระบบร้านค้าหรือโรงแรมใน Project 2 ประการที่สองคือ Cypress มีระบบตรวจสอบสถานะของ Element และรอการตอบสนองอัตโนมัติ ทำให้การทดสอบมีความเสถียร ไม่เกิด Flaky Tests จากความหน่วงของเครือข่าย อีกทั้งยังสร้างวิดีโอบันทึกการทดสอบ (`.mp4`) และภาพถ่ายหน้าจอให้อัตโนมัติ ช่วยให้การตรวจสอบข้อผิดพลาดและรวบรวมหลักฐานส่งงานทำได้อย่างครบถ้วนสมบูรณ์

6. สำหรับ Part 2 (Selenium Java) หาก QuickPizza เปลี่ยนตำแหน่ง Locator ของกล่องกรอก Calories ในไฟล์ `QP01Test.java` ผู้ทดสอบจะต้องไล่ค้นหาและตามแก้โค้ดทุกบรรทัดที่มีการเรียกใช้ Locator ดังกล่าวด้วยตนเอง (เช่น บรรทัดที่ 36–38 ที่รอ Element และบรรทัดที่ 41 ที่ทำการกรอกข้อมูล) ซึ่งอาจเกิดการตกหล่นได้ง่าย แต่สำหรับ `QP01PageObjectTest.java` ที่พัฒนาด้วย Page Object Model ในไฟล์ทดสอบจะไม่มีข้อความ Locator ปรากฏอยู่เลย มีเพียงการเรียกใช้เมธอดอย่าง `home.showAdvanced()` หรือ `home.setMaxCalories(600)` เท่านั้น โดย Locator ทั้งหมดถูกรวมศูนย์ไว้ที่ค่าคงที่ `private static final By MAX_CALORIES` ภายในคลาส `HomePage.java` เพียงจุดเดียว การแก้ไขจึงทำเพียงแค่เปลี่ยนค่าของตัวแปรนี้ที่เดียว ก็จะส่งผลให้ทุกการทดสอบที่เรียกใช้งานได้รับการอัปเดตอย่างสมบูรณ์ ช่วยลดภาระการบำรุงรักษาโค้ดและป้องกันความผิดพลาดได้อย่างมีประสิทธิภาพ

7. สำหรับ Part 4 (Cypress) สาเหตุที่ QP-01 ต้องรวมทุกขั้นตอนไว้ภายในการทดสอบ `it()` เดียวกัน เนื่องจาก QuickPizza ทำงานอยู่บนเซิร์ฟเวอร์หลายเครื่อง และใช้ Session Cookies เพื่อระบุให้เบราว์เซอร์เชื่อมต่ออยู่กับเซิร์ฟเวอร์เครื่องเดิมที่เก็บข้อมูลพิซซ่าที่เพิ่งถูกสร้างขึ้น โดยพฤติกรรมมาตรฐานของ Cypress จะทำการล้าง Cookies, Local Storage และ State ทั้งหมดทิ้งโดยอัตโนมัติก่อนเริ่มบล็อก `it()` แต่ละตัว หากแยกขั้นตอนที่ 6 และ 7 (การล็อกอิน Admin และตรวจสอบรายชื่อพิซซ่า) ออกไปเป็นบล็อกการทดสอบใหม่ คุกกี้ที่เชื่อมต่อกับเซิร์ฟเวอร์เดิมจะถูกล้างทิ้ง ทำให้เมื่อเปิดหน้า `/admin` เบราว์เซอร์อาจถูกส่งต่อไปยังเซิร์ฟเวอร์เครื่องอื่นที่ไม่มีข้อมูลพิซซ่าที่สร้างไว้ในขั้นตอนที่ 4 ส่งผลให้คำสั่ง `cy.contains('li', pizzaName)` หาพิซซ่าไม่เจอและเกิดข้อผิดพลาด Timeout การรวมการทดสอบทั้งหมดไว้ในบล็อกเดียวจึงจำเป็นอย่างยิ่งต่อการรักษาความต่อเนื่องของ Session การทดสอบ
