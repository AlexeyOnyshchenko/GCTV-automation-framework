package movie;

import base.WebTestBase;
import driver.IWebDriverProvidable;
import driver.WebBrowser;
import driver.WebDriverFactoryProducer;
import logger.LogFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.WebPage;
import page.login.LoginPage;
import page.main.MainPage;
import page.menu.SideMenuChunk;
import page.movie.CreateMovieCategoryPage;
import page.movie.MovieCategoryPage;
import testdata.TestConstants;


/**
 * Created by user on 7/20/17.
 */
public class MovieCategoryTests extends WebTestBase {
    private static final Logger LOG = LogFactory.getLogger(MovieCategoryTests.class);

    //Page Objects
    protected IWebDriverProvidable provider;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected WebDriver webDriver=null;
    protected SideMenuChunk sideMenu;
    protected MovieCategoryPage categoryPage;
    protected CreateMovieCategoryPage createCategoryPage;

    String movieCategoryName;

    String movieName = null;

    @Parameters({ "browserName", "url" })
    @BeforeMethod(alwaysRun = true)
    public void setup(String browserName, String url) {
        WebBrowser browser = WebBrowser.initBrowser(browserName, url);
        provider = WebDriverFactoryProducer.getWebDriverFactoryProvider();
        webDriver = provider.initBrowserDriver(browser);
        WebPage<LoginPage> page = new LoginPage(webDriver);

        loginPage = page.navigateToPageUrl(url, LoginPage.class);
        mainPage = loginPage.login(TestConstants.TestData.USER_EMAIL, TestConstants.TestData.USER_PASSWORD, MainPage.class);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        provider.killDriverInstance();
    }

    @Test
    public void createMovieCategory() {

        mainPage = new MainPage(webDriver);
        sideMenu = mainPage.selectMenu();
        categoryPage = sideMenu.openMovieCategoriesPage();
        createCategoryPage = categoryPage.clickOnCreateButton();
        createCategoryPage.fillCategoryFields(
                "Test category",
                "Test description",
                "1",
                "Available To All Hospitals");
        createCategoryPage.clickOnSaveButton();
    }
}
