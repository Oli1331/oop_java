package nsu.lab.expression.entity;

import java.util.Objects;

/**
 * Expression node for division.
 */
public class Div extends Expression {

    /**
     * Left operand.
     */
    protected Expression left;

    /**
     * Right operand.
     */
    protected Expression right;

    /**
     * Creates left division node.
     *
     * @param left  left operand
     * @param right right operand
     */
    public Div(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int eval(String var) {
        return left.eval(var) / right.eval(var);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "/" + right.toString() + ")";
    }

    @Override
    public Expression derivative(String var) {
        return new Div(
                new Sub(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var))),
                new Mul(right, right));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Div element = (Div) obj;
        return element.left.equals(left) && element.right.equals(right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), left, right);
    }
}
