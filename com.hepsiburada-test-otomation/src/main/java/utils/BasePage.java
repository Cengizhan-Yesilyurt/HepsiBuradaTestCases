package utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasePage {

    public static Logger logger = LogManager.getLogger(BasePage.class);
    public WebDriver driver;
    public WebDriverWait wait;
    JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
        js = (JavascriptExecutor) driver;
    }

    public WebElement findElement(By by) {

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));

        return driver.findElement(by);
    }

    public void sendKeys(By by, String value) {
        findElement(by).sendKeys(value);
    }

    public void click(By by) {
        findElement(by).click();
    }


    public String getText(By by){
        return findElement(by).getText();
    }


    public void moveDown(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView();", element);
        logger.info("Element visible olana kadar scroll down yapıldı.");
    }

    public List<WebElement> findElements(By by) {
        List<WebElement> elements = driver.findElements(by);

        return elements;
    }

    public WebElement findElementOnPage(By by, int i) {
        List<WebElement> list = findElements(by);
        logger.info(i + ". item return edildi ");
        return list.get(i);
    }


}

