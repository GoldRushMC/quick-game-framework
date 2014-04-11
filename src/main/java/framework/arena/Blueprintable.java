package framework.arena;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;

/**
 * The Blueprintable interface represents a layout template that can be applied to
 * Anywhere in Minecraft.
 */
public interface Blueprintable {

    /**
     * Gets a mapping of generic vector values and the materials to be applied
     * to those relative locations
     * @return The {@link Map} of {@link Vector}s to {@link Material}s
     */
    Map<Vector, Material> getRelativeLayout();

    /**
     * Gets the area that this blueprint has been applied
     * @return The {@link List} of {@link Location}s
     */
    List<Location> getCurrentLayout();

    /**
     * Gives the Volume of the Block region this Blueprint takes up.
     * @return The Volume (X * Y * Z)
     */
    int getSize();

    /**
     * Gives the Area of the Block region this Blueprint takes up.
     * @return The Area (X * Z)
     */
    int getArea();

}
