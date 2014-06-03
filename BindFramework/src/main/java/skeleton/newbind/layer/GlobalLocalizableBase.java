package skeleton.newbind.layer;

/**
 * Created by Lucas on 6/1/2014.
 */
public final class GlobalLocalizableBase extends LayerBase {

    @Override
    public boolean canDetect(Localizable local) {
        return true;
    }

    @Override
    public Layerable getCurrentLayer() {
        return this;
    }

    @Override
    public void setLocal(Layerable newLayer) {
        throw new UnsupportedOperationException("Cannot call setLocal() on the global space!");
    }
}
