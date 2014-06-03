package register.skeleton;

import framework.game.Gameable;
import framework.game.Goalable;
import framework.game.Ruleable;
import register.Resolvable;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

/**
 * Ruleable is the contract for how the rules with games are expected to behave.
 *
 */
class GameSkeleton implements Resolvable<Gameable>, Gameable {

    private Informer informer;
    private final ResolvableCollection<Ruleable<?>> rules;
    private final ResolvableCollection<Goalable<?>> goals;

    protected GameSkeleton(String name, String description, ResolvableCollection<Ruleable<?>> rules, ResolvableCollection<Goalable<?>> goals) {
        this.rules = rules;
        this.goals = goals;
        informer = new Informer(name, description);
    }

    @Override
    public Gameable resolveInstance() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        return this;
    }

    //Gameable Implementation

    @Override
    public Collection<Ruleable<?>> getRules() {
        return rules.getResolvedCollection();
    }

    @Override
    public Collection<Goalable<?>> getGoals() {
        return goals.getResolvedCollection();
    }

    //Informable Delegation

    @Override
    public String getDescription() {
        return informer.getDescription();
    }

    @Override
    public String getName() {
        return informer.getName();
    }
}
