package at.framework.steps.browser;

import at.framework.basement.variables.Variables;
import io.cucumber.java.en.And;
import io.cucumber.java.ru.И;
import lombok.extern.log4j.Log4j2;

import static at.framework.basement.helperClasses.UserProperty.checkValueAndReturnString;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class BrowserSteps {

    @And ("^opened link \"([^\"]*)\"$")
    @И("^совершен переход по ссылке \"([^\"]*)\"$")
    public void openUrl(String address) {
        String url = checkValueAndReturnString(address);
        open(url);
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
