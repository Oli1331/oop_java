package nsu.lab.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdjacencyListGraph<T> extends Graph<T> {
    Map<T, List<T>> adjacencyList = new HashMap<>();

    @Override
    public boolean addVertex(T vertex) {
        if (adjacencyList.containsKey(vertex)) {
            return false;
        }
        adjacencyList.put(vertex, new ArrayList<>());
        return true;
    }

    @Override
    public boolean delVertex(T vertex) {
        if (!adjacencyList.containsKey(vertex)) {
            return false;
        }
        adjacencyList.remove(vertex);
        for (var el : adjacencyList.values()) {
            el.remove(vertex);
        }
        return true;
    }

    @Override
    public boolean addEdge(T vertex1, T vertex2) {
        List<T> currentList = adjacencyList.get(vertex1);
        if (currentList == null || currentList.contains(vertex2)) {
            return false;
        }
        currentList.add(vertex2);
        return true;
    }

    @Override
    public boolean delEdge(T vertex1, T vertex2) {
        List<T> currentList = adjacencyList.get(vertex1);
        if (currentList == null) {
            return false;
        }
        return currentList.remove(vertex2);
    }

    @Override
    public List<T> getNeighbors(T vertex) {
        return adjacencyList.get(vertex);
    }
}
