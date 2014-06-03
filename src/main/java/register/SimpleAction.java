package register;

import framework.game.Progress;
import implement.game.actions.AbstractAction;
import skeleton.newbind.layer.Layerable;
import skeleton.newbind.layer.comm.Message;
import skeleton.newbind.layer.comm.StrongTypeMessage;

import java.util.Optional;

/**
 * Created by Lucas on 6/1/2014.
 */
public class SimpleAction extends AbstractAction {

    public SimpleAction(Layerable layer, Class matchingType) {
        super(layer, matchingType);
    }

    @Override
    public void performAction(Object... args) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void onGameStateChanged(Progress state) {

    }

    @Override
    protected Optional messageConverter(Message message) {
        return null;
    }

    @Override
    public void handleMessage(StrongTypeMessage message) {

    }
}
