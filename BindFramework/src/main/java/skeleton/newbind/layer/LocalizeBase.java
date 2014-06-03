package skeleton.newbind.layer;

/**
 * Base functionality for all layers
 */
public abstract class LocalizeBase implements Localizable {

    protected Layerable currentLayer;

    /**
     * Registers self to the Global layer
     */
    public LocalizeBase() {
        Layerable.GLOBAL_SPACE.registerLocal(this);
    }

    /**
     * Registers self to the given layer instance
     * @param parentLayer the layer to register to
     */
    public LocalizeBase(Layerable parentLayer) {
        parentLayer.registerLocal(this);
    }

    @Override
    public Layerable getCurrentLayer() {
        return currentLayer;
    }

    @Override
    public void setLocal(Layerable newLayer) {
        currentLayer = newLayer;
    }

    @Override
    public boolean canDetect(Localizable local) {
        return local.getCurrentLayer() == this.currentLayer ||
               local.getCurrentLayer().canDetect(this);
    }
}
