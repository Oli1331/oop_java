package nsu.lab.graph.api;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Graph interface providing basic operations.
 *
 * @param <T> vertex type
 */
public interface Igraph<T> {
    /**
     * Adds a vertex to the graph.
     *
     * @param vertex vertex to add
     * @return true if added, false otherwise
     */
    boolean addVertex(T vertex);

    /**
     * Deletes a vertex from the graph.
     *
     * @param vertex vertex to delete
     * @return true if deleted, false otherwise
     */
    boolean delVertex(T vertex);

    /**
     * Adds a directed edge between two vertices.
     *
     * @param vertex1 source vertex
     * @param vertex2 target vertex
     * @return true if added, false otherwise
     */
    boolean addEdge(T vertex1, T vertex2);

    /**
     * Deletes a directed edge between two vertices.
     *
     * @param vertex1 source vertex
     * @param vertex2 target vertex
     * @return true if deleted, false otherwise
     */
    boolean delEdge(T vertex1, T vertex2);

    /**
     * Gets neighbors of a vertex.
     *
     * @param vertex source vertex
     * @return list of neighbors
     */
    List<T> getNeighbors(T vertex);

    /**
     * Reads graph from a file.
     *
     * @param path   file path
     * @param parser parser function for vertices
     * @throws IOException if an I/O error occurs
     */
    void readFromFile(String path, Function<String, T> parser) throws IOException;

    /**
     * Gets all vertices in the graph.
     *
     * @return collection of vertices
     */
    Collection<T> getVertices();
}
