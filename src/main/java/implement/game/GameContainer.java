package implement.game;

import com.google.common.base.Stopwatch;
import framework.arena.Arenable;
import framework.game.*;
import framework.general.Enums;
import framework.team.Teamable;
import implement.team.DefaultTeam;
import inspire.Datum;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The base implementation of the Playable interface.
 */
public class GameContainer implements Playable {

    protected List<Participatable> participants = new ArrayList<Participatable>();
    protected List<Teamable> teams = new ArrayList<>();
    protected Scoreboard scoreboard;
    protected Stopwatch stopwatch = new Stopwatch();
    protected int timeLimit = 0;
    protected List<Datum<?>> data = new ArrayList<Datum<?>>();
    protected Arenable arena;
    protected Gameable game;
    protected Enums.GameStatus status;


    private GameContainer(Arenable arena, Gameable game, Scoreboard scoreboard, int timeLimit) {
        this.arena = arena;
        this.game = game;
        this.scoreboard = scoreboard;
        this.timeLimit = timeLimit;
    }


    protected GameContainer(Arenable arena, Gameable game, Scoreboard scoreboard, List<Participatable> participants, int timeLimit) {
        this(arena, game, scoreboard, timeLimit);
        this.participants = participants;
        Team t = scoreboard.registerNewTeam("Players");
        for(Participatable p : participants) {
            t.addPlayer(p.getPlayerLink());
        }
        teams.add(new DefaultTeam("Player List", "List of Players", t, participants));
    }

    protected GameContainer(Arenable arena, List<Teamable> teams, Gameable game, Scoreboard scoreboard, int timeLimit) {
        this(arena, game, scoreboard, timeLimit);
        this.teams = teams;
        for(Teamable team : teams) {
            participants.addAll(team.getMembers());
        }
    }

    @Override
    public void capturePresentState() {
//        data.add(new Datum<String>("SaveName", String.format("%s_%s", name, Calendar.getInstance().getTime().toString())));
        data.add(new Datum<List<Participatable>>("Participants", participants));
        data.add(new Datum<Scoreboard>("Scoreboard", scoreboard));
        data.add(new Datum<Long>("GameTime", stopwatch.elapsedTime(TimeUnit.SECONDS)));
    }

    //TODO Implement Save and Load
    @Override
    public boolean save(String saveName) {
        return false;
    }

    @Override
    public List<Datum<?>> getData() {
        if(data == null) capturePresentState();
        return data;
    }

    @Override
    public boolean load(String saveName) {
        return false;
    }

    @Override
    public boolean loadMostRecentSave() {
        if(data != null) {
            return true;
        }
        return false;
    }

    @Override
    public Enums.GameStatus getStatus() {
        return status;
    }

    @Override
    public String getName() {
        return game.getName();
    }

    @Override
    public String getDescription() {
        return game.getDescription();
    }

    @Override
    public Gameable getGame() {
        return game;
    }

    @Override
    public Arenable getArena() {
        return null;
    }

    @Override
    public List<Participatable> getParticipants() {
        return participants;
    }

    @Override
    public List<Teamable> getTeams() {
        return teams;
    }

    @Override
    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    @Override
    public void restartGame() {
        stopwatch.reset();
    }

    @Override
    public void startGame() {
        stopwatch.start();
    }

    @Override
    public void endGame() {
        stopwatch.stop();
    }

    @Override
    public boolean hasTimeLimit() {
        return timeLimit > 0;
    }

    @Override
    public int getTimeLimit() {
        return timeLimit;
    }

    @Override
    public Stopwatch getTimer() {
        return stopwatch;
    }
}
