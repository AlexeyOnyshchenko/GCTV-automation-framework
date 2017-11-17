package driver.utils;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import logger.LogFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;


public class WaitUtil<T> {

    private static final Logger LOG = LogFactory.getLogger(WaitUtil.class);

    private T condition;
    private FluentWait<T> wait;
    private final int DEFAULT_TIME = 60; // in seconds
    private int timeout;

    public WaitUtil(T condition) {
        this.condition = condition;
        this.timeout = DEFAULT_TIME;
        this.wait = getDefaultFluentWait();
        LOG.info("Initialized Wait Utility");
    }

    public WaitUtil<T> withTimeout(int timeOutInSec) {
        this.timeout = timeOutInSec;
        wait = getDefaultFluentWait(); // with updated timeout setting
        return this;
    }

    /**
     * creates new instance
     *
     * @return new instance of Fluent Wait
     */
    private FluentWait<T> getDefaultFluentWait() {
        return new FluentWait<T>(condition)
                .withTimeout(timeout, TimeUnit.SECONDS)
                .pollingEvery(1, TimeUnit.SECONDS)
                .ignoring(InterruptedException.class);
    }

    /**
     * Wait for condition with Guava Predicate
     *
     * @param predicate
     */
    public void forCondition(Predicate<T> predicate) {
        wait.until(predicate);
    }

    /**
     * Wait for condition with Guava Function
     *
     */
    public <V> V forCondition(Function<T, V> function) {
        return wait.until(function);
    }

}
