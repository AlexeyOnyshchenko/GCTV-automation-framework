package page.music;


import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;



public class MusicVideoPage {

    private static final Logger LOG = LogFactory.getLogger(MusicVideoPage.class);



    private By createNewMusicVideoButton = By.xpath("//span[contains(text(), 'Create')]");
    private By chooseYoutubeVideoSource = By.xpath("//div[contains(text(), 'Youtube')]");
    private By inputYoutubeUrl = By.xpath("//label[contains(text(), 'Youtube')]/following-sibling::div/input[@id='url']");

    private By chooseVideoCategoryDropdown = By.cssSelector("#single-button-category");
    private By chooseVideoCategory = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div[2]/form/div[5]/div[4]/div/div/ul/li[1]/a");
    private By chooseSharingStatusDropdown = By.cssSelector("#single-button-sharing");
    private By chooseSharingStatus = By.xpath(".//*[@id='page-wrapper']/div/div[3]/div[2]/form/div[5]/div[5]/div/div/ul/li/a");
    private By chooseVideoOrderField = By.id("order");
    private By saveVideoButton = By.className("btn-primary");

    //Vimeo Video
    private By inputVimeoUrl = By.xpath("//label[contains(text(), 'Vimeo')]/following-sibling::div/input[@id='url']");
    private By chooseVimeoVideoSource = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[5]/div/a/div/span[2]/i");

    //Delete music video

    private By deleteVideoButton = By.xpath(".//*[@id='page-wrapper']/div/div[2]/div[1]/div/ma-datagrid/table/tbody/tr[1]/td[11]/ma-column/gc-delete-field/ma-delete-button/a");
    private By confirmDeleteButton = By.className("btn-danger");


    public WebDriver driver;
    private WebDriverWait wait;

    public MusicVideoPage (WebDriver driver){
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
     Create Youtube music video
     */
    public void createNewMusicVideoButton(){
        LOG.info("Go to new music video screen");
        click(createNewMusicVideoButton);

    }

    public void chooseYoutubeVideoSource(){
        LOG.info("Choose Youtube music video source");
        click(chooseYoutubeVideoSource);
    }

    public void fillYoutubeURL(){
        LOG.info("Fill youtube music video URL ");
        click(inputYoutubeUrl);
        type(inputYoutubeUrl, "https://youtu.be/NZKXkD6EgBk");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void chooseVideoCategory(){
        LOG.info("Choose music video category");
        click(chooseVideoCategoryDropdown);
        click(chooseVideoCategory);
    }



    public void chooseSharingStatus(){
        LOG.info("Choose music video sharing status");
        click(chooseSharingStatusDropdown);
        click(chooseSharingStatus);
    }


    public void setVideoOrder(){
        LOG.info("Choose music video order");
        click(chooseVideoOrderField);
        type(chooseVideoOrderField, "7");

    }

    public void saveVideoButton(){
        LOG.info("Save music video");
        click(saveVideoButton);
    }

    /**
     Create Vimeo music video
     */

    public void chooseVimeoVideoSource(){
        LOG.info("Choose vimeo music video source");
        click(chooseVimeoVideoSource);
    }

    public void fillVimeoURL(){
        LOG.info("Fill vimeo music video URL");
        click(inputVimeoUrl);
        type(inputVimeoUrl, "https://vimeo.com/82395449");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     Delete music video
     */

    public void deleteVideoButton(){
        LOG.info("Delete music video");
        click(deleteVideoButton);
    }

    public void confirmDeleteButton(){
        LOG.info("Confirm delete music video");

        click(confirmDeleteButton);
    }

}

//driver.findElement(By.linkText("Create")).click();

