package implement.game.actions;

import framework.game.Gameable;

/**
 * An action that ends the game.
 */
public class EndGame extends AbstractAction {

    private final Gameable game;

    public EndGame(String name, String description, Gameable game) {
        super(name, description);
        this.game = game;
    }

    @Override
    public void performAction() {
        game.capturePresentState();
        game.endGame();
    }
}
