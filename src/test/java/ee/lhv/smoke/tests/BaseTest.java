package ee.lhv.smoke.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:/BIT/chromedriver.exe");
        Configuration.timeout = 20000;
        Configuration.browser = "chrome";
        Configuration.headless = true;
    }

}