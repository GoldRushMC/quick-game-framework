package implement.general;

import framework.general.Informable;

/**
 * Removes some boilerplate code from other classes.
 */
public abstract class AbstractInformable implements Informable {

    protected String name, description;

    public AbstractInformable(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getName() {
        return name;
    }
}
