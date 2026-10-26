package nsu.lab.graph.sort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import nsu.lab.graph.api.Graph;

public class KahnSort implements Isort {
    @Override
    public <T> List<T> sort(Graph<T> graph) {

        Queue<T> queue = new ArrayDeque<>();
        Collection<T> vertex = graph.getVertices();
        Map<T, Integer> inDegree = new HashMap<>();
        List<T> result = new ArrayList<>();

        for (T v : vertex) {
            for (T neighbourV : graph.getNeighbors(v)) {
                if (!inDegree.containsKey(neighbourV)) {
                    inDegree.put(neighbourV, 1);
                } else {
                    inDegree.replace(neighbourV, inDegree.get(neighbourV) + 1);
                }
            }
        }

        for (T v : vertex) {
            if (inDegree.get(v) == null || inDegree.get(v) == 0) {
                queue.add(v);
            }
        }

        while (!queue.isEmpty()) {
            T cleanVertex = queue.remove();
            result.add(cleanVertex);
            for (T neighbourV : graph.getNeighbors(cleanVertex)) {
                int countInEdgeOfNeighbour = inDegree.get(neighbourV);
                inDegree.replace(neighbourV, countInEdgeOfNeighbour - 1);
                if (countInEdgeOfNeighbour == 1) {
                    queue.add(neighbourV);
                }
            }
        }
        if (result.size() != vertex.size()) {
            throw new IllegalStateException(
                    "Graph contains a cycle, topological sort is impossible.");
        }
        return result;
    }
}
