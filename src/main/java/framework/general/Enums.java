package framework.general;

/**
 * Created by Lex on 11/04/2014.
 */
public final class Enums {

    public enum GameStatus {
        STOPPED(0), STARTED(1), RESTARTING(2);

        private int value;

        private GameStatus(int value) {
            this.value = value;
        }
    }
}
