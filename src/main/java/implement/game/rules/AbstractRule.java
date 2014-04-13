package implement.game.rules;

import framework.game.Actionable;
import framework.game.Gameable;
import framework.game.Ruleable;
import framework.game.Triggerable;
import implement.general.AbstractInformable;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
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

    public AbstractRule(String name, String description, Gameable game) {
        super(name, description);
        this.game = game;
    }

    @SuppressWarnings("unchecked")
    @EventHandler(priority = EventPriority.MONITOR)
    public void Listen(Event event) {
        String evtName = event.getClass().getName();
        if (triggerToEventMap.containsKey(evtName)) {
            List<Triggerable<? extends Event>> trigs = triggerToEventMap.get(evtName);
            for(Triggerable t : trigs) {
                t.matchCondition(event);
            }
        }
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
}
