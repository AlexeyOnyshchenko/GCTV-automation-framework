package login;


import base.WebTestBase;
import driver.IWebDriverProvidable;
import driver.WebBrowser;
import driver.WebDriverFactoryProducer;
import logger.LogFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;
import page.WebPage;
import page.alert.AlertChunk;
import page.login.LoginPage;
import page.main.MainPage;
import testdata.TestConstants;

public class UserLoginTest extends WebTestBase {
    private static final Logger LOG = LogFactory.getLogger(UserLoginTest.class);

    // Page Objects
    protected IWebDriverProvidable provider;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected AlertChunk alertChunk;

    @Parameters({ "browserName", "url" })
    @BeforeMethod(alwaysRun = true)
    public void setup(String browserName, String url) {
        WebBrowser browser = WebBrowser.initBrowser(browserName, url);
        provider = WebDriverFactoryProducer.getWebDriverFactoryProvider();
        WebDriver webDriver = provider.initBrowserDriver(browser);
        WebPage<LoginPage> page = new LoginPage(webDriver);

        loginPage = page.navigateToPageUrl(url, LoginPage.class);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        provider.killDriverInstance();
    }

    @DataProvider
    public Object[][] userLoginProvider() {
        return new Object[][] { { "admin@lifechild.tv", "password" } };
    }

    @Test(dataProvider = "userLoginProvider")
    public void userLoginLogoutTest(String login, String password) {
        mainPage = loginPage.login(login, password, MainPage.class);

        LOG.info("Validate - that user was logged in.");
        Assert.assertTrue(mainPage.isMenuDisplayed(), "Menu was not displayed");

        alertChunk = mainPage.logOut();

        loginPage = alertChunk.acceptLogOut();

        LOG.info("Validate - that the User was logged out.");
        Assert.assertTrue(loginPage.isSignInButtonDisplayed(), "User was logged out");
    }

    @Test
    public void userLoginWithWrongCredentialsTest() {
        loginPage.login(TestConstants.TestData.WRONG_USER_EMAIL, TestConstants.TestData.WRONG_PASSWORD, LoginPage.class);

        LOG.info("Validate - that the User wasn't logged in");
        Assert.assertTrue(loginPage.checkIfInvalidCredentialsMessageAppear(), "Invalid login message was not appear");
    }

}
