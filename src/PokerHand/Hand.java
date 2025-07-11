package PokerHand;

import java.util.List;
import PokerCard.Card;

// Simple Hand record which contains exactly five cards
// Using record for automated generation of and public getters
public record Hand(List<Card> cards) {

    public Hand(List<Card> cards) {
        if (cards.size() != 5)  throw new IllegalArgumentException("A poker hand must consist of exactly 5 cards");
        // Defensive copy
        this.cards = List.copyOf(cards);
    }
}
