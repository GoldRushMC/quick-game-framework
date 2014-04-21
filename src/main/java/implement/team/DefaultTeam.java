package implement.team;

import framework.game.Participatable;
import org.bukkit.scoreboard.Team;

import java.util.List;

/**
 * User: InspiredIdealist
 * Date: 4/14/2014
 */
public class DefaultTeam extends AbstractTeam {


    public DefaultTeam(String name, String description, Team teamLink, List<Participatable> members) {
        super(name, description, teamLink, members);
    }

    @Override
    public boolean abolishTeam() {
        for(Participatable p : members) {
            teamLink.removePlayer(p.getPlayerLink());
        }
        return members.removeAll(members);
    }

    @Override
    public Participatable getMVP() {
        int maxWins = 0;
        Participatable mvp = null;
        for(Participatable p : members) {
            int wins = 0;
            for(Integer win : p.getAllWins().values()) {
                wins += win;
            }
            if(wins > maxWins) {
                maxWins = wins;
                mvp = p;
            }
        }
        return mvp;
    }
}
