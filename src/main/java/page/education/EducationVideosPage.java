package page.education;

import logger.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import page.CriteriaPageFactory;
import io.qameta.allure.Step;


public class EducationVideosPage {
    private static final Logger LOG = LogFactory.getLogger(EducationVideosPage.class);

    //private By createVideoButton = By.xpath("//span[contains(text(), 'Create')]");
    @FindBy (how = How.XPATH, using="//span[contains(text(), 'Create')]")
    private WebElement createVideoButton;

    public WebDriver driver;

    public EducationVideosPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Step(value="Click on Create button")
    public void clickOnCreateButton() {
        LOG.info("Clicking on Create button");
        createVideoButton.click();
        //click(createVideoButton);
        //return CriteriaPageFactory.criteriaInitWebElements(driver, EducationVideoSourcePage.class);
    }


}
