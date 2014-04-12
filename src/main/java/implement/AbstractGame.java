package implement;

import com.google.common.base.Stopwatch;
import framework.game.Gameable;
import framework.game.Goalable;
import framework.game.Participatable;
import framework.game.Ruleable;
import inspire.Datum;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * The base implementation of the Gameable interface.
 */
public abstract class AbstractGame implements Gameable {

    List<Ruleable> rules = new ArrayList<Ruleable>();
    List<Goalable> goals = new ArrayList<Goalable>();
    List<Participatable> participants = new ArrayList<Participatable>();
    Scoreboard scoreboard;
    Stopwatch stopwatch;
    List<Datum<?>> data = new ArrayList<Datum<?>>();

    @Override
    public void capturePresentState() {
        data.add(new Datum<String>("SaveName", "LastSave"));
        data.add(new Datum<List<Ruleable>>("Rules", rules));
        data.add(new Datum<List<Goalable>>("Goals", goals));
        data.add(new Datum<List<Participatable>>("Participants", participants));
        data.add(new Datum<Scoreboard>("Scoreboard", scoreboard));
        data.add(new Datum<Long>("GameTime", stopwatch.elapsedTime(TimeUnit.SECONDS)));
    }

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
    public List<Ruleable> getRules() {
        return rules;
    }

    @Override
    public List<Goalable> getGoals() {
        return goals;
    }

    @Override
    public List<Participatable> getParticipants() {
        return participants;
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
    public Stopwatch getTimer() {
        return stopwatch;
    }
}
