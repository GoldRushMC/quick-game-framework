package framework.arena;

import framework.general.Informable;
import framework.save.Saveable;
import org.bukkit.block.Biome;

/**
 * The Environmentable interface represents a particular environment that can be used by Arenas
 */
public interface Environmentable extends Saveable {

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
}