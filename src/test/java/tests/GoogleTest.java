package tests;

import PageObject.GooglePageObject;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import main.Base;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class GoogleTest extends Base {

    public static Logger log= LogManager.getLogger(GoogleTest.class.getName());

    ExtentReports extent;
    ExtentTest logger;

    @BeforeTest
    public void startReport() throws IOException {
    driver=capabilities();
    log.info("getting the capabilities of the driver class");
    extent=new ExtentReports(System.getProperty("user.dir")+"//reports/google.html",true);
        log.info("report location");
    extent.addSystemInfo("Tester","Abhishek");
        log.info("environment variables");
    extent.loadConfig(new File(System.getProperty("user.dir")+"//extent-config.xml")) ;
        log.info("report structure");
    }

    @Test
    public void test1()
    {
        logger=extent.startTest("Pass Test");
        log.info("pass test");
        driver.get(props.getProperty("url"));
        log.info("navigating to the url");
        GooglePageObject gpo=new GooglePageObject(driver);
        log.info("object creation");
        gpo.getSearchItem().sendKeys("Latest Songs 2023");
        log.info("item is displayed");
        gpo.getSearch().sendKeys(Keys.ENTER);
        log.info("enter is clicked");
    }

    @Test
    public void test2()
    {
        logger=extent.startTest("Fail Test");
        log.info("test is failed");
        String title=driver.getTitle();
        log.info("title is");
        String url=driver.getCurrentUrl();
        log.info("url is");
        Assert.assertEquals("No title",title);
        log.info("comparing the string");
        Assert.assertEquals("url",url);
        log.info("comparing the string");
    }

    @Test
    public void test3()
    {
        logger=extent.startTest("Skip Test");
        log.info("test is skipped");
        throw new SkipException("Test is skipped");
    }

    @AfterTest
    public void endReport()
    {
        extent.flush();
        extent.close();
        log.info("report is closed");
        driver.close();
        log.info("current window is closed");
        driver.quit();
        log.info("all browser window is closed");
    }

    @AfterMethod
    public void getResult(ITestResult result) throws IOException {
        if(result.getStatus()== ITestResult.FAILURE)
        {
            logger.log(LogStatus.FAIL,"Test is failed"+result.getName());
            logger.log(LogStatus.FAIL,"Test is failed"+result.getThrowable());
            String screensource=Base.getScreenshot(driver,result.getName());
            logger.log(LogStatus.FAIL,logger.addScreencast(screensource));
        }
        else {
            logger.log(LogStatus.SKIP,"Test is skipped"+result.getName());
        }
        extent.endTest(logger);
    }

}
