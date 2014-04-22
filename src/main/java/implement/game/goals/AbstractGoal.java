package implement.game.goals;

import framework.game.Gameable;
import framework.game.Goalable;
import framework.team.Teamable;
import implement.game.rules.AbstractRule;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

/**
 * A Base implementation of the {@link Goalable} interface. This abstracts away some of the base functionality that
 * will be needed across all Goals, and also gets rid of some boilerplate code too.
 */
public abstract class AbstractGoal<T> extends AbstractRule implements Goalable<T> {

    protected T tracker;
    protected Scoreboard scoreboard;
    protected DisplaySlot displaySlot;
    protected Map<Teamable, T> teamToProgressMap = new HashMap<>();

    public AbstractGoal(String name, String description, Gameable game, Scoreboard scoreboard, T defaultTrackerInstance) {
        super(name, description, game);
        this.scoreboard = scoreboard;
        scoreboard.registerNewObjective(getName(), getCriteria());
        for(Teamable t : game.getContainer().getTeams()) {
            teamToProgressMap.put(t, defaultTrackerInstance);
        }
        tracker = defaultTrackerInstance;
    }

    @Override
    public String getDisplayName() throws IllegalStateException {
        return name;
    }

    @Override
    public void setDisplayName(String s) throws IllegalStateException, IllegalArgumentException {
        name = s;
    }

    @Override
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    @Override
    public void unregister() throws IllegalStateException {
        scoreboard.getObjectives().remove(this);
    }

    @Override
    public void setDisplaySlot(DisplaySlot displaySlot) throws IllegalStateException {
        this.displaySlot = displaySlot;
    }

    @Override
    public DisplaySlot getDisplaySlot() throws IllegalStateException {
        return displaySlot;
    }

    @Override
    public Score getScore(OfflinePlayer offlinePlayer) throws IllegalArgumentException, IllegalStateException {
        for(Score s : scoreboard.getScores(offlinePlayer)) {
            if(s.getPlayer() == offlinePlayer && s.getObjective() == this) {
                return s;
            }
        }
        //Throw an error if the player is not found.
        throw new NullPointerException(String.format("There is no score for player '%s' ", offlinePlayer.getName()));
    }

    @Override
    public T getProgressTrackerType() {
        return tracker;
    }
}
