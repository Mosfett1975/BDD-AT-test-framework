package at.framework.steps.browser;

import anotations.Name;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.reflections.Reflections;

import java.lang.reflect.Field;

import static at.framework.basement.variables.Variables.getVarResp;
import static at.framework.basement.variables.Variables.setVarObj;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

@Slf4j
public class WebHelper  {
    private WebHelper ( ) {
    }

    /**
     * @return get class by annotation @Name and set variable currentPage
     */
    public static void currentPageName ( String nameOfPage ) {
        setVarObj ( "currentPage", getPageObjectClass ( nameOfPage ) );
    }

    /**
     * Extract class by annotation @Name
     * @param pageName Name of class
     * @return имя класса
     */
    public static @NotNull Object getPageObjectClass ( String pageName ) {
        Reflections ref = new Reflections ( "pages" );
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

    /**
     * Return
     * @param pageElementName - @Name of PageFactory element
     * @return SelenideElement
     */
    @NotNull
    public static SelenideElement getElementPageFactory ( String pageElementName ) {
        Field[] fields = getFields ( );
        FindBy findExpression;
        for ( Field field : fields ) {
            Name findClass = field.getAnnotation ( Name.class );
            findExpression = field.getAnnotation ( FindBy.class );
            if ( field.getType ( ) == SelenideElement.class ) {
                field.setAccessible ( true );
                if ( findClass.value ( ).equals ( pageElementName ) ) {
                    return getElementSelector ( findExpression );
                }
            }
        }
        throw new IllegalArgumentException ( "SelenideElement not found" );
    }

    /**
     *
     * @param pageElementName - @Name of PageFactory collection
     * @return Selenide ElementsCollection
     */
    @NotNull
    public static ElementsCollection getCollectionPageFactory (String pageElementName) {
        Field[] fields = getFields ( );
        FindBy findExpression;
        for ( Field field : fields ) {
            Name findClass = field.getAnnotation ( Name.class );
            findExpression = field.getAnnotation ( FindBy.class );
            if ( field.getType ( ) == ElementsCollection.class ) {
                field.setAccessible ( true );
                if ( findClass.value ( ).equals ( pageElementName ) ) {
                    FindBy.FindByBuilder findByBuilder = new FindBy.FindByBuilder();
                    return $$(findByBuilder.buildIt(findExpression, null));
                }
            }
        }
        throw new IllegalArgumentException ( "ElementsCollection not found" );
    }


    @NotNull
    private static Field[] getFields ( ) {
        Class < ? > clazz = ( Class < ? > ) getVarResp ( "currentPage" );
        FindBy findExpression = null;
        Field[] fields = ( clazz ).getDeclaredFields ( );
        return fields;
    }

    /**
     * Extract element selector
     * @param findExpression
     * @return
     */
    private static SelenideElement getElementSelector ( FindBy findExpression ) {
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





}
