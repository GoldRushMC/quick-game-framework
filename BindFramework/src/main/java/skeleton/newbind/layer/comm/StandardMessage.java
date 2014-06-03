package skeleton.newbind.layer.comm;

import skeleton.newbind.layer.Localizable;

/**
 * The normal, most common implementation of message
 */
public class StandardMessage extends MessageBase<Object> {

    public StandardMessage(Localizable sender, Object payload) {
        super(sender, payload);
    }
}
