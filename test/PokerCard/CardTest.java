package PokerCard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {

    final Card testCard = new Card(CardSuit.D, CardValue.THREE);
    @Test
    void suit() {
        assertEquals(CardSuit.D, testCard.suit());
    }

    @Test
    void value() {
        assertEquals(CardValue.THREE.value(), testCard.value().value());
    }

    @Test
    void testToString() {
        assertEquals("D: THREE", testCard.toString());
    }
}