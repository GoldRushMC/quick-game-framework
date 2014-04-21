package implement.arena;

import framework.arena.Arenable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * Custom event that is used to append each event, in order to include the arena the event is occuring in.
 */
public class ArenaEvent extends Event {

    private static final HandlerList handlers = new HandlerList();
    protected Arenable arena;

    public ArenaEvent(final Arenable arena, final boolean isAsync) {
        super(isAsync);
        this.arena = arena;
    }

    public Arenable getArena() {
        return arena;
    }

    @Override
    public HandlerList getHandlers() {
        return getHandlerList();
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
