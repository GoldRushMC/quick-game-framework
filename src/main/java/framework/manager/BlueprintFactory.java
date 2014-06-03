package framework.manager;

import framework.arena.Blueprintable;
import framework.utility.Coordinate;
import implement.arena.AbstractBlueprint;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.util.Vector;
import scala.Option;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * User: InspiredIdealist
 * Date: 4/28/2014
 */
public class BlueprintFactory {

    public static Blueprintable CreateNewBlueprint(String name, String description, List<Location> area) {

        Option<World> w = Option.apply(area.get(0).getWorld());

        List<Coordinate> coords = new LinkedList<>();
        for(Location l : area) {
            Material item = l.getBlock().getType();
            coords.add(new Coordinate(l.toVector(), item));
        }
        return new AbstractBlueprint(name, description, coords, w);
    }

    public static Blueprintable CreateNewBlueprint(String name, String description, Map<Vector, Material> area) {

        List<Coordinate> coords = new LinkedList<>();
        for(Map.Entry<Vector, Material> l : area.entrySet()) {


        }
        return new AbstractBlueprint(name, description, coords, null);
    }
}
