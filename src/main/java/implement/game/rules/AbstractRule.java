package implement.game.rules;

import framework.arena.Arenable;
import framework.game.Actionable;
import framework.game.Gameable;
import framework.game.Ruleable;
import framework.game.Triggerable;
import implement.general.AbstractInformable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  * A Base implementation of the {@link Ruleable} interface. This abstracts away some of the base functionality that
 * will be needed across all Rules and Goals, and also gets rid of some boilerplate code too.
 */
public abstract class AbstractRule extends AbstractInformable implements Ruleable, Listener {

    protected String name, description;
    protected final Gameable game;
    private Map<String, List<Triggerable<? extends Event>>> triggerToEventMap = new ConcurrentHashMap<>();
    protected List<Triggerable<?>> triggers = new ArrayList<>();
    protected List<Actionable> actions = new ArrayList<>();
    protected Map<Triggerable<?>, List<Actionable>> triggerToActionMap = new ConcurrentHashMap<>();

    public AbstractRule(String name, String description, Gameable game) {
        super(name, description);
        this.game = game;
    }

   /* TODO Find a better way to convey location in each event.
    * The reason we can only do player events, is because we need the location to determine
    * which arena the event is occuring in. If the rule is contained within the arena, then follow through.
    * Otherwise, ignore the event invocation.
    *
    */

    /**
     * Listens for any and all player-related events. This may be expanded later to include all events, but
     * the current mechanism for tracking where the events are occuring is by using the player's location in
     * conjunction with the arena's location that the rule is invoked in.
     * @param event The catch-all for all player events.
     */
    @SuppressWarnings("unchecked")
    @EventHandler(priority = EventPriority.HIGHEST)
    public void Listen(PlayerEvent event) {

        //Do initial check to see if the event is occuring within the Arena.
        Arenable arena = game.getContainer().getArena();
        Player envoker = event.getPlayer();
        //If the event is not occuring within the arena this rule is contained in, go no further.
        //This should segment the event handling to occur only in the arena that the rule is in.
        if(!arena.getBlueprint().getCurrentLayout().contains(envoker.getLocation())) return;

        //Get event name
        String evtName = event.getClass().getName();

        //If the event has any triggers, match the conditions
        if (triggerToEventMap.containsKey(evtName)) {
            List<Triggerable<? extends Event>> trigs = triggerToEventMap.get(evtName);
            for(Triggerable t : trigs) {

                //check to see if any triggers have the conditions matched
                if(t.matchCondition(event)) {

                    //If so, get the actions mapped to this trigger
                    if (triggerToActionMap.containsKey(t)) {
                        for (Actionable a : triggerToActionMap.get(t)) {

                            //Check to see if the action is enabled, then execute it
                            if (a.isEnabled()) {
                                a.performAction(event);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Call this method to properly setup a new {@link Ruleable} instance
     * @param rule The rule involved
     * @param map The mapping of triggers to actions
     */
    protected static void mapTriggersAndActions(AbstractRule rule, Map<Triggerable<?>, List<Actionable>> map) {
        for(Map.Entry<Triggerable<?>, List<Actionable>> entry : map.entrySet()) {
            rule.actions.addAll(entry.getValue());
            rule.triggers.add(entry.getKey());
        }
        rule.triggerToActionMap.putAll(map);
        rule.mapTriggersToEvents();
    }

    /**
     * Convenience method to add one action to a trigger at a time.
     * @param rule The rule involved
     * @param trigger The trigger to map to
     * @param action The action to map
     */
    protected static void mapTriggerAndAction(AbstractRule rule, Triggerable<?> trigger, Actionable action) {
        List<Actionable> actions = new ArrayList<>();
        actions.add(action);
        if(rule.triggerToActionMap.containsKey(trigger)) {
            rule.triggerToActionMap.get(trigger).add(action);
        } else {
            Map<Triggerable<?>, List<Actionable>> map = new HashMap<>();
            map.put(trigger, actions);
            mapTriggersAndActions(rule, map);
        }
    }

    @Override
    public Map<Triggerable<?>, List<Actionable>> getTriggersToActionsMap() {
        return triggerToActionMap;
    }

    protected void mapTriggersToEvents() {
        for(Triggerable<? extends Event> t : triggers) {
            TypeVariable<?>[] params = t.getClass().getTypeParameters();
            TypeVariable type = params[0];
            String typeName = type.getName();

            if(!triggerToEventMap.containsKey(typeName)) {
                triggerToEventMap.put(typeName, new ArrayList<Triggerable<? extends Event>>());
            }
            triggerToEventMap.get(typeName).add(t);
        }
    }

    @Override
    public List<Triggerable<?>> getTriggers() {
        return triggers;
    }

    @Override
    public List<Actionable> getActions() {
        return actions;
    }

    @Override
    public Gameable getGame() {
        return game;
    }
}
