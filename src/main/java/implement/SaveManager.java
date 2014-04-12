package implement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * Created by Lex on 11/04/2014.
 */
public class SaveManager {

    Connection connection;
    Statement statement;

    public SaveManager(){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:qgf.db");

            statement = connection.createStatement();
            String sql = "SELECT VersionNumber FROM master";
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        }
    }

}
