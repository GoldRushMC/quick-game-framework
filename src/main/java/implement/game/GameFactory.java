package implement.game;

import framework.arena.Arenable;
import framework.game.*;
import framework.team.Teamable;
import implement.team.DefaultTeam;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

/**
 * User: InspiredIdealist
 * Date: 4/14/2014
 */
public class GameFactory {

    public static List<Gameable> ALL_GAME_TYPES = new ArrayList<>();
    public static List<Arenable> ALL_AVAILABLE_ARENAS = new ArrayList<>();
    /**
     * Creates a new game type
     *
     * @param name The name of the game
     * @param description What the game is about
     * @param rules The rules of the game
     * @param goals The objectives to achieve
     * @param allowEnvironmentChange Whether or not the environment can change mid-game
     * @return The {@link Gameable} object
     */
    public static Gameable CreateNewGame(String name, String description, final List<Ruleable> rules, final List<Goalable> goals, boolean allowEnvironmentChange) {
        Gameable game = new StandardGame(name, description, rules, goals, allowEnvironmentChange);
        ALL_GAME_TYPES.add(game);
        return game;
    }

    /**
     * Begins a new game, with a time limit.
     *
     * @param arena The arena the game is played in
     * @param game The game to play
     * @param teams The teams involved
     * @param scoreboard The scoreboard to keep score
     * @param timeLimit The time limit
     * @return The {@link Playable} object
     */
    public static Playable StartNewTeamGame(Arenable arena, Gameable game, List<Teamable> teams, Scoreboard scoreboard, int timeLimit) {
        return new GameContainer(arena, teams, game, scoreboard, timeLimit);
    }

    /**
     * Begins a new game.
     *
     * @param game The game to play
     * @param teams The teams involved
     * @param scoreboard The scoreboard to keep score
     * @return The {@link Playable} object
     */
    public static Playable StartNewTeamGame(Arenable arena, Gameable game, List<Teamable> teams, Scoreboard scoreboard) {

        return new GameContainer(arena, teams, game, scoreboard, 0);
    }

    public static Playable StartNewFreeForAll(Arenable arena, Gameable game, List<Participatable> participants, Scoreboard scoreboard) {
        return new GameContainer(arena, game, scoreboard, participants, 0);
    }

    public static Teamable CreateNewTeam(String name, String description, Scoreboard scoreboard, List<Participatable> members) {
        return new DefaultTeam(name, description, scoreboard.registerNewTeam(name), members);
    }
}
