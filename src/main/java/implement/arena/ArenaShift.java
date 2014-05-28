package implement.arena;

import framework.arena.Environmentable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.List;
import java.util.Map;

/**
 * This class makes the assumption that both environments are compatible,
 * which means they have the same layout, but may have different blocks for
 * each vector of the layout.
 */
public class ArenaShift implements Runnable {

    private final Environmentable current, newer;

    /**
     *
     * @param current The current Environment, to be changed
     * @param newer The new Environment, to convert into
     */
    public ArenaShift(Environmentable current, Environmentable newer) {
        this.current = current;
        this.newer = newer;
    }

    @Override
    public void run() {
        Map<Vector, ItemStack> layout = newer.getBlueprint().getRelativeLayout();
        List<Location> ref = current.getBlueprint().getCurrentLayout();
//        long timer = 0;
        for(Location l : ref) {
            //To prevent (hopefully) too much CPU usage from being consumed while converting from one environment to the next.
//            if(timer % 100 == 1) {
//                try {
//                    Thread.sleep(1000);
//                } catch(InterruptedException e) {
//                    Bukkit.getLogger().severe("Thread sleep interrupted in thread: " + Thread.currentThread().getName());
//                }
//            }

            Vector v = l.toVector().normalize();
            if(layout.containsKey(v)) {
                l.getBlock().setType(layout.get(v).getType());
            }
//            timer++;
        }
    }
}
