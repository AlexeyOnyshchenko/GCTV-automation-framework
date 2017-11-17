package driver;


import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class WebDriverProvider implements IWebDriverProvidable {

    public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();

    public static WebDriver getCreatedWebDriver() {
        return checkNotNull(webDriver.get(), "WebDriver was not created yet");
    }

    protected void maximizeBrowserScreen(WebDriver driver) {
        if (WebBrowser.getCreatedWebBrowser().getBrowserName().equals("chrome")) {
            Point targetPosition = new Point(0, 0);
            driver.manage().window().setPosition(targetPosition);

            Dimension targetSize = new Dimension(1920, 1080);
            driver.manage().window().setSize(targetSize);
        } else {
			/*
			 * Toolkit toolkit = Toolkit.getDefaultToolkit();
			 *
			 * Dimension screenResolution = new Dimension((int) toolkit
			 * .getScreenSize().getWidth(), (int) toolkit.getScreenSize()
			 * .getHeight());
			 * driver.manage().window().setSize(screenResolution);
			 */
            driver.manage().window().maximize();
        }
    }

    /**
     * Set the implicit timeout for web driver test execution
     *
     * @param driver
     *            selenium webdriver
     */
    protected void setImplicitTimeOut(WebDriver driver) {
        driver.manage()
                .timeouts()
                .implicitlyWait(
                        Integer.valueOf(PropertyReader.loadProperty("timeout")),
                        TimeUnit.SECONDS);
    }

    protected void deleteCookies(WebDriver webDriver) {
        webDriver.manage().deleteAllCookies();
    }

}
