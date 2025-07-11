import PokerCard.Card;
import PokerCard.CardSuit;
import PokerCard.CardValue;
import PokerHand.Hand;
import PokerHand.HandRank;
import PokerHand.RankedHand;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HandEvaluatorTest {

    @Test
    void evaluateHighCard() {
        Hand highCard = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.A),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.J)
        ));
        RankedHand evaluated = HandEvaluator.evaluate(highCard);

        assertEquals(HandRank.HIGH_CARD, evaluated.rank());
        assertEquals(List.of(14, 11, 5, 4, 2), evaluated.tieBreakerCardValues());
    }

    @Test
    void evaluatePair() {
        Hand highCard = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.A),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.TWO),
                new Card(CardSuit.C, CardValue.J)
        ));
        RankedHand evaluated = HandEvaluator.evaluate(highCard);

        assertEquals(HandRank.PAIR, evaluated.rank());
        assertEquals(List.of(2, 14, 11, 4), evaluated.tieBreakerCardValues());
    }

    @Test
    void evaluateTwoPair() {
        Hand highCard = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.A),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.TWO),
                new Card(CardSuit.C, CardValue.A)
        ));
        RankedHand evaluated = HandEvaluator.evaluate(highCard);

        assertEquals(HandRank.TWO_PAIR, evaluated.rank());
        assertEquals(List.of(14, 2, 4), evaluated.tieBreakerCardValues());
    }

    @Test
    void evaluateTreeOfAKind() {
        Hand highCard = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.A),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.A),
                new Card(CardSuit.C, CardValue.A)
        ));
        RankedHand evaluated = HandEvaluator.evaluate(highCard);

        assertEquals(HandRank.THREE_OF_A_KIND, evaluated.rank());
        assertEquals(List.of(14), evaluated.tieBreakerCardValues());
    }

    @Test
    void evaluateStraight() {
        Hand highCard = new Hand(List.of(
                new Card(CardSuit.C, CardValue.TWO),
                new Card(CardSuit.H, CardValue.THREE),
                new Card(CardSuit.D, CardValue.FOUR),
                new Card(CardSuit.S, CardValue.FIVE),
                new Card(CardSuit.C, CardValue.SIX)
        ));
        RankedHand evaluated = HandEvaluator.evaluate(highCard);

        assertEquals(HandRank.STRAIGHT, evaluated.rank());
        assertEquals(List.of(6), evaluated.tieBreakerCardValues());
    }

    @Test
    void evaluateFlush() {
        Hand highCard = new Hand(List.of(
                new Card(CardSuit.D, CardValue.Q),
                new Card(CardSuit.D, CardValue.THREE),
                new Card(CardSuit.D, CardValue.A),
                new Card(CardSuit.D, CardValue.FIVE),
                new Card(CardSuit.D, CardValue.J)
        ));
        RankedHand evaluated = HandEvaluator.evaluate(highCard);

        assertEquals(HandRank.FLUSH, evaluated.rank());
        assertEquals(List.of(14, 12, 11, 5, 3), evaluated.tieBreakerCardValues());
    }

    @Test
    void evaluateFullHouse() {
        Hand highCard = new Hand(List.of(
                new Card(CardSuit.D, CardValue.Q),
                new Card(CardSuit.H, CardValue.Q),
                new Card(CardSuit.D, CardValue.A),
                new Card(CardSuit.S, CardValue.Q),
                new Card(CardSuit.C, CardValue.A)
        ));
        RankedHand evaluated = HandEvaluator.evaluate(highCard);

        assertEquals(HandRank.FULL_HOUSE, evaluated.rank());
        assertEquals(List.of(12), evaluated.tieBreakerCardValues());
    }

    @Test
    void evaluateFourOfAKind() {
        Hand highCard = new Hand(List.of(
                new Card(CardSuit.H, CardValue.NINE),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.D, CardValue.NINE),
                new Card(CardSuit.H, CardValue.NINE),
                new Card(CardSuit.S, CardValue.A)
        ));
        RankedHand evaluated = HandEvaluator.evaluate(highCard);

        assertEquals(HandRank.FOUR_OF_A_KIND, evaluated.rank());
        assertEquals(List.of(9), evaluated.tieBreakerCardValues());
    }

    @Test
    void evaluateStraightFlush() {
        Hand highCard = new Hand(List.of(
                new Card(CardSuit.C, CardValue.SEVEN),
                new Card(CardSuit.C, CardValue.EIGHT),
                new Card(CardSuit.C, CardValue.NINE),
                new Card(CardSuit.C, CardValue.TEN),
                new Card(CardSuit.C, CardValue.J)
        ));
        RankedHand evaluated = HandEvaluator.evaluate(highCard);

        assertEquals(HandRank.STRAIGHT_FLUSH, evaluated.rank());
        assertEquals(List.of(11), evaluated.tieBreakerCardValues());
    }
}