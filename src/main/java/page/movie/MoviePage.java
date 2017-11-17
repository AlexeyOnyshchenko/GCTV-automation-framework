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
import page.CriteriaPageFactory;
import page.WebPage;
import io.qameta.allure.Step;
import page.education.EducationVideoSourcePage;


public class MoviePage extends WebPage<MoviePage> {
    private static final Logger LOG = LogFactory.getLogger(MoviePage.class);

    @FindBy(how = How.XPATH, using = "//*[@class='page-header']//span[text()='TV & Movies']")
    private WebElement movieTitle;

    //@FindBy(how=How.XPATH, using = "//video-add/a")
    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Create')]")
    private WebElement addMovieButton;

    @FindBy(how = How.XPATH, using = "//span[contains(text(), 'Delete')]")
    private WebElement deleteMovieButton;

    @FindBy(how = How.CLASS_NAME, using = "btn-danger")
    private WebElement confirmDeleteMovieButton;

    public MoviePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public Function<WebDriver, ?> loadedCriteria() {
        return ExpectedConditions.visibilityOf(movieTitle);
    }


    @Step(value = "Select movie")
    public void selectCreatedMovie(String title) {
        LOG.info(String.format("Select checkbox near the movie %s", title));
        WebElement element = getWebDriver().findElement(By.xpath(String.format("//span[text()='%s']/ancestor::tr//input", title)));
        element.click();
    }

    @Step(value = "Click on Create button")
    public EducationVideoSourcePage clickOnAddButton() {
        LOG.info("Clicking on Create button");
        addMovieButton.click();
        return CriteriaPageFactory.criteriaInitWebElements(webDriver,
                EducationVideoSourcePage.class);
    }

    public void clickOnDeleteButton(){
        LOG.info("Clicking on Delete button");
        deleteMovieButton.click();
    }
    public void clickOnConfirmDeleteButton(){
        LOG.info("Clicking on Confirm Delete button");
        confirmDeleteMovieButton.click();
    }
    @Step(value="TV/Movie title is displayed")
    public boolean isMovieTitleDisplays(){
        return isElementDisplayed(movieTitle, 2000);
    }


}



