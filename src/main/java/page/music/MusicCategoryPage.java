package page.music;

import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;


public class MusicCategoryPage {

    private static final Logger LOG = LogFactory.getLogger(MusicCategoryPage.class);

    //create music category

    private By createNewMusicCategoryButton = By.xpath(".//*[@id='page-wrapper']/div/div[1]/div/div/ma-view-actions/ma-create-button/a");
    private By categoryTitle = By.id("title");
    private By categoryDescription = By.id("description");
    private By categoryOrder = By.id("order");
    private By sharingMusicMenu = By.xpath(".//*[@id='public']/div[1]/span");
    private By availableSharing = By.id("ui-select-choices-row-0-0");
    private By restrictSharing = By.id("ui-select-choices-row-0-1");
    private By submitButton = By.xpath(".//*[@id='create-view']/form/div[5]/div/button");

    //delete music category

    private By deletevideoCategoryButton = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[1]/div/ma-datagrid/table/tbody/tr/td[7]/ma-column/gc-delete-field/ma-delete-button/a");
    private By confirmDeleteCategoryButton = By.xpath(".//*[@id='delete-view']/div/button[1]");



    public WebDriver driver;
    private WebDriverWait wait;

    public MusicCategoryPage(WebDriver driver){
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
     Create music category
     */

    public void createNewMusicCategoryButton(){
        LOG.info("Go to create new music video category screen");
        click(createNewMusicCategoryButton);
}

    public void fillMusicCategoryFields() {
        LOG.info("Fill music video category title, description, order");
        type(categoryTitle, "Title");
        type(categoryDescription, "Description");
        type(categoryOrder, "7");

    }

    public void chooseMusicCategorySharingOption() {
        LOG.info("Choose music video category sharing option");
        click(sharingMusicMenu);
        click(availableSharing);
        //click(restrictSharing);
    }

    public void clickOnSubmitButton() {
        LOG.info("Save music video category");
        click(submitButton);
    }

    /**
     Delete music category
     */

    public void deleteCategoryButton() {
        LOG.info("Delete music video category");
        click(deletevideoCategoryButton);
    }

    public void confirmDeleteCategoryButton() {
        LOG.info("Confirm delete music video category");
        click(confirmDeleteCategoryButton);
    }

}
