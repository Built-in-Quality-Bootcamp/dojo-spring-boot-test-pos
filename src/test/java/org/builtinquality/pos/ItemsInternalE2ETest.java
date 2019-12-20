package org.builtinquality.pos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemsInternalE2ETest {

    private WebDriver driver;

    @LocalServerPort
    private int port;

    @BeforeAll
    public static void setUpClass() throws Exception {
        WebDriverManager.chromedriver().version("78").setup();
    }

    @BeforeEach
    public void setUp() throws Exception {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void should_save_items() throws InterruptedException {
        driver.navigate().to(String.format("http://localhost:%s", port));

        WebElement body = driver.findElement(By.tagName("body"));
        body.findElement(By.id("code")).sendKeys("ITEM001");
        body.findElement(By.id("name")).sendKeys("可口可乐");
        body.findElement(By.id("unit")).sendKeys("听");
        body.findElement(By.id("price")).sendKeys("3");
        body.findElement(By.id("submit")).click();

        sleep(2000);

        List<WebElement> rows = body.findElement(By.id("tby")).findElements(By.tagName("tr"));
        assertThat(rows.size(), is(1));
        List<WebElement> columns = rows.get(0).findElements(By.tagName("td"));
        assertThat(columns.size(), is(4));
        assertThat(columns.get(0).getText(), is("ITEM001"));
        assertThat(columns.get(1).getText(), is("可口可乐"));
        assertThat(columns.get(2).getText(), is("听"));
        assertThat(columns.get(3).getText(), is("3"));

    }
}
