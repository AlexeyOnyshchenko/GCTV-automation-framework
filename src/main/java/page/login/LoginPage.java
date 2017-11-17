package page.login;

import logger.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import page.CriteriaPageFactory;
import page.WebPage;
import io.qameta.allure.Step;

/**
 * Created by olga on 11/10/16.
 */
public class LoginPage extends WebPage<LoginPage> {

    private static final Logger LOG = LogFactory.getLogger(LoginPage.class);

    @FindBy(how = How.XPATH, using = "//button[contains(text(),'Sign in')]")
    private WebElement signInButton;

    @FindBy(how = How.XPATH, using = "//input[@name='email']")
    private WebElement emailAddressField;

    @FindBy(how = How.XPATH, using = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(how = How.XPATH, using = "//form//li")
    private WebElement invalidCredentials;

    /**
     * Creates a new LoginPage object
     *
     * @param webDriver
     */
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public ExpectedCondition<WebElement> loadedCriteria() {
        return ExpectedConditions.visibilityOf(signInButton);
    }

    @Step(value = "Login with user account login: \"{0}\" and password: \"{1}\"")
    public <T extends WebPage<T>> T login(String username, String password,
                                          Class<T> expectedClazz) {
        fillLoginForm(username, password);
        return CriteriaPageFactory.criteriaInitWebElements(webDriver,
                expectedClazz);
    }

    /**
     * Fill login form with correct USERNAME and PASSWORD
     *
     * @param username
     * @param password
     * @return
     */
    public void fillLoginForm(String username, String password) {
        LOG.info("Logging in with '" + username + "' username and '" + password
                + "' password.");
        emailAddressField.clear();
        type(emailAddressField, username);
        passwordField.clear();
        type(passwordField, password);
        signInButton.click();
    }

    /**
     * Check If Invalid Credentials Message Appear
     *
     * @return boolean
     */
    @Step(value = "Check if that invalid credentials message appear")
    public boolean checkIfInvalidCredentialsMessageAppear() {
        return invalidCredentials.isDisplayed();
    }

    @Step(value = "Check whether 'Sign in' button is displayed")
    public boolean isSignInButtonDisplayed() {
        return signInButton.isDisplayed();
    }

   // public MainPage authenticate(String user, String passws) {
       // signInButton.click();
        //new MainPage();
    //}
}
