package PokerHand;

import java.util.List;
import PokerCard.Card;

/**
 * Simple Hand record which contains exactly five cards
 * Using record for automated generation of public getters
 *
 * @param cards All card part of this hand
 */
public record Hand(List<Card> cards) {

    /**
     * Constructs a poker hand containing exactly 5 cards
     *
     * @param cards Five cards representing this hand
     * @throws IllegalArgumentException If amount of provided cards does not equal 5
     */
    public Hand(List<Card> cards) {
        if (cards.size() != 5)  throw new IllegalArgumentException("A poker hand must consist of exactly 5 cards");
        // Defensive copy
        this.cards = List.copyOf(cards);
    }

    /**
     * Returns the hand and it's cards as string.
     *
     * @return Hand as formatted string
     */
    @Override
    public String toString() {
        return cards.toString();
    }
}
