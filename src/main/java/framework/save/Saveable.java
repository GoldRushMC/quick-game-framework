package framework.save;

import inspire.Datum;

import java.util.List;

/**
 * The Saveable interface provides a way to generically save any object. This interface defines
 * The different requirements for an object to be able to be saved.
 */
public interface Saveable {

    /**
     * Caches the present state of the {@link Saveable}.
     */
    void capturePresentState();

    /**
     * Gets the current in-cache data that was grabbed during the last {@link Saveable}.capturePresentState().
     * @return A {@link List} of {@link Datum}s which contain a pair of String and Mystery Value
     */
    public List<Datum<?>> getData();
}
