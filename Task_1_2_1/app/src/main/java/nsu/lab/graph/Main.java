package nsu.lab.graph;

import java.io.IOException;
import nsu.lab.graph.api.Graph;
import nsu.lab.graph.impl.AdjacencyMatrixGraph;
import nsu.lab.graph.sort.DfsSort;
import nsu.lab.graph.sort.KahnSort;

/**
 * Main application class.
 */
public class Main {

    /**
     * Default constructor.
     */
    public Main() {
    }

    /**
     * Application entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        if (args.length == 1) {
            KahnSort sotringMethod1 = new KahnSort();
            DfsSort sotringMethod2 = new DfsSort();
            Graph<String> graph = new AdjacencyMatrixGraph<>();
            try {
                graph.readFromFile(args[0], s -> s);
            } catch (IOException e) {
                System.out.println("can't open file");
            }

            System.out.println("Gragh:\n" + graph + "\nkahn topological sort:\n" + graph.topologicalSort(sotringMethod1) + "\ndfs topological sort:\n" + graph.topologicalSort(sotringMethod2));
        }
    }
}
