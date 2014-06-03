package skeleton.newbind.layer.comm;

import skeleton.newbind.layer.Localizable;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Lucas on 6/2/2014.
 */
public class CollectionMessage<T> extends StrongTypeMessage<Collection<T>> {

    public CollectionMessage(Localizable sender, Collection<T> payload) {
        super(sender, payload);
    }

    @Override
    public <T> Optional<T> tryAdaptValue(Class<? super T> type) {
        if(Collection.class.isAssignableFrom(type)) {
            if(payload.getClass().isAssignableFrom(type)) {
                return Optional.of((T)payload);
            }
        }
        return Optional.empty();
    }
}
