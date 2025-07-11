package PokerHand;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RankedHandTest {

    final RankedHand firstRank = new RankedHand(HandRank.FOUR_OF_A_KIND, List.of(10));
    final RankedHand secondRank = new RankedHand(HandRank.FLUSH, List.of(8, 7, 5, 3, 2));
    final RankedHand firstHighCard = new RankedHand(HandRank.HIGH_CARD, List.of(14, 13, 3, 7, 5));
    final RankedHand secondHighCard = new RankedHand(HandRank.HIGH_CARD, List.of(14, 9, 6, 3, 2));

    @Test
    void rank() {
        // Check ranks
        assertEquals(HandRank.FOUR_OF_A_KIND, firstRank.rank());
        assertEquals(HandRank.FLUSH, secondRank.rank());
        assertEquals(HandRank.HIGH_CARD, firstHighCard.rank());
        assertEquals(HandRank.HIGH_CARD, secondHighCard.rank());

        // Check tiebreakers
        assertEquals(List.of(10), firstRank.tieBreakerCardValues());
        assertEquals(List.of(8, 7, 5, 3, 2), secondRank.tieBreakerCardValues());
        assertEquals(List.of(14, 13, 3, 7, 5), firstHighCard.tieBreakerCardValues());
        assertEquals(List.of(14, 9, 6, 3, 2), secondHighCard.tieBreakerCardValues());
    }

    @Test
    void compareTo() {
        // Ranked hands
        assertEquals(-1, secondRank.compareTo(firstRank));
        assertEquals(0, firstRank.compareTo(firstRank));
        assertEquals(1, firstRank.compareTo(secondRank));
        // High card only
        assertEquals(1, firstHighCard.compareTo(secondHighCard));
        assertEquals(0, firstHighCard.compareTo(firstHighCard));
        assertEquals(-1, secondHighCard.compareTo(firstHighCard));
    }

    @Test
    void testToString() {
        assertEquals(" (FOUR_OF_A_KIND)", firstRank.toString());
        assertEquals(" (FLUSH)", secondRank.toString());
        assertEquals(" (HIGH_CARD)", firstHighCard.toString());
        assertEquals(" (HIGH_CARD)", secondHighCard.toString());
    }
}