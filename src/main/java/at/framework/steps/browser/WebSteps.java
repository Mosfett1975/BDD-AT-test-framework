package at.framework.steps.browser;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.cucumber.java.en.And;
import io.cucumber.java.ru.И;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import static at.framework.basement.helperClasses.UserProperty.getValueFromFileOrVar;
import static at.framework.steps.browser.WebHelper.*;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Log4j2
public class WebSteps {


    /**
     * Set current page
     *
     * @param setCurrentPage PageFactory class annotated with @Name
     */
    @And( "^set current page \"([^\"]*)\"$" )
    @И( "^текущая страница установлена \"([^\"]*)\"$" )
    public void openUrl ( String setCurrentPage ) {
        currentPageName ( setCurrentPage );
    }

    /**
     * Enter value to UI field
     */
    @And( "^in the field \"([^\"]*)\" entered value \"([^\"]*)\"$" )
    @И( "^в поле \"([^\"]*)\" введено значение \"([^\"]*)\"$" )
    public String fillField ( String field, String value ) {
        value = getValueFromFileOrVar ( value );
        SelenideElement element = getElementPageFactory ( field );
        element.sendKeys ( value );
        return value;
    }

    @And( "^click on (?:button|link|field|checkbox|radiobutton|text|element) \"([^\"]*)\"$" )
    @И( "^выполнено нажатие на (?:кнопку|ссылку|поле|чекбокс|радиокнопку|текст|элемент) \"([^\"]*)\"$" )
    public void clickOnElement ( String elementName ) {
        SelenideElement element = getElementPageFactory ( elementName );
        element.click ( );
    }

    @And( "^double click on (?:button|link|field|checkbox|radiobutton|text|element) \"([^\"]*)\"$" )
    @И( "^выполнено двойное нажатие на (?:кнопку|ссылку|поле|чекбокс|радиокнопку|текст|элемент) \"([^\"]*)\"$" )
    public void doubleClickOnElement ( String elementName ) {
        SelenideElement element = getElementPageFactory ( elementName );
        element.doubleClick ();
    }

    @And( "^select radiobutton \"([^\"]*)\" with value \"([^\"]*)\"$" )
    @И( "^выбрать радиокнопку \"([^\"]*)\" со значением \"([^\"]*)\"$" )
    public void selectRadiobutton ( String elementName, String value ) {
        SelenideElement element = getElementPageFactory ( elementName );
        value = getValueFromFileOrVar ( value );
        element.selectRadio (value);
    }

    @And( "^toggle radiobutton \"([^\"]*)\" to \"([^\"]*)\"$" )
    @И( "^переключить радиокнопку \"([^\"]*)\" в состояние \"([^\"]*)\"$" )
    public void toggleRadiobutton ( String elementName, boolean status ) {
        SelenideElement element = getElementPageFactory ( elementName );
        element.setSelected ( status );
    }

    @And( "^text in the field \"([^\"]*)\" equals to text \"([^\"]*)\"$" )
    @И( "^значение из поля \"([^\"]*)\" равно тексту \"([^\"]*)\"$" )
    public void equalText ( String elementName, String compareText ) {
        SelenideElement element = getElementPageFactory ( elementName );
        compareText = getValueFromFileOrVar ( compareText );
        if (!element.equals ( compareText )){
            assertThat("Compared texts are not equal ", element, is(compareText));
        }
    }

    @And( "^in the field \"([^\"]*)\" the current date is entered in the format \"([^\"]*)\"$" )
    @И( "^в поле \"([^\"]*)\" введена текущая дата в формате \"([^\"]*)\"$" )
    public void setCurrentDate ( String elementName, String dateFormat ) {
        SelenideElement element = getElementPageFactory ( elementName );
        String stringDate;
        long rawDate = System.currentTimeMillis ();
        try {
            stringDate = new SimpleDateFormat (dateFormat).format(rawDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException ( "Date format is not valid" );
        }
        element.clear ();
        element.setValue ( stringDate );
    }

    @And( "^in the field \"([^\"]*)\" the current date is entered in the format \"([^\"]*)\"$" )
    @И( "^в поле \"([^\"]*)\" введена текущая дата в формате \"([^\"]*)\"$" )
    public void setCurrentTime ( String elementName, String dateFormat ) {
        SelenideElement element = getElementPageFactory ( elementName );
        String stringDate;
        long rawDate = System.currentTimeMillis ();
        try {
            stringDate = new SimpleDateFormat (dateFormat).format(rawDate);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException ( "Date format is not valid" );
        }
        element.clear ();
        element.setValue ( stringDate );
    }


    /**
     * Scroll down to element
     */
    @And("^the page is scrolled until the element appears \"([^\"]*)\"$")
    @И("^страница прокручена до появления элемента \"([^\"]*)\"$")
    public void scrollWhileElemNotFoundOnPage(String elementName) {
        SelenideElement element = getElementPageFactory ( elementName );
        (( JavascriptExecutor ) getWebDriver()).executeScript("arguments[0].scrollIntoView(true);", element);
        sleep(500);
    }

    /**
     * Sometimes the element does not appear immediately (for example, the result of data processing is expected),
     * in order to avoid long waits using sleep() I suggest using this step.
     * @param elementName
     * @throws TimeoutException
     */
    @And("^waiting for the element to appear \"([^\"]*)\"$")
    @И("^выполнено ожидание появления элемента \"([^\"]*)\"$")
    public void waitForElement(String elementName) throws TimeoutException {
        SelenideElement element = getElementPageFactory ( elementName );
        int timeout = 30000;
        while (!element.exists()) {
            if(timeout==0){throw new TimeoutException ( "The element didn't appear for 30 seconds" );}
            sleep(1000);
            timeout = timeout - 1000;
        }
    }

    @And("^element \"([^\"]*)\" displayed on the page$")
    @И("^элемент \"([^\"]*)\" отображается на странице$")
    public void elementDisplayed(String elementName) {
        SelenideElement element = getElementPageFactory ( elementName );
        element.shouldHave(appear);
    }




    /**
     * -------Work with element list------
     */

    /**
     * Step for debug. Just to make sure you've spelled out the list of elements locator correctly :)
     *
     * @param listName
     */
    @And( "^display list of elements \"([^\"]*)\"$" )
    @И( "^вывести список элементов \"([^\"]*)\"$" )
    public void outputList ( String listName ) {
        ElementsCollection listOfElementsFromPage = getCollectionPageFactory ( listName );
        System.out.println ( "Elements of list:" );
        listOfElementsFromPage.forEach ( System.out::println );
        System.out.println ( "Size of list: " + listOfElementsFromPage.size ( ) );
    }

    @And ( "^clicked to (?:text|value) \"([^\"]*)\" into list \"([^\"]*)\"$" )
    @И( "^выполнено нажатие на (?:текст|значение) \"([^\"]*)\" в списке \"([^\"]*)\"$" )
    public void selectElementInListIfFoundByText ( String value, String listName ) {
        value = getValueFromFileOrVar ( value );
        ElementsCollection listOfElementsFromPage = getCollectionPageFactory ( listName );
        listOfElementsFromPage.find ( text ( value ) ).click ( );
    }




}
