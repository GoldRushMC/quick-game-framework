package skeleton.newbind.layer.comm;

import skeleton.newbind.layer.Localizable;

/**
 * This message has a clearly defined payload
 */
public class StrongTypeMessage<U> extends MessageBase<U> {

    public StrongTypeMessage(Localizable sender, U payload) {
        super(sender, payload);
    }

    public U getTypedPayload() {
        return payload;
    }
}
