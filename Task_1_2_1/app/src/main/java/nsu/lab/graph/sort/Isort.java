package nsu.lab.graph.sort;

import java.util.List;
import nsu.lab.graph.api.Graph;

public interface Isort {

    public <T> List<T> sort(Graph<T> graph);
}
