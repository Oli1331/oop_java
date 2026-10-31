package nsu.lab.graph.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import nsu.lab.graph.sort.Isort;

/**
 * Abstract graph class.
 *
 * @param <T> vertex type
 */
public abstract class Graph<T> implements Igraph<T> {

    /**
     * Default constructor.
     */
    public Graph() {
    }

    @Override
    public abstract boolean addVertex(T vertex);

    @Override
    public abstract boolean delVertex(T vertex);

    @Override
    public abstract boolean addEdge(T vertex1, T vertex2);

    @Override
    public abstract boolean delEdge(T vertex1, T vertex2);

    @Override
    public abstract List<T> getNeighbors(T vertex);

    @Override
    public abstract Collection<T> getVertices();

    @Override
    public void readFromFile(String strPath, Function<String, T> parser) throws IOException {
        Path path = Path.of(strPath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");

                T from = parser.apply(parts[0]);
                T to = parser.apply(parts[1]);

                addVertex(from);
                addVertex(to);
                addEdge(from, to);
            }
        }
    }

    /**
     * Performs Kahn's topological sort on the graph.
     *
     * @return sorted list of vertices
     */
    public List<T> topologicalSort(Isort sortingMethod) {
        return sortingMethod.sort(this);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (T v : getVertices()) {
            res.append(v)
                    .append(": ")
                    .append(getNeighbors(v))
                    .append("\n");
        }
        return res.toString();
    }
}
