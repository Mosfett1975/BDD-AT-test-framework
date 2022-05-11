package at.framework.basement.helperClasses;


import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;

import java.io.*;
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

    public static int checkValueAndReturnInt(String value) {
        String prop = getProperty(value);
        int n = Integer.parseInt(prop);
        return n;
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
}
