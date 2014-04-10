package com.goldrushmc.bukkit.main.game;

import com.goldrushmc.bukkit.main.Informable;

/**
 * The Actionable interface defines behavior behind how actions are to be performed
 * within the games.
 *
 */
public interface Actionable extends Informable {

    /**
     * Invokes the action tied to the instance.
     */
    void performAction();

    /**
     * Checks to see if the action is enabled or not.
     * @return {@code true} if enabled, {@code false} if not
     */
    boolean isEnabled();

    /**
     * Turns the action's state to either Enabled or Disabled.
     * @param setTo the state to choose. Enabled = true, Disabled = false
     */
    void setState(boolean setTo);
}
