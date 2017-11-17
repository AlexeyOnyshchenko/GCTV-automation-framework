package page.movies;

import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;


public class MoviePageAlexey {

    private static final Logger LOG = LogFactory.getLogger(MoviePageAlexey.class);

    private By createNewMovieButton = By.xpath(".//*[@id='page-wrapper']/div/div[1]/div/div/ma-view-actions/movie-add/a");

    //Add Youtube movie
    private By chooseYoutubeMovieSource = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[4]/div/a/div/span[2]/i");
    private By inputYoutubeURL = By.xpath("//label[contains(text(), 'Youtube')]/following-sibling::div/input[@id='url']");
    private By chooseMovieCategoryDropdown = By.xpath(".//*[@id='single-button-category']");
    private By chooseMovieCategory = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div[2]/form/div[5]/div[4]/div/div/ul/li[1]/a");
    private By chooseSharingStatusDropdown = By.xpath(".//*[@id='single-button-sharing']");
    private By chooseSharingStatus = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div[2]/form/div[5]/div[5]/div/div/ul/li/a");
    private By saveMovieButton = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div[2]/form/div[5]/div[7]/div/button");

    //Add Vimeo movie
    private By chooseVimeoMovieSource = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[5]/div/a/div/span[2]/i");
    private By inputVimeoURL = By.xpath("//label[contains(text(), 'Vimeo')]/following-sibling::div/input[@id='url']");

    //Delete movie
    private By deleteMovieButton = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[1]/div/ma-datagrid/table/tbody/tr[1]/td[11]/ma-column/gc-delete-field/ma-delete-button/a");
    private By confirmDeleteMovieButton = By.xpath(".//*[@id='delete-view']/div/button[1]");

    public WebDriver driver;
    private WebDriverWait wait;

    public MoviePageAlexey(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
    }

    private void click(final By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void type(final By locator, final String text) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).sendKeys(text);
    }

    /**
     Create Youtube movie
     */
    public void createNewMovieVideoButton(){
        LOG.info("Go to Create movie screen");
        click(createNewMovieButton);

    }

    public void chooseYoutubeMovieSource(){
        LOG.info("Choose Youtube movie source");
        click(chooseYoutubeMovieSource);
    }

    public void fillYoutubeURL(){
        LOG.info("Fill Youtube URL");
        click(inputYoutubeURL);
        type(inputYoutubeURL, "https://youtu.be/NZKXkD6EgBk");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void chooseMovieCategory(){
        LOG.info("Choose movie category");
        click(chooseMovieCategoryDropdown);
        click(chooseMovieCategory);
    }



    public void chooseSharingStatus(){
        LOG.info("Choose movie sharing status");
        click(chooseSharingStatusDropdown);
        click(chooseSharingStatus);
    }


    public void saveMovieButton(){
        LOG.info("Save movie");
        click(saveMovieButton);
    }

    /**
     Create Vimeo movie
     */

    public void chooseVimeoMovieSource(){
        LOG.info("Choose Vimeo movie source");
        click(chooseVimeoMovieSource);
    }

    public void fillVimeoURL(){
        LOG.info("Fill vimeo movie URL");
        click(inputVimeoURL);
        type(inputVimeoURL, "https://vimeo.com/82395449");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     Delete movie
     */

    public void deleteMovieButton(){
        LOG.info("Delete movie");
        click(deleteMovieButton);
    }

    public void confirmDeleteMovieButton(){
        LOG.info("Confirm delete movie");
        click(confirmDeleteMovieButton);
    }






















}
