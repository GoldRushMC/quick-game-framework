package framework.game;

import com.google.common.base.Stopwatch;
import framework.arena.Arenable;
import framework.general.Enums;
import framework.save.Saveable;
import framework.team.Teamable;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

/**
 * The Playable interface represents a container which hosts games
 */
public interface Playable extends Saveable {

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
     * Returns the current game selected.
     * @return The {@link Gameable} object
     */
    Gameable getGame();

    /**
     * Returns the {@link Arenable} the game will be played in.
     * @return The {@link Arenable} object
     */
    Arenable getArena();

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
     * Determines whether or not this game has a time limit set.
     * @return {@code true} if there is a time limit, {@code false} if not.
     */
    boolean hasTimeLimit();

    /**
     * Returns the time limit of this game
     * @return Will be 0 if there is no time limit.
     */
    int getTimeLimit();

    /**
     * Gets the {@link Stopwatch} instance associated with this game
     * @return the {@link Stopwatch} instance, or null if there is no time limit.
     */
    Stopwatch getTimer();

}
