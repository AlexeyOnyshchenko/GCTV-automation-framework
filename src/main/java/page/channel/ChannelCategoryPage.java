package page.channel;

import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;


public class ChannelCategoryPage {

    private static final Logger LOG = LogFactory.getLogger(ChannelCategoryPage.class);

    // Create channel category
    private By createNewChannelCategoryButton = By.xpath(".//*[@id='page-wrapper']/div/div[1]/div/div/ma-view-actions/ma-create-button/a");
    private By fillChannelCategoryTitle = By.xpath(".//*[@id='title']");
    private By fillChannelCategoryDescription = By.xpath(".//*[@id='description']");
    private By fillChannelCategoryOrder = By.xpath(".//*[@id='order']");
    private By chooseCategorySharingStatusDropdown = By.xpath(".//*[@id='public']/div[1]/span");
    private By chooseCategorySharingStatus = By.xpath(".//*[@id='ui-select-choices-row-0-0']/span");
    private By saveChannelCategoryButton = By.xpath(".//*[@id='create-view']/form/div[5]/div/button");

    // Delete channel category
    private By deleteChannelCategory = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[1]/div/ma-datagrid/table/tbody/tr[1]/td[7]/ma-column/gc-delete-field/ma-delete-button/a");
    private By confirmDeleteChannelCategory = By.xpath(".//*[@id='delete-view']/div/button[1]");

    public WebDriver driver;
    public WebDriverWait wait;

    public ChannelCategoryPage(WebDriver driver){
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
     Add Channel category
     */

    public void createNewChannelCategory(){
        LOG.info("Go to Create new channel category screen");
        click(createNewChannelCategoryButton);
    }
    public void fillChannelCategoryTitle(){
        LOG.info("Input channel category title");
        click(fillChannelCategoryTitle);
        type(fillChannelCategoryTitle, "Test channel category");
    }
    public void fillChannelCategoryDescription(){
        LOG.info("Input channel category description");
        click(fillChannelCategoryDescription);
        type(fillChannelCategoryDescription, "Description");
    }
    public void fillChannelCategoryOrder(){
        LOG.info("Input channel category order");
        click(fillChannelCategoryOrder);
        type(fillChannelCategoryOrder, "8");
    }
    public void chooseCategorySharingStatus(){
        LOG.info("Choose channel category sharing status");
        click(chooseCategorySharingStatusDropdown);
        click(chooseCategorySharingStatus);
    }
    public void saveChannelCategory(){
        LOG.info("Save channel category");
        click(saveChannelCategoryButton);
    }

    /**
     Delete Channel category
     */

    public void deleteChannelCategory(){
        LOG.info("Delete channel category");
        click(deleteChannelCategory);
    }
    public void confirmDeleteChannelCategory(){
        LOG.info("Confirm delete channel category");
        click(confirmDeleteChannelCategory);
    }







}
