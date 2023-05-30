package tests;

import org.junit.Assert;
import org.junit.Test;

import model.*;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The GraphAdjacencyListTest class contains JUnit tests for the
 * GraphAdjacencyList class, which
 * represents a graph using an adjacency list data structure.
 */
public class GraphAdjacencyListTest {

    private GraphAdjacencyList<Integer> graph;

    public void setupNotDirected() {

        graph = new GraphAdjacencyList<>(false);

    }

    public void setupDirected() {

        graph = new GraphAdjacencyList<>(true);

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

        Assert.assertEquals(3, minimumSpanningTree.size());

    }

    @Test
    public void prim2() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);

        List<Edge<Integer>> result = graph.prim(3);

        Assert.assertEquals(null, result);
    }

    @Test
    public void prim3() {
        setupDirected();

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

        Assert.assertEquals(4, minimumSpanningTree.size());
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

        String expectedOutput = "Minimun Spanning Tree\n" +
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

        String expectedOutput = "Minimun Spanning Tree\n" +
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
        setupNotDirected();
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

        Assert.assertEquals(1, graph.getVertices().get(0).getAdjacents().size());
        Assert.assertEquals(1, graph.getVertices().get(1).getAdjacents().size());

        Assert.assertEquals((Integer) 2, graph.getVertices().get(0).getAdjacents().get(0).getVertex2().getValue());
        Assert.assertEquals((Integer) 2, graph.getVertices().get(1).getAdjacents().get(0).getVertex1().getValue());
    }

    @Test
    public void testAddEdge2() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 2, 30);

        Assert.assertEquals(1, graph.getVertices().get(0).getAdjacents().size());
        Assert.assertEquals(0, graph.getVertices().get(1).getAdjacents().size());

        Assert.assertEquals((Integer) 2, graph.getVertices().get(0).getAdjacents().get(0).getVertex2().getValue());
    }

    @Test
    public void testAddEdge3() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);

        graph.addEdge(1, 3, 30);

        Assert.assertEquals(0, graph.getVertices().get(0).getAdjacents().size());
        Assert.assertEquals(0, graph.getVertices().get(1).getAdjacents().size());
    }

    @Test
    public void testAddVertex1() {
        setupNotDirected();
        graph.addVertex(1);
        ArrayList<Vertex<Integer>> vertices = graph.getVertices();
        int expectedSize = 1;
        int actualSize = vertices.size();
        Assert.assertEquals(expectedSize, actualSize);
        Vertex<Integer> vertex = vertices.get(0);
        int expectedValue = 1;
        int actualValue = vertex.getValue();
        Assert.assertEquals(expectedValue, actualValue);
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
        Assert.assertEquals(expectedSize, actualSize);
        ArrayList<Integer> expectedValues = new ArrayList<>(Arrays.asList(1, 3));
        ArrayList<Integer> actualValues = new ArrayList<>();
        for (Vertex<Integer> vertex : vertices) {
            actualValues.add(vertex.getValue());
        }
        Assert.assertEquals(expectedValues, actualValues);
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
        Assert.assertEquals(expectedSize, actualSize);
        ArrayList<Integer> expectedValues = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> actualValues = new ArrayList<>();
        for (Vertex<Integer> vertex : vertices) {
            actualValues.add(vertex.getValue());
        }
        Assert.assertEquals(expectedValues, actualValues);
    }

    @Test
    public void testGetAdjacentVerticesAsString1() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addEdge(1, 2, 0);

        String expected = "El vértice no existe en el grafo.";
        String result = graph.getAdjacentVerticesAsString(3);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetAdjacentVerticesAsString2() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addEdge(1, 2, 0);
        graph.addEdge(1, 3, 0);

        String expected = "Vértice: 1\nAdyacentes: 2 3 ";
        String result = graph.getAdjacentVerticesAsString(1);

        Assert.assertEquals(expected, result);
    }

    @Test
    public void testGetAdjacentVerticesAsString3() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        String expected = "Vértice: 2\nAdyacentes: ";
        String result = graph.getAdjacentVerticesAsString(2);

        Assert.assertEquals(expected, result);
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

        Assert.assertEquals(0, graph.getVertices().get(1).getAdjacents().size());
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

        Assert.assertEquals(0, graph.getVertices().get(0).getAdjacents().size());
        Assert.assertEquals(1, graph.getVertices().get(1).getAdjacents().size());

        Assert.assertEquals((Integer) 3, graph.getVertices().get(1).getAdjacents().get(0).getVertex2().getValue());
    }

    @Test
    public void testRemoveEdge3() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2, 30);

        graph.removeEdge(1, 3);

        Assert.assertEquals(1, graph.getVertices().get(0).getAdjacents().size());
        Assert.assertEquals(0, graph.getVertices().get(2).getAdjacents().size());
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

        Assert.assertEquals(2, graph.getVertices().size());
        Assert.assertEquals(0, graph.getVertices().get(0).getAdjacents().size());
        Assert.assertEquals(0, graph.getVertices().get(1).getAdjacents().size());
    }

    @Test
    public void testRemoveVertex2() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2, 30);

        graph.removeVertex(4);

        Assert.assertEquals(3, graph.getVertices().size());
        Assert.assertEquals(1, graph.getVertices().get(0).getAdjacents().size());
        Assert.assertEquals(1, graph.getVertices().get(1).getAdjacents().size());
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

        Assert.assertEquals(2, graph.getVertices().size());
        Assert.assertEquals(0, graph.getVertices().get(0).getAdjacents().size());
        Assert.assertEquals(0, graph.getVertices().get(1).getAdjacents().size());
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
        Assert.assertEquals(8, graph.getVertices().get(2).getDistance());
        Assert.assertEquals(3, graph.getVertices().get(3).getDistance());
        Assert.assertEquals(4, graph.getVertices().get(4).getDistance());

        Assert.assertEquals(10, graph.getVertices().get(0).getTime());
        Assert.assertEquals(7, graph.getVertices().get(1).getTime());
        Assert.assertEquals(9, graph.getVertices().get(2).getTime());
        Assert.assertEquals(6, graph.getVertices().get(3).getTime());
        Assert.assertEquals(5, graph.getVertices().get(4).getTime());
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
        Assert.assertEquals(graph.getVertices().get(1), graph.getVertices().get(3).getPredecessor());
        Assert.assertEquals(graph.getVertices().get(3), graph.getVertices().get(4).getPredecessor());
    }

    @Test
    public void testSearchVertex1() {
        GraphAdjacencyList<Integer> graph = new GraphAdjacencyList<>(false);
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
        GraphAdjacencyList<Integer> graph = new GraphAdjacencyList<>(false);
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
        GraphAdjacencyList<Integer> graph = new GraphAdjacencyList<>(false);

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
        Assert.assertEquals(2, graph.getVertices().get(3).getDistance());
        Assert.assertEquals(3, graph.getVertices().get(4).getDistance());
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
        Assert.assertEquals(2, graph.getVertices().get(2).getDistance());
        Assert.assertEquals(Integer.MAX_VALUE, graph.getVertices().get(3).getDistance());
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

        String expectedOutput = "Vertex: 1\nDistance: " + Integer.MAX_VALUE + "\nTime: 0\nAdjacents: \n\n";
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

        String expectedOutput = "Vertex: 1\nDistance: " + Integer.MAX_VALUE + "\nTime: 0\nAdjacents: 2(5.0) \n\n" +
                "Vertex: 2\nDistance: " + Integer.MAX_VALUE + "\nTime: 0\nAdjacents: 3(3.0) \n\n" +
                "Vertex: 3\nDistance: " + Integer.MAX_VALUE + "\nTime: 0\nAdjacents: \n\n";
        String actualOutput = graph.printGraph();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

}
