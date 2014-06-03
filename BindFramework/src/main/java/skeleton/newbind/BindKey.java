package skeleton.newbind;

import skeleton.newbind.layer.Localizable;

import java.util.Collection;
import java.util.UUID;

/**
 * Created by Lucas on 6/1/2014.
 */
public interface BindKey<T> {

    /**
     * Gets the class that this key represents
     *
     * @return
     */
    Class<? super T> getIdentity();

    /**
     * Get the unique identifier for this bind key.
     * Every bind key has a different identifier assigned to it.
     * @return The UUID associated with the bind key
     */
    UUID getIdentifier();

    /**
     * The Bindable that owns this key
     * @return The owner of the bind key
     */
    Identifiable getOwner();

    /**
     * The parent(or parents) that the bindable object wants updates on
     * @return
     */
    Collection<Identifiable> observes();

    /**
     * Gets the space that this bind key is associated with.
     * The default is the main global space, which spans accross all sessions.
     * @return The Local space the bind key is located in
     */
    Localizable getLocality();

    void setLocality(Localizable newLocal);
}
