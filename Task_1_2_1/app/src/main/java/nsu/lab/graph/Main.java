package nsu.lab.graph;

import java.io.IOException;
import nsu.lab.graph.api.Graph;
import nsu.lab.graph.impl.AdjacencyMatrixGraph;

/**
 * Main application class.
 */
public class Main {
    /**
     * Application entry point.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {

        if (args.length == 1) {
            Graph<String> graph = new AdjacencyMatrixGraph<>();
            try {
                graph.readFromFile(args[0], s -> s);
            } catch (IOException e) {
                System.out.println("can't open file");
            }

            System.out.println("Gragh:\n" + graph + "\ntopological sort:\n" + graph.kahnSort());
        }
    }
}
