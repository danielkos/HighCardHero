package PokerCard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardValueTest {

    @Test
    void testValues() {
        assertEquals(2, CardValue.TWO.value());
        assertEquals(3, CardValue.THREE.value());
        assertEquals(4, CardValue.FOUR.value());
        assertEquals(5, CardValue.FIVE.value());
        assertEquals(6, CardValue.SIX.value());
        assertEquals(7, CardValue.SEVEN.value());
        assertEquals(8, CardValue.EIGHT.value());
        assertEquals(9, CardValue.NINE.value());
        assertEquals(10, CardValue.TEN.value());
        assertEquals(11, CardValue.J.value());
        assertEquals(12, CardValue.Q.value());
        assertEquals(13, CardValue.K.value());
        assertEquals(14, CardValue.A.value());
    }
}