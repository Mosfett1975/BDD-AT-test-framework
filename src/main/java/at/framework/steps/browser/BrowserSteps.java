package at.framework.steps.browser;

import at.framework.basement.variables.Variables;
import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.And;
import io.cucumber.java.ru.И;
import lombok.extern.log4j.Log4j2;

import static at.framework.basement.helperClasses.UserProperty.checkValueAndReturnString;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class BrowserSteps {

    @And ("^opened link \"([^\"]*)\"$")
    @И("^совершен переход по ссылке \"([^\"]*)\"$")
    public void openUrl(String address) {
        String url = checkValueAndReturnString(address);
        open(url);
    }

    @And ("^URL current page equals \"([^\"]*)\"$")
    @И("^URL текущей страницы равен \"([^\"]*)\"$")
    public void checkForCurrentURL(String urlForCheck) {
        urlForCheck = checkValueAndReturnString(urlForCheck);
        String currentUrl = "";
        int sleepTime = 100;
        int time = 0;
        while (time < Configuration.timeout) {
            currentUrl = url();
            if (currentUrl.toLowerCase().equals(urlForCheck.toLowerCase())) {
                return;
            }
            sleep(sleepTime);
            time += sleepTime;
        }
        assertThat("URL does not equals to the specified in parameter ", currentUrl, is(urlForCheck));
    }

    @And("^opened link from variable \"([^\"]*)\"$")
    @И("^совершен переход по ссылке из переменной \"([^\"]*)\"$")
    public void openUrlVar(String var) {
        String url = Variables.getVar(var);
        open(url);
    }

    @And ("^the browser is opened to full screen$")
    @И("^браузер раскрыт на весь экран$")
    public void setFullScreen() {
        getWebDriver().manage().window().fullscreen();
    }

    @And("^the browser window is maximized$")
    @И("^окно браузера максимизировано$")
    public void maximizedScreen() {
        getWebDriver().manage().window().maximize();
    }
}
