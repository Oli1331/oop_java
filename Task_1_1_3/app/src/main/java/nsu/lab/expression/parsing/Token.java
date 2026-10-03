package nsu.lab.expression.parsing;

/**
 * A lexical token.
 *
 * @param type the token type
 * @param text the token text
 */
public record Token(TokenType type, String text) {}
