package register;

import java.lang.reflect.InvocationTargetException;

/**
 * Each Resolvable holds other resolvables that also must resolve
 */
public interface Resolvable<T> {

    T resolveInstance() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException;
}
