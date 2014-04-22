package implement.game.manage;

import framework.arena.Arenable;
import framework.game.Gameable;
import framework.game.Participatable;
import framework.game.Playable;
import framework.game.Ruleable;
import framework.general.Enums;
import framework.manager.Managable;
import framework.voting.Voteable;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class implements the Managable interface. It contains both the games and arenas, and maintains
 * all the games on a given server.
 */
public class GameManager implements Managable {

    public static GameManager instance;

    private List<Arenable> arenas = new ArrayList<>();
    private List<Playable> currentGames = new ArrayList<>();
    private List<Gameable> gameTypes = new ArrayList<>();

    private final JavaPlugin plugin;

    public GameManager(JavaPlugin plugin) {
        this.plugin = plugin;
        instance = this;
    }

    @Override
    public boolean registerGame(Gameable game) {
        //Gather all listeners for the game
        List<Ruleable> listeners = game.getRules();
        listeners.addAll(game.getGoals());

        for(Ruleable lis : listeners) {
            plugin.getServer().getPluginManager().registerEvents(lis, plugin);
        }
        return gameTypes.add(game);
    }

    @Override
    public boolean registerArena(Arenable arena) {
        return arenas.add(arena);
    }

    @Override
    public boolean unregisterArena(String arenaName) {
        Iterator<Arenable> arena = arenas.iterator();
        int count = arenas.size();
        while(arena.hasNext()) {
            Arenable a = arena.next();
            if(a.getName().equals(arenaName)) {
                arenas.remove(a);
                break;
            }
        }
        return arenas.size() < count;
    }

    @Override
    public boolean unregisterGame(String gameTypeName) {
        Iterator<Gameable> games = gameTypes.iterator();
        int count = gameTypes.size();
        while(games.hasNext()) {
            Gameable g = games.next();
            if(g.getName().equals(gameTypeName)) {
                gameTypes.remove(g);
                break;
            }
        }
        return gameTypes.size() < count;
    }

    @Override
    public List<Arenable> getArenas() {
        return arenas;
    }

    @Override
    public List<Playable> getCurrentGames() {
        List<Playable> currentGames = new ArrayList<Playable>();
        for(Arenable arena : arenas){
            if(arena.getCurrentGame().getStatus().equals(Enums.GameStatus.STARTED))
                currentGames.add(arena.getCurrentGame());
        }
        return currentGames;
    }

    @Override
    public List<Playable> getFullGameList()
    {
        return currentGames;
    }

    @Override
    public List<Participatable> getAllActiveParticipants() {
        List<Participatable> participants = new ArrayList<Participatable>();
        for(Arenable arena : arenas){
            participants.addAll(arena.getCurrentGame().getParticipants());
        }
        return participants;
    }

    @Override
    public List<Voteable> getVoting() {
        //TODO Impelement Voting!!!!
        return null;
    }

    @Override
    public List<Scoreboard> getScoreboards() {
        List<Scoreboard> scoreboards = new ArrayList<Scoreboard>();
        for(Arenable arena : arenas){
            scoreboards.add(arena.getCurrentGame().getScoreboard());
        }
        return scoreboards;
    }

    @Override
    public void stopAllGames() {
        for(Playable p : currentGames) {
            p.endGame();
        }
    }
}
