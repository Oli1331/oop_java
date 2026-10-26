package nsu.lab.graph.sort;

import java.util.List;
import nsu.lab.graph.api.Graph;

public class DfsSort implements Isort {

    @Override
    public <T> List<T> sort(Graph<T> graph) {
        return (List<T>) graph.getVertices();
    }
}
