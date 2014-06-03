package framework.arena;

import framework.general.Informable;
import org.bukkit.block.Biome;

/**
 * The Environmentable interface represents a particular environment that can be used by Arenas
 */
public interface Environmentable extends Informable {

    /**
     * Gets the type of environment this is
     * @return The {@link Biome} enumerable
     */
    Biome getBiomeType();

    /**
     * Gets the {@link Blueprintable} that defines the layout of the environment
     * @return the {@link Blueprintable} object
     */
    Blueprintable getBlueprint();

    /**
     * Determines whether or not two envrionments can be used interchangeably
     * @param other The {@link Environmentable} to compare
     * @return {@code true} if they have the same Vector mapping, {@code false} if not.
     */
    boolean isCompatibleWith(Environmentable other);
}
