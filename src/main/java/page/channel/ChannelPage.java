package page.channel;

import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;



public class ChannelPage {

    private static final Logger LOG = LogFactory.getLogger(ChannelPage.class);


    private By createNewChannelButton = By.xpath(".//*[@id='page-wrapper']/div/div[1]/div/div/ma-view-actions/ma-create-button/a");
    private By fillChannelURL = By.xpath(".//*[@id='url']");
    private By fillChannelTitle = By.xpath(".//*[@id='title']");
    private By fillChannelDescription = By.xpath(".//*[@id='description']");
    private By chooseChannelCategoryDropdown = By.xpath(".//*[@id='category.id']/div[1]/span");
    private By chooseChannelCategory = By.xpath(".//*[@id='ui-select-choices-row-0-0']/span");
    private By chooseChannelSharingStatusDropdown = By.xpath(".//*[@id='public']/div[1]/span");
    private By chooseChannelSharingStatus = By.xpath(".//*[@id='ui-select-choices-row-1-0']/span");
    private By saveChannelButton = By.xpath(".//*[@id='create-view']/form/div[6]/div/button");

    // Delete Channel
    private By deleteChannelButton = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[1]/div/ma-datagrid/table/tbody/tr[1]/td[10]/ma-column/gc-delete-field/ma-delete-button/a");
    private By confirmDeleteChannelButton = By.xpath(".//*[@id='delete-view']/div/button[1]");



    public WebDriver driver;
    private WebDriverWait wait;

    public ChannelPage (WebDriver driver){
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
     Add Channel
     */

    public void createNewChannel(){
        LOG.info("Go to Create channel screen");
        click(createNewChannelButton);
    }
    public void fillChannelURL(){
        LOG.info("Input channel URL");
        click(fillChannelURL);
        type(fillChannelURL, "https://www.twitch.tv/imaqtpie");
    }
    public void fillChannelTitle(){
        LOG.info("Input channel title");
        click(fillChannelTitle);
        type(fillChannelTitle, "Test Channel");
    }
    public void fillChannelDescription(){
        LOG.info("Input channel description");
        click(fillChannelDescription);
        type(fillChannelDescription, "Description");
    }
    public void chooseChannelCategory(){
        LOG.info("Choose channel category");
        click(chooseChannelCategoryDropdown);
        click(chooseChannelCategory);
    }
    public void chooseChannelSharing(){
        LOG.info("Choose channel sharing status");
        click(chooseChannelSharingStatusDropdown);
        click(chooseChannelSharingStatus);
    }
    public void saveChannel(){
        LOG.info("Save channel");
        click(saveChannelButton);
    }

    /**
     Delete Channel
     */

    public void deleteChannel(){
        LOG.info("Delete channel");
        click(deleteChannelButton);
    }
    public void confirmDeleteChannel(){
        LOG.info("Confirm delete channel");
        click(confirmDeleteChannelButton);
    }

}
