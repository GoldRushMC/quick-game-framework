package framework.game;

import framework.team.Teamable;

/**
 * A Facade that takes care of controlling the high-level state of a game
 */
public interface Playable {

    void loadGame(Gameable game);

    void addTeam(Teamable team);

    void startGame();

    void endGame();
}
