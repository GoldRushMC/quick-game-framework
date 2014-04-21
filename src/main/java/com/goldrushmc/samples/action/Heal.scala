package com.goldrushmc.samples.action

import org.bukkit.entity.Player
import implement.game.actions.AbstractAction
import framework.game.Ruleable

/**
 * An action that can heal a target player
 */
class Heal(private val healAmount: Double, rule: Ruleable) extends AbstractAction(null, "Heals the target player", rule) {

  override def performAction(args: AnyRef*): Unit = args match { case player: Player => player.setHealth(healAmount) }

}
