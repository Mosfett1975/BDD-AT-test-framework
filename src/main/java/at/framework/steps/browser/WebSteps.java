package at.framework.steps.browser;

import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.ru.И;
import lombok.extern.log4j.Log4j2;

import static at.framework.basement.helperClasses.UserProperty.getValueFromFileOrVar;
import static at.framework.basement.variables.Variables.getVarResp;
import static at.framework.steps.browser.WebHelper.currentPageName;
import static at.framework.steps.browser.WebHelper.getPageObjectField;

@Log4j2
public class WebSteps {



    @И("^текущая страница установлена \"([^\"]*)\"$")
    public void openUrl(String setCurrentPage) {
        currentPageName(setCurrentPage);
    }

    @И("^в поле \"([^\"]*)\" введено значение \"([^\"]*)\"$")
    public String fillField(String field, String value){
        value = getValueFromFileOrVar(value);
        SelenideElement element = getPageObjectField(field);
        element.sendKeys ( value );
        return value;
    }

    @И("^выполнено нажатие на (?:кнопку|ссылку|поле|чекбокс|радиокнопку|текст|элемент) \"([^\"]*)\"$")
    public void clickOnElement(String elementName)  {
        SelenideElement element = getPageObjectField(elementName);
        element.click();
    }

}
