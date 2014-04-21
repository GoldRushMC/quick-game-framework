package implement.game;

import framework.game.Gameable;
import framework.game.Goalable;
import framework.game.Playable;
import framework.game.Ruleable;
import implement.general.AbstractInformable;

import java.util.ArrayList;
import java.util.List;

/**
 * The Base implementation for the {@link Gameable} interface
 */
public abstract class AbstractGame extends AbstractInformable implements Gameable {

    protected Playable container;
    protected final List<Ruleable> rules = new ArrayList<>();
    protected final List<Goalable> goals = new ArrayList<>();
    protected boolean allowEnvironmentUpdates = false;

    public AbstractGame(String name, String description, boolean allowEnvironmentUpdates, Playable playable) {
        super(name, description);
        this.allowEnvironmentUpdates = allowEnvironmentUpdates;
    }

    public AbstractGame(String name, String description, boolean allowEnvironmentUpdates) {
        this(name, description, allowEnvironmentUpdates, null);
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
        return allowEnvironmentUpdates;
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
