package register;

import framework.game.Triggerable;
import org.bukkit.event.player.PlayerMoveEvent;
import skeleton.newbind.layer.LayerBase;
import skeleton.newbind.layer.Layerable;

/**
 * Created by Lucas on 6/1/2014.
 */
public class SimpleTrigger extends LayerBase implements Triggerable<PlayerMoveEvent> {

    private boolean enabled = true;

    public SimpleTrigger(Layerable parentLayer, boolean enabled) {
        super(parentLayer);
        this.enabled = enabled;
    }

    @Override
    public boolean matchCondition(PlayerMoveEvent playerMoveEvent) {
        return false;
    }

    @Override
    public void resetTrigger() {
        enabled = true;
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
