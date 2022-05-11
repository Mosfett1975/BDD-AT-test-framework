package pages.main;

import anotations.Name;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

@Name("Главное окно")
public class googleMainPage {

    @Name("Строка поиска")
    @FindBy(css = "input[title='Поиск']")
    public SelenideElement searchLine;

    @Name("Кнопка Поиск")
    @FindBy(css = "input[name='btnK']")
    public SelenideElement searchButton;
}
