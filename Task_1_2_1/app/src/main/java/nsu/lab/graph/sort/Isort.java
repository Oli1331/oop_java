package nsu.lab.graph.sort;

import java.util.List;
import nsu.lab.graph.api.Graph;

/**
 * Interface for graph sorting algorithms.
 */
public interface Isort {

    /**
     * Sorts the given graph topologically.
     *
     * @param <T> vertex type
     * @param graph graph to sort
     * @return sorted list of vertices
     */
    <T> List<T> sort(Graph<T> graph);
}
