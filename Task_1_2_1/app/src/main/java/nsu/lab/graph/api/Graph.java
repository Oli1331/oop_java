package nsu.lab.graph.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public abstract class Graph<T> implements Igraph<T> {
    public abstract boolean addVertex(T vertex);

    public abstract boolean delVertex(T vertex);

    public abstract boolean addEdge(T vertex1, T vertex2);

    public abstract boolean delEdge(T vertex1, T vertex2);

    public abstract List<T> getNeighbors(T vertex);

    public void readFromFile(String strPath, Function<String, T> parser) throws IOException {
        Path path = Path.of(strPath);
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");

                T from = parser.apply(parts[0]);
                T to = parser.apply(parts[1]);

                addVertex(from);
                addVertex( to);
                addEdge(from, to);
            }
        }
    }
}
