package skeleton.newbind.layer;

import skeleton.newbind.layer.comm.Message;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Base functionality for all layers
 */
public class LayerBase extends LocalizeBase implements Layerable {

    protected List<Localizable> localObjects = new ArrayList<>();
    protected Layerable currentLayer;

    /**
     * Registers self to the Global layer
     */
    public LayerBase() {
        Layerable.GLOBAL_SPACE.registerLocal(this);
    }

    /**
     * Registers self to the given layer instance
     * @param parentLayer the layer to register to
     */
    public LayerBase(Layerable parentLayer) {
        parentLayer.registerLocal(this);
    }

    @Override
    public Collection<Localizable> getAllLocals() {
        return localObjects;
    }

    @Override
    public void registerLocal(Localizable local) {
        localObjects.add(local);
    }

    @Override
    public void moveLocal(Localizable toMove, Layerable layerRequested) {
        layerRequested.registerLocal(toMove);
        localObjects.remove(toMove);
        toMove.setLocal(layerRequested);
    }

    @Override
    public void onMessageReceived(Message message) {
        //Ensure downward propagation of message
        sendMessageToLocals(message);
    }

    @Override
    public boolean canDetect(Localizable local) {
        return localObjects.contains(local) ||
               local.getCurrentLayer().canDetect(this);
    }

    @Override
    public void sendMessageToLocals(Message message) {
        //Pass message along to locals
        localObjects.forEach(local -> local.onMessageReceived(message));
    }
}
