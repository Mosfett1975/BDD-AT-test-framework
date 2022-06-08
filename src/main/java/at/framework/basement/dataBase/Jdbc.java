package at.framework.basement.dataBase;


import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import static at.framework.basement.helperClasses.UserProperty.getValueFromFileOrVar;

@Log4j2
public class Jdbc {


    /**
     * get variables for jdbc from userData.properties file
     */
    static String HOST = getValueFromFileOrVar ( "jdbc.host" );
    static String NAME = getValueFromFileOrVar ( "jdbc.name" );
    static String USER = getValueFromFileOrVar ( "jdbc.user" );
    static String PASS = getValueFromFileOrVar ( "jdbc.password" );
    static String URL = String.format( getValueFromFileOrVar ( "jdbc.url" ), HOST, NAME );

    /**
     * It's just for example, you can add jdbc driver to dependency what you need and change value in Class.forName
     * @param sqlRequest
     * @return
     */

    public static String createSQLRequest ( String sqlRequest ){
        String value = null;
        try {
            Class.forName ( "org.postgresql.Driver" );
            Connection conn = DriverManager.getConnection ( URL, USER, PASS );
            if (sqlRequest.contains("DELETE")){ int rs = conn.createStatement().executeUpdate( sqlRequest );}
            else{
                ResultSet rs = conn.createStatement().executeQuery( sqlRequest );
                if ( rs != null && rs.next() ) {
                    value = rs.getString( 1 );
                }}
            conn.close();
        } catch ( Exception ex ) {
            ex.printStackTrace();
        }

        return value;
        }
}
