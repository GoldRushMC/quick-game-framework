package implement.game.triggers;

import framework.arena.Blueprintable;
import framework.game.Ruleable;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * A Trigger that notifies its {@link Ruleable} when any player crosses its boundary.
 */
public class Boundary extends AbstractTrigger<PlayerMoveEvent> {

    Blueprintable bounds;

    public Boundary(Ruleable rule, Blueprintable bounds) {
        super("Boundary", "Indicates when a player has gone out of bounds.", rule);
        this.bounds = bounds;
    }

    @Override
    public boolean matchCondition(PlayerMoveEvent playerMoveEvent) {
        if(isEnabled && !bounds.getCurrentLayout().contains(playerMoveEvent.getTo())) {
            isEnabled = false;
            return true;
        }
        return false;
    }

    @Override
    public Ruleable getRule() {
        return rule;
    }

    @Override
    public void resetTrigger() {
        isEnabled = true;
    }
}
