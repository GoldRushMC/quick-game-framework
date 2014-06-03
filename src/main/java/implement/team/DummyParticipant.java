package implement.team;

import framework.game.Participatable;
import framework.team.Teamable;
import implement.general.AbstractInformable;
import inspire.Datum;
import org.bukkit.OfflinePlayer;

import java.util.List;
import java.util.Map;

/**
 * Created by Lucas on 5/30/2014.
 */
public class DummyParticipant extends AbstractInformable implements Participatable {

    private Teamable team;

    String description = "Dummy!";
    Map<String, Integer> wins;
    Map<String, Integer> losses;



    public DummyParticipant(Teamable team) {
        this.team = team;
    }

    public DummyParticipant() {}

    @Override
    public OfflinePlayer getPlayerLink() {
        return team.getMVP().getPlayerLink();
    }

    @Override
    public String getTitle() {
        return team.getName();
    }

    @Override
    public void setTitle(String newTitle) {

    }

    @Override
    public int getWins(String gameName) {
        return wins.get(gameName);
    }

    @Override
    public Map<String, Integer> getAllWins() {
        return wins;
    }

    @Override
    public Map<String, Integer> getAllLosses() {
        return losses;
    }

    @Override
    public int getLosses(String gameName) {
        return losses.get(gameName);
    }

    @Override
    public void capturePresentState() {
        //TODO capture present state!
    }

    @Override
    public List<Datum<?>> getData() {
        return null;
    }
}
