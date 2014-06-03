package register.skeleton;

import framework.general.Informable;

/**
 * Created by Lucas on 6/1/2014.
 */
public class Informer implements Informable {

    private String name, description;

    public Informer(String name, String description) {
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
