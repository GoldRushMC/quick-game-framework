package framework.general;

/**
 * Interface that is used to enable an object to describe itself to the outside world.
 * Isn't that every object's dream?
 * Use Case: Can be used to help players in-game figure out what each element of the game represent.
 */
public interface Informable {

    /**
     * Gets the description of the object.
     * @return The description
     */
    String getDescription();

    /**
     * Gets the name of the object.
     * @return The name
     */
    String getName();
//
//    void setName(String name);
//
//    void setDescription(String description);
}
