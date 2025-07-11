import PokerCard.Card;
import PokerCard.CardValue;
import PokerCard.CardSuit;
import PokerHand.Hand;
import PokerHand.RankedHand;

import java.util.List;


public class Main {

    public static void main(String[] args) {

        System.out.println("Hello and welcome!");

        Hand first = new Hand(List.of(
            new Card(CardSuit.C, CardValue.TWO),
            new Card(CardSuit.H, CardValue.TWO),
            new Card(CardSuit.D, CardValue.FOUR),
            new Card(CardSuit.S, CardValue.FIVE),
            new Card(CardSuit.C, CardValue.SEVEN)
        ));

        Hand second = new Hand(List.of(
            new Card(CardSuit.C, CardValue.THREE),
            new Card(CardSuit.D, CardValue.THREE),
            new Card(CardSuit.D, CardValue.THREE),
            new Card(CardSuit.S, CardValue.SIX),
            new Card(CardSuit.H, CardValue.EIGHT)
        ));

        // Rank both hands
        final RankedHand firstRankedHand = HandEvaluator.evaluate(first);
        final RankedHand secondRankedHand = HandEvaluator.evaluate(second);
        // Compare ranks
        final int result = firstRankedHand.compareTo(secondRankedHand);

        // Display result
        System.out.println("Competing hands: ");
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
}