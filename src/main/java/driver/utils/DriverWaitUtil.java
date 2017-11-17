package driver.utils;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.openqa.selenium.*;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by olga on 11/10/16.
 */
public class DriverWaitUtil {
    private WebDriver driver;
    private FluentWait<WebDriver> fluentWait;
    private int defaultTime = 60; // in seconds
    private int pollingTimeout;

    public DriverWaitUtil(WebDriver driver) {
        this.driver = driver;
        this.pollingTimeout = defaultTime;
        fluentWait = getDefaultWait(driver);
    }

    public DriverWaitUtil withTimeout(int timeOutInSec) {
        this.pollingTimeout = timeOutInSec;
        fluentWait = getDefaultWait(driver); // with updated timeout setting
        return this;
    }

    /**
     * @return a new instance
     */
    private FluentWait<WebDriver> getDefaultWait(WebDriver driver) {
        return new WebDriverWait(driver, defaultTime)
                .withTimeout(pollingTimeout, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    /**
     * Waits for an element to become visible
     *
     * @param locator
     *            locator
     * @return the element found
     */
    public WebElement forElement(final By locator) {
        return fluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }

            @Override
            public String toString() {
                return "presence of element located by: " + locator;
            }
        });
    }

    /**
     * Waits for an element to become visible
     *
     * @param locator
     *            locator
     * @return the elements found
     */
    public List<WebElement> forElements(final By locator) {
        return fluentWait.until(new Function<WebDriver, List<WebElement>>() {
            @Override
            public List<WebElement> apply(WebDriver driver) {
                return driver.findElements(locator);
            }

            @Override
            public String toString() {
                return "presence of element located by: " + locator;
            }
        });
    }

    /**
     * Wait until element is fixed on the screen(stop mooving)
     *
     * @param element
     *            web element
     * @return Web Element
     */
    public WebElement forElementToStoppedMoving(final WebElement element) {
        return fluentWait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                Point initialLocation = ((Locatable) element).getCoordinates()
                        .inViewPort();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // Do nothing
                }
                Point finalLocation = ((Locatable) element).getCoordinates()
                        .inViewPort();
                if (initialLocation.equals(finalLocation)) {
                    return element;
                } else {
                    return null;
                }
            }
        });
    }

    /**
     * Waits for a condition to become true
     *
     * @param expectedCondition
     *            expected condition
     * @return the result of the wait
     */

    public <V> V forElement(ExpectedCondition<V> expectedCondition) {
        return fluentWait.until((Function<WebDriver, V>) expectedCondition);
    }

    /**
     * Wait for the specified condition to be true
     *
     * @param isTrue
     */
    public void forCondition(Predicate<WebDriver> isTrue) {
        fluentWait.until(isTrue);
    }

    /**
     * Wait for the specified condition and returns the execution result once
     * ready
     *
     * @param function
     * @param <V>
     * @return
     */
    public <V> V forCondition(Function<? super WebDriver, V> function) {
        return fluentWait.until(function);
    }
}
