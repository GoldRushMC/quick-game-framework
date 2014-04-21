package com.goldrushmc.samples.action;

import framework.game.Ruleable;
import implement.game.actions.AbstractAction;
import org.bukkit.event.Cancellable;

/**
 * User: InspiredIdealist
 * Date: 4/20/2014
 */
public class Cancel extends AbstractAction {

    public Cancel(Ruleable rule) {
        super(null, "Cancels an event", rule);
    }

    @Override
    public void performAction(Object... args) {
        for(Object o : args) {
            if(o instanceof Cancellable) {
                ((Cancellable) o).setCancelled(true);
            }
        }
    }
}
