package framework.manager;

import framework.arena.Arenable;
import framework.game.Gameable;
import framework.game.Participatable;
import framework.game.Playable;
import framework.voting.Voteable;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

/**
 * The Managable interface provides a definition of how Games, Arenas, Participants, Scoreboards, Voting and Saving/Loading
 * should be coupled and organized. This is the top-level object in the Game Framework hierarchy, which houses and controls
 * all of the components of the games and arenas.
 */
public interface Managable {

    /**
     * Gets a list of {@link Arenable}s.
     * @return The {@link List} of {@link Arenable}s
     */
    List<Arenable> getArenas();

    /**
     * Gets a list of {@link framework.game.Playable}s that are currently being played
     * @return The {@link List} of {@link framework.game.Playable}s
     */
    List<Playable> getCurrentGames();

    /**
     * Gets a list of all the {@link framework.game.Playable}s available on the server.
     * @return The {@link List} of {@link framework.game.Playable}s
     */
    List<Playable> getFullGameList();

    /**
     * Gets a list of {@link Participatable}s.
     * @return The {@link List} of {@link Participatable}s
     */
    List<Participatable> getAllActiveParticipants();

    /**
     * Gets a list of {@link Voteable}s.
     * @return The {@link List} of {@link Voteable}s
     */
    List<Voteable> getVoting();

    /**
     * Gets a list of {@link Scoreboard}s.
     * @return The {@link List} of {@link Scoreboard}s
     */
    List<Scoreboard> getScoreboards();

    /**
     * Stops all current games
     */
    void stopAllGames();

    /**
     * Registers a game type to the framework
     *
     * @param game The game type to register
     * @return {@code true} if it is added, {@code false} if not.
     */
    boolean registerGame(Class<? extends Gameable> game);

    /**
     * Registers an arena to the framework
     *
     * @param arena The arena to register
     * @return {@code true} if it is added, {@code false} if not.
     */
    boolean registerArena(Arenable arena);

    /**
     * Unregisters an arena from the framework
     *
     * @param arenaName The arena to unregister
     * @return {@code true} if it is removed, {@code false} if not.
     */
    boolean unregisterArena(String arenaName);

    /**
     * Unregisters a game type from the framework
     *
     * @param gameTypeName The game type to unregister
     * @return {@code true} if it is removed, {@code false} if not.
     */
    boolean unregisterGame(String gameTypeName);

    /**
     * Registers the plugin which supports the framework of quick-game
     * @param plugin The plugin to register
     */
    void registerGameFactoryPlugin(JavaPlugin plugin);
}
