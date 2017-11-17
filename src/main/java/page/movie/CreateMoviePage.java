package page.movie;

import com.google.common.base.Function;
import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import page.CriteriaPageFactory;
import page.WebPage;
import io.qameta.allure.Step;



public class CreateMoviePage extends WebPage<CreateMoviePage> {
    public WebDriverWait wait;
    public JavascriptExecutor jse;
    private static final Logger LOG = LogFactory.getLogger(CreateMoviePage.class);

    @FindBy(how = How.XPATH, using="//*[@class='page-header']//*[text()='Create new movie']")
    private WebElement createMovieTitle;

/*    @FindBy(how=How.XPATH, using = "*//*//**//*[@ng-repeat='source in controller.video.sources']*//*//**//*[text()='Upload a digital file']")
    private WebElement uploadMovie;

    @FindBy(how=How.ID, using="file-chooser")
    private WebElement videoUploadField;*/

    @FindBy(how=How.XPATH, using="//label[contains(text(), 'Youtube')]/following-sibling::div/input[@id='url']")
    private WebElement youtubeUrlInput;

    @FindBy(how=How.XPATH, using="//label[contains(text(), 'Vimeo')]/following-sibling::div/input[@id='url']")
    private WebElement vimeoUrlInput;

    @FindBy(how=How.ID, using="title")
    private WebElement titleInputField;

    //@FindBy(how=How.ID, using="description")
    //private WebElement descriptionInputField;

    @FindBy(how=How.ID, using="single-button-category")
    private WebElement selectCategoryButton;

    @FindBy(how = How.ID, using="single-button-sharing")
    private WebElement sharingStatusButton;

    @FindBy(how = How.ID, using="ageEnable")
    private WebElement ageRatingCheckbox;

    @FindBy(how=How.XPATH, using = "//button[@type='submit']")
    private WebElement saveChangesButton;

    @FindBy(how=How.XPATH, using="//input[@value='Back to list']")
    private WebElement backToListButton;

    @FindBy(how=How.XPATH, using="//input[@value='Add new one']")
    private WebElement addNewButton;


    public CreateMoviePage(WebDriver webDriver) {
        super(webDriver);
        this.wait = new WebDriverWait(webDriver, 5);
        jse=(JavascriptExecutor)webDriver;
    }

    @Override
    public Function<WebDriver, ?> loadedCriteria() {
        return ExpectedConditions.visibilityOf(createMovieTitle);
    }


    @Step(value="Create  new movie")
    //public void fillMovieFields(String fileName, String title, String description, String category, String status)

    public void fillYoutubeURL(String url){
        LOG.info("Input URL");
        youtubeUrlInput.sendKeys(url);
    }
    public void fillVimeoURL(String url){
        LOG.info("Input URL");
        vimeoUrlInput.sendKeys(url);
    }
    public void fillMovieFields(String category, String status){
        /*LOG.info(String.format("Upload file %s", fileName));
        uploadMovie.sendKeys(returnVideoPath(fileName));*/

        LOG.info("Select category");
        selectCategoryButton.click();
        WebElement categoryText = getWebDriver().findElement(By.xpath(String.format("//*[@class='dropdown-menu uib-dropdown-menu']//a[text()='%s']", category)));
        categoryText.click();

        LOG.info("Select sharing status");
        sharingStatusButton.click();
        WebElement statusText = getWebDriver().findElement(By.xpath(String.format("//*[@class='dropdown-menu uib-dropdown-menu']//a[text()='%s']", status)));
        statusText.click();
    }
    public void ageRatingEnable() {
        LOG.info("Click on Save Changes button");
        WebElement ageRatingCheckbox = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ageEnable")));

        jse.executeScript("arguments[0].scrollIntoView(true);", ageRatingCheckbox);
        wait.until(ExpectedConditions.elementToBeClickable(By.id("ageEnable"))).click();
        //return new EducationVideosPage(driver);
        //return PageFactory.initElements(driver, EducationVideosPage.class);
    }

    @Step(value="Click on Save Changes button")
    public void clickOnSaveButton(){
        LOG.info("Clicking on Save Changes button");
        saveChangesButton.click();
    }

    @Step(value="Back ot list button displayed")
    public boolean isBackToListButton(){
        return isElementDisplayed(backToListButton, 2000);
    }

    @Step(value="Return to Movie List page")
    public MoviePage clickOnBackToList(){
        LOG.info("Click on Back to List button");
        backToListButton.click();
        return CriteriaPageFactory.criteriaInitWebElements(webDriver,
                MoviePage.class);
    }

    @Step(value="Check error message displayed")
    public boolean isErrorMessageDisplayed(String message){
        WebElement element = getWebDriver().findElement(By.xpath(String.format("//div[@class='humane humane-flatty-error humane-animate'][text()='%']", message)));
        return element.isDisplayed();
    }

}
