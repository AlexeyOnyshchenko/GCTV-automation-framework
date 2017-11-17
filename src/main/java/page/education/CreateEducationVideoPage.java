package page.education;

import logger.LogFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;



public class CreateEducationVideoPage{
    private static final Logger LOG = LogFactory.getLogger(CreateEducationVideoPage.class);

    private By inputYoutubeURL = By.xpath("//label[contains(text(), 'Youtube url')]/following-sibling::div/input[@id='url']");

    private By chooseCategoryDropdown = By.xpath(".//*[@id='single-button-category']");
    private By chooseCategory = new By.ByLinkText("test");

    private By chooseVideoSharingStatusDropdown = By.xpath(".//*[@id='single-button-sharing']");
    private By chooseSharingStatus = new By.ByLinkText("Available To All Hospitals");

    private By chooseOrder = By.xpath(".//*[@id='order']");

    //private By saveChangesButton = By.xpath("//button[contains(text(),'Save changes')]");
    //private By saveChangesButton = By.className("btn-primary");
    private By backToListButton = By.xpath("//input[@value='Back to list']");





    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor jse;


    public CreateEducationVideoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 5);
        jse=(JavascriptExecutor)driver;
    }


    private void click(final By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    private void type(final By locator, final String text) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).sendKeys(text);
    }


    public void fillVideoFields() {
        LOG.info("Enter video URL");
        click(inputYoutubeURL);
        type(inputYoutubeURL, "https://youtu.be/NZKXkD6EgBk");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOG.info("Select video category");
        click(chooseCategoryDropdown);
        click(chooseCategory);

        LOG.info("Select video sharing status");
        click(chooseVideoSharingStatusDropdown);
        click(chooseSharingStatus);

        LOG.info("Enter video order");
        click(chooseOrder);
        type(chooseOrder,"1");

    }

    public void clickOnSaveButton() {
        LOG.info("Click on Save Changes button");
        WebElement saveChangesButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".btn-primary")));

        jse.executeScript("arguments[0].scrollIntoView(true);", saveChangesButton);
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn-primary"))).click();
        //return new EducationVideosPage(driver);
        //return PageFactory.initElements(driver, EducationVideosPage.class);

    }
    public void clickOnBackButton(){
        LOG.info("Click on Back button");
        click(backToListButton);
    }




}



