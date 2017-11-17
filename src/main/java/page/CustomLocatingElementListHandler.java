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
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;


public class CustomLocatingElementListHandler implements InvocationHandler {

    private static final Logger LOG = LogFactory
            .getLogger(CustomLocatingElementListHandler.class);

    private DriverWaitUtil driverWaitUtil = new DriverWaitUtil(
            WebDriverProvider.getCreatedWebDriver());

    private final ElementLocator locator;

    public CustomLocatingElementListHandler(ElementLocator locator) {
        this.locator = locator;
    }

    public Object invoke(Object object, Method method, Object[] objects)
            throws Throwable {
        List<WebElement> elements = null;
        try {
            elements = locator.findElements();

            try {
                return method.invoke(elements, objects);
            } catch (InvocationTargetException e) {
                // Unwrap the underlying exception
                throw e.getCause();
            }
        } catch (StaleElementReferenceException e) {
            LOG.error("some element from element's list is not attached to DOM, performing search once again"
                    + e.getMessage());
            for (WebElement element : elements) {
                driverWaitUtil.forCondition(ExpectedConditions
                        .not(ExpectedConditions.stalenessOf(element)));
            }
            return method.invoke(elements, objects);
        }
    }
}
