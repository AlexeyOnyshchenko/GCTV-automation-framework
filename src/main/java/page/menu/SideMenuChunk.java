package page.menu;

import com.google.common.base.Function;
import logger.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import page.CriteriaPageFactory;
import page.WebPage;
import page.movie.MovieCategoryPage;
import page.movie.MoviePage;
import io.qameta.allure.Step;

import static page.CriteriaPageFactory.*;

/**
 * Created by olga on 18/10/16.
 */
public class SideMenuChunk extends WebPage<SideMenuChunk> {

    private static final Logger LOG = LogFactory.getLogger(SideMenuChunk.class);

    @FindBy(how = How.CSS, using="#side-menu")
    private WebElement menuElement;

    @FindBy(how=How.XPATH, using = "//*[@id='side-menu']/li[1]/a")
    private WebElement movieDropDown;

    @FindBy(how = How.XPATH, using="//ul[contains(@class,'nav nav-second-level')]//a[contains(text(),'Movies')]")
    private WebElement movieMenu;

    @FindBy(how = How.XPATH, using="//ul[contains(@class,'nav nav-second-level')]//a[contains(text(),'Movie Categories')]")
    private WebElement movieCategoryMenu;

    public SideMenuChunk(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public Function<WebDriver, ?> loadedCriteria() {
        return ExpectedConditions.visibilityOf(menuElement);
    }

    @Step(value="Open Movies Page")
    public MoviePage openMoviesPage() {
        LOG.info("Clicking on Movie toggle button");
        movieDropDown.click();

        LOG.info("Clicking on Movie menu button");
        movieMenu.click();
        return CriteriaPageFactory.criteriaInitWebElements(webDriver,
        MoviePage.class);
    }

    @Step(value="Open Movie categories Page")
    public MovieCategoryPage openMovieCategoriesPage(){
        LOG.info("Clicking on Movie toggle button");
        movieDropDown.click();

        LOG.info("Clicking on Movie Categorues menu button");
        movieCategoryMenu.click();
        return criteriaInitWebElements(webDriver,
                MovieCategoryPage.class);
    }
}
