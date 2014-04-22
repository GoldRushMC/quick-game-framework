package framework.game;

import framework.general.Informable;

import java.util.List;

/**
 * The Gameable interface defines what makes up a game
 */
public interface Gameable extends Informable {

    /**
     * The list of rules to be followed.
     * @return The list of {@link Ruleable}s
     */
    List<Ruleable> getRules();

    /**
     * Gets the goals associated with this game.
     * @return The list of {@link Goalable}s
     */
    List<Goalable> getGoals();

    /**
     * Determines whether or not to allow the environment to update at any time.
     * @return {@code true} if it is allowed, {@code false} if not.
     */
    boolean allowEnvironmentUpdates();

    /**
     * Returns the container which this game is running in.
     * @return The {@link Playable} instance
     */
    Playable getContainer();

    /**
     * Sets a new container for the game.
     * @param container The {@link Playable} instance that hosts this game
     */
    void setContainer(Playable container);
}
