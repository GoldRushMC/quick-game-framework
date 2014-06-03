package register.skeleton;

import register.Resolvable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Contains objects that can resolve other objects
 */
public class ResolvableCollection<T> extends ArrayList<Resolvable<? extends T>> {

    private boolean resolved;
    private Collection<T> resolvedCollection;


    public Collection<T> getResolvedCollection() {
        if(resolvedCollection == null) {
            List<T> resolved = new ArrayList<T>();
            this.forEach(tResolvable -> {
                try {
                    resolved.add(tResolvable.resolveInstance());
                } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            });
            this.resolved = true;
            this.resolvedCollection = resolved;
        }
        return resolvedCollection;
    }

    public boolean isResolved() {
        return resolved;
    }
}
