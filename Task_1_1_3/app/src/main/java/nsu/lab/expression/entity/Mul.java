package nsu.lab.expression.entity;

import java.util.Objects;

/**
 * Expression node for multiplication.
 */
public class Mul extends Expression {

    /**
     * Left operand.
     */
    protected Expression left;

    /**
     * Right operand.
     */
    protected Expression right;

    /**
     * Creates left multiplication node.
     *
     * @param a left operand
     * @param b right operand
     */
    public Mul(Expression a, Expression b) {
        this.left = a;
        this.right = b;
    }

    @Override
    public int eval(String var) {
        return left.eval(var) * right.eval(var);
    }

    @Override
    public String toString() {
        return "(" + left.toString() + "*" + right.toString() + ")";
    }

    @Override
    public Expression derivative(String var) {
        return new Add(new Mul(left.derivative(var), right), new Mul(left, right.derivative(var)));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Mul element = (Mul) obj;
        return element.left.equals(left) && element.right.equals(right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), left, right);
    }
}
