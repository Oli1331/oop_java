package nsu.lab.graph;

public record Edge<T>(T from, T to) {

    @Override
    public String toString() {
        return from + " -> " + to;
    }
}
