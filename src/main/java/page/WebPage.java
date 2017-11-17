package page;

import com.google.common.base.Predicate;
import logger.LogFactory;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import io.qameta.allure.Step;

import java.util.Set;

/**
 * Created by olga on 11/10/16.
 */
public abstract class WebPage<T extends WebPage<T>> extends Page<T> {

    private static final Logger LOG = LogFactory.getLogger(WebPage.class);
    public static String beforeWindowHandle = "";
    public static String currentWindowHandle = "";

    public WebPage(WebDriver webDriver) {
        super(webDriver);
    }

    /**
     * Navigates to url and return Page Object from navigation url
     *
     * @param url
     *            navigation url
     * @param pageClass
     *            Page Object class
     * @return Page Object instance after navigation
     */
    @Step(value = "Navigate to page: \"{0}\"")
    public <V extends WebPage<V>> V navigateToPageUrl(String url, Class<V> pageClass) {

        if (url.endsWith("/")) {
            url = url.substring(0, url.length() - 1);
        }
        LOG.info("Navigating to: " + url);
        webDriver.get(url);
        return CriteriaPageFactory.criteriaInitWebElements(webDriver, pageClass);
    }

    /**
     * Refreshing current Page Object (like F5 in browser)
     *
     * @return current Page Object instance
     */
    @Step(value = "Refreshing current page")
    public T pageRefresh() {
        if (pageType == null) {
            throw new RuntimeException("pageType is null. Can't use this method unless Page is initialized correctly");
        }
        webDriver.navigate().refresh();
        return CriteriaPageFactory.criteriaInitWebElements(webDriver, pageType);
    }

    /**
     * gets web page title
     *
     * @return title of current web page
     */
    public String getPageTitle() {
        return webDriver.getTitle();
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    /**
     * gets web page title
     *
     * @return URL of current web page
     */
    @Step(value = "Check whether current url contains string: \"{0}\"")
    public boolean urlContainsSubstring(String substring) {
        return webDriver.getCurrentUrl().contains(substring);
    }

    @Step(value = "switch to newly opened window")
    public <V extends WebPage<V>> V switchToWindow(Class<V> clazz) {
        /*driverWaitUtil.forCondition(new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver o) {
                return getWindowHadles().size() > 1;
            }
        });*/
        beforeWindowHandle = webDriver.getWindowHandle();
        for (String winHandle : getWindowHadles()) {
            webDriver.switchTo().window(winHandle);
            currentWindowHandle = winHandle;
        }
        return CriteriaPageFactory.criteriaInitWebElements(webDriver, clazz);
    }

    @Step(value = "switch to previous opened window if second was closed automatically")
    public <V extends WebPage<V>> V switchBackToWindow(Class<V> clazz) {
        /*driverWaitUtil.forCondition(new Predicate<WebDriver>() {
            @Override
            public boolean apply(WebDriver o) {
                return getWindowHadles().size() == 1;
            }
        });*/
        beforeWindowHandle = webDriver.getWindowHandle();
        for (String winHandle : getWindowHadles()) {
            webDriver.switchTo().window(winHandle);
            currentWindowHandle = winHandle;
        }
        return CriteriaPageFactory.criteriaInitWebElements(webDriver, clazz);
    }

    @Step(value = "Close opened window")
    public <V extends WebPage<V>> V closeCurrentOpenedWindow(Class<V> clazz) {
        if (getWindowHadles().size() > 1) {
            webDriver.switchTo().window(currentWindowHandle);
            webDriver.close();
        }
        webDriver.switchTo().window(beforeWindowHandle);
        return CriteriaPageFactory.criteriaInitWebElements(webDriver, clazz);
    }

    public Set<String> getWindowHadles() {
        return webDriver.getWindowHandles();
    }

    @Step(value = "Navigate back")
    public <V extends WebPage<V>> V navigateBack(Class<V> clazz) {
        webDriver.navigate().back();
        return CriteriaPageFactory.criteriaInitWebElements(webDriver, clazz);
    }

    public void type(WebElement element, String text) {
        for (int i = 0; i < text.length(); i++) {
            element.sendKeys(String.valueOf(text.charAt(i)));
        }
    }
}
