package implement.game.actions;

import framework.game.Actionable;
import implement.general.AbstractInformable;

/**
 * User: InspiredIdealist
 * Date: 4/13/2014
 */
public abstract class AbstractAction extends AbstractInformable implements Actionable{

    boolean enabled = true;

    protected AbstractAction(String name, String description) {
        super(name, description);
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setState(boolean setTo) {
        enabled = setTo;
    }
}
