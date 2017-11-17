package driver;



import io.github.bonigarcia.wdm.WebDriverManager;
import logger.LogFactory;
import driver.WebBrowser.BrowserType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;

import java.io.IOException;

import static com.google.common.base.Preconditions.checkNotNull;

public class LocalWebDriverProvider extends WebDriverProvider{
    private static final Logger LOG = LogFactory
            .getLogger(LocalWebDriverProvider.class);

    private static BrowserType browserType;

    /**
     * Created by olga on 11/10/16.
     */


    @Override
    public WebDriver initBrowserDriver(WebBrowser webBrowser) {
        if (webDriver.get() == null) {
            DesiredCapabilities desiredCaps = new DesiredCapabilities();
            browserType = BrowserType.get(webBrowser.getBrowserName());


            if (browserType.equals(BrowserType.CHROME)) {
                //setChromeDriver(getDriverBinaryPath());
                WebDriverManager.getInstance(ChromeDriver.class).setup();
                //setChromeOptions(desiredCaps);
                webDriver.set(new ChromeDriver());
                LOG.info("Chrome was started");
            } else if (browserType.equals(BrowserType.FIREFOX)) {
                setFFProfile(desiredCaps);
                webDriver.set(new FirefoxDriver(desiredCaps));
                LOG.info("Firefox was started");
            } else if (browserType.equals(BrowserType.IExplorer)) {
                setIEDriver(getDriverBinaryPath());
                setIECapabilities(desiredCaps);
                webDriver.set(new InternetExplorerDriver(desiredCaps));
                LOG.info("IE was started");
            }
            else {
                LOG.error("Invalid browsername was passed for local execution");
                throw new IllegalArgumentException(
                        "Invalid browser property set in configuration test file");
            }

            deleteCookies(webDriver.get());
            maximizeBrowserScreen(webDriver.get());

            setImplicitTimeOut(webDriver.get());
        }
        return webDriver.get();
    }


    @Override
    public void killDriverInstance() {
        LOG.info("After Test WebDriver killing driver instance");
        if (getCreatedWebDriver() != null) {
            LOG.info("Clearing cookies");
            deleteCookies(getCreatedWebDriver());
            LOG.info("Closing browser window");
            getCreatedWebDriver().quit();
            webDriver.set(null);

            if (checkNotNull(browserType).equals(BrowserType.CHROME)
                    && getExecutionOsName().startsWith("mac")) {
                killChromeDriver();
            }
            WebBrowser.getCreatedWebBrowser().resetWebBrowser();
        }
    }

    /**
     * Sets the chrome driver path for specific OS.
     *
     * @throws Exception
     *             the exception if current OS does not support Chrome
     */
    private void setChromeDriver(StringBuffer driversBinaryPath) {
        if (getExecutionOsName().startsWith("win")) {
            driversBinaryPath.append("chrome-win/chromedriver.exe");
        } else if (getExecutionOsName().startsWith("lin")) {
            driversBinaryPath.append("chrome-lin/chromedriver");
        } else if (getExecutionOsName().startsWith("mac")) {
            driversBinaryPath.append("chrome-mac/chromedriver");
        } else
            throw new IllegalArgumentException(
                    "Your OS is invalid for webdriver tests");

        System.setProperty("webdriver.chrome.driver",
                driversBinaryPath.toString());
        // System.setProperty("webdriver.chrome.driver",
        // "src/main/resources/drivers/chrome-mac/chromedriver");
    }

    private void setIEDriver(StringBuffer driversBinaryPath) {
        if (getExecutionOsName().startsWith("win")) {
            if (isExecutionOn64XWinOS()) {
                driversBinaryPath.append("ie-win-64/IEDriverServer.exe");
            } else {
                driversBinaryPath.append("ie-win-32/IEDriverServer.exe");
            }
        } else throw new IllegalArgumentException("Your OS is invalid for webdriver tests");

        System.setProperty("webdriver.ie.driver",
                driversBinaryPath.toString());
        // System.setProperty("webdriver.chrome.driver",
        // "src/main/resources/drivers/chrome-mac/chromedriver");
    }

    /**
     * Set chrome options to web driver
     *
     * @param desiredCaps
     *            web driver desired capabilites
     */
    private void setChromeOptions(DesiredCapabilities desiredCaps) {
        ChromeOptions options = new ChromeOptions();
        // options.addArguments("test-type");
        options.addArguments("--test-type");
        options.addArguments("--disable-device-discovery-notifications");
        // options.addArguments("--user-data-dir=src/test/resources/drivers/chrome_profile");
        options.addArguments("--disable-desktop-notifications");
        desiredCaps.setCapability(ChromeOptions.CAPABILITY, options);
    }

    /**
     * set firefox profile to web driver
     *
     * @param desiredCaps
     *            desired capabilities
     */
    private void setFFProfile(DesiredCapabilities desiredCaps) {
        FirefoxProfile fp = new FirefoxProfile();
        desiredCaps.setCapability(FirefoxDriver.PROFILE, fp);
    }

    private void setIECapabilities(DesiredCapabilities desiredCaps) {
        desiredCaps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        desiredCaps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, true);
        desiredCaps.setCapability("ignoreZoomSetting", true);
        desiredCaps.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        LOG.info("IE capabilities were set");
    }

    /**
     * @return current OS name
     */
    private String getExecutionOsName() {
        return System.getProperty("os.name").toLowerCase();
    }

    private boolean isExecutionOn64XWinOS() {
        return System.getProperty("os.arch").toLowerCase().contains("64");
    }

    private StringBuffer getDriverBinaryPath() {
        return new StringBuffer("src/main/resources/drivers/");
    }

    private void killChromeDriver() {
        Runtime r = Runtime.getRuntime();
        try {
            Process p = r.exec("ps aux | grep \"chromedriver\"");
            p.destroy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
