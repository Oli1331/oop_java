package nsu.lab.graph.api;

public record Edge<T>(T from, T to) {

    @Override
    public String toString() {
        return from + " -> " + to;
    }
}
