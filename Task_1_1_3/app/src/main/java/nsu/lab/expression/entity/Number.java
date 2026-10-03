package nsu.lab.expression.entity;

import java.util.Objects;

public class Number extends Expression {
    private final Integer value;

    public Number(int value) {
        this.value = value;
    }

    @Override
    public int eval(String var) {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public Expression derivative(String var) {
        return new Number(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || obj.getClass() != getClass()) return false;

        Number num = (Number) obj;
        return num.value.equals(value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
