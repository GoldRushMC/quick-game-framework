package implement.team;

import framework.game.Participatable;
import implement.general.Stat;
import inspire.Datum;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * The Base implementation for the interface {@link Participatable}
 */
public class StandardParticipant implements Participatable {

    protected Map<Stat, Map<String, Integer>> stats = new EnumMap<>(Stat.class);
    protected Player playerLink;
    protected String title;

    public StandardParticipant(Player player) {
        playerLink = player;
        title = player.getDisplayName();
    }

    public StandardParticipant(Player player, List<Datum<Datum<Integer>>> stats) {
        this(player);
        for(Datum<Datum<Integer>> stat : stats) {

        }
    }

    @Override
    public OfflinePlayer getPlayerLink() {
        return playerLink;
    }

    @Override
    public String name() {
        return playerLink.getName();
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void setTitle(String newTitle) {
        this.title = newTitle;
        playerLink.setDisplayName(newTitle);
    }

    @Override
    public int getWins(String gameName) {
        return stats.get(Stat.WINS).get(gameName);
    }

    @Override
    public Map<String, Integer> getAllWins() {
        return stats.get(Stat.WINS);
    }

    @Override
    public Map<String, Integer> getAllLosses() {
        return stats.get(Stat.LOSSES);
    }

    @Override
    public int getLosses(String gameName) {
        return stats.get(Stat.LOSSES).get(gameName);
    }

    @Override
    public void capturePresentState() {

    }

    @Override
    public boolean save(String saveName) {
        return false;
    }

    @Override
    public List<Datum<?>> getData() {
        return null;
    }

    @Override
    public boolean load(String saveName) {
        return false;
    }

    @Override
    public boolean loadMostRecentSave() {
        return false;
    }

    @Override
    public String getDescription() {
        return String.format("A player by the name of: %s", name());
    }

    @Override
    public String getName() {
        return String.format("title: %s | username: %s", title, playerLink.getName());
    }
}
