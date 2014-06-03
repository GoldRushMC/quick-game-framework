package framework.game;

import framework.general.Informable;

import java.util.Collection;

/**
 * The Gameable interface defines what makes up a game
 */
public interface Gameable extends Informable {

    /**
     * The list of rules to be followed.
     * @return The list of {@link Ruleable}s
     */
    Collection<Ruleable<?>> getRules();

    /**
     * Gets the goals associated with this game.
     * @return The list of {@link Goalable}s
     */
    Collection<Goalable<?>> getGoals();
}
