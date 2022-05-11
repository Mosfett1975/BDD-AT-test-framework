package anotations;

/**
 * Анотация для вызова PageObject'а по имени в сценарии Cucumber'а
 */

public @interface Name {
    String value();
}
