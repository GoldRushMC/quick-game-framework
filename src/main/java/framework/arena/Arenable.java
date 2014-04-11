package framework.arena;

import framework.general.Informable;
import framework.game.Gameable;
import framework.save.Saveable;

import java.util.List;

/**
 * The Arenable interface represents an arena that can host games
 */
public interface Arenable extends Saveable {

    /**
     * Gets the types of {@link framework.game.Gameable}s supported by this arena.
     * @return A list of strings that represent the names of supported game types.
     */
    List<String> getSupportedGameTypes();

    /**
     * Gets the in-game {@link Gameable} interfaces that are supported.
     * By this environment.
     *
     * Use Case: The Voting Booth.
     *
     * @return The {@link List} of {@link Gameable} objects
     */
    List<Gameable> getSupportedGames();

    /**
     * Gets the different possible environment types of this arena
     * @return The {@link List} of possible {@link Environmentable}s
     */
    List<Environmentable> getEnvironments();

    /**
     * Gets the current environment the arena has
     * @return The {@link Environmentable} object
     */
    Environmentable getCurrentEnvironment();

    /**
     * Attempts to change the current environment the arena is in.
     * A reason this may fail is because a game may not be yet finished,
     * and games have the ability to dictate whether environment changes
     * are allowed or not.
     * @param environment The {@link Environmentable} to change to
     * @return {@code true} if the environment changed sucessfully, {@code false} if not.
     */
    boolean changeEnvironment(Environmentable environment);

    /**
     * Gets the current game being played.
     * @return The {@link Gameable} object
     */
    Gameable getCurrentGame();

    /**
     * Gets the {@link Blueprintable} that defines the layout of the arena
     * @return the {@link Blueprintable} object
     */
    Blueprintable getBlueprint();
}
