package skeleton.newbind.layer.bind;

import skeleton.newbind.Identifiable;
import skeleton.newbind.layer.Layerable;
import skeleton.newbind.layer.LocalizeBase;
import skeleton.newbind.layer.comm.Message;
import skeleton.newbind.layer.comm.StrongTypeMessage;

import java.util.Optional;
import java.util.UUID;

/**
 * A local object capable of sending strongly typed messages
 */
public abstract class Receptor<T extends StrongTypeMessage<?>> extends LocalizeBase implements Identifiable {

    /**
     * Used to filter messages
     */
    private final Class<? super T> typeMatcher;
    private final UUID id = UUID.randomUUID();

    public Receptor(Layerable layer) {
        super(layer);
        typeMatcher = Object.class;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public Receptor(Class<T> matchingType) {
        typeMatcher = matchingType;
    }

    public Receptor(Layerable layer, Class<T> matchingType) {
        super(layer);
        typeMatcher = matchingType;
    }

    @Override
    public void onMessageReceived(Message message) {
        if(typeMatcher.isInstance(message.messageType())) {
            Optional<T> handler = messageConverter(message);
            handler.ifPresent(this::handleMessage);
        }
    }

    protected abstract Optional<T> messageConverter(Message message);

    public abstract void handleMessage(T message);
}

