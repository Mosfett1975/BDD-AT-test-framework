package at.framework.steps.browser;

import anotations.Name;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.reflections.Reflections;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.stream.Stream;

import static at.framework.basement.variables.Variables.getVarResp;
import static at.framework.basement.variables.Variables.setVarObj;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static java.util.stream.Collectors.toMap;

@Log4j2
public class WebHelper extends PageFactory {
    private WebHelper ( ) {
    }

    /**
     * @return Получение класса по значению аннотации @Name и устанавливает переменную currentPage
     */

    public static void currentPageName ( String nameOfPage ) {
        setVarObj ( "currentPage", getPageObjectClass ( nameOfPage ) );
    }

    /**
     * Извлекает класс по значению аннотации @Name
     * @param pageName Имя искомого класса
     * @return имя класса
     */
    
    public static Object getPageObjectClass ( String pageName ) {
        Reflections ref = new Reflections ( "pages.main" );
        for ( Class < ? > cl : ref.getTypesAnnotatedWith ( Name.class ) ) {
            Name findable = cl.getAnnotation ( Name.class );
            System.out.printf ( "Found class: %s, with meta name: %s%n",
                    cl.getSimpleName ( ), findable.value ( ) );
            if ( findable.value ( ).equals ( pageName ) ) {
                return cl;
            }
        }
        throw new IllegalArgumentException ( "Class not found" );

    }

    public static SelenideElement getPageObjectField ( String pageElementName ) {
        Class < ? > clazz = ( Class < ? > ) getVarResp ( "currentPage" );
        FindBy findExpression = null;
        Field[] fields = ( clazz ).getDeclaredFields ( );
        for ( Field field : fields ) {
            Name findClass = field.getAnnotation ( Name.class );
            findExpression = field.getAnnotation ( FindBy.class );
            if ( field.getType ( ) == SelenideElement.class ) {
                field.setAccessible ( true );
                if ( findClass.value ( ).equals ( pageElementName ) ) {
                    return getElementLocator ( findExpression );
                }
            }
        }
        throw new IllegalArgumentException ( "Page object not found" );

    }

    private static SelenideElement getElementLocator ( FindBy findExpression ) {
        String locator = null;

        if ( findExpression.css ( ) != null ) {
            locator = findExpression.css ( );
            return $ ( By.cssSelector ( locator ) );
        } else if ( findExpression.className ( ) != null ) {
            locator = findExpression.className ( );
            return $ ( By.className ( locator ) );
        } else if ( findExpression.id ( ) != null ) {
            locator = findExpression.id ( );
            return $ ( By.id ( locator ) );
        } else if ( findExpression.xpath ( ) != null ) {
            locator = findExpression.xpath ( );
            return $ ( By.xpath ( locator ) );
        } else if ( findExpression.tagName ( ) != null ) {
            locator = findExpression.tagName ( );
            return $ ( By.tagName ( locator ) );
        } else if ( findExpression.partialLinkText ( ) != null ) {
            locator = findExpression.partialLinkText ( );
            return $ ( By.partialLinkText ( locator ) );
        }
        throw new IllegalArgumentException ( "Element locator not found" );
    }


//    public void  getPageObjectMethod(String pageElementName) {
//        Object clazz = getPageObjectClass("Главное окно");
//
//      // Class clazz = currentPage.getClass();
//        Method[] methods = ((Class<?>) clazz).getDeclaredMethods();
//        for (Method method: methods) {
//            Name name = method.getAnnotation(Name.class);
//            System.out.println(name.value());
//            if(name != null){
//            System.out.println(method.getName());}
//        }
//    }
//    private Object extractFieldValueViaReflection(Field field) {
//        return extractFieldValue(field, this);
//    }
//    public static Object extractFieldValue(Field field, Object owner) {
//        field.setAccessible(true);
//        try {
//            return field.get(owner);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } finally {
//            field.setAccessible(false);
//        }
//    }


}
