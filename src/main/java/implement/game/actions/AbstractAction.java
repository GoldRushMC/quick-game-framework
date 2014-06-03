package implement.game.actions;

import framework.game.Actionable;
import framework.game.Progress;
import skeleton.newbind.layer.Layerable;
import skeleton.newbind.layer.bind.Receptor;
import skeleton.newbind.layer.comm.Message;
import skeleton.newbind.layer.comm.StrongTypeMessage;

import java.util.Optional;

/**
 * User: InspiredIdealist
 * Date: 4/13/2014
 */
public abstract class AbstractAction<T extends StrongTypeMessage<?>> extends Receptor<T> implements Actionable {

    protected boolean enabled = true;

    protected AbstractAction(Layerable layer, Class<T> matchingType) {
        super(layer, matchingType);
    }

    protected AbstractAction(Class<T> matchingType) {
        super(matchingType);
    }

    protected AbstractAction(Layerable layer) {
        super(layer);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setState(boolean setTo) {
        enabled = setTo;
    }

    @Override
    public void onMessageReceived(Message message) {
        Optional<Progress> prog = message.tryAdaptValue(Progress.class);
        if(prog.isPresent()) {
            onGameStateChanged(prog.get());
        } else super.onMessageReceived(message);
    }

    public abstract void onGameStateChanged(Progress state);
}
