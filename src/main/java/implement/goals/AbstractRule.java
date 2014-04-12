package implement.goals;

import framework.game.Actionable;
import framework.game.Ruleable;
import framework.game.Triggerable;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * User: InspiredIdealist
 * Date: 4/11/2014
 */
public abstract class AbstractRule implements Ruleable {

    private Map<String, List<Triggerable<?>>> triggerToEventMap = new ConcurrentHashMap<>();
    protected List<Triggerable<?>> triggers = new ArrayList<>();

    public AbstractRule(List<Triggerable<?>> triggers, List<Actionable> actions) {
        this.triggers = triggers;
        mapTriggersToEvents();
    }

    @SuppressWarnings("unchecked")
    @EventHandler(priority = EventPriority.MONITOR)
    public void Listen(Event event) {
        String evtName = event.getClass().getName();
        if (triggerToEventMap.containsKey(evtName)) {
            List<Triggerable<?>> trigs = triggerToEventMap.get(evtName);
            for(Triggerable t : trigs) {
                t.matchCondition(event);
            }
        }
    }

    private void mapTriggersToEvents() {
        for(Triggerable<? extends Event> t : triggers) {
            TypeVariable<?>[] params = t.getClass().getTypeParameters();
            TypeVariable type = params[0];
            String typeName = type.getName();

            if(triggerToEventMap.containsKey(typeName)) {
                triggerToEventMap.put(typeName, new ArrayList<Triggerable<?>>());
            }
            triggerToEventMap.get(typeName).add(t);
        }
    }
}
