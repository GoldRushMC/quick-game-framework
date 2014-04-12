package framework.game;

import framework.general.Informable;
import org.bukkit.event.Event;

/**
 * Conditionables are event-handlers for the {@link Ruleable}s.
 * Multiple Conditionables are part of a single {@link Ruleable}
 */
public interface Triggerable<T extends Event> extends Informable{

    /**
     * Looks at any given event and attempts to match it to a condition
     * @param t The {@link Event} arguments to examine
     * @return {@code true} if the condition has been met, {@code false} if not.
     */
    boolean matchCondition(T t);

    /**
     * Gets the rule this trigger is associated to.
     * @return The {@link Ruleable} implementation
     */
    Ruleable getRule();

    /**
     * If the trigger state is false, the trigger will not call the 'onTrigger()' event again.
     * This method allows the implementation to change the state back to false, so the listener can
     * start listening again.
     */
    void resetTrigger();
}
