package nsu.lab.expression.parsing;

/**
 * Lexical token types.
 */
public enum TokenType {
    /**
     * Integer literal.
     */
    NUMBER,
    /**
     * Variable name.
     */
    VARIABLE,
    /**
     * Subtraction operator.
     */
    MINUS,
    /**
     * Addition operator.
     */
    PLUS,
    /**
     * Multiplication operator.
     */
    MULTIPLY,
    /**
     * Division operator.
     */
    DIV,
    /**
     * Opening parenthesis.
     */
    LBRACKET,
    /**
     * Closing parenthesis.
     */
    RBRACKET,
    /**
     * End of the input.
     */
    EOF
}
