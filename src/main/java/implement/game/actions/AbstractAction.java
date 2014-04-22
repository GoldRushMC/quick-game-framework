package implement.game.actions;

import framework.game.Actionable;
import framework.game.Ruleable;
import implement.general.AbstractInformable;

/**
 * User: InspiredIdealist
 * Date: 4/13/2014
 */
public abstract class AbstractAction extends AbstractInformable implements Actionable{

    protected boolean enabled = true;
    protected Ruleable rule;

    protected AbstractAction(String name, String description, Ruleable rule) {
        super(name, description);
        this.rule = rule;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setState(boolean setTo) {
        enabled = setTo;
    }

    @Override
    public Ruleable getRule() {
        return rule;
    }
}
