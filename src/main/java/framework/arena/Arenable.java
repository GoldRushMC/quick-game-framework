package framework.arena;

import framework.game.Gameable;

import java.util.Collection;
import java.util.List;

/**
 * The Arenable interface represents an arena that can host games
 */
public interface Arenable extends Environmentable {

    /**
     * Gets the types of {@link framework.game.Gameable}s supported by this arena.
     * @return A list of strings that represent the names of supported game types.
     */
    Collection<String> getRecommendedGameTypes();

    /**
     * Gets the in-game {@link framework.game.Gameable} interfaces that are supported.
     * By this environment.
     *
     * Use Case: The Voting Booth.
     *
     * @return The {@link List} of {@link framework.game.Playable} objects
     */
    Collection<Class<? extends Gameable>> getRecommendedGames();

    /**
     * Gets the current environment the arena has
     * @return The {@link Environmentable} object
     */
    Environmentable getCurrentEnvironment();

//    /**
//     * Attempts to change the current environment the arena is in.
//     * @param environment The {@link Environmentable} to change to
//     * @return {@code true} if the environment changed sucessfully, {@code false} if not.
//     */
//    boolean changeEnvironment(Environmentable environment);
//
//    /**
//     * Attempts to change the current environment the arena is in.
//     * A reason this may fail is because a game may not be yet finished,
//     * and games have the ability to dictate whether environment changes
//     * are allowed or not.
//     * @param environmentName The name of the {@link Environmentable} to change to. This is retrieved from the getEnvironments() list.
//     * @return {@code true} if the environment changed sucessfully, {@code false} if not.
//     */
//    boolean changeEnvironment(String environmentName);
}
