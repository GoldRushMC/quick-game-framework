package framework.utility;

import framework.game.Participatable;
import implement.team.StandardParticipant;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * User: InspiredIdealist
 * Date: 4/20/2014
 */
public class Convert {

    public static List<Participatable> makeStandardParticipants(Player[] args) {
        List<Participatable> list = new ArrayList<>();
        for(Player t : args) {
            list.add(new StandardParticipant(t));
        }
        return list;
    }
}
