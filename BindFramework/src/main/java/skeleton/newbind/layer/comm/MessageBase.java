package skeleton.newbind.layer.comm;

import skeleton.newbind.layer.ImproperPayloadTypeException;
import skeleton.newbind.layer.Localizable;

import java.util.Optional;

/**
 * Created by Lucas on 6/2/2014.
 */
public abstract class MessageBase<T> implements Message {

    protected final Localizable sender;
    protected final T payload;

    public MessageBase(Localizable sender, T payload) {
        this.sender = sender;
        this.payload = payload;
    }

    public MessageBase(Message message, Class<T> type) throws ImproperPayloadTypeException {
        sender = message.getSender();
        Optional<T> payload = message.tryAdaptValue(type);
        if(payload.isPresent()) this.payload = payload.get();
        else throw new ImproperPayloadTypeException();
    }

    @Override
    public Object getPayload() {
        return payload;
    }

    @Override
    public <T> Optional<T> tryAdaptValue(Class<? super T> type) {
        if(type.isAssignableFrom(payload.getClass())) {
            return Optional.of((T)getPayload());
        }
        return Optional.empty();
    }

    @Override
    public Class<?> messageType() {
        return payload.getClass();
    }

    @Override
    public Localizable getSender() {
        return sender;
    }
}
