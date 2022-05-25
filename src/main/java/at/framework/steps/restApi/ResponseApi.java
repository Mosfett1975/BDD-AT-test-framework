package at.framework.steps.restApi;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ru.И;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.*;

import java.util.Map;

import static at.framework.basement.variables.Variables.getVarResp;




@Log4j2
public class ResponseApi {


    private final ApiHelper apiHelper = new ApiHelper();



    @И("^в ответе \"([^\"]+)\" statusCode: (\\d+)$")
    public void  checkStatusCode (String responseVar, int statusCode){
        Response response = (Response) getVarResp(responseVar);
        response.then().statusCode(statusCode);
    }

    /** Метод для проверки ответа на соответствие таблице
     *
     * @param responseVar Переменная для проверки
     * @param dataTable Проверяемая таблица
     */

    @И("^ответ из переменной \"([^\"]+)\" соответствует условиям из таблицы$")
    public void checkInTable (String responseVar, DataTable dataTable){
        Response response = (Response) getVarResp(responseVar);
        apiHelper.checkTable(response, dataTable);
    }



}
