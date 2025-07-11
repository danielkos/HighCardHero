package PokerCard;

/**
 * Simple record representing a card which has a suit and a value
 * Using record for automated generation of constructor and public getters
 */
public record Card(CardSuit suit, CardValue value) {

    /**
     * Returns the card suit and value as string.
     *
     * @return Card suit and value  as formatted string
     */
    @Override
    public String toString() {
        return this.suit.name() + ": " + this.value.name();
    }
}
