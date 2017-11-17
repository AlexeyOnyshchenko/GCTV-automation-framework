package page.main;

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
import page.alert.AlertChunk;
import page.login.LoginPage;
import page.menu.SideMenuChunk;
import io.qameta.allure.Step;

/**
 * Created by olga on 11/10/16.
 */
public class MainPage extends WebPage<MainPage> {
    private static final Logger LOG = LogFactory.getLogger(MainPage.class);

    @FindBy(how = How.XPATH, using = "//ma-menu-bar")
    private WebElement menuElement;

    @FindBy(how = How.ID, using = "single-button")
    private WebElement userButton;

    @FindBy(how = How.XPATH, using = "//a[contains(text(),'Logout')]")
    private WebElement logoutButton;

    public MainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Step(value = "Check whether ChildLife menu is displayed")
    public boolean isMenuDisplayed() {
        LOG.info("Checking ChildLife menu is displayed");
        return menuElement.isDisplayed();
    }

    @Step(value = "LogOut")
    public AlertChunk logOut() {
        LOG.info("Clicking on 'User button' button");
        userButton.click();

        LOG.info("Clicking on 'LogOut' button");
        logoutButton.click();
        return CriteriaPageFactory.criteriaInitWebElements(webDriver,
                AlertChunk.class);
    }

    @Step(value="Select menu")
    public SideMenuChunk selectMenu(){
        return CriteriaPageFactory.criteriaInitWebElements(webDriver,
                SideMenuChunk.class);
    }

    @Override
    public Function<WebDriver, ?> loadedCriteria() {
        return ExpectedConditions.visibilityOf(userButton);
    }
}
