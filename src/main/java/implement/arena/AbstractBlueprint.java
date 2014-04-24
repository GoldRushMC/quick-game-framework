package implement.arena;

import framework.arena.Blueprintable;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;

/**
 * Created by Lex on 15/04/2014.
 */
public class AbstractBlueprint implements Blueprintable{

    Map<Vector, ItemStack> relativeLayout;

    public AbstractBlueprint(Map<Vector, ItemStack> data){
        this.relativeLayout = data;
    }

    @Override
    public Map<Vector, ItemStack> getRelativeLayout() {
        return relativeLayout;
    }

    @Override
    public List<Location> getCurrentLayout() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getArea() {
        return 0;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
