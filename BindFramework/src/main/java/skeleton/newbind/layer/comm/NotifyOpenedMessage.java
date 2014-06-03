package skeleton.newbind.layer.comm;

import skeleton.newbind.layer.Layerable;

/**
 * Message that notifies the sender if the message payload is accessed
 */
public class NotifyOpenedMessage extends StandardMessage {

    public NotifyOpenedMessage(Layerable sender, Object payload) {
        super(sender, payload);
    }

    @Override
    public Object getPayload() {
        getSender().onMessageReceived(new RecieptMessage());
        return super.getPayload();
    }
}
