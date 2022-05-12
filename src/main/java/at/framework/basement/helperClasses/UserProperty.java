package at.framework.basement.helperClasses;


import com.google.common.base.Strings;
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

public static String getValueFromFile(String value){
        String stringPath = StringUtils.EMPTY;
        try{
            Path path = Paths.get(value);
            stringPath = path.toString();
            String valueFromFile = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
            return valueFromFile;
        } catch (IOException | InvalidPathException e) {log.trace("Значение не найдено");}
        log.trace("Значение в файле не найдено, будет использовано входное значение");
        return value;
}

    @SneakyThrows(IOException.class)
    public static Properties loadProperties() {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(PATHTOPROPERTIES)));
        return properties;
    }

    public static String getProperty(String propertyName) {
        return loadProperties().getProperty(propertyName);
    }
    public static String loadProperty(String propertyName, String defaultValue) {
        String value = checkValueAndReturnString(propertyName);
        return value != null ? value : defaultValue;
    }


}
