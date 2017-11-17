package page;

import driver.utils.DriverWaitUtil;
import driver.utils.JSExecution;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static com.sun.jna.Platform.isAndroid;


public abstract class Page<T extends Page<T>> implements IPageLoadedCriteria {

    protected Class<T> pageType;
    protected WebDriver webDriver;
    protected JSExecution webDriverJSExecutor;
    protected DriverWaitUtil driverWaitUtil;

	/*
	 * sets the pageType the class value of current Page Object It is used in
	 * other functionality
	 */

    @SuppressWarnings("unchecked")
    protected Page(WebDriver webDriver) {
        Type genericSuperClass = getClass().getGenericSuperclass();
        if (genericSuperClass instanceof ParameterizedType) {
            // we want to make sure that generic were specified before trying
            // to get that information
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperClass;
            Type type = parameterizedType.getActualTypeArguments()[0];
            pageType = (Class<T>) (type instanceof TypeVariable ? Object.class
                    : type);
        }
        this.webDriver = webDriver;
        this.webDriverJSExecutor = new JSExecution(webDriver);
        this.driverWaitUtil = new DriverWaitUtil(webDriver);
    }

    /**
     * Get the web driver
     *
     * @return web driver
     */
    public WebDriver getWebDriver() {
        return webDriver;
    }

    public Class<T> getPageType() {
        return pageType;
    }

    public void setPageType(Class<T> pageType) {
        this.pageType = pageType;
    }

    /**
     * Get Web Driver Wait Utility
     *
     * @return the web driver waiter utility
     */
    public DriverWaitUtil getDriverWaitUtil() {
        return driverWaitUtil;
    }

    /**
     * Get JS executor
     *
     * @return
     */
    public JSExecution getWebDriverJSExecutor() {
        return webDriverJSExecutor;
    }

    public boolean isElementDisplayed(WebElement element, int timeout) {
        long end = getCurrentGMT6Time() + (long)timeout;
        boolean nseLogMessage = true;

        while(getCurrentGMT6Time() < end) {
            try {
                if(element == null) {
                    this.suspend(500);
                } else if(element != null) {
                    break;
                }
            } catch (NoSuchElementException var8) {
                if(nseLogMessage) {
                    nseLogMessage = false;
                }
            }
        }

        return element == null?false:element.isDisplayed();
    }

    public static long getCurrentGMT6Time() {
        return getDateInTimeZone(new Date(), "America/Los_Angeles").getTime();
    }

    public static Date getDateInTimeZone(Date currentDate, String timeZoneId) {
        GregorianCalendar mbCal = new GregorianCalendar(TimeZone.getTimeZone(timeZoneId));
        mbCal.setTimeInMillis(currentDate.getTime());
        Calendar cal = Calendar.getInstance();
        cal.set(1, mbCal.get(1));
        cal.set(2, mbCal.get(2));
        cal.set(5, mbCal.get(5));
        cal.set(11, mbCal.get(11));
        cal.set(12, mbCal.get(12));
        cal.set(13, mbCal.get(13));
        cal.set(14, mbCal.get(14));
        return cal.getTime();
    }

    public void suspend(int millis) {

        try {
            Thread.sleep((long)millis);
        } catch (InterruptedException var3) {
            var3.printStackTrace();
        }

    }

    public String returnVideoPath(String fileName){
        //Get the working Directory
        String path = System.getProperty("user.dir");

        //Append the path within the framework for files in the test-file-upload-data folder and the file name
        String resourcesPath = File.separator + "src" + File.separator + "main" + File.separator + "resources";
        path = path + resourcesPath + File.separator + "test-file-upload-data" + File.separator  + fileName;

        //Get the absolute path of the file
        File file = new File(path);
        String absolutePath = file.getAbsolutePath();
        return absolutePath;
    }

    public void typeText(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);
    }

}
