package implement.game.actions;

import com.goldrushmc.QGCore;
import framework.arena.Blueprintable;
import framework.game.Actionable;
import framework.game.Progress;
import inspire.message.BlueprintMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;
import skeleton.newbind.layer.Layerable;
import skeleton.newbind.layer.comm.Message;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * This class makes the assumption that both environments are compatible,
 * which means they have the same layout, but may have different blocks for
 * each vector of the layout.
 */
public class ArenaShift extends AbstractAction<BlueprintMessage> implements Runnable, Actionable {

    private Blueprintable current, newer;

    public ArenaShift(Layerable layer) {
        super(layer);
    }

    @Override
    public void run() {
        Map<Vector, Material> layout = newer.getRelativeLayout();
        Collection<Location> ref = current.getCurrentLayout();
//        long timer = 0;
        ref.parallelStream().forEachOrdered(location -> {
            Vector v = location.toVector().normalize();
            if(layout.containsKey(v)) {
                location.getBlock().setType(layout.get(v));
            }
        });

//        for(Location l : ref) {
//            //To prevent (hopefully) too much CPU usage from being consumed while converting from one environment to the next.
////            if(timer % 100 == 1) {
////                try {
////                    Thread.sleep(1000);
////                } catch(InterruptedException e) {
////                    Bukkit.getLogger().severe("Thread sleep interrupted in thread: " + Thread.currentThread().getName());
////                }
////            }
//
//            Vector v = l.toVector().normalize();
//            if(layout.containsKey(v)) {
//                l.getBlock().setType(layout.get(v));
//            }
//            timer++;
//        }
    }

    @Override
    public String getDescription() {
        return "Changes the arena to a different environment";
    }

    @Override
    public String getName() {
        return "ArenaShift";
    }

    @Override
    public void performAction(Object... args) {
        Bukkit.getScheduler().runTaskAsynchronously(QGCore.getInstance(), this);
    }

    @Override
    protected Optional<BlueprintMessage> messageConverter(Message message) {
        Optional<Blueprintable> optBlueprint = message.tryAdaptValue(Blueprintable.class);
        if(optBlueprint.isPresent()){
            return Optional.of(new BlueprintMessage(message.getSender(), optBlueprint.get()));
        }
        return Optional.empty();
    }

    @Override
    public void onGameStateChanged(Progress state) {
        if(state.equals(Progress.COMPLETE)) {

        }
    }

    @Override
    public void handleMessage(BlueprintMessage message) {
        if(current == null) current = message.getTypedPayload();
        else {
            newer = message.getTypedPayload();
            performAction();
            //Make the newer layout current
            current = newer;
        }
    }
}
