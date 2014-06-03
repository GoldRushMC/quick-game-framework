package skeleton.newbind.layer.comm;

import skeleton.newbind.layer.Localizable;

import java.util.Optional;

/**
 * Purely used to notify that a message has been opened by a local object
 */
public class RecieptMessage implements Message {

    @Override
    public Localizable getSender() {
        return null;
    }

    @Override
    public Object getPayload() {
        return null;
    }

    @Override
    public <T> Optional<T> tryAdaptValue(Class<? super T> type) {
        return null;
    }

    @Override
    public Class<?> messageType() {
        return null;
    }
}
