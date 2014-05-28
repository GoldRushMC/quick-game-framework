package com.goldrushmc.samples.trigger

import org.bukkit.event.player.PlayerInteractEntityEvent
import org.bukkit.entity.Player
import framework.team.Teamable
import java.util
import framework.game.Ruleable
import scala.collection.JavaConverters._

/**
 * Alerts the rule when a user has come in contact with their teammate.
 */
class IsTeammate(rule: Ruleable) extends Touch[Player]("Detects when a player interacts with a teammate", rule) {
  override def matchCondition(pEvt: PlayerInteractEntityEvent) = {
    pEvt.getRightClicked match {
      case p: Player =>
        val interactor = pEvt.getPlayer
        val teams = rule.getGame.getContainer.getTeams.asScala.filter(x => x.getMembers.containsAll(List(interactor, p).asJavaCollection))
        if (teams.length == 1) true
    }
    false
  }
}
