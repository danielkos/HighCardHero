package PokerHand;

import java.util.List;

/**
 * Class representing a poker hand which was already ranked/evaluated according to HandRank's
 * Decouples comparison of poker hands from ranking logic.
 * Applies HiGH CARD rules if no rank applicable.
 */
public class RankedHand implements Comparable<RankedHand> {
    // Rank of current hand
    private final HandRank rank;
    // Optional tiebreaker card values
    private final List<Integer> tieBreakerCardValues;

    /**
     * Constructs a ranked hand instance
     *
     * @param rank Rank of this instance, see HandRank
     * @param tieBreakerCardValues Array of tiebreaker card values
     */
    public RankedHand(HandRank rank, List<Integer> tieBreakerCardValues) {
        this.rank = rank;
        this.tieBreakerCardValues = tieBreakerCardValues;
    }

    /**
     * Returns current hand rank
     *
     * @return hand rank value
     */
    public HandRank rank() {
        return rank;
    }

    /**
     * Compares current ranked hand vs another one
     * If no ranks present, applies HIGH CARD rules.
     *
     * @param o Other ranked hand
     * @return negative, zero or positive value depending on if current hand is less, equal or greater than other
     */
    @Override
    public int compareTo(RankedHand o) {
        // First compare ranks
        int compareValue = Integer.compare(this.rank.ordinal(), o.rank().ordinal());
        // Return compare value if ranks are not the same
        if (compareValue != 0) return compareValue;

        // Otherwise check tiebreaker cards values
        // There might be multiple tiebreaker cards, e.g. 2 Pair vs 2 Pair
        final var minSize = Math.min(this.tieBreakerCardValues.size(), o.tieBreakerCardValues.size());
        for (int i = 0; i < minSize; i++) {
            // Compare tiebreaker card values
            final var tieBreakerCmpResult = this.tieBreakerCardValues.get(i).compareTo(o.tieBreakerCardValues.get(i));
            // Break loop if values are not equal, continue otherwise
            if(tieBreakerCmpResult != 0 ) {
                compareValue = tieBreakerCmpResult;
                break;
            };
        }

        return compareValue;
    }

    /**
     * Returns the rank of the hand as string.
     *
     * @return Rank as formatted string
     */
    @Override
    public String toString() {
        return " (" + rank + ")";
    }
}
