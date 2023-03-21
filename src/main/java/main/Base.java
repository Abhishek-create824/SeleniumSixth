package main;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class Base {

    public WebDriver driver;
    public Properties props;

    public static String getScreenshot(WebDriver driver,String testcase) throws IOException {
        String date=new SimpleDateFormat("yyyyMMdd").format(new Date());
        TakesScreenshot ts=(TakesScreenshot)driver;
        File scrFile=ts.getScreenshotAs(OutputType.FILE);
        String destinationFile=System.getProperty("user.dir")+"//FailedTestCase"+testcase+date+".png";
        FileUtils.copyFile(scrFile,new File(destinationFile));
        return destinationFile;
    }

    public WebDriver capabilities() throws IOException {
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources/global.properties");
        props=new Properties();
        props.load(fis);
        String browsername=props.getProperty("browser");
        if(browsername.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"//chromedriver.exe");
            ChromeOptions options=new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver=new ChromeDriver(options);
        }
//        else if(browsername.equalsIgnoreCase("firefox"))
//        {
//            System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"//geckodriver.exe");
//            FirefoxOptions options=new FirefoxOptions();
//            options.addArguments("--remote-allow-origins=*");
//            driver=new FirefoxDriver(options);
//        }
        else {
            System.out.println("no driver specified");
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }

}
