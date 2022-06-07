package pages.main;

import anotations.Name;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

@Name("Main page")
public class googleMainPageEng {

    @Name("Search line")
    @FindBy(css = "input[title='Поиск']")
    public SelenideElement searchLine;

    @Name("Search button")
    @FindBy(css = "input[name='btnK']")
    public SelenideElement searchButton;

    @Name("Results")
    @FindBy(xpath = "//*[@id=\"rso\"]//h3")
    public ElementsCollection searchResult;
}
