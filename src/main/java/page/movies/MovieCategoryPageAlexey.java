package page.movies;

import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;



public class MovieCategoryPageAlexey {

    private static final Logger LOG = LogFactory.getLogger(MovieCategoryPageAlexey.class);

    //Create Movie category
    private By createNewMovieCategoryButton = By.xpath(".//*[@id='page-wrapper']/div/div[1]/div/div/ma-view-actions/ma-create-button/a");
    private By categoryTitle = By.id("title");
    private By categoryDescription = By.id("description");
    private By categoryOrder = By.id("order");
    private By sharingMovieMenu = By.xpath(".//*[@id='public']/div[1]/span");
    private By availableSharing = By.xpath(".//*[@id='ui-select-choices-row-0-0']/span");
    private By restrictSharing = By.id("ui-select-choices-row-3-1");
    private By submitButton = By.xpath(".//*[@id='create-view']/form/div[5]/div/button");

    //Delete Movie category

    private By deleteMovieCategoryButton = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[1]/div/ma-datagrid/table/tbody/tr[1]/td[7]/ma-column/gc-delete-field/ma-delete-button/a");
    private By confirmDeleteCategoryButton = By.xpath(".//*[@id='delete-view']/div/button[1]");

    public WebDriver driver;
    private WebDriverWait wait;

    public MovieCategoryPageAlexey(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);

    }

    private void click (final By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();

    }

    private void type(final By locator, final String text) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(text);
    }

    /**
     Create movie category
     */

    public void createMovieCategory(){
        LOG.info("Create new movie category");
        click(createNewMovieCategoryButton);
    }

    public void fillMovieCategoryFields() {
        LOG.info("Fill movie category title, description, order");
        type(categoryTitle, "Title");
        type(categoryDescription, "Description");
        type(categoryOrder, "7");

    }

    public void chooseMovieCategorySharingOption() {
        LOG.info("Choose movie category sharing status");
        click(sharingMovieMenu);
        click(availableSharing);
        //click(restrictSharing);
    }

    public void clickOnSubmitButton() {
        LOG.info("Save movie category");
        click(submitButton);
    }

    /**
     Delete movie category
     */

    public void deleteCategoryButton() {
        LOG.info("Delete movie category");
        click(deleteMovieCategoryButton);
    }

    public void confirmDeleteCategoryButton() {
        LOG.info("Confirm delete movie category");
        click(confirmDeleteCategoryButton);
    }



}
