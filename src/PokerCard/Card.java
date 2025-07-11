package PokerCard;

/**
 * Simple record representing a card which has a suit and a value
 * Using record for automated generation of constructor and public getters
 */
public record Card(CardSuit suit, CardValue value) {}
