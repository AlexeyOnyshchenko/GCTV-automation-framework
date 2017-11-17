package driver;


import logger.LogFactory;
import org.slf4j.Logger;

public class Environment {
    private static final Logger LOG = LogFactory.getLogger(Environment.class);

    public static boolean isMobileExecution() {
        boolean isMobile = WebBrowser.getCreatedWebBrowser().getBrowserName()
                .equals("ios")
                || WebBrowser.getCreatedWebBrowser().getBrowserName()
                .equals("android");
        LOG.info("mobile execution: " + isMobile);
        return isMobile;
    }

    public static boolean isWebExecution() {
        boolean isWeb = !isMobileExecution();
        LOG.info("web execution: " + isWeb);
        return isWeb;
    }
}
