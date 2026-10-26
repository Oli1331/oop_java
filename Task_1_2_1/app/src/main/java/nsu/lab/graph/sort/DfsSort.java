package nsu.lab.graph.sort;

import java.util.List;
import nsu.lab.graph.api.Graph;

/**
 * DFS-based algorithm implementation for sorting.
 */
public class DfsSort implements Isort {
    /**
     * Default constructor.
     */
    public DfsSort() {
    }

    @Override
    public <T> List<T> sort(Graph<T> graph) {
        return (List<T>) graph.getVertices();
    }
}
