package register.skeleton;

import framework.game.Gameable;
import framework.game.Goalable;
import framework.game.Ruleable;

import java.util.Collection;

/**
 * A standard game implementation to be used by GameSkeleton
 */
public class StandardGame implements Gameable {

    private String name, description;
    private final Collection<Ruleable<?>> rules;
    private final Collection<Goalable<?>> goals;

    public StandardGame(String name, String description, Collection<Ruleable<?>> rules, Collection<Goalable<?>> goals) {
        this.name = name;
        this.description = description;
        this.rules = rules;
        this.goals = goals;
    }

    @Override
    public Collection<Ruleable<?>> getRules() {
        return rules;
    }

    @Override
    public Collection<Goalable<?>> getGoals() {
        return goals;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }
}
