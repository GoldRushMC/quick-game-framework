package implement.game.manage;

import com.goldrushmc.Main;
import framework.arena.Blueprintable;
import framework.save.SerialDatum;
import implement.arena.AbstractBlueprint;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

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

    /**
     * Saves Given serializable data to the database
     * @param saveName {@link java.lang.String} name of the same, has to be unique
     * @param data {@link framework.save.SerialDatum} collection, has to be consisted of basic types
     * @return true if successfull, false if not
     */
    public boolean saveData(String saveName, List<SerialDatum<?>> data){
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            for(SerialDatum<?> datum : data) {
                String sql = "INSERT INTO Data (SaveName,Descriptor,Data) " +
                        "VALUES ('" +
                        saveName + "', '" +
                        datum.getKey().toString() + "', '" +
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

    /**
     * Loads data from database the is linked to the given save name
     * @param saveName {@link java.lang.String} name of the same, has to be unique
     * @return list of received {@link framework.save.SerialDatum}, will be null if unsuccessful
     */
    public List<SerialDatum<?>> loadData(String saveName){
        try{
            List<SerialDatum<?>> data = new ArrayList<SerialDatum<?>>();

            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);

            statement = connection.createStatement();
            String sql = "SELECT Descriptor, Data FROM Data WHERE SaveName = '" + saveName + "'";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                SerialDatum<?> datum = new SerialDatum<Object>(rs.getString(1), null);
                datum.fromString(rs.getString(2));
                data.add(datum);
            }

            statement.close();
            connection.close();
            return data;
        } catch (Exception ex){
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            return null;
        }
    }

    public Boolean saveBlueprint(Blueprintable blueprint){
        File file = new File(Main.instance.getDataFolder(), blueprint.getName()+".blu");

        try{
            if(!file.exists())
                file.createNewFile();

            FileWriter fileWriter = new FileWriter(file, true);

            for(Vector vector : blueprint.getRelativeLayout().keySet()){
                String line = vector.getX() + "," + vector.getBlockY() + "," + vector.getZ() + "|";
                line = line + blueprint.getRelativeLayout().get(vector).getType().name() + "|";
                line = line + blueprint.getRelativeLayout().get(vector).getDurability();

                fileWriter.append(line + "\n");
            }
            return true;
        } catch (Exception ex){
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            return false;
        }
    }

    public List<Blueprintable> loadAllBlueprints(){
        List<Blueprintable> blueprints = new ArrayList<Blueprintable>();

        for(File file : finder(Main.instance.getDataFolder().getAbsolutePath())){
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));

                String line = br.readLine();

                Map<Vector, ItemStack> map = new HashMap<Vector, ItemStack>();

                while ( line != null ) {
                    String[] splitArray = line.split("|");
                    String[] vectorSplitArray = splitArray[0].split(",");
                    Vector vector = new Vector(Double.valueOf(vectorSplitArray[0]), Double.valueOf(vectorSplitArray[1]), Double.valueOf(vectorSplitArray[2]));
                    ItemStack stack = new ItemStack(Material.getMaterial(splitArray[1]), Integer.valueOf(splitArray[2]));
                    map.put(vector, stack);
                    line = br.readLine();
                }

                AbstractBlueprint blueprint = new AbstractBlueprint(map);

            } catch(Exception ex){
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
                return null;
            }
        }
        return null;
    }

    public File[] finder( String dirName){
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String filename)
            { return filename.endsWith(".blu"); }
        } );

    }
}
