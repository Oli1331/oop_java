package nsu.lab.blackjack.core;

/** Possible outcomes of a round. */
public enum RoundResult {
    /** Player got blackjack. */
    PLAYER_BLACKJACK,
    /** Player won by points. */
    PLAYER_WIN,
    /** Dealer won by points. */
    DEALER_WIN,
    /** Player went over 21. */
    PLAYER_BUST,
    /** Dealer went over 21. */
    DEALER_BUST,
    /** Equal points. */
    TIE
}
