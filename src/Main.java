import PokerCard.Card;
import PokerCard.CardValue;
import PokerCard.CardSuit;
import PokerHand.Hand;
import PokerHand.RankedHand;

import java.util.List;
import java.util.Random;

public class Main {

    /**
     * Picks randomly a card suit
     *
     * @return Randomly chosen card suit
     */
    private static CardSuit randomCardSuit() {
        final int index =  new Random().nextInt(CardSuit.values().length);
        return CardSuit.values()[index];
    }

    /**
     * Picks randomly a card value
     *
     * @return Randomly chosen card value
     */
    private static CardValue randomCardValue() {
        final int index =  new Random().nextInt(CardValue.values().length);
        return CardValue.values()[index];
    }

    /**
     * Returns a randomly generate hand
     *
     * @return A hand of 5 randomly generated cards
     */
    private static Hand randomHand() {
        return new Hand(List.of(
                new Card(randomCardSuit(), randomCardValue()),
                new Card(randomCardSuit(), randomCardValue()),
                new Card(randomCardSuit(), randomCardValue()),
                new Card(randomCardSuit(), randomCardValue()),
                new Card(randomCardSuit(), randomCardValue())));
    }

    /**
     * Evaluates both hands and selects a winning hand.
     * Prints hands and winning rank.
     *
     * @param first First competing hand
     * @param second Second competing hand
     */
    public static void chooseWinningHand(Hand first, Hand second) {
        // Rank hands
        final RankedHand firstRankedHand = HandEvaluator.evaluate(first);
        final RankedHand secondRankedHand = HandEvaluator.evaluate(second);
        // Compare ranks
        final int result = firstRankedHand.compareTo(secondRankedHand);

        // Display result
        System.out.println("1: " + first);
        System.out.println("2: " + second);

        if (result > 0) {
            System.out.println("First hand wins: " + firstRankedHand + "!");
        } else if (result < 0) {
            System.out.println("Second hand wins: " + secondRankedHand + "!");
        } else  {
            System.out.println("It's a tie");
        }
    }

    public static void main(String[] args) {

        System.out.println("Hello and welcome!");
        // Two predefined hands
        final Hand first = new Hand(List.of(
            new Card(CardSuit.C, CardValue.TWO),
            new Card(CardSuit.H, CardValue.TWO),
            new Card(CardSuit.D, CardValue.FOUR),
            new Card(CardSuit.S, CardValue.FOUR),
            new Card(CardSuit.C, CardValue.SEVEN)
        ));

        final Hand second = new Hand(List.of(
            new Card(CardSuit.C, CardValue.THREE),
            new Card(CardSuit.D, CardValue.THREE),
            new Card(CardSuit.D, CardValue.THREE),
            new Card(CardSuit.S, CardValue.SIX),
            new Card(CardSuit.H, CardValue.SIX)
        ));

        // Two random hands
        final Hand rndFirst = randomHand();
        final Hand rndSecond = randomHand();

        // Display result
        System.out.println("Competing predefined hands: ");
        chooseWinningHand(first, second);

        System.out.println();

        // Display result
        System.out.println("Competing random hands: ");
        chooseWinningHand(rndFirst, rndSecond);
    }
}