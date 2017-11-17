package base;

import driver.utils.TestConfigsGetter;
import logger.LogFactory;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by olga on 11/10/16.
 */
public class WebTestBase extends TestBase {

    private static final Logger LOG = LogFactory.getLogger(WebTestBase.class);

    protected ThreadLocal<SoftAssert> softAssert = new ThreadLocal<SoftAssert>();

    @BeforeClass(alwaysRun = true)
    public void setupWebTests(ITestContext context) {
        LOG.info("base.WebTestBase before class method");
        LOG.info("Before Class Suite");
    }

    @AfterClass(alwaysRun = true)
    public void tearDownWebTests(ITestContext context) {
        LOG.info("base.WebTestBase after class method");
        LOG.info("After Class Suite");
    }

    @BeforeMethod
    public void beforeWebTest(ITestContext context, Method method) {
        LOG.info("base.WebTestBase before @Test method - setting test Properties");
        String logTestFolder = getTestClassName() + getTestParams(context);

        TestConfigsGetter.setTestParams(logTestFolder);
    }

    @AfterMethod
    public void afterWebTest(ITestContext context, Method method) {
        LOG.info("base.WebTestBase after @Test method");
        String logTestFolder = getTestClassName() + getTestParams(context);
        TestConfigsGetter.getTestParams().setLogTestFodler(logTestFolder);
    }

    /**
     * gets the name of Test Class
     *
     * @return
     */

    private String getTestClassName() {
        return this.getClass().getSimpleName();
    }

    /**
     * get the test parameters enumerated with '_'
     *
     * @param context
     *            test context
     * @return parameters with '_'
     */
    private String getTestParams(ITestContext context) {
        StringBuilder testNameWithParams = new StringBuilder("");
        Map<String, String> testParams = context.getCurrentXmlTest()
                .getAllParameters();
        for (Map.Entry<String, String> entry : testParams.entrySet()) {
            // we don't need a url in param
            if (!entry.getValue().startsWith("http")) {
                testNameWithParams.append("_" + entry.getValue());
            }
        }
        return testNameWithParams.toString();
    }
}
