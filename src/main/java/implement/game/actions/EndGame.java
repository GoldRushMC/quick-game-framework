package implement.game.actions;

import framework.game.Playable;

/**
 * An action that ends the game.
 */
public class EndGame extends AbstractAction {

    private final Playable game;

    public EndGame(String name, String description, Playable game) {
        super(name, description);
        this.game = game;
    }

    @Override
    public void performAction() {
        game.capturePresentState();
        game.endGame();
    }
}
