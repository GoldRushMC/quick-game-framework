package framework.game;

import framework.general.Informable;
import com.google.common.base.Stopwatch;
import framework.save.Saveable;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

/**
 * The Gameable interface represents any mini-game
 */
public interface Gameable extends Saveable {

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
     * The list of participants in the game
     * @return The list of {@link Participatable}s
     */
    List<Participatable> getParticipants();

    /**
     * Gets the {@link org.bukkit.scoreboard.Scoreboard} of the game.
     * This will return null if the game does not require a scoreboard.
     * @return The {@link Scoreboard}
     */
    Scoreboard getScoreboard();

    /**
     * Called at anytime, hard resets the game. Any goal progress will be lost.
     */
    void restartGame();

    /**
     * Starts the game, and any timer that may be attached.
     */
    void startGame();

    /**
     * Ends the game, and any timer that may be attached.
     */
    void endGame();

    /**
     * Determines whether or not to allow the environment to update at any time.
     * @return {@code true} if it is allowed, {@code false} if not.
     */
    boolean allowEnvironmentUpdates();

    /**
     * Determines whether or not this game has a time limit set.
     * @return {@code true} if there is a time limit, {@code false} if not.
     */
    boolean hasTimeLimit();

    /**
     * Gets the {@link Stopwatch} instance associated with this game
     * @return the {@link Stopwatch} instance, or null if there is no time limit.
     */
    Stopwatch getTimer();

}
