package driver;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkNotNull;


public class WebBrowser {

    private String url;
    private String browserName;

    private static ThreadLocal<WebBrowser> webBrowser = new ThreadLocal<WebBrowser>();

    private WebBrowser() {

    }

    private WebBrowser(String browserName, String url) {
        this.browserName = browserName;
        this.url = url;
    }

    /**
     *
     * @return first set url of the web site
     */
    public String getUrl() {
        return url;
    }

    /**
     *
     * @return first set url of the web site
     */
    public String getBrowserName() {
        return browserName;
    }

    /**
     * singleton that get the instance of Web Browse object
     *
     * @param browserName
     *            browser name (Chrome, FF, etc)
     * @return Web Browser instance object
     */

    public static WebBrowser initBrowser(String browserName, String url) {
        // if (webBrowser.get() == null) {
        webBrowser.set(new WebBrowser(browserName, url));
        // }
        return webBrowser.get();
    }

    /**
     * resets the Browser object
     */
	/*
	 * public void resetBrowser() { webBrowser.set(null); }
	 */

    /**
     * @return pre-created Web Browser object
     */

    public static WebBrowser getCreatedWebBrowser() {
        return checkNotNull(webBrowser.get(),
                "Web browser is not set and it is null");
    }

    public void resetWebBrowser() {
        webBrowser.set(null);
    }

    /**
     * Enum that represents
     *
     */
    public enum BrowserType {
        FIREFOX("firefox"), CHROME("chrome"), IExplorer("iexplorer"), CHROME_ANDROID("android"), SAFARI_IOS(
                "ios");

        private String browserKey;
        private static Map<String, BrowserType> browserMap = new HashMap<String, BrowserType>();

        static {
            for (BrowserType bt : BrowserType.values()) {
                browserMap.put(bt.key(), bt);
            }
        }

        private BrowserType(String key) {
            browserKey = key;
        }

        private String key() {
            return this.browserKey;
        }

        public static BrowserType get(String key) {
            if (browserMap.containsKey(key)) {
                return browserMap.get(key);
            }
            return CHROME;
        }

    }

}
