package implement;

import inspire.Datum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

/**
 * Created by Lex on 11/04/2014.
 */
public class SaveManager {

    Connection connection;
    Statement statement;
    String fileName;

    /**
     * Initiates test connection to the datasource, outputs current datasource version to the console.
     */
    public SaveManager(String fileName){

        this.fileName = fileName;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);

            statement = connection.createStatement();
            String sql = "SELECT VersionNumber FROM master";
            ResultSet rs = statement.executeQuery(sql);

            //Prints db version number for validation purposes
            System.out.println(rs.getString(1));

            statement.close();
            connection.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        }
    }

    public boolean saveData(String saveName, List<Datum<?>> data){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            for(Datum<?> datum : data) {
                String sql = "INSERT INTO Data (SaveName,Descriptor,Data) " +
                        "VALUES ('" +
                        saveName + "', '" +
                        datum.getKey() + "', '" +
                        datum.getValue() +
                        "');";
                statement.executeUpdate(sql);
            }

            statement.close();
            connection.commit();
            connection.close();
            return true;
        } catch (Exception ex){
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            return false;
        }
    }

}
