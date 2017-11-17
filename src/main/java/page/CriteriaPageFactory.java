package page;

import driver.utils.DriverWaitUtil;
import driver.utils.JSExecution;
import logger.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static com.google.common.base.Preconditions.checkNotNull;


public class CriteriaPageFactory extends PageFactory {

    private static final Logger LOG = LogFactory
            .getLogger(CriteriaPageFactory.class);

    private static ThreadLocal<DriverWaitUtil> driverWaitUtil = new ThreadLocal<DriverWaitUtil>();

    /**
     * should be used instead of initElements when we get the Page Factory
     * approach with Page Objects in Web tests
     *
     * @param driver
     *            web driver
     * @param pageClassToProxy
     *            Page Object class
     * @return instance of Page Object we pass to class parameter
     */
    public static <T extends WebPage<T>> T criteriaInitWebElements(
            WebDriver driver, Class<T> pageClassToProxy) {
        T page = criteriaInitElements(driver, pageClassToProxy);

        return page;
    }

    /**
     * Initialize the Page Object after waiting for loaded criteria to be pass
     *
     * @param driver
     *            web driver
     * @param pageClassToProxy
     *            Page Object class
     * @return Page Object instance
     */
    private static <T extends IPageLoadedCriteria> T criteriaInitElements(
            WebDriver driver, Class<T> pageClassToProxy) {
        T page = instantiatePage(driver, pageClassToProxy);
        initElements(driver, page);
        waitForNextPageToLoad(driver);
        waitForPageObjectCriteria(driver, page);

        return checkNotNull(page, "Page was not instantiated");
    }

    public static void initElements(WebDriver driver, Object page) {
        final WebDriver driverRef = driver;
        initElements(new CustomDefaultElementLocatorFactory(driverRef), page);
    }

    public static void initElements(ElementLocatorFactory factory, Object page) {
        final ElementLocatorFactory factoryRef = factory;
        initElements(new CustomDefaultFieldDecorator(factoryRef), page);
    }

    /**
     * Page Factory duplicate method because it is private in Page Factory
     *
     * @param driver
     * @param pageClassToProxy
     * @return
     */
    private static <T> T instantiatePage(WebDriver driver,
                                         Class<T> pageClassToProxy) {
        try {
            try {
                Constructor<T> constructor = pageClassToProxy
                        .getConstructor(WebDriver.class);
                return constructor.newInstance(driver);
            } catch (NoSuchMethodException e) {
                return pageClassToProxy.newInstance();
            }
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Wait until Page is loaded in DOM
     *
     * @param driver
     *            web driver
     */
    public static void waitForNextPageToLoad(WebDriver driver) {
        driverWaitUtil.set(new DriverWaitUtil(driver));
        driverWaitUtil.get().forCondition(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                JSExecution jsExecutor = new JSExecution(driver);
                return jsExecutor.execScript("return document.readyState")
                        .equals("complete");
            }
        });
    }

    /**
     * Wait for page is loaded by it's loaded criteria
     *
     * @param driver
     *            web driver
     * @param page
     *            Page Object
     */
    private static <T extends IPageLoadedCriteria> void waitForPageObjectCriteria(
            WebDriver driver, T page) {
        LOG.info("Waiting for page to be loaded");
        driverWaitUtil.set(new DriverWaitUtil(driver));
        driverWaitUtil.get().forCondition(page.loadedCriteria());
    }
}
