package tests;

import org.junit.Assert;
import org.junit.Test;

import datastructures.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * The GraphMatrixTest class contains JUnit tests for the
 * GraphMatrix class, which
 * represents a graph using an adjacency matrix data structure.
 */
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

        Map<Integer, DistanceInfo<Integer>> distances = graph.dijkstra(1);

        assertEquals(1, distances.size());
        assertEquals(0.0, distances.get(1).getDistance(), 0);
    }

    @Test
    public void testDijkstra2() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        Map<Integer, DistanceInfo<Integer>> distances = graph.dijkstra(1);
        DistanceInfo<Integer> distance = distances.get(2);
        Assert.assertEquals(Double.POSITIVE_INFINITY, distance.getDistance(), 0.001);
    }

    @Test
    public void testDijkstra3() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 2);
        Map<Integer, DistanceInfo<Integer>> distances = graph.dijkstra(1);
        DistanceInfo<Integer> distance2 = distances.get(2);
        DistanceInfo<Integer> distance3 = distances.get(3);
        Assert.assertEquals(1.0, distance2.getDistance(), 0.001);
        Assert.assertEquals(3.0, distance3.getDistance(), 0.001);
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

    @Test
    public void prim1() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 4, 5);

        List<Edge<Integer>> minimumSpanningTree = graph.prim(1);

        assertEquals(3, minimumSpanningTree.size());
    }

    @Test
    public void prim2() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);

        List<Edge<Integer>> result = graph.prim(3);

        assertNull(result);
    }

    @Test
    public void prim3() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 3);
        graph.addEdge(2, 4, 4);
        graph.addEdge(3, 4, 5);
        graph.addEdge(4, 5, 10);

        List<Edge<Integer>> minimumSpanningTree = graph.prim(1);

        assertEquals(4, minimumSpanningTree.size());
    }

    @Test
    public void testPrintPrim1() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 3);
        graph.addEdge(1, 3, 4);

        List<Edge<Integer>> minimumSpanningTree = graph.prim(1);

        String expectedOutput = "Minimum Spanning Tree\n" +
                " Edge: 1 -- 2 Weight:  5.0\n" +
                " Edge: 1 -- 3 Weight:  4.0";

        String actualOutput = graph.printPrim(minimumSpanningTree);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintPrim2() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);
        graph.addEdge(1, 2, 5);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(2, 4, 1);
        graph.addEdge(3, 4, 6);
        graph.addEdge(3, 5, 3);
        graph.addEdge(4, 5, 2);

        List<Edge<Integer>> minimumSpanningTree = graph.prim(1);

        String expectedOutput = "Minimum Spanning Tree\n" +
                " Edge: 1 -- 2 Weight:  5.0\n" +
                " Edge: 1 -- 3 Weight:  2.0\n" +
                " Edge: 3 -- 4 Weight:  6.0\n" +
                " Edge: 3 -- 5 Weight:  3.0\n" +
                " Edge: 2 -- 4 Weight:  1.0";

        String actualOutput = graph.printPrim(minimumSpanningTree);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintPrim3() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 5);

        List<Edge<Integer>> minimumSpanningTree = graph.prim(3);

        String expectedOutput = "El vértice no existe en el grafo.";

        String actualOutput = graph.printPrim(minimumSpanningTree);

        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testAddEdge1() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2, 30);

        assertEquals(30, graph.getAdjacencyMatrix()[0][1], 0);
        assertEquals(30, graph.getAdjacencyMatrix()[1][0], 0);

        assertEquals((Integer) 1, graph.getVertices().get(0).getValue());
        assertEquals((Integer) 2, graph.getVertices().get(1).getValue());
    }

    @Test
    public void testAddEdge2() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2, 30);

        assertEquals(30, graph.getAdjacencyMatrix()[0][1], 0);
        assertEquals(Double.POSITIVE_INFINITY, graph.getAdjacencyMatrix()[1][0], 0);

        assertEquals((Integer) 1, graph.getVertices().get(0).getValue());
    }

    @Test
    public void testAddEdge3() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2, Double.POSITIVE_INFINITY);

        assertEquals(Double.POSITIVE_INFINITY, graph.getAdjacencyMatrix()[0][1], 0);
        assertEquals(Double.POSITIVE_INFINITY, graph.getAdjacencyMatrix()[1][0], 0);
    }

    @Test
    public void testAddVertex1() {
        setupNotDirected();
        graph.addVertex(1);
        ArrayList<Vertex<Integer>> vertices = graph.getVertices();
        int expectedSize = 1;
        int actualSize = vertices.size();
        assertEquals(expectedSize, actualSize);
        Vertex<Integer> vertex = vertices.get(0);
        int expectedValue = 1;
        int actualValue = vertex.getValue();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testAddVertex2() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(1);
        graph.addVertex(3);
        ArrayList<Vertex<Integer>> vertices = graph.getVertices();
        int expectedSize = 2;
        int actualSize = vertices.size();
        assertEquals(expectedSize, actualSize);
        ArrayList<Integer> expectedValues = new ArrayList<>(Arrays.asList(1, 3));
        ArrayList<Integer> actualValues = new ArrayList<>();
        for (Vertex<Integer> vertex : vertices) {
            actualValues.add(vertex.getValue());
        }
        assertEquals(expectedValues, actualValues);
    }

    @Test
    public void testAddVertex3() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        ArrayList<Vertex<Integer>> vertices = graph.getVertices();
        int expectedSize = 4;
        int actualSize = vertices.size();
        assertEquals(expectedSize, actualSize);
        ArrayList<Integer> expectedValues = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> actualValues = new ArrayList<>();
        for (Vertex<Integer> vertex : vertices) {
            actualValues.add(vertex.getValue());
        }
        assertEquals(expectedValues, actualValues);
    }

    @Test
    public void testGetAdjacentVerticesAsString1() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 0);

        String expected = "El vértice no existe en el grafo.";
        String result = graph.getAdjacentVerticesAsString(3);

        assertEquals(expected, result);
    }

    @Test
    public void testGetAdjacentVerticesAsString2() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2, 0);
        graph.addEdge(1, 3, 0);

        String expected = "Vértice: 1\nAdyacentes: 1 2 3 ";
        String result = graph.getAdjacentVerticesAsString(1);

        assertEquals(expected, result);
    }

    @Test
    public void testGetAdjacentVerticesAsString3() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        String expected = "Vértice: 2\nAdyacentes: 2 ";
        String result = graph.getAdjacentVerticesAsString(2);

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveEdge1() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2, 30);
        graph.addEdge(2, 3, 30);

        graph.removeEdge(2, 3);

        assertEquals(Double.POSITIVE_INFINITY, graph.getAdjacencyMatrix()[1][2], 0);
    }

    @Test
    public void testRemoveEdge2() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2, 30);
        graph.addEdge(2, 3, 30);

        graph.removeEdge(1, 2);

        assertEquals(Double.POSITIVE_INFINITY, graph.getAdjacencyMatrix()[0][1], 0);
        assertEquals(30, graph.getAdjacencyMatrix()[1][2], 0);
    }

    @Test
    public void testRemoveEdge3() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2, 30);

        graph.removeEdge(1, 3);

        assertEquals(30, graph.getAdjacencyMatrix()[0][1], 0);
        assertEquals(Double.POSITIVE_INFINITY, graph.getAdjacencyMatrix()[0][2], 0);
    }

    @Test
    public void testRemoveVertex1() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2, 30);
        graph.addEdge(2, 3, 30);

        graph.removeVertex(2);

        assertEquals(2, graph.getVertices().size());
        assertEquals(Double.POSITIVE_INFINITY, graph.getAdjacencyMatrix()[0][1], 0);
        assertEquals(Double.POSITIVE_INFINITY, graph.getAdjacencyMatrix()[1][0], 0);
        assertEquals(Double.POSITIVE_INFINITY, graph.getAdjacencyMatrix()[1][0], 0);

    }

    @Test
    public void testRemoveVertex2() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2, 30);

        graph.removeVertex(4);

        assertEquals(3, graph.getVertices().size());
        assertEquals(30, graph.getAdjacencyMatrix()[0][1], 0);
        assertEquals(30, graph.getAdjacencyMatrix()[1][0], 0);
    }

    @Test
    public void testRemoveVertex3() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2, 30);
        graph.addEdge(2, 3, 30);

        graph.removeVertex(2);

        assertEquals(2, graph.getVertices().size());
        assertEquals(Double.POSITIVE_INFINITY, graph.getAdjacencyMatrix()[0][1], 0);
        assertEquals(Double.POSITIVE_INFINITY, graph.getAdjacencyMatrix()[1][0], 0);
    }

    @Test
    public void testFloydWarshall1() {
        setupDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 2);
        graph.addEdge(1, 3, 3);

        double[][] distances = graph.floydWarshall();

        // Verificar que la matriz de distancias sea correcta
        assertEquals(0.0, distances[0][0], 0);
        assertEquals(1.0, distances[0][1], 0);
        assertEquals(3.0, distances[0][2], 0);
        assertEquals(Double.POSITIVE_INFINITY, distances[1][0], 0);
        assertEquals(0.0, distances[1][1], 0);
        assertEquals(2.0, distances[1][2], 0);
        assertEquals(Double.POSITIVE_INFINITY, distances[2][0], 0);
        assertEquals(Double.POSITIVE_INFINITY, distances[2][1], 0);
        assertEquals(0.0, distances[2][2], 0);
    }

    @Test
    public void testFloydWarshall2() {
        setupDirected();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(1, 2, 1);
        graph.addEdge(2, 3, 2);
        graph.addEdge(3, 4, 1);
        graph.addEdge(1, 4, 10);

        double[][] distances = graph.floydWarshall();

        // Verificar que la matriz de distancias sea correcta
        assertEquals(0.0, distances[0][0], 0);
        assertEquals(1.0, distances[0][1], 0);
        assertEquals(3.0, distances[0][2], 0);
        assertEquals(4.0, distances[0][3], 0);
        assertEquals(Double.POSITIVE_INFINITY, distances[1][0], 0);
        assertEquals(0.0, distances[1][1], 0);
        assertEquals(2.0, distances[1][2], 0);
        assertEquals(3.0, distances[1][3], 0);
        assertEquals(Double.POSITIVE_INFINITY, distances[2][0], 0);
        assertEquals(Double.POSITIVE_INFINITY, distances[2][1], 0);
        assertEquals(0.0, distances[2][2], 0);
        assertEquals(1.0, distances[2][3], 0);
        assertEquals(Double.POSITIVE_INFINITY, distances[3][0], 0);
        assertEquals(Double.POSITIVE_INFINITY, distances[3][1], 0);
        assertEquals(Double.POSITIVE_INFINITY, distances[3][2], 0);
        assertEquals(0.0, distances[3][3], 0);
    }

    @Test
    public void testFloydWarshall3() {
        setupDirected();
        double[][] distances = graph.floydWarshall();

        // Verificar que la matriz de distancias sea correcta
        assertEquals(0, distances.length);
    }

    @Test
    public void testDFS1() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2, 30);
        graph.addEdge(1, 3, 30);
        graph.addEdge(2, 4, 30);
        graph.addEdge(3, 4, 30);
        graph.addEdge(4, 5, 30);

        graph.DFS();

        Assert.assertEquals(Color.BLACK, graph.getVertices().get(0).getColor());
        Assert.assertEquals(Color.BLACK, graph.getVertices().get(1).getColor());
        Assert.assertEquals(Color.BLACK, graph.getVertices().get(2).getColor());
        Assert.assertEquals(Color.BLACK, graph.getVertices().get(3).getColor());
        Assert.assertEquals(Color.BLACK, graph.getVertices().get(4).getColor());
    }

    @Test
    public void testDFS2() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2, 30);
        graph.addEdge(1, 3, 30);
        graph.addEdge(2, 4, 30);
        graph.addEdge(3, 4, 30);
        graph.addEdge(4, 5, 30);

        graph.DFS();

        Assert.assertEquals(1, graph.getVertices().get(0).getDistance());
        Assert.assertEquals(2, graph.getVertices().get(1).getDistance());
        Assert.assertEquals(3, graph.getVertices().get(2).getDistance());
        Assert.assertEquals(4, graph.getVertices().get(3).getDistance());
        Assert.assertEquals(5, graph.getVertices().get(4).getDistance());

        Assert.assertEquals(10, graph.getVertices().get(0).getTime());
        Assert.assertEquals(9, graph.getVertices().get(1).getTime());
        Assert.assertEquals(8, graph.getVertices().get(2).getTime());
        Assert.assertEquals(7, graph.getVertices().get(3).getTime());
        Assert.assertEquals(6, graph.getVertices().get(4).getTime());
    }

    @Test
    public void testDFS3() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2, 30);
        graph.addEdge(1, 3, 30);
        graph.addEdge(2, 4, 30);
        graph.addEdge(3, 4, 30);
        graph.addEdge(4, 5, 30);

        graph.DFS();

        Assert.assertNull(graph.getVertices().get(0).getPredecessor());
        Assert.assertEquals(graph.getVertices().get(0), graph.getVertices().get(1).getPredecessor());
        Assert.assertEquals(graph.getVertices().get(1), graph.getVertices().get(2).getPredecessor());
        Assert.assertEquals(graph.getVertices().get(3), graph.getVertices().get(4).getPredecessor());
    }

    @Test
    public void testSearchVertex1() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2, 0);
        graph.addEdge(2, 3, 0);
        graph.addEdge(3, 4, 0);

        int expectedIndex = 2;
        int actualIndex = graph.searchVertex(3);
        Assert.assertEquals(expectedIndex, actualIndex);

    }

    @Test
    public void testSearchVertex2() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addEdge(1, 2, 0);
        graph.addEdge(2, 3, 0);
        graph.addEdge(3, 4, 0);

        int expectedIndex = -1;
        int actualIndex = graph.searchVertex(5);
        Assert.assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testSearchVertex3() {
        setupNotDirected();

        int expectedIndex = -1;
        int actualIndex = graph.searchVertex(1);
        Assert.assertEquals(expectedIndex, actualIndex);
    }

    @Test
    public void testBFS1() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2, 30);
        graph.addEdge(1, 3, 30);
        graph.addEdge(2, 4, 30);
        graph.addEdge(3, 4, 30);
        graph.addEdge(4, 5, 30);

        graph.BFS(1);

        Assert.assertEquals(0, graph.getVertices().get(0).getDistance());
        Assert.assertEquals(1, graph.getVertices().get(1).getDistance());
        Assert.assertEquals(1, graph.getVertices().get(2).getDistance());
        Assert.assertEquals(1, graph.getVertices().get(3).getDistance());
        Assert.assertEquals(1, graph.getVertices().get(4).getDistance());
    }

    @Test
    public void testBFS2() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2, 30);
        graph.addEdge(1, 3, 30);
        graph.addEdge(2, 4, 30);
        graph.addEdge(3, 4, 30);
        graph.addEdge(4, 5, 30);

        graph.BFS(1);

        Assert.assertEquals(Color.BLACK, graph.getVertices().get(0).getColor());
        Assert.assertEquals(Color.BLACK, graph.getVertices().get(1).getColor());
        Assert.assertEquals(Color.BLACK, graph.getVertices().get(2).getColor());
        Assert.assertEquals(Color.BLACK, graph.getVertices().get(3).getColor());
        Assert.assertEquals(Color.BLACK, graph.getVertices().get(4).getColor());
    }

    @Test
    public void testBFS3() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(1, 2, 30);
        graph.addEdge(2, 3, 30);

        graph.BFS(1);

        Assert.assertEquals(0, graph.getVertices().get(0).getDistance());
        Assert.assertEquals(1, graph.getVertices().get(1).getDistance());
        Assert.assertEquals(1, graph.getVertices().get(2).getDistance());
        Assert.assertEquals(1, graph.getVertices().get(3).getDistance());
    }

    @Test
    public void testPrintGraph1() {
        setupDirected();

        String expectedOutput = "";
        String actualOutput = graph.printGraph();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintGraph2() {
        setupDirected();
        graph.addVertex(1);

        String expectedOutput = "1 ---(0.0)---> 1\n";
        String actualOutput = graph.printGraph();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintGraph3() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2, 5);
        graph.addEdge(2, 3, 3);

        String expectedOutput = "1 ---(0.0)---> 1\n" +
                "1 ---(5.0)---> 2\n" +
                "2 ---(0.0)---> 2\n" +
                "2 ---(3.0)---> 3\n" +
                "3 ---(0.0)---> 3\n" +
                "";
        String actualOutput = graph.printGraph();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSearchEdge1() {
        // Crear el grafo y añadir los vértices y la arista
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 30);

        // Realizar la búsqueda de la arista
        Edge<Integer> edge = graph.searchEdge(1, 2);

        // Verificar que se haya encontrado la arista
        Assert.assertEquals((Integer) 1, edge.getVertex1().getValue());
        Assert.assertEquals((Integer) 2, edge.getVertex2().getValue());
    }

    @Test
    public void testSearchEdge2() {
        // Crear el grafo y añadir los vértices sin arista
        setupDirected();
        graph.addVertex(1);

        // Realizar la búsqueda de la arista
        Edge<Integer> edge = graph.searchEdge(1, 2);

        // Verificar que no se haya encontrado la arista
        Assert.assertNull(edge);
    }

    @Test
    public void testSearchEdge3() {
        // Crear el grafo y añadir los vértices y la arista
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 30);

        // Realizar la búsqueda de la arista
        Edge<Integer> edge = graph.searchEdge(1, 2);

        // Verificar que se haya encontrado la arista
        Assert.assertEquals((Integer) 1, edge.getVertex1().getValue());
        Assert.assertEquals((Integer) 2, edge.getVertex2().getValue());
        Assert.assertEquals(30, edge.getWeight(), 0);
    }

}