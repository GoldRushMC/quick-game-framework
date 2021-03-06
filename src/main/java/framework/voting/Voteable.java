package framework.voting;

import com.google.common.base.Stopwatch;
import framework.general.Informable;

import java.util.List;
import java.util.Map;

/**
 * The Voteable Interface represents a vote
 */
public interface Voteable extends Informable {

    /**
     * The list of items to be voted upon.
     * @return The list of {@link Informable}s
     */
    List<Informable> getVoteInformables();

    /**
     * The Map of items to be voted upon and the number of votes received.
     * @return The Map of {@link Informable}s and {@link java.lang.Integer}s
     */
    Map<Informable, Integer> getVotesPerInformable();

    /**
     * Starts the player vote.
     */
    public void startVote();

    /**
     * Stops the player vote.
     */
    public void stopVote();

    /**
     * Determines whether the vote has time limit.
     * @return Boolean
     */
    boolean hasTimeLimit();

    /**
     * Gets the {@link Stopwatch} instance associated with this vote
     * @return the {@link Stopwatch} instance, or null if there is no time limit.
     */
    Stopwatch getTimer();
}
