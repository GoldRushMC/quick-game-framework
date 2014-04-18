package implement.game.triggers

import org.bukkit.event.player.PlayerLevelChangeEvent
import framework.game.Ruleable

/**
 * User: InspiredIdealist
 * Date: 4/18/2014
 */

abstract class LevelChange(description: String, rule: Ruleable) extends AbstractTrigger[PlayerLevelChangeEvent](null, description, rule) {

  override def matchCondition(t: PlayerLevelChangeEvent): Boolean = change(t.getNewLevel, t.getOldLevel)

  def change(newLvl: Int, oldLvl: Int): Boolean
}

class LevelUp(rule: Ruleable) extends LevelChange("Activates when a player levels up", rule) {
  override def change(newLvl: Int, oldLvl: Int) = newLvl > oldLvl
}

class LevelDown(rule: Ruleable) extends LevelChange("Activates when a player levels down", rule) {
  override def change(newLvl: Int, oldLvl: Int) = newLvl < oldLvl
}
