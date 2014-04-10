package com.goldrushmc.bukkit.main.game;

import com.goldrushmc.bukkit.main.Informable;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;

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
     * A description of the condition needed to perform the onTrigger() method.
     * @return the description, as a {@link String}
     */
    String getConditionDescription();

    /**
     * A trigger goes from a {@code false} state to {@code true} if it's condition is met.
     * This method must call the {@link Ruleable} interface to determine what to do next.
     */
    void onTrigger();

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
