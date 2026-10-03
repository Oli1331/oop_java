package nsu.lab.expression.parsing;

import nsu.lab.expression.entity.Add;
import nsu.lab.expression.entity.Div;
import nsu.lab.expression.entity.Expression;
import nsu.lab.expression.entity.Mul;
import nsu.lab.expression.entity.Number;
import nsu.lab.expression.entity.Sub;
import nsu.lab.expression.entity.Variable;

import java.util.ArrayList;
import java.util.List;

/** Recursive descent parser that builds an expression tree from a string. */
public class Parser {

    private Parser() {}

    /**
     * Parses the given string into an expression.
     *
     * @param str the expression text
     * @return the parsed expression
     */
    public static Expression pars(String str) {
        List<Token> tokens = new ArrayList<>();
        getToken(tokens, str);
        Couner numToken = new Couner(0);
        return simpleRecursivePars(tokens, numToken);
    }

    private static Expression simpleRecursivePars(List<Token> tokens, Couner numToken) {
        if (tokens.get(numToken.getNum()).type() == TokenType.LBRACKET) {
            numToken.inc();
            Expression left = simpleRecursivePars(tokens, numToken);

            numToken.inc();
            Token operator = tokens.get(numToken.getNum());

            numToken.inc();
            Expression right = simpleRecursivePars(tokens, numToken);

            numToken.inc();
            if (tokens.get(numToken.getNum()).type() != TokenType.RBRACKET)
                throw new IllegalArgumentException("Closing parenthesis expected");
            switch (operator.type()) {
                case PLUS -> {
                    return new Add(left, right);
                }
                case MINUS -> {
                    return new Sub(left, right);
                }
                case MULTIPLY -> {
                    return new Mul(left, right);
                }
                case DIV -> {
                    return new Div(left, right);
                }
                default ->
                        throw new IllegalArgumentException(
                                "The operator was expected, but was met: " + operator);
            }
        }
        if (tokens.get(numToken.getNum()).type() == TokenType.VARIABLE) {
            return new Variable(tokens.get(numToken.getNum()).text());
        }
        if (tokens.get(numToken.getNum()).type() == TokenType.NUMBER) {
            return new Number(Integer.parseInt(tokens.get(numToken.getNum()).text()));
        }
        throw new IllegalArgumentException("String format error: " + tokens.get(numToken.getNum()));
    }

    private static void getToken(List<Token> tokens, String str) {
        int possition = 0;
        while (possition < str.length()) {
            char symbol = str.charAt(possition);
            switch (symbol) {
                case ' ' -> possition++;
                case '(' -> {
                    tokens.add(new Token(TokenType.LBRACKET, "("));
                    possition++;
                }
                case ')' -> {
                    tokens.add(new Token(TokenType.RBRACKET, ")"));
                    possition++;
                }
                case '+' -> {
                    tokens.add(new Token(TokenType.PLUS, "+"));
                    possition++;
                }
                case '-' -> {
                    tokens.add(new Token(TokenType.MINUS, "-"));
                    possition++;
                }
                case '*' -> {
                    tokens.add(new Token(TokenType.MULTIPLY, "*"));
                    possition++;
                }
                case '/' -> {
                    tokens.add(new Token(TokenType.DIV, "/"));
                    possition++;
                }
                default -> {
                    if (Character.isDigit(symbol)) {
                        int from = possition;
                        while (possition < str.length()
                                && Character.isDigit(str.charAt(possition))) {
                            possition++;
                        }
                        tokens.add(new Token(TokenType.NUMBER, str.substring(from, possition)));
                    } else if (Character.isLetter(symbol)) {
                        int from = possition;
                        while (possition < str.length()
                                && Character.isLetter(str.charAt(possition))) {
                            possition++;
                        }
                        tokens.add(new Token(TokenType.VARIABLE, str.substring(from, possition)));
                    } else {
                        throw new IllegalArgumentException("Unknown character: " + symbol);
                    }
                }
            }
        }
        tokens.add(new Token(TokenType.EOF, "\0"));
    }
}
