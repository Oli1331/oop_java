package nsu.lab.graph.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Tests for {@link AdjacencyListGraph}.
 */
class AdjacencyListGraphTest {
    private AdjacencyListGraph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new AdjacencyListGraph<>();
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "B", "C", "D"})
    void testAddAndDelVertex(String vertex) {
        assertTrue(graph.addVertex(vertex));
        assertFalse(graph.addVertex(vertex));
        assertTrue(graph.delVertex(vertex));
        assertFalse(graph.delVertex(vertex));
    }

    @ParameterizedTest
    @CsvSource({
            "A, B",
            "B, C",
            "A, A"
    })
    void testAddAndDelEdge(String from, String to) {
        graph.addVertex(from);
        graph.addVertex(to);

        assertTrue(graph.addEdge(from, to));
        assertFalse(graph.addEdge(from, to));
        assertTrue(graph.getNeighbors(from).contains(to));

        assertTrue(graph.delEdge(from, to));
        assertFalse(graph.delEdge(from, to));
        assertFalse(graph.getNeighbors(from).contains(to));
    }

    @Test
    void testAddEdgeWithMissingVertices() {
        graph.addVertex("A");
        assertFalse(graph.addEdge("A", "B"));
        assertFalse(graph.addEdge("B", "A"));
    }

    @Test
    void testDelVertexRemovesIncomingEdges() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addEdge("A", "B");

        assertTrue(graph.delVertex("B"));
        assertTrue(graph.getNeighbors("A").isEmpty());
    }

    @Test
    void testGetNeighbors() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");

        List<String> neighbors = graph.getNeighbors("A");
        assertEquals(2, neighbors.size());
        assertTrue(neighbors.contains("B"));
        assertTrue(neighbors.contains("C"));
        assertTrue(graph.getNeighbors("UNKNOWN").isEmpty());
    }

    @Test
    void testKahnSort() {
        for (var v : List.of("A", "B", "C", "D", "E")) {
            graph.addVertex(v);
        }
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("D", "E");
        assertEquals(List.of("A", "B", "C", "D", "E"), graph.kahnSort());

        graph.addEdge("E", "A");
        assertNull(graph.kahnSort());
    }


}
