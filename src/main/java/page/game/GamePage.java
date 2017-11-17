package page.game;


import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;




public class GamePage {

    private static final Logger LOG = LogFactory.getLogger(GamePage.class);


    private By addNewGameButton = By.xpath(".//*[@id='page-wrapper']/div/div[1]/div/div/ma-view-actions/game-add/a");
    private By mobileTypeGameButton = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[2]/div/a/div/span[2]/i");
    private By inputAndroidGameURL = By.xpath(".//*[@id='urlAndroid']");
    private By inputIOSgameURL = By.xpath(".//*[@id='urliOS']");
    private By chooseGenreDropdown = By.xpath(".//*[@id='single-button-category']");
    private By chooseGameGenre = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div/form/div[3]/div[4]/div/div/ul/li/a");
    private By chooseDistributionDropdown = By.xpath(".//*[@id='single-button-dis']");
    //private TextFieldImpl faq = new TextFieldImpl(".//*[@id='single-button-dis']");

    //Free distribution
    private By chooseDistributionFree = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div/form/div[3]/div[5]/div/div/ul/li[1]/a");
    //Paid distribution
    private By chooseDistributionPaid = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div/form/div[3]/div[5]/div/div/ul/li[2]/a");

    private By saveChangesButton = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div/form/div[3]/div[11]/div/button");

    // Add STEAM game
    private By steamTypeGameButton = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[5]/div/a/div/span[1]");
    private By inputSteamURL = By.xpath(".//*[@id='url']");
    private By inputGameTitle = By.xpath(".//*[@id='title']");
    private By inputGameDescription = By.xpath(".//*[@id='description']");
    private By chooseSteamGameGenre = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div/form/div[2]/div[4]/div/div/ul/li/a");
    //Free distribution
    private By chooseSteamGameDistributionFree = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div/form/div[2]/div[5]/div/div/ul/li[1]/a");

    //Paid distribution
    private By chooseSteamGameDistributionPaid = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div/form/div[2]/div[5]/div/div/ul/li[2]/a");
    private By steamSaveChangesButton = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div/form/div[2]/div[12]/div/button");

    // Add HTML5 online game
    private By onlineTypeGameButton = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[3]/div/a/div/span[1]");
    private By onlneSaveChangesButton = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div/form/div[2]/div[11]/div/button");

    //Delete existing game
    private By deleteGameButton = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[1]/div/ma-datagrid/table/tbody/tr[1]/td[11]/ma-column/gc-delete-field/ma-delete-button/a");
    private By confirmDeleteGameButton = By.xpath(".//*[@id='delete-view']/div/button[1]");


    //Alert message
    private By isAlertMessagePresents = By.xpath("html/body/div[2]");





    public WebDriver driver;
    public WebDriverWait wait;

    public GamePage(WebDriver driver) {
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
     Add Android game
     */

    public void addNewGameButton(){
        LOG.info("Go to Create game screen");
        click(addNewGameButton);
    }
    public void mobileTypeGameButton(){
        LOG.info("Select mobile type game");
        click(mobileTypeGameButton);
    }
    public void inputAndroidGameURL(){
        LOG.info("Input android game URL");
        type(inputAndroidGameURL, "https://play.google.com/store/apps/details?id=com.outfit7.movingeye.swampattack&hl=ru");
    }
    public void chooseGameGenre(){
        LOG.info("Choose game genre");
        click(chooseGenreDropdown);
        click(chooseGameGenre);
    }
    public void chooseGameDestribution(){
        LOG.info("Choose game distribution");
        click(chooseDistributionDropdown);
        click(chooseDistributionFree);
    }
    public void gameSaveChanges(){
        LOG.info("Save changes");
        click(saveChangesButton);
    }

    /**
     Add iOS game
     */

    public void inputIOSgameURL(){
        LOG.info("Input iOS game URL");
        type(inputIOSgameURL, "https://itunes.apple.com/ru/app/carx-drift-racing/id644907661?mt=8");
    }

    /**
     Add Steam game
     */

    public void steamGameTypeButton(){
        LOG.info("Choose Steam game type");
        click(steamTypeGameButton);
    }
    public void inputSteamURL(){
        LOG.info("Fill Steam URL");
        type(inputSteamURL, "http://store.steampowered.com/app/421020/DiRT_4/");
    }
    public void inputGameTitle(){
        LOG.info("Type Steam game title");
        type(inputGameTitle, "Test Steam Game");
    }
    public void inputGameDescription(){
        LOG.info("Type Steam game description");
        type(inputGameDescription, "Test Steam Description");
    }
    public void chooseSteamGameGenre(){
        LOG.info("Choose game genre");
        click(chooseGenreDropdown);
        click(chooseSteamGameGenre);
    }
    public void chooseSteamGameDisctribution(){
        LOG.info("Choose game distribution");
        click(chooseDistributionDropdown);
        click(chooseSteamGameDistributionFree);
    }
    public void saveChangesButton(){
        LOG.info("Save changes");
        click(steamSaveChangesButton);
    }

    /**
     Add HTML5 online game
     */

    public void onlineGameTypeButton(){
        LOG.info("Choose Online game type");
        click(onlineTypeGameButton);
    }
    public void inputOnlineGameURL(){
        LOG.info("Input HTML5 URL");
        type(inputSteamURL, "https://canvasrider.com");
    }
    public void inputOnlineGameTitle(){
        LOG.info("Input HTML5 game title");
        type(inputGameTitle, "Test HTML5 online game");
    }
    public void inputOnlineGameDescription(){
        LOG.info("Input HTML5 game description");
        type(inputGameDescription, "Test HTML5 description");
    }
    public void chooseOnlineGameGenre(){
        LOG.info("Choose game genre");
        click(chooseGenreDropdown);
        click(chooseSteamGameGenre);
    }
    public void onlineSaveChangesButton(){
        LOG.info("Save changes");
        click(onlneSaveChangesButton);
    }

    /**
     Delete game
     */
    public void deleteGameButton(){
        click(deleteGameButton);
    }
    public void confirmDeleteGameButton(){
        click(confirmDeleteGameButton);
    }

    //private checkTextBoxHint(String text) {
       // faq.typeText();
    }




