package testData;

import org.apache.commons.lang3.RandomStringUtils;

public enum RegistrationTestData {
    LOGIN(RandomStringUtils.randomAlphabetic(10)),
    FIRSTNAME(RandomStringUtils.randomAlphabetic(10)),
    LASTNAME(RandomStringUtils.randomAlphabetic(10)),
    PASSWORD(RandomStringUtils.random(20, "AREDerds222%$#@")),
    CONFIRMPASSWORD(RegistrationTestData.PASSWORD.value);


    private final String value;

    RegistrationTestData(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
