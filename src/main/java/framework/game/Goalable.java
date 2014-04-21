package framework.game;

import org.bukkit.scoreboard.Objective;

/**
 * This interface makes the {@link Objective} interface play a more important role in games,
 * as opposed to being a passive string.
 */
public interface Goalable<T> extends Objective, Ruleable {

    /**
     * The type that is used to track the progress of goals.
     * @return The tracker type
     */
    T getProgressTrackerType();

    /**
     * Gets the raw progress {@code int}
     * @return The total progress of this goal, out of 100.
     */
    int getProgress(Participatable requestor);

    /**
     * Gets a print-friendly version of the progress made.
     * @return The formatted string version of getProgress().
     */
    String getFormattedProgress(Participatable requestor);
}
