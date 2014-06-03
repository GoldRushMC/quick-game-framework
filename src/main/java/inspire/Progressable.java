package inspire;

import framework.game.Participatable;
import framework.game.Progress;

/**
 * Holds the behavior for progressing in a game
 */
public interface Progressable {

    /**
     * Enables the given participant for progression in a game
     *
     * @param participant The participant to register for participation
     */
    void registerParticipant(Participatable participant);

    /**
     * Disables the given participant for progression in a game
     * @param participant The participant to unregister for participation
     */
    void unregisterParticipant(Participatable participant);

    /**
     * The type that is used to track the progress of goals.
     * @return The tracker type
     */
    Progress getProgress(Participatable requestor);

    /**
     * Gets the raw progress {@code int}
     * @return The total progress of this goal, out of 100.
     */
    int getRawProgress(Participatable requestor);

    /**
     * Gets a print-friendly version of the progress made.
     * @return The formatted string version of getProgress().
     */
    String getFormattedProgress(Participatable requestor);
}
