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
 * Tests for {@link IncidenceMatrixGraph}.
 */
class IncidenceMatrixGraphTest {
    private IncidenceMatrixGraph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new IncidenceMatrixGraph<>(2, 2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", "B", "C"})
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
        assertEquals(List.of(to), graph.getNeighbors(from));

        assertTrue(graph.delEdge(from, to));
        assertFalse(graph.delEdge(from, to));
        assertTrue(graph.getNeighbors(from).isEmpty());
    }

    @Test
    void testMatrixExpansion() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("A", "C");

        assertEquals(2, graph.getNeighbors("A").size());
        assertEquals(List.of("C"), graph.getNeighbors("B"));
    }

    @Test
    void testDelVertexRemovesConnectedEdges() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("A", "C");

        assertTrue(graph.delVertex("B"));
        assertEquals(List.of("C"), graph.getNeighbors("A"));
        assertNull(graph.getNeighbors("B"));
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
