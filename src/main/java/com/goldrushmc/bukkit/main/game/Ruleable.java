package com.goldrushmc.bukkit.main.game;

import com.goldrushmc.bukkit.main.Informable;
import org.bukkit.scoreboard.Objective;

import java.util.List;

/**
 * Ruleable is the contract for how the rules with games are expected to behave.
 *
 */
public interface Ruleable extends Informable{
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

}
