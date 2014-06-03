package skeleton.newbind.layer;

import skeleton.newbind.layer.comm.Message;

import java.util.Collection;

/**
 * Represents a layer of localization
 */
public interface Layerable extends Localizable {

    public static Layerable GLOBAL_SPACE = new GlobalLocalizableBase();

    /**
     * Retrieves all current Localizable objects associated with this layer
     * @return A Collection of Localizables
     */
    Collection<? extends Localizable> getAllLocals();

    /**
     * Adds a new Localizable to this layer,
     * and registers it accordingly.
     * @param local
     */
    void registerLocal(Localizable local);

    /**
     * Moves a Localizable from the current layer to the one requested
     * @param toMove the local object to move
     * @param layerRequested the layer to move the local object to
     */
    void moveLocal(Localizable toMove, Layerable layerRequested);

    void sendMessageToLocals(Message message);

}
