package pages.main;

import anotations.Name;
import at.framework.steps.browser.WebHelper;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

@Name("Для теста")
public class doubleForTest  {

    @Name("Строка поиска1")
    @FindBy(css = "input[title='Поиск']")
    public SelenideElement searchLine;

    @Name("Кнопка Поиск1")
    @FindBy(css = "input[name='btnK']")
    public SelenideElement searchButton;

    @Name("Результаты поиска1")
    @FindBy(xpath = "//*[@id=\"rso\"]//h3")
    public ElementsCollection searchResult;
}
