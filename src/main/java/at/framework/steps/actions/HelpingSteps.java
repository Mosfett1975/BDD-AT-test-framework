package at.framework.steps.actions;

import at.framework.basement.variables.Variables;
import io.cucumber.java.en.And;
import io.cucumber.java.ru.И;
import lombok.extern.log4j.Log4j2;

import static at.framework.basement.helperClasses.UserProperty.checkValueAndReturnString;
import static com.codeborne.selenide.Selenide.sleep;


@Log4j2
public class HelpingSteps {

    @And ("^delayed by (\\d+) (?:second|seconds)")
    @И("^выполнена задержка (\\d+) (?:секунд|секунды)")
    public void delayInSeconds(long second) {
        sleep(1000 * second);
    }

    @And ("^variable \"([^\"]*)\" set equal to \"(.*)\"$")
    @И("^установлено значение переменной \"([^\"]*)\" равным \"(.*)\"$")
    public void setVariable(String variableName, String value) {
        value = checkValueAndReturnString(value);
        Variables.setVar(variableName, value);
    }
}
