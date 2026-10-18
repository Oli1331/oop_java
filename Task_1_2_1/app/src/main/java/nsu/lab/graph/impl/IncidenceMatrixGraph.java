package nsu.lab.graph.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nsu.lab.graph.api.Edge;
import nsu.lab.graph.api.Graph;

public class IncidenceMatrixGraph<T> extends Graph<T> {
    int[][] incidenceMatrix;
    int countVertex;
    int countEdge;
    Map<T, Integer> vertexToInd = new HashMap<>();
    List<T> indToVertex = new ArrayList<>();
    Map<Edge<T>, Integer> edgeToInd = new HashMap<>();
    List<Edge<T>> indToEdge = new ArrayList<>();


    public IncidenceMatrixGraph(int countVertex, int countEdge) {
        this.countVertex = 0;
        this.countEdge = 0;
        incidenceMatrix = new int[countEdge][countVertex];
    }


    @Override
    public boolean addVertex(T vertex) {
        if (vertexToInd.containsKey(vertex)) {
            return false;
        }
        provideColumn();
        vertexToInd.put(vertex, countVertex);
        indToVertex.add(countVertex, vertex);
        countVertex++;
        return true;
    }

    @Override
    public boolean delVertex(T vertex) {
        Integer indVertex = vertexToInd.get(vertex);
        if (indVertex == null) {
            return false;
        }
        int currentEdgeInd = 0;
        while (currentEdgeInd < countEdge) {
            if (incidenceMatrix[currentEdgeInd][indVertex] != 0) {
                Edge<T> edge = indToEdge.get(currentEdgeInd);
                delEdge(edge.from(), edge.to());
            } else {
                currentEdgeInd++;
            }
        }
        countVertex--;
        vertexToInd.remove(vertex);

        if (indVertex != countVertex) {
            T lastVertex = indToVertex.get(countVertex);
            vertexToInd.put(lastVertex, indVertex);
            indToVertex.set(indVertex, lastVertex);
        }
        indToVertex.remove(countVertex);

        for (int i = 0; i < countEdge; i++) {
            incidenceMatrix[i][indVertex] = incidenceMatrix[i][countVertex];
            incidenceMatrix[i][countVertex] = 0;
        }
        return true;
    }

    @Override
    public boolean addEdge(T vertex1, T vertex2) {
        Edge<T> edge = new Edge<>(vertex1, vertex2);
        if (edgeToInd.containsKey(edge)) {
            return false;
        }
        addVertex(vertex1);
        addVertex(vertex2);
        provideRow();
        int indexVertex1 = vertexToInd.get(vertex1);
        int indexVertex2 = vertexToInd.get(vertex2);
        edgeToInd.put(edge, countEdge);
        indToEdge.add(countEdge, edge);
        incidenceMatrix[countEdge][indexVertex1] = -1;
        incidenceMatrix[countEdge][indexVertex2] = 1;
        if (indexVertex1 == indexVertex2) {
            incidenceMatrix[countEdge][indexVertex2] = 2;
        }
        countEdge++;
        return true;
    }


    @Override
    public boolean delEdge(T vertex1, T vertex2) {
        Edge<T> edge = new Edge<>(vertex1, vertex2);
        Integer indEdge = edgeToInd.remove(edge);
        if (indEdge == null) {
            return false;
        }
        countEdge--;

        if (indEdge != countEdge) {
            Edge<T> lastEdge = indToEdge.get(countEdge);
            edgeToInd.put(lastEdge, indEdge);
            indToEdge.set(indEdge, lastEdge);
            System.arraycopy(incidenceMatrix[countEdge], 0, incidenceMatrix[indEdge], 0, countVertex);
        }
        indToEdge.remove(countEdge);

        for (int i = 0; i < countVertex; i++) {
            incidenceMatrix[countEdge][i] = 0;
        }
        return true;
    }

    @Override
    public List<T> getNeighbors(T vertex) {
        Integer indVertex = vertexToInd.get(vertex);
        if (indVertex == null) {
            return null;
        }
        List<T> result = new ArrayList<>();
        for (int i = 0; i < countEdge; i++) {
            if (incidenceMatrix[i][indVertex] == -1 || incidenceMatrix[i][indVertex] == 2) {
                T neighbor = indToEdge.get(i).to();
                result.add(neighbor);
            }
        }
        return result;

    }

    private void provideColumn() {
        if (countVertex >= incidenceMatrix[0].length) {
            int newCapacityVertex = incidenceMatrix[0].length * 2;
            int[][] newMatrix = new int[incidenceMatrix.length][newCapacityVertex];

            for (int i = 0; i < incidenceMatrix.length; i++) {
                System.arraycopy(incidenceMatrix[i], 0, newMatrix[i], 0, incidenceMatrix[0].length);
            }

            incidenceMatrix = newMatrix;
        }
    }

    private void provideRow() {
        if (countEdge >= incidenceMatrix.length) {
            int newCapacityEdge = incidenceMatrix.length * 2;
            int[][] newMatrix = new int[newCapacityEdge][incidenceMatrix[0].length];

            for (int i = 0; i < incidenceMatrix.length; i++) {
                System.arraycopy(incidenceMatrix[i], 0, newMatrix[i], 0, incidenceMatrix[0].length);
            }

            incidenceMatrix = newMatrix;
        }
    }
}
