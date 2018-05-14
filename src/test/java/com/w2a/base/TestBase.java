package com.w2a.base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.w2a.utilities.ExcelReader;
import com.w2a.utilities.ExtentManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    /*
     *
     * WebDriver - done
     * Properties - done
     * Logs - log4j jar, .log files, lo4j.properties file, Logger class
     * ExtentReports, ReportNG
     * DB
     * Excel
     * Mail
     * Jenkins
     *
     */

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
    public static WebDriverWait wait;
    public static ExtentReports rep = ExtentManager.getInstance();
    public static ExtentTest test;

    @BeforeSuite
    public void setUp() throws IOException {
        if (driver == null) {

            //load Config.properties files
            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
            config.load(fis);
            log.debug("Config file loaded.");

            //load OR.properties files
            fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
            OR.load(fis);
            log.debug("OR file loaded.");
        }

        //For using drivers configure PATH in system settings or use System.setProperty
        if (config.getProperty("browser").equals("chrome")) {
            driver = new ChromeDriver();
            log.debug("ChromeDriver created.");
        } else if (config.getProperty("browser").equals("firefox")) {
            driver = new FirefoxDriver();
            log.debug("Firefox created.");
        } else if (config.getProperty("browser").equals("internetexplorer")) {
            driver = new InternetExplorerDriver();
            log.debug("InternetExplorerDriver created.");
        }

        driver.get(config.getProperty("testsiteurl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
        log.debug("@BeforeSuit method completed");
    }

    public void click(String locator) {
        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).click();
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(OR.getProperty(locator))).click();
        }
        test.log(LogStatus.INFO, "Click on " + locator);
    }

    public void type(String locator, String data) {
        if (locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(data);
        } else if (locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(data);
        } else if (locator.endsWith("_ID")) {
            driver.findElement(By.id(OR.getProperty(locator))).sendKeys(data);
        }
        test.log(LogStatus.INFO, "Type in field: " + locator + " Data: " + data);
    }

    @AfterSuite
    public void tearDown() {
        driver.quit();
        log.debug("@After method completed");
    }
}
