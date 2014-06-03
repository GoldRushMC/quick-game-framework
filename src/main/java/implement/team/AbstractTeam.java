package implement.team;

import framework.game.Participatable;
import framework.team.Teamable;
import implement.general.AbstractInformable;
import org.bukkit.OfflinePlayer;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Base implementation for the {@link Teamable} interface.
 */
public abstract class AbstractTeam extends AbstractInformable implements Teamable {

    protected Team teamLink;
    protected List<Participatable> members = new ArrayList<>();
    protected boolean inGame = false;
    protected int wins;
    protected int losses;
    protected int teamSizeLimit;

    protected AbstractTeam(String name, String description, Team teamLink, List<Participatable> members) {
        super(description, name);
        this.teamLink = teamLink;
        this.members = members;
    }

    public AbstractTeam() {}

    @Override
    public Team getTeamLink() {
        return teamLink;
    }

    @Override
    public List<Participatable> getMembers() {
        return members;
    }

    @Override
    public boolean addPlayerToTeam(Participatable player) {
        return !members.contains(player) && !inGame && members.add(player);
    }

    @Override
    public boolean removePlayerFromTeam(Participatable player) {
        return members.contains(player) && !inGame && members.remove(player);
    }

    @Override
    public int getTeamSizeLimit() {
        return teamSizeLimit;
    }

    @Override
    public int getTeamSize() {
        return members.size();
    }

    @Override
    public boolean inGame() {
        return inGame;
    }

    @Override
    public void setInGameStatus(boolean isInGame) {
        inGame = isInGame;
    }


    //Participatable Stuffs

    private Participatable dummyParticipant = new DummyParticipant(this);

    @Override
    public OfflinePlayer getPlayerLink() {
        return getMVP().getPlayerLink();
    }

    @Override
    public String getTitle() {
        return dummyParticipant.getTitle();
    }

    @Override
    public void setTitle(String newTitle) {
        dummyParticipant.setTitle(newTitle);
    }

    @Override
    public int getWins(String gameName) {
        return dummyParticipant.getWins(gameName);
    }

    @Override
    public Map<String, Integer> getAllWins() {
        return dummyParticipant.getAllWins();
    }

    @Override
    public Map<String, Integer> getAllLosses() {
        return dummyParticipant.getAllLosses();
    }

    @Override
    public int getLosses(String gameName) {
        return dummyParticipant.getLosses(gameName);
    }
}
