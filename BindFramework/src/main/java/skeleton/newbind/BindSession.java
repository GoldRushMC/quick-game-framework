package skeleton.newbind;

import skeleton.newbind.layer.GlobalLocalizableBase;
import skeleton.newbind.layer.Layerable;
import skeleton.newbind.layer.comm.Message;

/**
 * Allows the layers to operate on a seperate thread, and send messages safely
 */
public class BindSession extends Thread {

    private static BindSession session;
    private final Layerable topLayer;

    private BindSession() {
        topLayer = new GlobalLocalizableBase();
    }

    public static void initialize() {
        session = new BindSession();
    }

    public static void dispatchToLayers(Message message) {
        session.topLayer.sendMessageToLocals(message);
    }


}
