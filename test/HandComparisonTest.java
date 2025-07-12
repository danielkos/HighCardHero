import PokerCard.Card;
import PokerCard.CardSuit;
import PokerCard.CardValue;
import PokerHand.Hand;
import PokerHand.HandRank;
import PokerHand.RankedHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandComparisonTest {

    /**
     * Helper function to evaluate, compare hands and assert a result
     *
     * @param first First poker hand
     * @param second Second poker hand
     * @param expected Expected compare result
     */
    void assertCompare(Hand first, Hand second, int expected) {
        assertEquals(expected, HandEvaluator.evaluate(first).compareTo(HandEvaluator.evaluate(second)));
    }

    /**
     * Test regarding same rank but possibly different tiebreakers
     */
    @Test
    void compareHighCard() {
        Hand h1 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.K),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.J))
        );

        Hand h2 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.A),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.J))
        );
        assertCompare(h1, h2, -1);
    }

    @Test
    void comparePair() {
        Hand h1 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.A),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.TWO),
                new Card(CardSuit.C, CardValue.J)
        ));

        Hand h2 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.FOUR),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.TWO),
                new Card(CardSuit.C, CardValue.J)
        ));
        assertCompare(h1, h2, -1);
    }

    @Test
    void compareTwoPair() {
        Hand h1 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.A),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.TWO),
                new Card(CardSuit.C, CardValue.A)
        ));
        Hand h2 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.A),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.TWO),
                new Card(CardSuit.C, CardValue.A)
        ));
        assertCompare(h1, h2, 0);
    }

    @Test
    void compareTreeOfAKind() {
        Hand h1 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.A),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.A),
                new Card(CardSuit.C, CardValue.A)
        ));

        Hand h2 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.FOUR),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.A),
                new Card(CardSuit.C, CardValue.FOUR)
        ));
        assertCompare(h1, h2, 1);
    }

    @Test
    void compareStraight() {
        Hand h1 = new Hand(List.of(

                new Card(CardSuit.S, CardValue.FIVE),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.H, CardValue.THREE),
                new Card(CardSuit.C, CardValue.SEVEN)
        ));

        Hand h2 = new Hand(List.of(
                new Card(CardSuit.H, CardValue.THREE),
                new Card(CardSuit.S, CardValue.FIVE),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.C, CardValue.SIX)
        ));
        assertCompare(h1, h2, 1);
    }

    @Test
    void compareFlush() {
        Hand h1 = new Hand(List.of(
                new Card(CardSuit.D, CardValue.Q),
                new Card(CardSuit.D, CardValue.THREE),
                new Card(CardSuit.D, CardValue.A),
                new Card(CardSuit.D, CardValue.FIVE),
                new Card(CardSuit.D, CardValue.J)
        ));

        Hand h2 = new Hand(List.of(
                new Card(CardSuit.D, CardValue.J),
                new Card(CardSuit.D, CardValue.THREE),
                new Card(CardSuit.D, CardValue.A),
                new Card(CardSuit.D, CardValue.FIVE),
                new Card(CardSuit.D, CardValue.J)
        ));

        assertCompare(h1, h2, 1);
    }

    @Test
    void compareFullHouse() {
        Hand h1 = new Hand(List.of(
                new Card(CardSuit.D, CardValue.Q),
                new Card(CardSuit.H, CardValue.Q),
                new Card(CardSuit.D, CardValue.A),
                new Card(CardSuit.S, CardValue.Q),
                new Card(CardSuit.C, CardValue.A)
        ));

        Hand h2 = new Hand(List.of(
                new Card(CardSuit.D, CardValue.Q),
                new Card(CardSuit.H, CardValue.Q),
                new Card(CardSuit.D, CardValue.A),
                new Card(CardSuit.S, CardValue.A),
                new Card(CardSuit.C, CardValue.A)
        ));


        assertCompare(h1, h2, -1);
    }

    @Test
    void compareFourOfAKind() {
        Hand h1 = new Hand(List.of(
                new Card(CardSuit.H, CardValue.NINE),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.D, CardValue.NINE),
                new Card(CardSuit.H, CardValue.NINE),
                new Card(CardSuit.S, CardValue.A)
        ));

        Hand h2 = new Hand(List.of(
                new Card(CardSuit.H, CardValue.NINE),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.D, CardValue.NINE),
                new Card(CardSuit.H, CardValue.NINE),
                new Card(CardSuit.S, CardValue.A)
        ));

        assertCompare(h1, h2, 0);
    }

    @Test
    void compareStraightFlush() {
        Hand h1 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.SEVEN),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.J)
        ));

        Hand h2 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.J),
                new Card(CardSuit.C, CardValue.Q)
        ));

        assertCompare(h1, h2, -1);
    }

    @Test
    void compareTreeOfAKindVSPair() {
        Hand h1 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.A),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.A),
                new Card(CardSuit.C, CardValue.A)
        ));

        Hand h2 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.FIVE),
                new Card(CardSuit.H, CardValue.FOUR),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.A),
                new Card(CardSuit.C, CardValue.TWO)
        ));
        assertCompare(h1, h2, 1);
    }

    /**
     * Test regarding different ranks and tiebreakers
     */
    @Test
    void compareTwoPairsVSStraight() {
        Hand h1 = new Hand(List.of(
                new Card(CardSuit.S, CardValue.FIVE),
                new Card(CardSuit.D, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.H, CardValue.THREE),
                new Card(CardSuit.C, CardValue.SEVEN)
        ));

        Hand h2 = new Hand(List.of(
                new Card(CardSuit.H, CardValue.THREE),
                new Card(CardSuit.S, CardValue.FIVE),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.C, CardValue.SIX)
        ));
        assertCompare(h1, h2, -1);
    }

    @Test
    void compareStraightFlushVSFlush() {
        Hand h1 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.SEVEN),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.J)
        ));

        Hand h2 = new Hand(List.of(
                new Card(CardSuit.D, CardValue.Q),
                new Card(CardSuit.H, CardValue.Q),
                new Card(CardSuit.D, CardValue.A),
                new Card(CardSuit.S, CardValue.A),
                new Card(CardSuit.C, CardValue.A)
        ));

        assertCompare(h1, h2, 1);
    }

    @Test
    void compareHighCardVSFourOfAKind() {
        Hand h1 = new Hand(List.of(
                new Card(CardSuit.H, CardValue.NINE),
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.D, CardValue.J),
                new Card(CardSuit.H, CardValue.THREE),
                new Card(CardSuit.S, CardValue.A)
        ));

        Hand h2 = new Hand(List.of(
                new Card(CardSuit.H, CardValue.NINE),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.D, CardValue.NINE),
                new Card(CardSuit.H, CardValue.NINE),
                new Card(CardSuit.S, CardValue.A)
        ));

        assertCompare(h1, h2, -1);
    }

    @Test
    void compareTwoPairVSFullHouse() {
        Hand h1 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.A),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.TWO),
                new Card(CardSuit.C, CardValue.A)
        ));
        Hand h2 = new Hand(List.of(
                new Card(CardSuit.C, CardValue.J),
                new Card(CardSuit.H, CardValue.A),
                new Card(CardSuit.D, CardValue.J),
                new Card(CardSuit.S, CardValue.J),
                new Card(CardSuit.C, CardValue.A)
        ));
        assertCompare(h1, h2, -1);
    }

    @Test
    void compareStraightVSFourOfAKind() {
        Hand h1 = new Hand(List.of(

                new Card(CardSuit.S, CardValue.Q),
                new Card(CardSuit.D, CardValue.A),
                new Card(CardSuit.C, CardValue.J),
                new Card(CardSuit.H, CardValue.K),
                new Card(CardSuit.C, CardValue.TEN)
        ));

        Hand h2 = new Hand(List.of(
                new Card(CardSuit.H, CardValue.THREE),
                new Card(CardSuit.S, CardValue.SIX),
                new Card(CardSuit.D, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX),
                new Card(CardSuit.C, CardValue.SIX)
        ));
        assertCompare(h1, h2, -1);
    }
}