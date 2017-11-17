package page.education;

import com.google.common.base.Function;
import logger.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import io.qameta.allure.Step;
import page.CriteriaPageFactory;
import page.WebPage;
import page.movie.CreateMoviePage;


/**
 * Created by user on 7/20/17.
 */
public class EducationVideoSourcePage extends WebPage<EducationVideoSourcePage> {
    private static final Logger LOG = LogFactory.getLogger(EducationVideoSourcePage.class);

    //private By youtubeSource = By.xpath("//div[contains(text(), 'Youtube')]");
    @FindBy(how = How.XPATH,using="//div[contains(text(), 'Youtube')]")
    private WebElement youtubeSource;

    @FindBy(how = How.XPATH,using="//div[contains(text(), 'Vimeo')]")
    private WebElement vimeoSource;

    @FindBy(how = How.XPATH, using = "//*[@class='ng-scope']//span[text()='Select the video destination']")
    private WebElement sourceTitle;

    //public WebDriver webDriver;

    public EducationVideoSourcePage (WebDriver webDriver){
        super(webDriver);
        //this.webDriver = webDriver;
        //PageFactory.initElements(webDriver, this);
    }

    @Step(value="Click on Youtube source button")
    public CreateMoviePage clickOnYoutubeButton() {
        LOG.info("Youtube source choosing");
        youtubeSource.click();
        return CriteriaPageFactory.criteriaInitWebElements(webDriver,
                CreateMoviePage.class);
    }

    @Step(value="Click on Vimeo source button")
    public CreateMoviePage clickOnVimeoButton() {
        LOG.info("Vimeo source choosing");
        vimeoSource.click();
        return CriteriaPageFactory.criteriaInitWebElements(webDriver,
                CreateMoviePage.class);
    }

    @Override
    public Function<WebDriver, ?> loadedCriteria() {
        return ExpectedConditions.visibilityOf(sourceTitle);
    }
}
