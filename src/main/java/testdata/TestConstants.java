package testdata;


import java.util.concurrent.ThreadLocalRandom;

public class TestConstants {
    /**
     * Test Data for tests
     */
    public final class TestData {

        public static final String USER_EMAIL = "admin@lifechild.tv";

        public static final String USER_PASSWORD = "password";

        public static final String USER_EMAIL_2 = "blagko.olga@gmail.com";

        public static final String USER_PASSWORD_2 = "password";

        public static final String WRONG_PASSWORD = "lala@co.com";

        public static final String WRONG_USER_EMAIL = "pass";

        public static final String DEFAULT_FILTER_PERIOD = "24";

    }

    /**
     * Random Data for tests
     */
    public final static class RandomTestData {

        public static int randomInt(int min, int max) {
            return ThreadLocalRandom.current().nextInt(min, max + 1);
        }

        public static final String RANDOM_TITLE = "TITLE " + System.currentTimeMillis();

        public static final String RANDOM_DESCRIPTION = "DESCRIPTION " + System.currentTimeMillis();

        public static final String RANDOM_ORDER = "2";

    }
}
