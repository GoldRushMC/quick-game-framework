package framework.arena;

import framework.general.Informable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;

/**
 * The Blueprintable interface represents a layout template that can be applied to
 * Anywhere in Minecraft.
 */
public interface Blueprintable extends Informable{

    /**
     * Gets a mapping of generic vector values and the materials to be applied
     * to those relative locations
     * @return The {@link Map} of {@link Vector}s to {@link Material}s
     */
    Map<Vector, ItemStack> getRelativeLayout();

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

    /**
     * Gets the relative boundary of this blueprint
     * @return A List of {@link Vector}s that represent the boundary
     */
    List<Vector> getRelativeBoundary();

    /**
     * Gets the current, referential boundary of the blueprint,
     * Which is represented directly in the {@link org.bukkit.World}
     * @return A List of {@link Location}s representing the boundary in game
     */
    List<Location> getCurrentBoundary();

    /**
     * Maps the layout in the actual map.
     *
     * @param southWest The south-west corner of the blueprint
     * @param northEast The north-east corner of the blueprint
     */
    void createReferentialLayout(Location southWest, Location northEast);

    /**
     * Maps the layout in the actual map.
     * @param center The center of the blueprint
     */
    void createReferentialLayout(Location center);
}
