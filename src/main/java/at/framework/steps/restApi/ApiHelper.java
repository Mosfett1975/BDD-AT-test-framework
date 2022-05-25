package at.framework.steps.restApi;

import at.framework.basement.helperClasses.UserProperty;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import lombok.extern.log4j.Log4j2;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.hamcrest.text.MatchesPattern;
import org.junit.Assert;

import java.util.List;
import java.util.function.Function;

@Log4j2
public class ApiHelper {
    public ApiHelper() {
    }

    void checkTable(Response response, DataTable dataTable) {
        String actualValue = null;
        StringBuilder errorMessage = new StringBuilder();
        if (dataTable.isEmpty()) {
            throw new IllegalArgumentException("Таблица пустая");
        }
        for (List<String> responseParam : dataTable.asLists()) {
            String type = responseParam.get(0).toUpperCase();
            String key = responseParam.get(1);
            String operation = responseParam.get(2);
            String expectedValue = UserProperty.getProperty(responseParam.get(3));
            Function<String, Matcher> matcher = compareOperation(operation);
            try {
                actualValue = objectiveOperation(response, type, key);
                MatcherAssert.assertThat(String.format("Ожидаемое значение не соответствует фактическому: ", type, key, actualValue,
                        operation, expectedValue), actualValue, matcher.apply(expectedValue));
            } catch (AssertionError e) {
                errorMessage.append(e.getMessage());
            }
            if (!errorMessage.toString().isEmpty()) {
                Assert.fail(errorMessage.toString());
            }
        }

    }

    static Function<String, Matcher> compareOperation(String compareString) {
        Function<String, Matcher> matcher = null;
        switch (compareString) {
            case "==":
                matcher = Matchers::equalTo;
                break;
            case "~":
                matcher = MatchesPattern::matchesPattern;
                break;
            case "!=":
                matcher = s -> Matchers.not(Matchers.equalTo(s));
                break;
            case "!~":
                matcher = s -> Matchers.not(MatchesPattern.matchesPattern(s));
                break;
            default:
                throw new IllegalArgumentException("Недопустимое условие: " + compareString);
        }
        return matcher;
    }

    String objectiveOperation(Response response, String type, String key) {
        String value = null;
        switch (type) {
            case "STATUS":
                switch (key) {
                    case "status":
                        value = response.getStatusLine();
                        break;
                    case "code":
                        value = String.valueOf(response.getStatusCode());
                        break;
                }
                break;
            case "HEADER": {
                value = response.getHeader(key);
                break;
            }
            case "JSON": {
                if (response.getBody().jsonPath().get(key) != null) {
                    value = response.getBody().jsonPath().get(key).toString();
                }
                break;
            }
            default: {
                log.info("Неверно задан элемент для проверки");
                throw new IllegalArgumentException(String.format("Не задано поведение для части ответа %s", type));
            }
        }
        return value;
    }
}