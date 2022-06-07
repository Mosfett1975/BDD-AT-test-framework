import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.FileDownloadMode.PROXY;

@RunWith(Cucumber.class)
@CucumberOptions(
        tags = "@test",
        features = "src/test/features",
        glue = {"at.framework"},
        plugin = {
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",
                "pretty",
                "summary"

        }
)

public class RunTest {
}

