package implement.game;

import framework.game.Gameable;
import framework.game.Goalable;
import framework.game.Ruleable;
import implement.general.AbstractInformable;

import java.util.ArrayList;
import java.util.List;

/**
 * The Standard Game Template. Allows for dynamic game making.
 */
public class StandardGame extends AbstractInformable implements Gameable{

    protected List<Ruleable> rules = new ArrayList<>();
    protected List<Goalable> goals = new ArrayList<>();
    protected boolean environmentUpdates;

    public StandardGame(String name, String description, List<Ruleable> rules, List<Goalable> goals, boolean allowEnvironmentUpdates) {
        super(name, description);
        this.rules = rules;
        this.goals = goals;
        this.environmentUpdates = allowEnvironmentUpdates;
    }

    @Override
    public List<Ruleable> getRules() {
        return rules;
    }

    @Override
    public List<Goalable> getGoals() {
        return goals;
    }

    @Override
    public boolean allowEnvironmentUpdates() {
        return environmentUpdates;
    }
}
