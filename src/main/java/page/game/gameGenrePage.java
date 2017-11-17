package page.game;

import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

public class gameGenrePage {
    private static final Logger LOG = LogFactory.getLogger(GamePage.class);

    //create game genre

    private By createNewGameGenreButton = By.xpath(".//*[@id='page-wrapper']/div/div[1]/div/div/ma-view-actions/ma-create-button/a");
    private By gameGenreTitle = By.id("title");
    private By gameGenreDescription = By.id("description");
    private By gameGenreOrder = By.id("order");
    private By sharingGenreMenu = By.xpath(".//*[@id='public']/div[1]/span");
    private By availableSharing = By.id("ui-select-choices-row-0-0");
    private By restrictSharing = By.id("ui-select-choices-row-0-1");
    private By submitButton = By.xpath(".//*[@id='create-view']/form/div[5]/div/button");

    //delete game genre

    private By deleteGameGenreButton = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[1]/div/ma-datagrid/table/tbody/tr/td[7]/ma-column/gc-delete-field/ma-delete-button/a");
    private By confirmDeleteGenreButton = By.xpath(".//*[@id='delete-view']/div/button[1]");


    public WebDriver driver;
    private WebDriverWait wait;

    public gameGenrePage(WebDriver driver){
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

    public void createNewGameGenreButton(){
        LOG.info("Go to Create game genre screen");
        click(createNewGameGenreButton);
    }

    public void fillGameGenreFields() {
        LOG.info("Input game genre Title, Description and Order");
        type(gameGenreTitle, "Title");
        type(gameGenreDescription, "Description");
        type(gameGenreOrder, "7");

    }

    public void chooseGameGenreSharingOption() {
        LOG.info("Choose game sharing");
        click(sharingGenreMenu);
        click(availableSharing);
        //click(restrictSharing);
    }

    public void clickOnSubmitButton() {
        LOG.info("Save changes");
        click(submitButton);
    }

    /**
     Delete music category
     */

    public void deleteCategoryButton() {
        LOG.info("Delete game genre");
        click(deleteGameGenreButton);
    }

    public void confirmDeleteCategoryButton() {
        LOG.info("Confirm delete game genre");
        click(confirmDeleteGenreButton);
    }

}




