package page;

import driver.WebDriverProvider;
import driver.utils.DriverWaitUtil;
import logger.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.AbstractAnnotations;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class CustomDefaultElementLocator implements ElementLocator {

    private static final Logger LOG = LogFactory
            .getLogger(CustomDefaultElementLocator.class);

    private final int MAX_STALE_ELEMENT_RETRIES = 200;

    private final SearchContext searchContext;
    private final boolean shouldCache;
    private final By by;
    private WebElement cachedElement;
    private List<WebElement> cachedElementList;

    private DriverWaitUtil driverWaitUtil = new DriverWaitUtil(
            WebDriverProvider.getCreatedWebDriver());

    /**
     * Creates a new element locator.
     *
     * @param searchContext
     *            The context to use when finding the element
     * @param field
     *            The field on the Page Object that will hold the located value
     */
    public CustomDefaultElementLocator(SearchContext searchContext, Field field) {
        this(searchContext, new Annotations(field));
    }

    /**
     * Use this constructor in order to process custom annotaions.
     *
     * @param searchContext
     *            The context to use when finding the element
     * @param annotations
     *            AbstractAnnotations class implementation
     */
    public CustomDefaultElementLocator(SearchContext searchContext,
                                       AbstractAnnotations annotations) {
        this.searchContext = searchContext;
        this.shouldCache = annotations.isLookupCached();
        this.by = annotations.buildBy();
    }

    public WebElement findElement() {
        WebElement neededElement = null;
        boolean founded = false;
        int cycle = 1;
        while (!founded && cycle <= MAX_STALE_ELEMENT_RETRIES) {
            try {

                if (cachedElement != null && shouldCache) {
                    return cachedElement;
                }

                neededElement = searchContext.findElement(by);

                if (shouldCache) {
                    cachedElement = neededElement;
                }
                founded = true;
                break;
            } catch (StaleElementReferenceException e) {
                LOG.error("element is not attached to the DOM, performing search once again"
                        + e.getMessage());
                driverWaitUtil.forCondition(ExpectedConditions
                        .not(ExpectedConditions.stalenessOf(neededElement)));
                driverWaitUtil.forCondition(ExpectedConditions
                        .visibilityOf(neededElement));
            }
            cycle++;
        }

        return checkNotNull(neededElement, by + " element wasn't founded");
    }

    public List<WebElement> findElements() {
        List<WebElement> neededElements = null;
        boolean founded = false;
        int cycle = 1;
        while (!founded && cycle <= MAX_STALE_ELEMENT_RETRIES) {
            try {
                if (cachedElementList != null && shouldCache) {
                    return cachedElementList;
                }

                neededElements = searchContext.findElements(by);

                if (shouldCache) {
                    cachedElementList = neededElements;
                }
                founded = true;
                break;
            } catch (StaleElementReferenceException e) {
                LOG.error("element is not attached to the DOM, performing search once again"
                        + e.getMessage());

                for (WebElement element : neededElements) {
                    driverWaitUtil.forCondition(ExpectedConditions
                            .not(ExpectedConditions.stalenessOf(element)));
                }

            }
            cycle++;
        }

        return checkNotNull(neededElements, by + " elements were not found");
    }

}
