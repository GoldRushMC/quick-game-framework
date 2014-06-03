package framework.game;

import inspire.Progressable;
import org.bukkit.event.Event;
import org.bukkit.scoreboard.Objective;

/**
 * This interface makes the {@link Objective} interface play a more important role in games,
 * as opposed to being a passive string.
 */
public interface Goalable<T extends Event> extends Ruleable<T>, Progressable {

}
