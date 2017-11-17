package page.video;

import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;



public class VideoPage {

    private static final Logger LOG = LogFactory.getLogger(VideoPage.class);

    // Add Youtube video
    private By addNewVideoButton = By.xpath(".//*[@id='page-wrapper']/div/div[1]/div/div/ma-view-actions/video-add/a");
    private By chooseYoutubeVideoSource = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[4]/div/a/div/span[2]/i");
    private By fillYoutubeVideoURL = By.xpath("//label[contains(text(), 'Youtube')]/following-sibling::div/input[@id='url']");
    private By chooseVideoCategoryDropdown = By.xpath(".//*[@id='single-button-category']");
    private By chooseVideoCategory = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div[2]/form/div[5]/div[4]/div/div/ul/li/a");
    private By chooseVideoSharingStatusDropdown = By.xpath(".//*[@id='single-button-sharing']");
    private By chooseVideoSharingStatus = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div[2]/form/div[5]/div[5]/div/div/ul/li/a");
    private By saveYoutubeVideoButton = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div[2]/form/div[5]/div[6]/div/button");

    // Add Vimeo video

    private By chooseVimeoVideoSource = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[5]/div/a/div/span[2]/i");
    private By inputVimeoVideoUrl = By.xpath("//label[contains(text(), 'Vimeo')]/following-sibling::div/input[@id='url']");

    // Delete existing video

    private By deleteVideoButton = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[1]/div/ma-datagrid/table/tbody/tr[1]/td[11]/ma-column/gc-delete-field/ma-delete-button/a");
    private By confirmDeleteVideoButton = By.xpath(".//*[@id='delete-view']/div/button[1]");





    public WebDriver driver;
    private WebDriverWait wait;

    public VideoPage (WebDriver driver){
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
     Create Youtube video
     */

    public void createNewVideoButton(){
        LOG.info("Go to Create video screen");
        click(addNewVideoButton);
    }
    public void chooseYoutubeVideoSource(){
        LOG.info("Choose Youtube video source");
        click(chooseYoutubeVideoSource);
    }
    public void fillYoutubeVideoURL(){
        LOG.info("Fill youtube URL");
        click(fillYoutubeVideoURL);
        type(fillYoutubeVideoURL,"https://youtu.be/NZKXkD6EgBk");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void chooseVideoCategory(){
        LOG.info("Choose video category");
        click(chooseVideoCategoryDropdown);
        click(chooseVideoCategory);
    }
    public void chooseVideoSharingStatus(){
        LOG.info("Choose video sharing status");
        click(chooseVideoSharingStatusDropdown);
        click(chooseVideoSharingStatus);
    }
    public void saveYoutubeVideo(){
        LOG.info("Save new video");
        click(saveYoutubeVideoButton);
    }


    /**
     Create Vimeo video
     */

    public void chooseVimeoVideoSource(){
        LOG.info("Choose Vimeo video source");
        click(chooseVimeoVideoSource);
    }
    public void fillVimeoVideoURL(){
        LOG.info("Fill Vimeo URL");
        click(inputVimeoVideoUrl);
        type(inputVimeoVideoUrl,"https://vimeo.com/82395449");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     Delete video
     */

    public void deleteVideoButton(){
        LOG.info("Delete video");
        click(deleteVideoButton);
    }
    public void confirmDeleteVideoButton(){
        LOG.info("Confirm delete video");
        click(confirmDeleteVideoButton);
    }













}
