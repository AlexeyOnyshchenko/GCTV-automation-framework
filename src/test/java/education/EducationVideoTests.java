package education;

import base.TestBase;
import base.WebTestBase;
import driver.IWebDriverProvidable;
import driver.WebBrowser;
import driver.WebDriverFactoryProducer;
import logger.LogFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.WebPage;
import page.education.CreateEducationVideoPage;
import page.education.EducationVideoSourcePage;
import page.education.EducationVideosPage;
import page.login.LoginPage;
import page.main.MainPage;
import page.menu.SideMenuPage;
import testdata.TestConstants;


public class EducationVideoTests extends WebTestBase{
    private static final Logger LOG = LogFactory.getLogger(EducationVideoTests.class);

    protected EducationVideosPage educationVideosPage;
    protected SideMenuPage sideMenuPage;
    protected EducationVideoSourcePage educationVideoSourcePage;
    protected CreateEducationVideoPage createEducationVideoPage;
    protected IWebDriverProvidable provider;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected WebDriver driver;


    @Parameters({ "browserName", "url" })
    @BeforeMethod(alwaysRun = true)
    public void setup(String browserName, String url) {
        WebBrowser browser = WebBrowser.initBrowser(browserName, url);
        provider = WebDriverFactoryProducer.getWebDriverFactoryProvider();
        driver = provider.initBrowserDriver(browser);
        WebPage<LoginPage> page = new LoginPage(driver);

        loginPage = page.navigateToPageUrl(url, LoginPage.class);
        mainPage = loginPage.login(TestConstants.TestData.USER_EMAIL, TestConstants.TestData.USER_PASSWORD, MainPage.class);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        provider.killDriverInstance();
    }


    @Test
    public void createEducationVideo() {
        sideMenuPage = new SideMenuPage(driver);
        educationVideosPage = new EducationVideosPage(driver);
        //educationVideoSourcePage = new EducationVideoSourcePage(driver);
        createEducationVideoPage = new CreateEducationVideoPage(driver);

        sideMenuPage.openEducationVideoPage();
        educationVideosPage.clickOnCreateButton();
        educationVideoSourcePage.clickOnYoutubeButton();
        createEducationVideoPage.fillVideoFields();
        createEducationVideoPage.clickOnSaveButton();
        createEducationVideoPage.clickOnBackButton();

        //Assert.assertTrue







    }





}
