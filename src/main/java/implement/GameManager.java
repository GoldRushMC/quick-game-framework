package implement;

import framework.arena.Arenable;
import framework.game.Gameable;
import framework.game.Participatable;
import framework.general.Enums;
import framework.manager.Managable;
import framework.save.Saveable;
import framework.voting.Voteable;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lex on 11/04/2014.
 */
public class GameManager implements Managable{

    List<Arenable> arenas = new ArrayList<Arenable>();


    @Override
    public List<Arenable> getArenas() {
        return arenas;
    }

    @Override
    public List<Gameable> getCurrentGames() {
        List<Gameable> currentGames = new ArrayList<Gameable>();
        for(Arenable arena : arenas){
            if(arena.getCurrentGame().getStatus().equals(Enums.GameStatus.STARTED))
                currentGames.add(arena.getCurrentGame());
        }
        return currentGames;
    }

    @Override
    public List<Gameable> getFullGameList()
    {
        List<Gameable> currentGames = new ArrayList<Gameable>();
        for(Arenable arena : arenas){
                currentGames.add(arena.getCurrentGame());
        }
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
    public boolean save(Saveable toSave) {
        return false;
    }

    @Override
    public boolean load(Saveable toLoad) {
        return false;
    }
}
