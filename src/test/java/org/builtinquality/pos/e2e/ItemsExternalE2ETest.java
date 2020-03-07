package org.builtinquality.pos.e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.List;

import static java.lang.Thread.sleep;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItemsExternalE2ETest {

    private static WebDriver driver;

    public static void main(String[] args) throws Exception {
        setUp();
        shouldSaveItems();
        tearDown();
    }

    static void setUp() throws Exception {
        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
    }

    static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    static void shouldSaveItems() throws InterruptedException {
        driver.navigate().to("http://localhost:8080");

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
