package listeners;

import driver.Environment;
import driver.WebBrowser;
import driver.WebDriverProvider;
import driver.utils.TestConfigsGetter;
import logger.LogFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import io.qameta.allure.Step;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import io.qameta.allure.Attachment;


public class ScreenshotFailureTestListener extends TestListenerAdapter {

    private static final Logger LOG = LogFactory
            .getLogger(ScreenshotFailureTestListener.class);

    private final String TEST_LOG_PATH = "target/test-logs";
    private final String SCREENSHOT_FOLDER_NAME = "Screenshots for test failures";
    private final String SCREENSHOT_FILE_FORMAT = "png";

    /**
     * Enum that has different types of screenshots
     *
     * @author Taras.Lytvyn
     *
     */
    private enum ScreenShot {
        DesktopScreen, BrowserScreen;
    }

    public ScreenshotFailureTestListener() {
        LOG.info("Initialized Screenshot Failure Test Listener.");
        LOG.info("Screehshots that will be created on test failures will be web browser screenshots");
        LOG.info("Screehshots extension is: " + "." + SCREENSHOT_FILE_FORMAT);
        LOG.info("Test log path is: " + TEST_LOG_PATH);
    }

    @Step("Screenshots on Test Failure New:")
    @Override
    public void onTestFailure(ITestResult result) {
        createWebBrowserScreenShot(result);
    }

    /**
     * Create desktop screenshot after test fail
     *
     * @param result
     *            result
     * @return image byte array
     */
    @Attachment(value = "Desktop screenshot after test failed", type = "image/png")
    private byte[] createDesktopScreenShot(ITestResult result) {
        String currentTestName = TestListenerUtil.getTestName(result);
        BufferedImage image = null;
        try {
            LOG.debug("Writing out desktop screenshot on {} test failure",
                    currentTestName);
            image = new Robot().createScreenCapture(new Rectangle(Toolkit
                    .getDefaultToolkit().getScreenSize()));
            LOG.info("Captured desktop screenshot for test: " + currentTestName);
        } catch (HeadlessException e) {
            LOG.error("Problem with screen detection on test: "
                    + currentTestName, e);
        } catch (AWTException e) {
            LOG.error("AWT error occured for test: " + currentTestName, e);
        }
        saveScreenshotFileToLog(image, currentTestName,
                ScreenShot.DesktopScreen.name(),
                WebBrowser.getCreatedWebBrowser(), result);

        return TestListenerUtil.getByteArrayFromImage(image,
                SCREENSHOT_FILE_FORMAT);
    }

    /**
     * creates browser screenshot after test fail
     *
     * @param result
     *            result
     * @return image byte array
     */
    @Attachment(value = "Browser screenshot after test failed", type = "image/png")
    private byte[] createWebBrowserScreenShot(ITestResult result) {

        // take screenshot
        String currentTestName = TestListenerUtil.getTestName(result);
        BufferedImage image = null;
        LOG.debug("Writing out web browser screenshot on {} test failure",
                currentTestName);
        WebDriver driver = WebDriverProvider.getCreatedWebDriver();

        image = TestListenerUtil.takeWebBrowserScreenshot(driver);
        saveScreenshotFileToLog(image, currentTestName,
                ScreenShot.BrowserScreen.name(),
                WebBrowser.getCreatedWebBrowser(), result);

        return TestListenerUtil.getByteArrayFromImage(image,
                SCREENSHOT_FILE_FORMAT);
    }

    /**
     * save the screenshot file to test log
     *
     * @param image
     *            image
     * @param testName
     *            test name
     * @param screenshotType
     *            screenshot type
     */
    private void saveScreenshotFileToLog(BufferedImage image, String testName,
                                         String screenshotType, WebBrowser browser, ITestResult result) {
        String screenShotNamePath = screenShotNamePath(testName,
                screenshotType, browser, result);
        LOG.info("Screenshot path for test: " + testName + " is: "
                + screenShotNamePath);
        try {
            File screenShotFile = new File(screenShotNamePath);
            ImageIO.write(image, SCREENSHOT_FILE_FORMAT, screenShotFile);

            LOG.info("Successfully wrote " + screenshotType
                    + " screenshot to {}", screenShotFile.getAbsolutePath());
        } catch (IOException e) {
            LOG.error("Cannot write screenshot to path: ", screenShotNamePath);
        }
    }

    /**
     * get screenshot path where to store screenshot
     *
     * @param testName
     *            test name
     * @param screenShotType
     *            screenshot type
     *
     * @return text of full screenshot path
     */
    private String screenShotNamePath(String testName, String screenShotType,
                                      WebBrowser browser, ITestResult result) {
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        String imageFileName = testName + "_"
                + formater.format(Calendar.getInstance().getTime()) + "_at_"
                + screenShotType + "_on_" + browser.getBrowserName() + "."
                + SCREENSHOT_FILE_FORMAT;
        String filePath = getScreenShotDirectoryForSeparateTestSuite(result)
                + "/" + imageFileName;

        return filePath;
    }

    /**
     * get the directory where to store screenshot for test
     *
     * @return folder location
     */
    private String getScreenShotDirectoryForSeparateTestSuite(ITestResult result) {
        LOG.info("Test Class execution: "
                + result.getTestClass().getRealClass().getName());

        String currentTestLogPath = TEST_LOG_PATH + "/"
                + TestConfigsGetter.getTestParams().getLogTestFodler();

        String screenShotFolderName = currentTestLogPath + "/"
                + SCREENSHOT_FOLDER_NAME;

        File screenShotFolder = new File(screenShotFolderName);
        if (screenShotFolder.exists() && screenShotFolder.isDirectory()) {
            LOG.debug("Screenshot path for current test is: "
                    + screenShotFolderName);
            return screenShotFolderName;
        } else {
            LOG.debug("Creating test logs screenshot folder for current test for path: "
                    + screenShotFolderName);
            new File(screenShotFolderName).mkdirs();
            LOG.debug("Screenshot path for current test is: "
                    + screenShotFolderName);
            return screenShotFolderName;
        }
    }

}
