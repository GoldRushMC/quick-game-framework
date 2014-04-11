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

    List<Gameable> games = new ArrayList<Gameable>();


    @Override
    public List<Arenable> getArenas() {
        List<Arenable> arenas = new ArrayList<Arenable>();
        for(Gameable game : games){
            //arenas.add(game.) TODO: I'm pretty sure that games would have an arena would they not?
        }
        return null;
    }

    @Override
    public List<Gameable> getCurrentGames() {
        List<Gameable> currentGames = new ArrayList<Gameable>();
        for(Gameable game : games){
            if(game.getStatus().equals(Enums.GameStatus.STARTED))
                currentGames.add(game);
        }
        return currentGames;
    }

    @Override
    public List<Gameable> getFullGameList() {
        return games;
    }

    @Override
    public List<Participatable> getAllActiveParticipants() {
        List<Participatable> participants = new ArrayList<Participatable>();
        for(Gameable game : games){
            participants.addAll(game.getParticipants());
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
        for(Gameable game : games){
            scoreboards.add(game.getScoreboard());
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
