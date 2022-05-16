package at.framework.basement.helperClasses;


import io.restassured.response.Response;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static at.framework.basement.variables.Variables.getVar;

@Log4j2
public class UserProperty {

    static String PATHTOPROPERTIES = "src/test/features/userData.properties";


    private UserProperty() {
    }




    /**
     * Выполняется проверка наличия входного параметра в Properties
     *
     * @param value
     * @return
     */
    public static String checkValueAndReturnString(String value) {
        String prop = getProperty(value);
        if (prop == null) {
            return value;
        }
        return prop;
    }

public static String getValueFromFileOrVar(String value){
        String stringPath = StringUtils.EMPTY;
        try{
            Path path = Paths.get(value);
            stringPath = path.toString();
            String valueFromFile = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            return valueFromFile;
        } catch (IOException | InvalidPathException e) {log.trace("Value not found in file");}
        if (checkValueAndReturnString(value) !=null){
            Object var = checkValueAndReturnString(value);
            if (var instanceof Response) {
                return  ((Response) var).getBody().asString();
            }
            return (String) var;
        }
        log.trace("Value not found, value will be use from input parameter");
        return value;
}

public static String getValueWithPropParam (String addParamToURL){
        String patternForProperty = "\\{([^{}]+)\\}";
        String parsed = "";
    Pattern p = Pattern.compile(patternForProperty);
    Matcher m = p.matcher(addParamToURL);
    while (m.find()){
        String varName = m.group(1);
        String value =getVar(varName);
        if (value == null){
            throw  new IllegalArgumentException("Value was not found in properties");
        }
        parsed = m.replaceFirst(value);
        m = p.matcher(parsed);
        if (parsed.isEmpty()) {parsed = addParamToURL;}
    }
    return parsed;
}

    @SneakyThrows(IOException.class)
    public static Properties loadProperties() {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(PATHTOPROPERTIES)));
        return properties;
    }

    public static String getProperty(String propertyName) {
        if(loadProperties().getProperty(propertyName)==null){
            return propertyName;
        }
        return loadProperties().getProperty(propertyName);
    }
    public static String loadProperty(String propertyName, String defaultValue) {
        String value = checkValueAndReturnString(propertyName);
        return value != null ? value : defaultValue;
    }


}
