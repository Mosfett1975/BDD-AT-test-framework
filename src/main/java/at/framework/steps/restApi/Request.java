package at.framework.steps.restApi;

import at.framework.basement.api.RestApiRequest;
import at.framework.basement.variables.Variables;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import io.restassured.response.Response;

public class Request {

    private static final String REQUEST_URL = "выполнен запрос ((?:GET|PUT|POST|DELETE|HEAD|TRACE|OPTIONS|PATCH)) на URL \"([^\"]+)\"";

    @И("^" + REQUEST_URL + " с параметрами из таблицы, ответ сохранен в переменную \"([^\"]+)\"$")
    public void sendRequest (String method, String url, String responseVar, DataTable dataTable){
        Response response = RestApiRequest.sendRequest(method, url, dataTable);
        getBodyAndSaveToVariable(responseVar, response);
    }

    private void getBodyAndSaveToVariable(String variableName, Response response) {
        Variables.setVarObj(variableName, response);
    }
}
