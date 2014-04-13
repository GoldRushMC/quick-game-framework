package framework.game;

import com.google.common.base.Stopwatch;
import framework.general.Enums;
import framework.save.Saveable;
import framework.team.Teamable;
import org.bukkit.scoreboard.Scoreboard;

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
     * The list of teams involved in this game.
     * <br/>
     * If this is a One-on-One or Free-For-All game type,
     * The team returned is every {@link Participatable} in the game
     * @return The list of {@link Teamable}s
     */
    List<Teamable> getTeams();

    /**
     * Gets the {@link org.bukkit.scoreboard.Scoreboard} of the game.
     * This will return null if the game does not require a scoreboard.
     * @return The {@link Scoreboard}
     */
    Scoreboard getScoreboard();

    /**
     * Gets the {@link framework.general.Enums.GameStatus} of the game.
     * This will return null if the game has not been initiated.
     * @return the {@link framework.general.Enums.GameStatus}
     */
    Enums.GameStatus getStatus();

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
