package at.framework.basement.initDriver;

import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.BeforeClass;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.selenide.AllureSelenide;

import static com.codeborne.selenide.FileDownloadMode.PROXY;

public class DriverInit {

    @BeforeClass
    public static void setUp() {
        Configuration.proxyEnabled = true;
        Configuration.proxyHost = "0.0.0.0";
        Configuration.fileDownload = PROXY;
        Configuration.timeout = 5000;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true)
                .savePageSource(true));
    }
}
