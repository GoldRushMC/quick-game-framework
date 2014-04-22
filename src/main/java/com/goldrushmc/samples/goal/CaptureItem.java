package com.goldrushmc.samples.goal;

import com.goldrushmc.samples.action.EndGame;
import com.goldrushmc.samples.trigger.ItemCaptured;
import framework.game.Gameable;
import framework.game.Participatable;
import framework.team.Teamable;
import implement.game.goals.AbstractGoal;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;

/**
 * A goal to capture some item with specific metadata
 */
public class CaptureItem extends AbstractGoal<ItemStack> {

    public CaptureItem(Gameable game, Scoreboard scoreboard, Material toCapture, String... metadata) {
        super(null, String.format("Capture the enemy's %s", toCapture.name().toLowerCase()), game, scoreboard, CreateItem(toCapture, metadata));

        mapTriggerAndAction(this, new ItemCaptured<>(this), new EndGame(this));
    }

    @Override
    public int getProgress(Participatable requestor) {
        for(Teamable t : teamToProgressMap.keySet()) {
            if(t.getMembers().contains(requestor)) {
                return teamToProgressMap.get(t).getItemMeta().getLore() != null ? 0 : -1;
            }
        }
        return -1;
    }

    @Override
    public String getFormattedProgress(Participatable requestor) {
        int progress = getProgress(requestor);
        String toCapture = tracker.getType().name().toLowerCase();
        return String.format(progress > -1 ? progress > 0 ? "Your team has scored!" : "You have the enemy's %s!" : "You have not yet captured the enemy's %s.", toCapture);
    }

    @Override
    public String getCriteria() throws IllegalStateException {
        return String.format("Capture the enemy's %s", tracker.getType().name().toLowerCase());
    }

    @Override
    public boolean isModifiable() throws IllegalStateException {
        return false;
    }

    public static ItemStack CreateItem(Material material, String... lore) {
        ItemStack item = new ItemStack(material);
        for(String l : lore) {
            item.getItemMeta().getLore().add(l);
        }
        return item;
    }
}
