package framework.game;

import framework.general.Informable;
import org.bukkit.event.Event;
import skeleton.newbind.layer.Layerable;

import java.util.Collection;

/**
 * Ruleable is the contract for how the rules with games are expected to behave.
 *
 */
public interface Ruleable<T extends Event> extends Informable, Triggerable<T>, Layerable {

    /**
     * Gets the list of {@link Actionable}s that are caused by this rule
     *
     * @return the list of {@link Actionable}s
     */
    Collection<Actionable> getActions();
}
