package framework.save;

import framework.general.Informable;
import inspire.Datum;

import java.util.List;

/**
 * The Saveable interface provides a way to generically save any object. This interface defines
 * The different requirements for an object to be able to be saved.
 */
public interface Saveable extends Informable{

    /**
     * Caches the present state of the {@link Saveable}.
     */
    void capturePresentState();

    /**
     * Saves the instance to the storage of choice.
     * @param saveName The name of the save
     * @return {@code true} if the save was successful, {@code false} if not
     */
    public boolean save(String saveName);

    /**
     * Gets the current in-cache data that was grabbed during the last {@link Saveable}.capturePresentState().
     * @return A {@link List} of {@link Datum}s which contain a pair of String and Mystery Value
     */
    public List<Datum> getData();

    /**
     * Loads the save with the specified save name.
     * @param saveName The name of the save
     * @return {@code true} if the load succeeded, {@code false} if not
     */
    boolean load(String saveName);

    /**
     * Loads the most recent save.
     * @return {@code true} if the load succeeded, {@code false} if not
     */
    boolean loadMostRecentSave();
}
