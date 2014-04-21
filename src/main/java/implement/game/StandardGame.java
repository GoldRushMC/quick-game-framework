package implement.game;

import framework.game.Gameable;
import framework.game.Goalable;
import framework.game.Playable;
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
    protected Playable container;

    public StandardGame(String name, String description, List<Ruleable> rules, List<Goalable> goals, boolean allowEnvironmentUpdates, Playable container) {
        super(name, description);
        this.rules = rules;
        this.goals = goals;
        this.environmentUpdates = allowEnvironmentUpdates;
        this.container = container;
    }

    public StandardGame(String name, String description, List<Ruleable> rules, List<Goalable> goals, boolean allowEnvironmentUpdates) {
        this(name, description, rules, goals, allowEnvironmentUpdates, null);
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

    @Override
    public Playable getContainer() {
        return container;
    }

    @Override
    public void setContainer(Playable container) {
        this.container = container;
    }
}
