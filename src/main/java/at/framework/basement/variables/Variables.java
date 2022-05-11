package at.framework.basement.variables;

import java.util.HashMap;
import java.util.Map;


/**
 * Класс для работы с переменными сценариев
 */
public final class Variables {

    public static Map<String, String> variable = new HashMap<>();

    private Variables() {

    }

    /**
     * Запись переменной в мапу
     * @param name
     * @param value
     */
    public static void setVar(String name, String value) {
        variable.put(name, value);
    }

    /**
     * Возвращает значение переменной по ключу
     * @param name
     * @return value
     */
    public static String getVar(String name) {
        String value = variable.get(name);
        if (value==null){ throw new IllegalArgumentException("Переменная " + name + " не найдена");}
        return value;
    }

}
