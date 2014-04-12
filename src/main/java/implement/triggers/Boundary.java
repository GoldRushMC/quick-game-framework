package implement.triggers;

import framework.arena.Blueprintable;
import framework.game.Ruleable;
import framework.game.Triggerable;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * User: InspiredIdealist
 * Date: 4/11/2014
 */
public class Boundary implements Triggerable<PlayerMoveEvent> {

    Blueprintable bounds;
    Ruleable rule;
    boolean isEnabled = true;

    public Boundary(Ruleable rule, Blueprintable bounds) {
        this.rule = rule;
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

    @Override
    public String getDescription() {
        return "Indicates when a player has gone out of bounds.";
    }

    @Override
    public String getName() {
        return "Boundary";
    }
}
