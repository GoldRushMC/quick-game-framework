package framework.game;

import framework.general.Informable;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.Map;

/**
 * Ruleable is the contract for how the rules with games are expected to behave.
 *
 */
public interface Ruleable extends Informable, Listener {
    /**
     * Gets the list of {@link Actionable}s that are caused by this rule
     *
     * @return the list of {@link Actionable}s
     */
    List<Actionable> getActions();

    /**
     * Gets the list of {@link Triggerable}s that this Ruleable uses
     * @return The list of {@link Triggerable}s
     */
    List<Triggerable<?>> getTriggers();

    /**
     * Gets the mapping from the triggers to the actions.
     * @return The {@link Map} of {@link Triggerable}s and {@link Actionable}s
     */
    Map<Triggerable<?>, List<Actionable>> getTriggersToActionsMap();

    /**
     * Returns the {@link Gameable} instance this rule is tied to.
     * @return the {@link Gameable} instance
     */
    Gameable getGame();
}
