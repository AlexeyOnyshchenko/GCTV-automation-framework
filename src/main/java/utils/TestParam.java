package utils;

import logger.LogFactory;
import org.slf4j.Logger;


public class TestParam {

    private static final Logger LOG = LogFactory.getLogger(TestParam.class);

    private String logTestFodler;

    public TestParam(String logTestFodler) {
        super();
        this.logTestFodler = logTestFodler;

        LOG.info("Logging test folder: " + this.logTestFodler);
    }

    public String getLogTestFodler() {
        return logTestFodler;
    }

    public void setLogTestFodler(String logTestFodler) {
        this.logTestFodler = logTestFodler;
    }
}