package com.goldrushmc.samples.trigger

import org.bukkit.entity.Entity
import framework.game.Ruleable
import implement.game.triggers.AbstractTrigger
import org.bukkit.event.player.PlayerInteractEntityEvent

/**
 * Alerts the game when a user has come in contact with the specified entity.
 */
class Touch[T <: Entity](description: String, rule: Ruleable) extends AbstractTrigger[PlayerInteractEntityEvent](null, description, rule) {
  def matchCondition(pEvt: PlayerInteractEntityEvent): Boolean = pEvt.getRightClicked.isInstanceOf[T]
}
