package implement.team;

import framework.game.Participatable;
import framework.team.Teamable;
import implement.general.AbstractInformable;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * The Base implementation for the {@link Teamable} interface.
 */
public abstract class AbstractTeam extends AbstractInformable implements Teamable {

    protected Team teamLink;
    protected List<Participatable> members = new ArrayList<>();
    protected boolean inGame = false;
    int teamSizeLimit;

    protected AbstractTeam(String name, String description, Team teamLink, List<Participatable> members) {
        super(description, name);
        this.teamLink = teamLink;
        this.members = members;
    }

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
}
