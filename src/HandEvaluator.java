import PokerCard.Card;
import PokerCard.CardValue;
import PokerHand.Hand;
import PokerHand.HandRank;
import PokerHand.RankedHand;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Evaluates a poker hand and determines it rank and potential tiebreaker card values
 */
public final class HandEvaluator {

    /**
     * Evaluate a poker hand and return a ranked hand#
     *
     * @param hand Poker hand of five cards
     * @return Ranked poker hand
     */
    public static RankedHand evaluate(Hand hand) {
        // Sorted cards in descending order
        final List<Card> descendingSortedCards = hand.cards()
                .stream()
                .sorted(Comparator.comparingInt((Card card) -> card.value().value()).reversed())
                .toList();
        // Sorted card value in ascending order
        final List<Integer> descendingSortedCardValues = descendingSortedCards
                .stream()
                .map(card -> card.value().value())
                .sorted(Comparator.reverseOrder())
                .toList();
        // Count occurrences of each card value
        final Map<CardValue, Long> cardValueCounts = descendingSortedCards
                .stream()
                .collect(Collectors.groupingBy(Card::value, Collectors.counting()));

        // Check if there is a flush before checking straight flush
        // Meaning all cards have the same suit
        final boolean hasFlush = descendingSortedCards.stream().map(Card::suit).distinct().count() == 1;

        // Check if there is a straight before checking straight flush
        boolean hasStraight = true;
        for(int i = 1; i < descendingSortedCardValues.size(); i++) {
            if(descendingSortedCardValues.get(i - 1) - descendingSortedCardValues.get(i) != 1) {
                hasStraight = false;
                break;
            }
        }

        // 1.Check for highest rank STRAIGHT_FLUSH and a possibly early return
        // Tiebreakers: Highest card in hand
        if(hasFlush && hasStraight) {
            return new RankedHand(HandRank.STRAIGHT_FLUSH, List.of(descendingSortedCardValues.getFirst()));
        }

        // 2. Check for second-highest rank FOUR_OF_A_KIND
        // Tiebreakers: Value of 4 cards
        // Find "four of a kind" if available
        final Optional<Map.Entry<CardValue, Long>> fourOfAKind = cardValueCounts
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 4)
                .findFirst();

        if(fourOfAKind.isPresent()) {
            // Add tiebreaker
            final CardValue fourOfAkindCardValue = fourOfAKind.get().getKey();
            return new RankedHand(HandRank.FOUR_OF_A_KIND, List.of(fourOfAkindCardValue.value()));
        }

        // 3. Check for rank FULL_HOUSE
        // Tiebreakers: Value of 3 cards
        // Required: three of a kind and pair

        // Also used for three of a kind rank
        final Optional<Map.Entry<CardValue, Long>> threeOfAKind = cardValueCounts
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 3)
                .findFirst();
        // Also used for double and single pairs
        final List<CardValue> allPairsDescendingOrder = cardValueCounts
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 2)
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparingInt(CardValue::value).reversed())
                .toList();

        if(threeOfAKind.isPresent() && !allPairsDescendingOrder.isEmpty()) {
            // Add tiebreaker
            final CardValue threeOfAkindCardValue = threeOfAKind.get().getKey();
            return new RankedHand(HandRank.FULL_HOUSE, List.of(threeOfAkindCardValue.value()));
        }

        // 4. Check for rank FLUSH
        // Tiebreakers: Rules if HIGH_CARD
        if(hasFlush) {
            return new RankedHand(HandRank.FLUSH, List.copyOf(descendingSortedCardValues));
        }

        // 5. Check for rank STRAIGHT
        // Tiebreakers: Rules
        if(hasStraight) {
            return new RankedHand(HandRank.STRAIGHT, List.of(descendingSortedCardValues.getFirst()));
        }

        // 6. Check for rank THREE_OF_A_KIND
        // Tiebreakers: Value of 3 cards
        if(threeOfAKind.isPresent()) {
            // Add tiebreaker
            final CardValue threeOfAkindCardValue = threeOfAKind.get().getKey();
            return new RankedHand(HandRank.THREE_OF_A_KIND, List.of(threeOfAkindCardValue.value()));
        }

        // 7. Check for rank TWO_PAIR
        // Tiebreakers: Value of high and low pair, finally value of left over card
        if(allPairsDescendingOrder.size() == 2) {
            // Add remaining card value as tie additional tiebreaker
            final CardValue remainingCardValue = cardValueCounts
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() == 1)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElseThrow();

            return new RankedHand(HandRank.TWO_PAIR, List.of(
                    allPairsDescendingOrder.getFirst().value(),
                    allPairsDescendingOrder.getLast().value(),
                    remainingCardValue.value())
            );
        }

        // 7. Check for rank PAIR
        // Tiebreakers: Value of pair, finally value of left over cards
        if(allPairsDescendingOrder.size() == 1) {
            // Add remaining card value as tie additional tiebreaker
            final List<Integer> remainingCardValues = cardValueCounts
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() == 1)
                    .map(entry -> entry.getKey().value())
                    .sorted(Comparator.reverseOrder())
                    .toList();

            final List<Integer> tiebreakers = new ArrayList<>();
            tiebreakers.add(allPairsDescendingOrder.getFirst().value());
            tiebreakers.addAll(remainingCardValues);

            return new RankedHand(HandRank.PAIR, tiebreakers);
        }

        // 8. Return rank HIGH_CARD
        return new RankedHand(HandRank.HIGH_CARD, descendingSortedCardValues);
    }
}
