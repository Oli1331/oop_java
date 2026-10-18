package nsu.lab.graph.api;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public interface Igraph<T> {
    boolean addVertex(T vertex);

    boolean delVertex(T vertex);

    boolean addEdge(T vertex1, T vertex2);

    boolean delEdge(T vertex1, T vertex2);

    List<T> getNeighbors(T vertex);

    void readFromFile(String path, Function<String, T> parser) throws IOException;


}
