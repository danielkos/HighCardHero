package PokerCard;

// All available card values.
// Using enum values to improve readability
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

    private final int value;

    CardValue(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }
}
