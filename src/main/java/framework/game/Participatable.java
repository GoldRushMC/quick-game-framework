package framework.game;

import framework.save.Saveable;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.Map;

/**
 * The interface for participants of games.
 */
public interface Participatable extends Saveable {

    /**
     * Gets the reference held to the actual Bukkit {@link Player} object.
     * @return The {@link Player} object
     */
    OfflinePlayer getPlayerLink();

    /**
     * Convenience Method.
     * Get the name of the player.
     * @return The normal name of the player, same value as {@link Player}.getName()
     */
    String name();

    /**
     * Gets the current title of the player. This can vary from game to game.
     * This title is displayed above the head of the player.
     * @return The title of the player
     */
    String getTitle();

    /**
     * This sets the title to be displayed above the head of the player.
     * @param newTitle The title to display
     */
    void setTitle(String newTitle);

    /**
     * Gets the amount of victories, for the game specified, this player has accumulated.
     * @param gameName the name of the game
     * @return count of wins
     */
    int getWins(String gameName);

    /**
     * Gets all of the victories in all of the games achieved by the participant.
     * @return The {@link Map} of Game Names to Win Count.
     */
    Map<String, Integer> getAllWins();

    /**
     * Gets all of the losses in all of the games by the participant.
     * @return The {@link Map} of Game Names to Loss Count.
     */
    Map<String, Integer> getAllLosses();

    /**
     * Gets the amount of losses, for the game specified, this player has accumulated.
     * @param gameName the the name of the game
     * @return count of losses
     */
    int getLosses(String gameName);
}
