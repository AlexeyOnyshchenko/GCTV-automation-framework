package driver.utils;


import utils.TestParam;

import static com.google.common.base.Preconditions.checkNotNull;

public class TestConfigsGetter {

    private static ThreadLocal<TestParam> testParams = new ThreadLocal<TestParam>();

    public static void setTestParams(String logTestFolder) {
        testParams.set(new TestParam(logTestFolder));
    }

    public static TestParam getTestParams() {
        return checkNotNull(testParams.get(), "");
    }
}
