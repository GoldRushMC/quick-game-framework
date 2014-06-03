package inspire.message;

import framework.game.Progress;
import skeleton.newbind.layer.Localizable;
import skeleton.newbind.layer.comm.StrongTypeMessage;

/**
 * Created by Lucas on 6/2/2014.
 */
public class GameStateMessage extends StrongTypeMessage<Progress> {
    public GameStateMessage(Localizable sender, Progress payload) {
        super(sender, payload);
    }
}
