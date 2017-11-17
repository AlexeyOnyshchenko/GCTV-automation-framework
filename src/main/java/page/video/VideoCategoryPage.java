package page.video;

import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;


public class VideoCategoryPage {

    private static final Logger LOG = LogFactory.getLogger(VideoPage.class);

    // Add video category

    private By addNewVideoCategoryButton = By.xpath(".//*[@id='page-wrapper']/div/div[1]/div/div/ma-view-actions/ma-create-button/a");
    private By fillVideoCategoryTitle = By.xpath(".//*[@id='title']");
    private By fillVideoCategoryDescription = By.xpath(".//*[@id='description']");
    private By fillVideoCategoryOrder = By.xpath(".//*[@id='order']");
    private By chooseVideoCategorySharingStatusDropdown = By.xpath(".//*[@id='public']/div[1]/span");
    private By chooseVideoCategorySharingStatus = By.xpath(".//*[@id='ui-select-choices-row-0-0']/span");
    private By saveVideoCategoryButton = By.xpath(".//*[@id='create-view']/form/div[5]/div/button");

    // Delete video category

    private By deleteVideoCategoryButton = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[1]/div/ma-datagrid/table/tbody/tr[1]/td[7]/ma-column/gc-delete-field/ma-delete-button/a");
    private By confirmDeleteVideoCategoryButton = By.xpath(".//*[@id='delete-view']/div/button[1]");


    public WebDriver driver;
    private WebDriverWait wait;

    public VideoCategoryPage (WebDriver driver){
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
     Create video category
     */

    public void createVideoCategoryButton(){
        LOG.info("Go to Create video category screen");
        click(addNewVideoCategoryButton);
    }
    public void fillVideoCategoryInfo(){
        LOG.info("Fill video category title, description and order");
        type(fillVideoCategoryTitle, "Title");
        type(fillVideoCategoryDescription, "Description");
        type(fillVideoCategoryOrder, "7");
    }

    public void chooseCategorySharingStatus(){
        LOG.info("Choose video category sharing status");
        click(chooseVideoCategorySharingStatusDropdown);
        click(chooseVideoCategorySharingStatus);
    }

    public void saveVideoCategory(){
        LOG.info("Save video category");
        click(saveVideoCategoryButton);
    }

    /**
     Delete  video category
     */
    public void deleteVideoCategory(){
        LOG.info("Delete video category");
        click(deleteVideoCategoryButton);
    }
    public void confirmDeleteButton(){
        LOG.info("Confirm delete video category");
        click(confirmDeleteVideoCategoryButton);
    }


    }


