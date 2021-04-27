package listener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.BaseTest;


public class Listener extends BaseTest implements ITestListener {

    @Override
    public void onTestStart(ITestResult iTestResult) {

        driver.get(baseurl);
        log.info("----hepsiburada.com acıldı----");
        Assert.assertEquals(driver.getTitle().toLowerCase(), "türkiye'nin en büyük online alışveriş sitesi hepsiburada.com");


    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("Başarılı");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        System.out.println("Fail");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        System.out.println("skip");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {
        String sourceDirectory = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", sourceDirectory + "\\drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        log.info("----chrome option arguments added----");
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        log.info("-----Test Finished-----");
        driver.quit();
    }
}
