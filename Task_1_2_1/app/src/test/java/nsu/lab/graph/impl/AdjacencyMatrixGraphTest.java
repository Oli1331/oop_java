package nsu.lab.graph.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class AdjacencyMatrixGraphTest {
    private AdjacencyMatrixGraph<String> graph;

    @BeforeEach
    void setUp() {
        graph = new AdjacencyMatrixGraph<>(2);
    }

    @ParameterizedTest
    @ValueSource(strings = {"V1", "V2", "V3", "V4"})
    void testAddAndDelVertex(String vertex) {
        assertTrue(graph.addVertex(vertex));
        assertFalse(graph.addVertex(vertex));
        assertTrue(graph.delVertex(vertex));
        assertFalse(graph.delVertex(vertex));
    }

    @Test
    void testCapacityExpansion() {
        assertTrue(graph.addVertex("A"));
        assertTrue(graph.addVertex("B"));
        assertTrue(graph.addVertex("C"));
        assertTrue(graph.addVertex("D"));
        assertTrue(graph.addEdge("A", "D"));
        assertEquals(List.of("D"), graph.getNeighbors("A"));
    }

    @ParameterizedTest
    @CsvSource({
            "A, B",
            "B, A",
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
    void testDelMiddleVertexKeepsOtherEdgesIntact() {
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addEdge("C", "A");
        graph.addEdge("B", "C");

        assertTrue(graph.delVertex("A"));
        assertEquals(List.of("C"), graph.getNeighbors("B"));
        assertTrue(graph.getNeighbors("C").isEmpty());
    }

    @Test
    void testGetNeighborsForNonExistentVertex() {
        assertTrue(graph.getNeighbors("NONE").isEmpty());
    }
}