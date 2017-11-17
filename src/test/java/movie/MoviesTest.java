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
import page.education.EducationVideoSourcePage;
import page.login.LoginPage;
import page.main.MainPage;
import page.menu.SideMenuChunk;
import page.movie.CreateMoviePage;
import page.movie.MoviePage;
import testdata.TestConstants;

public class MoviesTest extends WebTestBase {
    private static final Logger LOG = LogFactory.getLogger(MoviesTest.class);

    // Page Objects
    protected IWebDriverProvidable provider;
    protected LoginPage loginPage;
    protected MainPage mainPage;
    protected WebDriver webDriver=null;
    protected SideMenuChunk sideMenu;
    protected MoviePage moviePage;
    protected CreateMoviePage createMoviePage;
    protected EducationVideoSourcePage videoSourcePage;

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
    public void createYoutubeMovie(){
        mainPage = new MainPage(webDriver);
        sideMenu = mainPage.selectMenu();
        moviePage = sideMenu.openMoviesPage();
        videoSourcePage = moviePage.clickOnAddButton();
        createMoviePage = videoSourcePage.clickOnYoutubeButton();
        createMoviePage.fillYoutubeURL("https://youtu.be/NZKXkD6EgBk");
        createMoviePage.fillMovieFields(
                "TOP MOVIES",
                "Available To All Hospitals");
        createMoviePage.ageRatingEnable();
        createMoviePage.clickOnSaveButton();
        Assert.assertTrue(createMoviePage.isBackToListButton(), "Back tot List Button isn't displayed");

        createMoviePage.clickOnBackToList();
    }

    @Test
    public void createVimeoMovie(){
        mainPage = new MainPage(webDriver);
        sideMenu = mainPage.selectMenu();
        moviePage = sideMenu.openMoviesPage();
        videoSourcePage = moviePage.clickOnAddButton();
        createMoviePage = videoSourcePage.clickOnVimeoButton();
        createMoviePage.fillVimeoURL("https://vimeo.com/227660894");
        createMoviePage.fillMovieFields(
                "TOP MOVIES",
                "Available To All Hospitals");
        createMoviePage.ageRatingEnable();
        createMoviePage.clickOnSaveButton();
        Assert.assertTrue(createMoviePage.isBackToListButton(), "Back tot List Button isn't displayed");

        createMoviePage.clickOnBackToList();

    }

    @Test
    public void deleteMovie(){
    mainPage = new MainPage(webDriver);
    sideMenu = mainPage.selectMenu();
    moviePage = sideMenu.openMoviesPage();
    moviePage.clickOnDeleteButton();
        moviePage.clickOnConfirmDeleteButton();
        //Assert.assertTrue(moviePage.isMovieTitleDisplays(), "TV/Movie title isn't displayed");

    }
    @Test
    public void createYoutubePlaylistMovie(){
        mainPage = new MainPage(webDriver);
        sideMenu = mainPage.selectMenu();
        moviePage = sideMenu.openMoviesPage();
        videoSourcePage = moviePage.clickOnAddButton();
        createMoviePage = videoSourcePage.clickOnYoutubeButton();
        createMoviePage.fillYoutubeURL("https://www.youtube.com/watch?v=0KSOMA3QBU0&list=PLMC9KNkIncKtPzgY-5rmhvj7fax8fdxoj");
        createMoviePage.fillMovieFields(
                "TOP MOVIES",
                "Available To All Hospitals");
        createMoviePage.ageRatingEnable();
        createMoviePage.clickOnSaveButton();
        Assert.assertTrue(createMoviePage.isBackToListButton(), "Back tot List Button isn't displayed");

        createMoviePage.clickOnBackToList();
    }




/*    @Test(dependsOnMethods = "createMovieCategory")
    public void updateMovieCategory(){
        moviePage = new MoviePageAlexey(webDriver);
        moviePage.selectCreatedMovie(movieName);

    }*/

}
