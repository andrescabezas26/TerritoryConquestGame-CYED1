package tests;

import org.junit.Assert;
import org.junit.Test;

import datastructures.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphMatrixTest {

    private GraphMatrix<Integer> graph;

    public void setupNotDirected() {
        graph = new GraphMatrix<>(false);
    }

    public void setupDirected() {
        graph = new GraphMatrix<>(true);
    }

    @Test
    public void testDijkstra1() {
        setupNotDirected();
        graph.addVertex(1);

        Map<Integer, Double> distances = graph.dijkstra(1);

        assertEquals(1, distances.size());
        assertEquals(0.0, distances.get(1), 0);
    }

    @Test
    public void testDijkstra2() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 5);
        graph.addEdge(2, 3, 2);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 2);
        graph.addEdge(3, 5, 4);
        graph.addEdge(4, 5, 3);

        Map<Integer, Double> expectedDistances = new HashMap<>();
        expectedDistances.put(1, 0.0);
        expectedDistances.put(2, 3.0);
        expectedDistances.put(3, 5.0);
        expectedDistances.put(4, 4.0);
        expectedDistances.put(5, 7.0);

        Map<Integer, Double> actualDistances = graph.dijkstra(1);

        assertEquals(expectedDistances, actualDistances);
    }

    @Test
    public void testDijkstra3() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        Map<Integer, Double> expectedDistances = new HashMap<>();
        expectedDistances.put(1, 0.0);
        expectedDistances.put(2, Double.POSITIVE_INFINITY);
        expectedDistances.put(3, Double.POSITIVE_INFINITY);

        Map<Integer, Double> actualDistances = graph.dijkstra(1);

        assertEquals(expectedDistances, actualDistances);
    }

    @Test
    public void testKruskal1() {
        setupNotDirected();
        graph.addVertex(1);

        List<Edge<Integer>> minimumSpanningTree = graph.kruskal();

        assertTrue(minimumSpanningTree.isEmpty());
    }

    @Test
    public void testKruskal2() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        List<Edge<Integer>> minimumSpanningTree = graph.kruskal();

        assertTrue(minimumSpanningTree.isEmpty());
    }

    @Test
    public void testKruskal3() {
        // Create a graph
        setupNotDirected();

        // Add vertices
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        // Add edges
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 3);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 6);
        graph.addEdge(3, 4, 5);
        graph.addEdge(3, 5, 2);
        graph.addEdge(4, 5, 7);

        // Apply Kruskal's algorithm
        List<Edge<Integer>> minimumSpanningTree = graph.kruskal();

        // Verify the result
        assertEquals(4, minimumSpanningTree.size());
        assertEquals((Integer) 1, minimumSpanningTree.get(0).getVertex1().getValue());
        assertEquals((Integer) 2, minimumSpanningTree.get(0).getVertex2().getValue());
        assertEquals(1, minimumSpanningTree.get(0).getWeight(), 0);
        assertEquals((Integer) 3, minimumSpanningTree.get(1).getVertex1().getValue());
        assertEquals((Integer) 5, minimumSpanningTree.get(1).getVertex2().getValue());
        assertEquals(2, minimumSpanningTree.get(1).getWeight(), 0);
        assertEquals((Integer) 1, minimumSpanningTree.get(2).getVertex1().getValue());
        assertEquals((Integer) 3, minimumSpanningTree.get(2).getVertex2().getValue());
        assertEquals(3, minimumSpanningTree.get(2).getWeight(), 0);
        assertEquals((Integer) 3, minimumSpanningTree.get(3).getVertex1().getValue());
        assertEquals((Integer) 4, minimumSpanningTree.get(3).getVertex2().getValue());
        assertEquals(5, minimumSpanningTree.get(3).getWeight(), 0);
    }

}