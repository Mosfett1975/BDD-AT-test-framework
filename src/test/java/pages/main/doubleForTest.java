package pages.main;

import anotations.Name;
import at.framework.steps.browser.WebHelper;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

@Name("Для теста")
public class doubleForTest extends WebHelper {

    @Name("Строка поиска1")
    @FindBy(css = "input[title='Поиск']")
    public SelenideElement searchLine;

    @Name("Кнопка Поиск1")
    @FindBy(css = "input[name='btnK']")
    public SelenideElement searchButton;

    @Name("Результаты поиска1")
    @FindBy(xpath = "//div[@class='bkWMgd']")
    public ElementsCollection searchResult;
}
