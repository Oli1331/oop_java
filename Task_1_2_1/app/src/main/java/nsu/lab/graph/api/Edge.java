package nsu.lab.graph.api;

/**
 * Represents a directed edge between two vertices.
 *
 * @param <T>  the vertex type
 * @param from source vertex
 * @param to   target vertex
 */
public record Edge<T>(T from, T to) {

    @Override
    public String toString() {
        return from + " -> " + to;
    }
}
