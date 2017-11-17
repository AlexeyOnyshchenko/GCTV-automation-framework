package page.movie;

import com.google.common.base.Function;
import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import page.WebPage;
import io.qameta.allure.Step;
import page.CriteriaPageFactory;
import page.menu.SideMenuChunk;

/**
 * Created by olga on 18/10/16.
 */
public class MovieCategoryPage extends WebPage<MovieCategoryPage> {
    private static final Logger LOG = LogFactory.getLogger(MovieCategoryPage.class);

    @FindBy(how = How.XPATH, using = "//span[text()='Movie Categories']")
    private WebElement categoryTitle;

    @FindBy(how=How.XPATH, using = "//span[contains(text(), 'Create')]")
    private WebElement createButton;


    private WebDriverWait wait;



    private void click(final By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }





    public MovieCategoryPage(WebDriver webDriver) {
        super(webDriver);

    }

    @Override
    public Function<WebDriver, ?> loadedCriteria() {
        return  ExpectedConditions.visibilityOf(categoryTitle);
    }

    @Step(value="Check category")
    public void selectCategory(String category){
        WebElement element = getWebDriver().findElement(By.xpath(String.format("//span[text()='%s']/ancestor::tr//input", category)));
        element.click();
    }

    public CreateMovieCategoryPage clickOnCreateButton(){
       LOG.info("Click on Create category button");
        createButton.click();
        return CriteriaPageFactory.criteriaInitWebElements(webDriver,
               CreateMovieCategoryPage.class);
    }

    //@Step
    //public CreateMovieCategoryPage createButtonClick() {
        //createButton.click();
        //return CriteriaPageFactory.criteriaInitWebElements(webDriver,
             //   CreateMovieCategoryPage.class);

    //cli}

}
