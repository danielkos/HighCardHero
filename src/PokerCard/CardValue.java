package PokerCard;

/**
 * All available card values.
 * Using enum custom values to improve readability
 */
public enum CardValue {
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    J(11),
    Q(12),
    K(13),
    A(14);

    // Custom enum value
    private final int value;

    /**
     * Constructs a card value
     * @param value Custom card value
     */
    CardValue(int value) {
        this.value = value;
    }

    /**
     * Return card value
     * @return Custom card value
     */
    public int value() {
        return value;
    }
}
