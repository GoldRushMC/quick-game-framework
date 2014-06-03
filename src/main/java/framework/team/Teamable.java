package framework.team;

import framework.game.Participatable;
import org.bukkit.scoreboard.Team;

import java.util.List;

/**
 * The Teamable interface defines how a team is supposed to exist and interact within the framework.
 */
public interface Teamable extends Participatable {

    /**
     * Gets the link between this instance and the {@link Team} in the Bukkit {@link org.bukkit.scoreboard.Scoreboard}.
     * @return The {@link Team} instance
     */
    Team getTeamLink();

    /**
     * Gets all of the {@link framework.game.Participatable} members of the team.
     * @return a {@link List} of {@link framework.game.Participatable}s
     */
    List<Participatable> getMembers();

    /**
     * Tries to add a player to the {@link framework.team.Teamable}.
     * <br/>
     * A player may not be able to be added if the team is currently in a game that restricts
     * players from moving teams.
     * @param player the {@link framework.game.Participatable} to add
     * @return {@code true} if the member being added does not exceed the size limit, {@code false} if it does.
     */
    boolean addPlayerToTeam(Participatable player);

    /**
     * Tries to remove a player from the {@link framework.team.Teamable}.
     * <br/>
     * A player may not be able to be removed if the team is currently in a game that restricts
     * players from moving teams.
     *
     * @param player the {@link framework.game.Participatable} to remove
     * @return {@code true} if the player was successfully removed, {@code false} if not.
     */
    boolean removePlayerFromTeam(Participatable player);

    /**
     * Retrieves the maximum size the team may be.
     * @return the size limit of the team
     */
    int getTeamSizeLimit();

    /**
     * Retrieves the current size of the team.
     * @return the current size of the team
     */
    int getTeamSize();

    /**
     * Attempts to delete the team from the server.
     * <br/>
     * There are a variety of reasons a team may not be able to be deleted, in which case, this will fail.
     * <br/>
     * One example is if the team is in the middle of a game, the team cannot be deleted.
     * @return {@code true} if the team is able to be deleted, {@code false} if not.
     */
    boolean abolishTeam();

    /**
     * Gets the highest-scoring player of the team.
     * @return The {@link framework.game.Participatable} associated with the MVP player
     */
    Participatable getMVP();

    /**
     * Flag that determines if the Teamable is in a game or not.
     * @return {@code true} if the team is in a game, {@code false} if not
     */
    boolean inGame();

    /**
     * Sets whether or not the team is currently in game.
     * @param isInGame {@code true} if the team is in game, {@code false} if not.
     */
    void setInGameStatus(boolean isInGame);
}
