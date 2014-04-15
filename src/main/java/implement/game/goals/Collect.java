package implement.game.goals;

import framework.game.Playable;
import framework.game.Participatable;
import framework.team.Teamable;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A basic goal for collecting things.
 */
public class Collect extends AbstractGoal {

    protected final Material toCollect;
    protected final int amountToCollect;
    protected Map<Teamable, List<ItemStack>> teamToItemsCollectedMap = new HashMap<>();

    public Collect(Material toCollect, int amountToCollect, Playable game, Scoreboard scoreboard) {
        super("Collect", "Players must collect a certain amount of " + toCollect.name(), game, scoreboard);
        this.toCollect = toCollect;
        this.amountToCollect = amountToCollect;
        //Initialize teams with an empty ItemStack list
        for(Teamable t : game.getTeams()) {
            teamToItemsCollectedMap.put(t, new ArrayList<ItemStack>());
        }
    }

    @Override
    public int getProgress(Participatable requestor) {
        int collected = 0;
        for(Map.Entry<Teamable, List<ItemStack>> score : teamToItemsCollectedMap.entrySet()) {
            Teamable team = score.getKey();
            if(team.getMembers().contains(requestor)) {
                for(ItemStack item : score.getValue()) {
                    collected += item.getAmount();
                }
                break;
            }
        }
        return collected;
    }

    @Override
    public String getFormattedProgress(Participatable requestor) {
        Teamable team = null;
        for(Teamable t : teamToItemsCollectedMap.keySet()) {
            if(t.getMembers().contains(requestor)) {
                team = t;
                break;
            }
        }
        if(team == null) return "You are not on a team. how are you even requesting this?!?!";

        return String.format("The total amount of collected %s for team %s is: (%d)", toCollect.name(), team.getName(), getProgress(requestor));
    }

    @Override
    public String getCriteria() throws IllegalStateException {
        return String.format("You must collect (%d) of %s(s)", amountToCollect, toCollect.name());
    }

    @Override
    public boolean isModifiable() throws IllegalStateException {
        return true;
    }
}
