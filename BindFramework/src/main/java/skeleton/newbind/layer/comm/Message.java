package skeleton.newbind.layer.comm;

import skeleton.newbind.layer.Localizable;

import java.util.Optional;

/**
 * Messages can be passed between Layers and Local objects,
 * And can propagate throughout the network
 */
public interface Message {

    Localizable getSender();

    Object getPayload();

    <T> Optional<T> tryAdaptValue(Class<? super T> type);

    Class<?> messageType();

}
