package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestContext;
import org.testng.annotations.*;
import logger.LogFactory;
import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by olga on 11/10/16.
 */
public class TestBase {

    public static WebDriver driver;


    public static void init() {
        WebDriverManager.getInstance(ChromeDriver.class).setup();
        driver = new ChromeDriver();
        //WebDriverManager.getInstance(FirefoxDriver.class).setup();
        //driver = new FirefoxDriver();
        driver.manage().window().maximize();

        driver.get("http://localhost:1337/auth");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        login("admin@lifechild.tv", "password");
        //login("al.phobos90@gmail.com", "password");
    }

    public static void stop() {
        driver.quit();
    }

    @BeforeSuite
    public void setUp() throws Exception {
        //init();

    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        //stop();
    }

    public static void login(String email, String password) {
        WebElement loginField = driver.findElement(By.xpath("html/body/div[1]/div/div/div/form/input[2]"));
        loginField.sendKeys(email);
        //loginField.sendKeys(Constants.TestData.USER_EMAIL);

        WebElement passwordField = driver.findElement(By.xpath("html/body/div[1]/div/div/div/form/input[3]"));
        passwordField.sendKeys(password);
        //passwordField.sendKeys(Constants.TestData.USER_PASSWORD);

        WebElement signinButton = driver.findElement(By.xpath("html/body/div[1]/div/div/div/form/button"));
        signinButton.click();
    }


    private static final Logger LOG = LogFactory.getLogger(TestBase.class);

    /**
     * @param p
     * @throws Exception
     */

    @BeforeMethod(alwaysRun = true)
    public void setupTestBase(Method m, Object[] p) throws Exception {
        LOG.info("Start test " + m.getName() + " with parameters " + Arrays.asList(p));

    }

    /**
     * @param m
     * @throws Exception
     */
    @AfterMethod(alwaysRun = true)
    public void tearDownTestBase(Method m) throws Exception {
        LOG.info("Stop test " + m.getName());
        //driver.findElement(By.linkText("Link text")).click();
    }

    /**
     * @param method
     * @throws Exception
     */
    @BeforeMethod(alwaysRun = true)
    public void setupTestBaseMethod(ITestContext context, Method method) {
        LOG.info("base.TestBase before @Test method");
        }

    /**
     * @param method
     * @throws Exception
     */
        @AfterMethod(alwaysRun = true)
        public void tearDownTestBaseMethod(ITestContext context, Method method) {
        LOG.info("base.TestBase after @Test method");
        }



}
