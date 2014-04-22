package com.goldrushmc.samples.goal;

import framework.game.Gameable;
import framework.game.Participatable;
import framework.team.Teamable;
import implement.game.goals.AbstractGoal;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A basic goal for collecting things.
 */
public class Collect extends AbstractGoal<List<ItemStack>> {

    protected final Material toCollect;
    protected final int amountToCollect;

    public Collect(Material toCollect, int amountToCollect, Gameable game, Scoreboard scoreboard) {
        super(null, "Players must collect a certain amount of " + toCollect.name(), game, scoreboard, new ArrayList<ItemStack>());
        this.toCollect = toCollect;
        this.amountToCollect = amountToCollect;
    }

    @Override
    public int getProgress(Participatable requestor) {
        int collected = 0;
        for(Map.Entry<Teamable, List<ItemStack>> score : teamToProgressMap.entrySet()) {
            Teamable team = score.getKey();
            if(team.getMembers().contains(requestor)) {
                for(ItemStack item : score.getValue()) {
                    collected += item.getAmount();
                }
                return collected;
            }
        }
        //Represents when a person is not on a team.
        return -1;
    }

    @Override
    public String getFormattedProgress(Participatable requestor) {
        Teamable team = null;
        for(Teamable t : teamToProgressMap.keySet()) {
            if(t.getMembers().contains(requestor)) {
                team = t;
                break;
            }
        }
        if(team == null) return "You are not on a team. how are you even requesting this?!?!";

        return String.format("The total amount of collected %s for team %s is: (%d)", toCollect.name(), team.getName(), getProgress(requestor));
    }

    @Override
    public String getCriteria() throws IllegalStateException {
        return String.format("You must collect (%d) of %s(s)", amountToCollect, toCollect.name());
    }

    @Override
    public boolean isModifiable() throws IllegalStateException {
        return true;
    }
}
