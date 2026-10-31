package nsu.lab.graph.sort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<T, Integer> state = new HashMap<>();
        Deque<T> order = new ArrayDeque<>();

        for (T vertex : graph.getVertices()) {
            state.put(vertex, 0);
        }

        for (T vertex : graph.getVertices()) {
            if (state.get(vertex) == 0) {
                dfs(graph, vertex, state, order);
            }
        }

        return new ArrayList<>(order);
    }

    private <T> void dfs(Graph<T> graph,
                         T current,
                         Map<T, Integer> state,
                         Deque<T> order) {
        state.put(current, 1); // Входим в вершину (серый)

        for (T neighbor : graph.getNeighbors(current)) {
            int neighborState = state.getOrDefault(neighbor, 0);
            if (neighborState == 1) {
                throw new IllegalStateException(
                        "Graph contains a cycle, topological sort is impossible.");
            }
            if (neighborState == 0) {
                dfs(graph, neighbor, state, order);
            }
        }

        state.put(current, 2);
        order.addFirst(current);
    }
}
