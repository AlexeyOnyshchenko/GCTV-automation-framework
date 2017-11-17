package testdata;

import java.util.concurrent.ThreadLocalRandom;


public class Constants {
    public final class TestData {

        public static final String USER_EMAIL = "admin@lifechild.tv";

        public static final String USER_PASSWORD = "password";

        public static final String USER_EMAIL_2 = "al.phobos90@gmail.com";

        public static final String USER_PASSWORD_2 = "password";

        public static final String WRONG_PASSWORD = "pass";

        public static final String WRONG_USER_EMAIL = "lala@co.com";

        public static final String DEFAULT_FILTER_PERIOD = "24";

    }


    public final static class RandomTestData {

        public static int randomInt(int min, int max) {
            return ThreadLocalRandom.current().nextInt(min, max + 1);
        }

        public static final String RANDOM_TITLE = "TITLE " + System.currentTimeMillis();

        public static final String RANDOM_DESCRIPTION = "DESCRIPTION " + System.currentTimeMillis();

    }
}

