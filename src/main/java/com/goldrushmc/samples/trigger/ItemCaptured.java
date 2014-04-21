package com.goldrushmc.samples.trigger;

import framework.game.Goalable;
import implement.game.triggers.AbstractTrigger;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

/**
 * User: InspiredIdealist
 * Date: 4/19/2014
 */
public class ItemCaptured<T extends ItemStack> extends AbstractTrigger<PlayerPickupItemEvent> {

    private T toCapture;

    public ItemCaptured(Goalable<T> rule) {
        super(null, "Triggers when the correct item has been picked up", rule);
        toCapture = rule.getProgressTrackerType();
    }

    @Override
    public boolean matchCondition(PlayerPickupItemEvent playerPickupItemEvent) {
        return playerPickupItemEvent.getItem().getItemStack().isSimilar(toCapture);
    }
}
