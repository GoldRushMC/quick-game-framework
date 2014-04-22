package com.goldrushmc.samples.action

import framework.game.Ruleable
import implement.game.actions.AbstractAction


abstract class GameStateChange(name: String, desc: String, rule: Ruleable) extends AbstractAction(name, desc, rule) {

  val game = rule.getGame.getContainer

  override def performAction(args: AnyRef*) {
    game.capturePresentState()
    changeGameState()
    enabled = false
  }

  def changeGameState()
}

/**
 * An action that ends the game.
 */
class EndGame(rule: Ruleable) extends GameStateChange("EndGame", "Ends the game", rule){
  override def changeGameState() {
    game.endGame()
  }

}

/**
 * An action that ends the game.
 */
class StartGame(rule: Ruleable) extends GameStateChange("EndGame", "Ends the game", rule){
  override def changeGameState() {
    game.startGame()
  }
}