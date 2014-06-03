package skeleton.newbind.layer;

import skeleton.newbind.layer.comm.Message;

/**
 * Created by Lucas on 6/1/2014.
 */
public interface Localizable {

    /**
     * The Localizable must decide how "far" another Localizable must be, before it is
     * too distant to be detected.
     * @param local
     * @return
     */
    boolean canDetect(Localizable local);

    /**
     * Gets the current layer this Localizable is in.
     * @return The layer number
     */
    Layerable getCurrentLayer();

    /**
     * Sets the current layer that the Localizable is in.
     * @param newLayer The new layer to move to
     */
    void setLocal(Layerable newLayer);

    void onMessageReceived(Message message);
}
