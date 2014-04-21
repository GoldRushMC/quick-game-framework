package com.goldrushmc.samples.action;

import framework.game.Ruleable;
import implement.game.actions.AbstractAction;
import org.bukkit.entity.Player;

/**
 * User: InspiredIdealist
 * Date: 4/20/2014
 */
public class SendMessage extends AbstractAction {

    private String message;

    public SendMessage(String message, Ruleable rule) {
        super(null, "Sends a message to the given player", rule);
        this.message = message;
    }

    @Override
    public void performAction(Object... args) {
        for(Object o : args) {
            if (o instanceof Player) {
                ((Player) o).sendMessage(message);
            }
        }
    }
}
