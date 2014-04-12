package implement.goals;

import framework.game.Actionable;
import framework.game.Goalable;
import framework.game.Triggerable;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

/**
 * User: InspiredIdealist
 * Date: 4/11/2014
 */
public class Collect implements Goalable, Listener {

    String name = "Collect";
    int amountCollected, toCollect;
    Scoreboard scoreboard;
    List<Actionable> actions = new ArrayList<>();


    public Collect(int amountToCollect, Scoreboard scoreboard) {
        this.toCollect = amountToCollect;
        this.scoreboard = scoreboard;
        scoreboard.registerNewObjective(getName(), getCriteria());
    }

    @Override
    public int getProgress() {
        return amountCollected;
    }

    @Override
    public String getFormattedProgress() {
        return String.format("Total Collected: %s", amountCollected);
    }

    @Override
    public String getDescription() {
        return "A goal for collecting things";
    }

    @Override
    public String getName() throws IllegalStateException {
        return name;
    }

    @Override
    public String getDisplayName() throws IllegalStateException {
        return name;
    }

    @Override
    public void setDisplayName(String s) throws IllegalStateException, IllegalArgumentException {
        this.name = s;
    }

    @Override
    public String getCriteria() throws IllegalStateException {
        return "Amount remaining to collect: " + (toCollect - amountCollected);
    }

    @Override
    public boolean isModifiable() throws IllegalStateException {
        return true;
    }

    @Override
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    @Override
    public void unregister() throws IllegalStateException {

    }

    @Override
    public void setDisplaySlot(DisplaySlot displaySlot) throws IllegalStateException {

    }

    @Override
    public DisplaySlot getDisplaySlot() throws IllegalStateException {
        return DisplaySlot.SIDEBAR;
    }

    @Override
    public Score getScore(OfflinePlayer offlinePlayer) throws IllegalArgumentException, IllegalStateException {
        return null;
    }

    @Override
    public List<Actionable> getActions() {
        return null;
    }

    @Override
    public List<Triggerable<?>> getTriggers() {
        return null;
    }
}
