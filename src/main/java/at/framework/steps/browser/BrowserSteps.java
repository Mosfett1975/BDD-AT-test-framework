package at.framework.steps.browser;

import at.framework.basement.variables.Variables;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import lombok.extern.log4j.Log4j2;

import static at.framework.basement.helperClasses.UserProperty.checkValueAndReturnString;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

@Log4j2
public class BrowserSteps {


    @И("^совершен переход по ссылке \"([^\"]*)\"$")
    public void openUrl(String address) {
        String url = checkValueAndReturnString(address);
        open(url);
    }

//    @Когда("^совершен переход по ссылке из переменной \"([^\"]*)\"$")
    @И("^совершен переход по ссылке из переменной \"([^\"]*)\"$")
    public void openUrlVar(String var) {
        String url = Variables.getVar(var);
        open(url);
    }

    @И("^браузер раскрыт на весь экран$")
    public void fullScreen() {
        getWebDriver().manage().window().fullscreen();
    }

    @И("^окно браузера максимизировано$")
    public void maximizedScreen() {
        getWebDriver().manage().window().maximize();
    }
}
