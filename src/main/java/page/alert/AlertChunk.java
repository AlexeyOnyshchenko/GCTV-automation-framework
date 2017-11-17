package page.alert;

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
import page.login.LoginPage;
import io.qameta.allure.Step;

/**
 * Created by olga on 11/10/16.
 */
public class AlertChunk extends WebPage<AlertChunk> {
    private static final Logger LOG = LogFactory.getLogger(AlertChunk.class);

    @FindBy(how= How.XPATH, using="//*[@class='modal-title text-center']")
    private WebElement dialogText;

    @FindBy(how = How.XPATH, using="//button[contains(text(), 'Yes, log out')]")
    private WebElement acceptLogoutButton;

    public AlertChunk(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public Function<WebDriver, ?> loadedCriteria() {
        return ExpectedConditions.visibilityOf(dialogText);
    }

    @Step(value = "Accept Logout")
    public LoginPage acceptLogOut() {
        LOG.info("Clicking on 'Yes, logout' button");
        acceptLogoutButton.click();

        return CriteriaPageFactory.criteriaInitWebElements(webDriver,
                LoginPage.class);
    }
}
