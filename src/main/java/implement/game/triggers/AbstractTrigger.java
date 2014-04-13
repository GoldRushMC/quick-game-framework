package implement.game.triggers;

import framework.game.Ruleable;
import framework.game.Triggerable;
import implement.general.AbstractInformable;
import org.bukkit.event.Event;

/**
 * The Base implementation for {@link Triggerable}
 */
public abstract class AbstractTrigger<T extends Event> extends AbstractInformable implements Triggerable<T> {

    protected Ruleable rule;
    protected boolean isEnabled = true;

    public AbstractTrigger(String name, String description, Ruleable rule) {
        super(name, description);
    }

    @Override
    public Ruleable getRule() {
        return rule;
    }

    @Override
    public void resetTrigger() {
        isEnabled = true;
    }
}
