package nsu.lab.expression.parsing;

public class Couner {
    private int num;

    public Couner(int num) {
        this.num = num;
    }

    public int inc() {
        return ++num;
    }

    public int getNum() {
        return num;
    }
}
