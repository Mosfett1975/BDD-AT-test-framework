package at.framework.basement.api;

import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSender;
import io.restassured.specification.RequestSpecification;
import lombok.extern.log4j.Log4j2;
import java.util.List;

import static at.framework.basement.helperClasses.UserProperty.*;
import static at.framework.basement.helperClasses.UserProperty.checkValueAndReturnString;
import static at.framework.basement.variables.Variables.setVar;
import static java.lang.String.format;

@Log4j2
public class RestApiRequest {

public static String extractedUrl;
    public static String baseURi;





    public static Response sendRequest(String method, String url, DataTable dataTable){
        String baseURi = getProperty("baseURi");
        RestAssured.baseURI = baseURi;
        extractedUrl = getProperty(url);
        RequestSender request = createRequest(dataTable);
        return request.request(Method.valueOf(method), extractedUrl);
    }

    private static RequestSender createRequest(DataTable dataTable) {
        String valueWithParam = null;
        RequestSpecification request = RestAssured.given();
        String value = null;
        for (List<String> parameterOfRequest : dataTable.asLists()) {
            String type = parameterOfRequest.get(0);
            //Проверка наличия параметров в properties
            String name = checkValueAndReturnString(parameterOfRequest.get(1));
            value = checkValueAndReturnString(parameterOfRequest.get(2));
            value = getValueFromFileOrVar(value);

            switch (type.toUpperCase()) {
                case "BASIC_AUTHENTICATION": {
                    request.auth().basic(name, value);
                    break;
                }
                case "RELAXED_HTTPS": {
                    request.relaxedHTTPSValidation();
                    break;
                }
                case "ACCESS_TOKEN": {
                    request.header(name, "Bearer " + value.replace("\"", ""));
                    break;
                }
                case "PARAMETER": {
                    request.queryParam(name, value);
                    break;
                }
                case "MULTIPART": {
                    request.multiPart(name, value);
                    break;
                }
                case "FORM_PARAMETER": {
                    request.formParam(name, value);
                    break;
                }
                case "PATH_PARAMETER": {
//                    setVar(name, value);
//                    extractedUrl=getValueWithPropParam(extractedUrl);
                    request.pathParam(name, value);
                    break;
                }
                case "HEADER": {
                    request.header(name, value);
                    break;
                }
                case "COOKIES": {
                    Cookie cookies = new Cookie.Builder(name, value).build();
                    request.cookie(cookies);
                    break;
                }
                case "BODY": {
                    valueWithParam = getValueWithPropParam(value);
                    request.body(valueWithParam);
                    break;
                }
                default: {
                    throw new IllegalArgumentException(format("Некорректно задан тип %s для параметра запроса %s ", type, name));
                }
            }
        }
        log.trace("Request: \n" + value);
        return request;
    }
}
