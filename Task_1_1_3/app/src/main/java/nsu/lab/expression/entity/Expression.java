package nsu.lab.expression.entity;

public abstract class Expression {

    public abstract int eval(String var);

    public abstract Expression derivative(String var);

    public void print() {
        System.out.println(this);
    }
}
