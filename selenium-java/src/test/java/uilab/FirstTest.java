package uilab;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

class FirstTest {
    // Goal: the web form opens and its title is "Web form".
    @Test
    void webFormOpens() {
        System.setProperty("webdriver.chrome.driver",
            System.getenv("CHROMEDRIVER"));
        ChromeOptions options = new ChromeOptions();
        if (Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new");
        }
        WebDriver driver = new ChromeDriver(options);
        try {
            driver.get(
                "https://www.selenium.dev/selenium/web/web-form.html");
            assertEquals("Web form", driver.getTitle());
        } finally {
            driver.quit();
        }
    }
}
