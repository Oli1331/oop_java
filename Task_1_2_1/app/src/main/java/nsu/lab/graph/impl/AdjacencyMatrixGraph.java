package nsu.lab.graph.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nsu.lab.graph.api.Graph;

/**
 * Adjacency matrix representation of a graph.
 *
 * @param <T> vertex type
 */
public class AdjacencyMatrixGraph<T> extends Graph<T> {
    private final Map<T, Integer> elementToInd = new HashMap<>();
    private final List<T> indToElement = new ArrayList<>();
    private boolean[][] adjacencyMatrix;
    private int count = 0;

    /**
     * Constructs graph with initial matrix capacity.
     *
     * @param size initial capacity
     */
    public AdjacencyMatrixGraph(int size) {
        adjacencyMatrix = new boolean[size][size];
    }

    /**
     * Default constructor.
     */
    public AdjacencyMatrixGraph() {
        adjacencyMatrix = new boolean[1][1];
    }

    @Override
    public boolean addVertex(T vertex) {
        if (elementToInd.containsKey(vertex)) {
            return false;
        }
        provideCapacity();
        elementToInd.put(vertex, count);
        indToElement.add(count, vertex);
        count++;
        return true;
    }

    @Override
    public boolean delVertex(T vertex) {
        Integer delIndex = elementToInd.remove(vertex);
        if (delIndex == null) {
            return false;
        }
        int lastIndex = count - 1;

        if (delIndex != lastIndex) {
            T lastVertex = indToElement.get(lastIndex);
            boolean selfLoop = adjacencyMatrix[lastIndex][lastIndex];

            for (int i = 0; i < count; i++) {
                adjacencyMatrix[delIndex][i] = adjacencyMatrix[lastIndex][i];
                adjacencyMatrix[i][delIndex] = adjacencyMatrix[i][lastIndex];
            }
            adjacencyMatrix[delIndex][delIndex] = selfLoop;

            elementToInd.put(lastVertex, delIndex);
            indToElement.set(delIndex, lastVertex);
        }

        for (int i = 0; i < count; i++) {
            adjacencyMatrix[lastIndex][i] = false;
            adjacencyMatrix[i][lastIndex] = false;
        }

        indToElement.remove(lastIndex);
        count--;
        return true;
    }

    @Override
    public boolean addEdge(T vertex1, T vertex2) {
        Integer index1 = elementToInd.get(vertex1);
        Integer index2 = elementToInd.get(vertex2);

        if ((index1 == null) || (index2 == null) || adjacencyMatrix[index1][index2]) {
            return false;
        }
        adjacencyMatrix[index1][index2] = true;
        return true;
    }

    @Override
    public boolean delEdge(T vertex1, T vertex2) {
        Integer index1 = elementToInd.get(vertex1);
        Integer index2 = elementToInd.get(vertex2);

        if ((index1 == null) || index2 == null || (!adjacencyMatrix[index1][index2])) {
            return false;
        }
        adjacencyMatrix[index1][index2] = false;
        return true;
    }

    @Override
    public List<T> getNeighbors(T vertex) {
        List<T> res = new ArrayList<>();
        Integer currentIndex = elementToInd.get(vertex);
        if (currentIndex == null) {
            return res;
        }
        for (int i = 0; i < count; i++) {
            if (adjacencyMatrix[currentIndex][i]) {
                res.add(indToElement.get(i));
            }
        }
        return res;
    }

    private void provideCapacity() {
        if (count >= adjacencyMatrix.length) {
            int newCapacity = 1 + adjacencyMatrix.length * 2;
            boolean[][] newMatrix = new boolean[newCapacity][newCapacity];

            for (int i = 0; i < adjacencyMatrix.length; i++) {
                System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, count);
            }

            adjacencyMatrix = newMatrix;
        }
    }

    @Override
    public Collection<T> getVertices() {
        return indToElement;
    }
}
