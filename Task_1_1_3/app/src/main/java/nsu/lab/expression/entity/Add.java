package nsu.lab.expression.entity;

import java.util.Objects;

/** Expression node for addition. */
public class Add extends Expression {

    /** Left operand. */
    protected Expression a;

    /** Right operand. */
    protected Expression b;

    /**
     * Creates an addition node.
     *
     * @param a left operand
     * @param b right operand
     */
    public Add(Expression a, Expression b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public int eval(String var) {
        return a.eval(var) + b.eval(var);
    }

    @Override
    public String toString() {
        return "(" + a.toString() + "+" + b.toString() + ")";
    }

    @Override
    public Expression derivative(String var) {
        return new Add(a.derivative(var), b.derivative(var));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || obj.getClass() != getClass()) return false;

        Add element = (Add) obj;
        return element.a.equals(a) && element.b.equals(b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), a, b);
    }
}
