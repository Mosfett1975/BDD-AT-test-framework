package at.framework.basement.initDriver;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.BeforeAll;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverInit {

    @BeforeAll
    public static void setUp() {
        Configuration.remote = "http://localhost:4444/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.browserSize = "1900x1080x24";
        Configuration.browserPosition = "0x0";
        Configuration.timeout = 5000;
        Configuration.screenshots = true;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true)
                .savePageSource(true));

    }
}
