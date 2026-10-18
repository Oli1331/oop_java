package nsu.lab.graph;

import java.util.List;

public interface Igraph<T> {
    boolean addVertex(T vertex);

    boolean delVertex(T vertex);

    boolean addEdge(T vertex1, T vertex2);

    boolean delEdge(T vertex1, T vertex2);

    List<T> getNeighbors(T vertex);

    boolean readFromFile(String path);


}
