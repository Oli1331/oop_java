package nsu.lab.expression.entity;

/** Base class for an expression tree node. */
public abstract class Expression {

    /** Creates an expression node. */
    protected Expression() {}

    /**
     * Evaluates the expression.
     *
     * @param var variable values, for example {@code "x=2;y=3"}
     * @return the evaluated value
     */
    public abstract int eval(String var);

    /**
     * Builds the derivative of the expression.
     *
     * @param var the variable to differentiate by
     * @return the derivative expression
     */
    public abstract Expression derivative(String var);
}
