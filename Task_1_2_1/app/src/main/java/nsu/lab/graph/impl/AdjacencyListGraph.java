package nsu.lab.graph.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nsu.lab.graph.api.Graph;

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
        if (currentList == null || !adjacencyList.containsKey(vertex2) || currentList.contains(vertex2)) {
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
        List<T> neighbors = adjacencyList.get(vertex);
        if (neighbors == null) {
            return Collections.emptyList();
        }
        return neighbors;
    }
}
