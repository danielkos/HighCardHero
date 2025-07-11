package PokerHand;

import PokerCard.Card;
import PokerCard.CardSuit;
import PokerCard.CardValue;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    @Test
    void testIllegalAmountOfCards() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hand(List.of(new Card(CardSuit.H, CardValue.A))));
    }
    @Test
    void testToString() {
        Hand someHand = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.D, CardValue.NINE),
                new Card(CardSuit.H, CardValue.J),
                new Card(CardSuit.S, CardValue.Q),
                new Card(CardSuit.C, CardValue.A)
        ));

        assertEquals("[C: TWO, D: NINE, H: J, S: Q, C: A]", someHand.toString());
    }
}