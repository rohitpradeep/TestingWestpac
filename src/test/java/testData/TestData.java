package testData;

import org.apache.commons.lang3.RandomStringUtils;

public enum TestData {
    VALIDLOGIN {
        @Override
        public String userName() {
            return "rrnz";
        }

        @Override
        public String password() {
            return "Test99nz$";
        }
    },
    INVALIDLOGIN {
        @Override
        public String userName() {
            return RandomStringUtils.randomAlphabetic(10);
        }

        @Override
        public String password() {
            return RandomStringUtils.randomAlphabetic(10);
        }
    };


    public abstract String userName();

    public abstract String password();
}
