package pages.main;

import anotations.Name;
import at.framework.steps.browser.WebHelper;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

@Name("Главное окно")
public class googleMainPage extends PageFactory {

    @Name("Строка поиска")
    @FindBy(css = "input[title='Поиск']")
    public SelenideElement searchLine;

    @Name("Кнопка Поиск")
    @FindBy(css = "input[name='btnK']")
    public SelenideElement searchButton;

    @Name("Результаты поиска")
    @FindBy(xpath = "//div[@class='bkWMgd']")
    public ElementsCollection searchResult;
}
