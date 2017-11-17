package page.movie;

import com.google.common.base.Function;
import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import page.WebPage;
import io.qameta.allure.Step;


public class CreateMovieCategoryPage extends WebPage<CreateMovieCategoryPage> {
    private static final Logger LOG = LogFactory.getLogger(CreateMovieCategoryPage.class);

    @FindBy(how= How.XPATH, using="//span[text()='Create new moviecategory']")
    private WebElement createCategoryTitle;

    @FindBy(how =How.ID, using="title")
    private WebElement titleInputField;

    @FindBy(how=How.ID, using="description")
    private WebElement descriptionInputField;

    @FindBy(how =How.ID, using = "order")
    private WebElement orderInputField;

    @FindBy(how=How.ID, using = "public")
    private WebElement sharingStatusButton;

    @FindBy(how=How.XPATH, using = "//button[@type='submit']")
    private WebElement saveButton;

    public CreateMovieCategoryPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public Function<WebDriver, ?> loadedCriteria() {
        return  ExpectedConditions.visibilityOf(createCategoryTitle);
    }

    @Step(value="Fill category fields")
    public void fillCategoryFields(String title, String description, String order, String status){
        LOG.info("Enter title");
        titleInputField.sendKeys(title);

        LOG.info("Enter description");
        descriptionInputField.sendKeys(description);

        LOG.info("Enter order");
        orderInputField.sendKeys(order);

        LOG.info("Select sharing status");
        sharingStatusButton.click();
        WebElement element = getWebDriver().findElement(By.xpath(String.format("//*[@role='option']//span[contains(text(),'%s')]",status)));
        element.click();
    }

    @Step(value="Click on Save button")
    public void clickOnSaveButton(){
        LOG.info("Clicking on Save button");
        saveButton.click();
    }

    @Step(value="Check error message displayed")
    public boolean isErrorMessageDisplayed(String message){
        WebElement element = getWebDriver().findElement(By.xpath(String.format("//div[@class='humane humane-flatty-error humane-animate'][text()='%']", message)));
        return element.isDisplayed();
    }
}
