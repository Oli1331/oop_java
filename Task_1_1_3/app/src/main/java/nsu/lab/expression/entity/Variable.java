package nsu.lab.expression.entity;

import java.util.Objects;

public class Variable extends Expression {
    String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int eval(String var) {
        String[] vars = var.split(";");
        for (var v : vars) {
            String[] pairs = v.split("=");
            if (pairs.length == 2) {
                if (pairs[0].trim().equals(name)) {
                    try {
                        return Integer.parseInt(pairs[1].trim());
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Value is not a number: " + pairs[1]);
                    }
                }
            }
        }
        throw new IllegalArgumentException("Variable '" + name + "' not found in: " + var);
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public Expression derivative(String var) {
        if (var.trim().equals(name)) {

            return new Number(1);
        }
        return new Number(0);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || obj.getClass() != getClass()) return false;

        Variable var = (Variable) obj;
        return var.name.equals(name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
