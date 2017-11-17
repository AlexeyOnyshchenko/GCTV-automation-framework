package driver;

import logger.LogFactory;
import org.slf4j.Logger;

import static com.google.common.base.Preconditions.checkNotNull;

public class WebDriverFactoryProducer {

    private static final Logger LOG = LogFactory
            .getLogger(WebDriverFactoryProducer.class);

    public static IWebDriverProvidable getWebDriverFactoryProvider() {
        String providerName = checkNotNull(System.getProperty("provider"),
                "provider property is not set");
        if (providerName.equalsIgnoreCase("local")) {
            LOG.info("setting local provider");
            return new LocalWebDriverProvider();
        } else
            throw new IllegalArgumentException("invalid 'provider' was set;");
    }
}
