package implement.arena;

import framework.arena.Arenable;
import framework.arena.Blueprintable;
import framework.arena.Environmentable;
import framework.game.Gameable;
import framework.game.Playable;
import framework.game.Progress;
import framework.general.Informable;
import framework.team.Teamable;
import inspire.message.GameStateMessage;
import inspire.message.TeamRegistrationMessage;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.hanging.HangingEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.vehicle.VehicleEvent;
import register.skeleton.Informer;
import skeleton.newbind.layer.LayerBase;
import skeleton.newbind.layer.comm.StandardMessage;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Handles local events for Bukkit
 */
public class ArenaBase extends LayerBase implements Listener, Arenable, Playable {

    private Informable informer;
    private Environmentable environment;
    private Collection<Class<? extends Gameable>> recommendedGames;

    public ArenaBase(String name, String description, Environmentable area) {
        informer = new Informer(name, description);
        this.environment = area;
    }

    //Arenable implementation

    @Override
    public Collection<String> getRecommendedGameTypes() {
        List<String> names = new LinkedList<>();
        recommendedGames.forEach(game -> names.add(game.getSimpleName()));
        return names;
    }

    @Override
    public Collection<Class<? extends Gameable>> getRecommendedGames() {
        return recommendedGames;
    }

    @Override
    public Environmentable getCurrentEnvironment() {
        return environment;
    }

    //Only use the event if it falls within the arena
    private void checkLocation(Location location, Event evt) {
        if(environment.getBlueprint().getCurrentLayout().contains(location)) {
            sendMessageToLocals(new StandardMessage(this, evt));
        }
    }

    //Hook into every single top-level event that can give a location.

    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerEvent(PlayerEvent evt) {
        checkLocation(evt.getPlayer().getLocation(), evt);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onEntityEvent(EntityEvent evt) {
        checkLocation(evt.getEntity().getLocation(), evt);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockEvent(BlockEvent evt) {
        checkLocation(evt.getBlock().getLocation(), evt);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onHangingEvent(HangingEvent evt) {
        checkLocation(evt.getEntity().getLocation(), evt);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryEvent(InventoryEvent evt) {
        checkLocation(evt.getView().getPlayer().getLocation(), evt);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onVehicleEvent(VehicleEvent evt) {
        checkLocation(evt.getVehicle().getLocation(), evt);
    }

    @Override
    public void loadGame(Gameable game) {
        game.getRules().parallelStream().forEach(rule -> {
            registerLocal(rule);
            rule.getActions().forEach(this::registerLocal);
        });

        game.getGoals().parallelStream().forEach(goal -> {
            registerLocal(goal);
            goal.getActions().forEach(this::registerLocal);
        });
    }

    @Override
    public void addTeam(Teamable team) {
        sendMessageToLocals(new TeamRegistrationMessage(this, team));
    }

    @Override
    public void startGame() {
        sendMessageToLocals(new GameStateMessage(this, Progress.STARTED));
    }

    @Override
    public void endGame() {
        sendMessageToLocals(new GameStateMessage(this, Progress.COMPLETE));
    }

    //Environmentable Delegation

    @Override
    public Biome getBiomeType() {
        return environment.getBiomeType();
    }

    @Override
    public Blueprintable getBlueprint() {
        return environment.getBlueprint();
    }

    @Override
    public boolean isCompatibleWith(Environmentable other) {
        return environment.isCompatibleWith(other);
    }

    //Informable Delegation

    @Override
    public String getName() {
        return informer.getName();
    }

    @Override
    public String getDescription() {
        return informer.getDescription();
    }
}
