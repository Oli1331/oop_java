package nsu.lab.graph.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.function.Function;

public abstract class Graph<T> implements Igraph<T> {
    public abstract boolean addVertex(T vertex);

    public abstract boolean delVertex(T vertex);

    public abstract boolean addEdge(T vertex1, T vertex2);

    public abstract boolean delEdge(T vertex1, T vertex2);

    public abstract List<T> getNeighbors(T vertex);

    public abstract Collection<T> getVertices();

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

    public List<T> kahnSort() {
        Queue<T> queue = new ArrayDeque<>();
        Collection<T> vertex = getVertices();
        Map<T, Integer> inDegree = new HashMap<>();
        List<T> result = new ArrayList<>();

        for (T v : getVertices()) {
            for (T neighbourV : getNeighbors(v)) {
                if (!inDegree.containsKey(neighbourV)) {
                    inDegree.put(neighbourV, 1);
                } else {
                    inDegree.replace(neighbourV, inDegree.get(neighbourV) + 1);
                }
            }
        }

        for (T v : getVertices()) {
            if (inDegree.get(v) == null || inDegree.get(v) == 0) {
                queue.add(v);
            }
        }

        while (!queue.isEmpty()) {
            T cleanVertex = queue.remove();
            result.add(cleanVertex);
            for (T neighbourV : getNeighbors(cleanVertex)) {
                int countInEdgeOfNeighbour = inDegree.get(neighbourV);
                inDegree.replace(neighbourV, countInEdgeOfNeighbour - 1);
                if (countInEdgeOfNeighbour == 1) {
                    queue.add(neighbourV);
                }
            }
        }
        if (result.size() == vertex.size()) {
            return result;
        }
        return null;
    }


    @Override
    public String toString() {
        StringBuffer res=new StringBuffer();
        for (var v:getVertices()){
            res.append(v);
            res.append(": ");
            res.append(getNeighbors(v));
            res.append("\n");
        }
        return res.toString();
    }
}
