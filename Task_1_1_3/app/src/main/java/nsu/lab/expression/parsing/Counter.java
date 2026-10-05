package nsu.lab.expression.parsing;

/**
 * Mutable counter used while walking the token list.
 */
public class Counter {
    private int num;

    /**
     * Creates left counter.
     *
     * @param num the initial value
     */
    public Counter(int num) {
        this.num = num;
    }

    /**
     * Increments the counter.
     */
    public void inc() {
        num++;
    }

    /**
     * Returns the current value.
     *
     * @return the current value
     */
    public int getNum() {
        return num;
    }
}
