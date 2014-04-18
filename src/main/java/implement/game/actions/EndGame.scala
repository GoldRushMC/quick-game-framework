package implement.game.actions

import framework.game.{Gameable, Playable}


abstract class GameStateChange(name: String, desc: String, val game: Playable) extends AbstractAction(name, desc) {

  override def performAction() {
    changeGameState()
    enabled = false
  }

  def changeGameState()
}

/**
 * An action that ends the game.
 */
class EndGame(game: Playable) extends GameStateChange("EndGame", "Ends the game", game){
  override def changeGameState() {
    game.capturePresentState()
    game.endGame()
  }

}

/**
 * An action that ends the game.
 */
class StartGame(game: Playable) extends GameStateChange("EndGame", "Ends the game", game){
  override def changeGameState() {
    game.startGame()
  }
}