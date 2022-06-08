package at.framework.basement.dataBase;

import at.framework.basement.variables.Variables;
import io.cucumber.java.en.And;
import io.cucumber.java.ru.И;


public class sqlSteps {

    @And ("^received current date and time from DB and saved to variable \"([^\"]*)\"$")
    @И("^получена  текущие дата и время из БД и сохранено в переменную \"([^\"]*)\"$")
    public void getTime (String variableForSQLTime) {
       Variables.setVar (variableForSQLTime, Jdbc.createSQLRequest ("SELECT now()"));
    }

}
