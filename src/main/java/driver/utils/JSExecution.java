package driver.utils;


import logger.LogFactory;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.google.common.base.Preconditions.checkNotNull;

public class JSExecution {
    private static final Logger LOG = LogFactory.getLogger(JSExecution.class);

    protected WebDriver driver;
    protected JavascriptExecutor js;

    public JSExecution(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
    }

    /**
     * execute the script with parameters
     *
     * @param expr
     *            js expression
     * @param arguments
     *            parameters
     * @return Object result of execution
     */
    public Object execScript(final String expr, Object... arguments) {
        return js.executeScript(expr, arguments);
    }

    /**
     * execute the async script with parameters
     *
     * @param expr
     *            js expression
     * @param arguments
     *            parameters
     * @return Object result of execution
     */
    public Object execAsyncScript(final String expr, Object... arguments) {
        return js.executeAsyncScript(expr, arguments);
    }

    /**
     * clicks on elements with JS executor
     *
     * @param element
     *            what WebElement to click
     */
    public void clickOnElement(WebElement element) {
        execScript("arguments[0].click();", element);
    }

    /**
     * clicks on elements with JS executor and click event
     *
     * @param element
     *            what WebElement to click
     */
    public void clickOnElementWithClickEvent(WebElement element) {
        execScript(clickSimulationScript()
                + "; simulate(arguments[0], \"click\")", element);
    }

    /**
     * Types text to WebElement with JS
     *
     * @param element
     *            what element to type text in
     * @param text
     *            what text to type
     */
    public void typeTextToTextBox(WebElement element, String text) {
        execScript("arguments[0].setAttribute('value','" + text + "');",
                element);
    }

    /**
     * scroll vertically the page/screen
     *
     * @param pixel
     *            how many pixels to scroll
     */
    public void scrollVertical(int pixel) {
        execScript("scroll(0," + pixel + ")");
    }

    /**
     * scroll vertically the page/screen up to header
     */
    public void scrollPageUp() {
        execScript("scroll(0,0)");
    }

    /**
     * scroll horizontally the page/screen
     *
     * @param pixel
     *            how many pixels to scroll
     */
    public void scrollHorizontal(int pixel) {
        execScript("scroll(" + pixel + ",0)");
    }

    /**
     * Wait for all ajax calls to be finished
     *
     * @param timeoutInSeconds
     *            limit timeout - how many time to wait
     */
    public void waitForAjax(int timeoutInSeconds) {
        if (checkJQueryDefined()) {
            LOG.info("Checking active ajax calls by calling jquery.active");
            for (int i = 0; i < timeoutInSeconds; i++) {
                Object numberOfAjaxConnections = execScript("return jQuery.active");
                // return should be a number
                if (numberOfAjaxConnections instanceof Long) {
                    Long n = (Long) numberOfAjaxConnections;
                    LOG.info("Number of active jquery ajax calls: " + n);
                    if (n.longValue() == 0L) {
                        break;
                    }
                }
                delay(1000);
            }
        } else {
            LOG.info("JQuery is undefined for page");
        }
    }

    /**
     * ExpectedCondition that is waiting until active ajax calls will be
     * finished with time limit Ex.: Can be used in Page load criteria
     *
     * @param timeout
     *            limit timeout to wait
     * @return ExpectedCondition
     */
    public ExpectedCondition<Boolean> waitForAjax(final long timeout) {
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                if (checkJQueryDefined()) {
                    final long startTime = System.currentTimeMillis();

                    while ((startTime + timeout) >= System.currentTimeMillis()) {
                        final Boolean scriptResult = (Boolean) execScript("return jQuery.active == 0");

                        if (scriptResult) {
                            return true;
                        }
                        delay(100);
                    }
                }
                return false;
            }
        };
    }

    /**
     * checks whether the jQuery is defined on Page
     *
     * @return
     */
    private boolean checkJQueryDefined() {
        String jqueryResult = (String) execScript("return window.jQuery");
        if (jqueryResult != null) {
            return true;
        } else
            return false;
    }

    /**
     * set delay
     *
     * @param amount
     *            delay in ms
     */
    private void delay(final long amount) {
        try {
            Thread.sleep(amount);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * touch the click event to the page with click script
     *
     * @return click event script
     */
    private String clickSimulationScript() {
        byte[] encoded = null;
        try {
            InputStream inputEncoded = this.getClass().getResourceAsStream(
                    "/firing-js-click-script.txt");
            encoded = IOUtils.toByteArray(inputEncoded);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(encoded, StandardCharsets.UTF_8);
    }

    public static class JavaScriptCookieCleanerLoader {

        private static final Logger LOG = LogFactory
                .getLogger(JavaScriptCookieCleanerLoader.class);

        public static String getCookieClearScript(String scriptName) {
            byte[] encoded = null;
            try {
                InputStream inputEncoded = JavaScriptCookieCleanerLoader.class
                        .getResourceAsStream("/cookies/" + scriptName + ".txt");
                encoded = IOUtils.toByteArray(inputEncoded);
            } catch (IOException e) {
                LOG.error("Script IO exception - check script files in resources");
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return new String(checkNotNull(encoded,
                    "encoded script from file is null"), StandardCharsets.UTF_8);
        }
    }
}
