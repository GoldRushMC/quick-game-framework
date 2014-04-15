package framework.manager;

import framework.arena.Arenable;
import framework.game.Playable;
import framework.game.Participatable;
import framework.voting.Voteable;
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
}
