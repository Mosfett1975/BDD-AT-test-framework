package at.framework.basement.initDriver;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.BeforeAll;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;

public class DriverInit {

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1900x1080x24";
        Configuration.browserPosition = "0x0";
        Configuration.timeout = 5000;
        Configuration.screenshots = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true)
                .savePageSource(true));

    }
}
