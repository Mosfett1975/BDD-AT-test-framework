package at.framework.steps.restApi;

import at.framework.basement.variables.Variables;
import io.cucumber.java.ru.И;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;



@Log4j2
public class ResponseApi {

    @И("^в ответе \"([^\"]+)\" statusCode: (\\d+)$")
    public void  checkStatusCode (String responseVar, int statusCode){
        Response response = (Response) Variables.getVarResp(responseVar);
        response.then().statusCode(statusCode);
    }
}
