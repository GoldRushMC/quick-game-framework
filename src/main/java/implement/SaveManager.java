package implement;

import java.sql.*;
import java.sql.DriverManager;

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
