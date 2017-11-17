package page;

import driver.WebDriverProvider;
import driver.utils.DriverWaitUtil;
import logger.LogFactory;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by olga on 11/10/16.
 */
public class CustomLocatingElementHandler implements InvocationHandler {

    private static final Logger LOG = LogFactory
            .getLogger(CustomLocatingElementHandler.class);

    private final int MAX_STALE_ELEMENT_RETRIES = 200;
    private final ElementLocator locator;
    private DriverWaitUtil driverWaitUtil = new DriverWaitUtil(
            WebDriverProvider.getCreatedWebDriver());

    public CustomLocatingElementHandler(ElementLocator locator) {
        this.locator = locator;
    }

    public Object invoke(Object object, Method method, Object[] objects)
            throws Throwable {

        WebElement element = researchElement();

        if ("getWrappedElement".equals(method.getName())) {
            return element;
        }

        try {
            // just to call simple getSize() method to check element stale
            // exception
            element.getSize();
        } catch (StaleElementReferenceException e1) {
            LOG.error("Element is not attached to DOM" + element.toString());
            element = researchElement();
        }

        // continue with research element and invoke method on it
        try {
            element = researchElement();
            Object invoke = method.invoke(element, objects);
            return invoke;

        } catch (StaleElementReferenceException e2) {
            LOG.error("Element is not attached to DOM: " + element.toString());
            element = researchElement();
            return method.invoke(element, objects);

            // Throwable
        } catch (Throwable e1) {
            // LOG.error("Element is not attached to DOM: " +
            // element.toString());
            LOG.error("Exception error: " + e1.getMessage());
            element = researchElement();
            return method.invoke(element, objects);
        }
    }

    private WebElement researchElement() {
        WebElement element = null;
        boolean founded = false;
        int cycle = 1;
        while (!founded && cycle <= MAX_STALE_ELEMENT_RETRIES) {
            try {
                Thread.sleep(200);
                element = locator.findElement();
                founded = true;
                break;
            } catch (StaleElementReferenceException e) {
                LOG.error("Element is not attached to the DOM, performing search once again"
                        + e.getMessage());
                driverWaitUtil.forCondition(ExpectedConditions
                        .not(ExpectedConditions.stalenessOf(locator
                                .findElement())));
                driverWaitUtil.forCondition(ExpectedConditions
                        .visibilityOf(locator.findElement()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            cycle++;
        }
        return checkNotNull(element, " element wasn't founded");
    }
}
