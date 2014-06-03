package register;

import framework.arena.Blueprintable;
import implement.general.AbstractInformable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.util.Vector;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Lucas on 5/30/2014.
 */
public class CompositeBlueprint extends AbstractInformable implements Blueprintable {

    protected List<Blueprintable> blueprintsLoaded;
    private Blueprintable currentBlueprint;

    public CompositeBlueprint(FileConfiguration fc) {
        List<Map<?, ?>> map = fc.getMapList("blueprints");

    }

    @Override
    public Map<Vector, Material> getRelativeLayout() {
        return currentBlueprint.getRelativeLayout();
    }

    @Override
    public Collection<Location> getCurrentLayout() {
        return currentBlueprint.getCurrentLayout();
    }

    @Override
    public int getSize() {
        return currentBlueprint.getSize();
    }

    @Override
    public int getArea() {
        return currentBlueprint.getArea();
    }

    @Override
    public Collection<Vector> getRelativeBoundary() {
        return currentBlueprint.getRelativeBoundary();
    }

    @Override
    public Collection<Location> getCurrentBoundary() {
        return currentBlueprint.getCurrentBoundary();
    }

    @Override
    public void createReferentialLayout(Location southWest, Location northEast) {
        currentBlueprint.createReferentialLayout(southWest, northEast);
    }

    @Override
    public void createReferentialLayout(Location center) {
        currentBlueprint.createReferentialLayout(center);
    }

    @Override
    public String getDescription() {
        return currentBlueprint.getDescription();
    }

    @Override
    public String getName() {
        return currentBlueprint.getName();
    }
}
