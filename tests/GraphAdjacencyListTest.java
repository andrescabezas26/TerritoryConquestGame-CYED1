package tests;

import org.junit.Assert;
import org.junit.Test;

import datastructures.*;
import model.*;

import java.util.ArrayList;
import java.util.Arrays;

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

        graph.addEdge(1, 3);

        Assert.assertEquals(0, graph.getVertices().get(0).getAdjacents().size());
        Assert.assertEquals(0, graph.getVertices().get(1).getAdjacents().size());
    }

    @Test
    public void testAddVertex1() {
        setupNotDirected();
        graph.addVertex(1);
        ArrayList<VertexList<Integer>> vertices = graph.getVertices();
        int expectedSize = 1;
        int actualSize = vertices.size();
        Assert.assertEquals(expectedSize, actualSize);
        VertexList<Integer> vertex = vertices.get(0);
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
        ArrayList<VertexList<Integer>> vertices = graph.getVertices();
        int expectedSize = 2;
        int actualSize = vertices.size();
        Assert.assertEquals(expectedSize, actualSize);
        ArrayList<Integer> expectedValues = new ArrayList<>(Arrays.asList(1, 3));
        ArrayList<Integer> actualValues = new ArrayList<>();
        for (VertexList<Integer> vertex : vertices) {
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
        ArrayList<VertexList<Integer>> vertices = graph.getVertices();
        int expectedSize = 4;
        int actualSize = vertices.size();
        Assert.assertEquals(expectedSize, actualSize);
        ArrayList<Integer> expectedValues = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        ArrayList<Integer> actualValues = new ArrayList<>();
        for (VertexList<Integer> vertex : vertices) {
            actualValues.add(vertex.getValue());
        }
        Assert.assertEquals(expectedValues, actualValues);
    }

    @Test
    public void testPrintAdjacents1() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        String expectedOutput = "Vertex: 1\nList of adjacents: \n2 \n\nVertex: 2\nList of adjacents: \n1 \n3 \n\nVertex: 3\nList of adjacents: \n2 \n\n";
        String actualOutput = graph.printAdjacentsOfGraph();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintAdjacents2() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        String expectedOutput = "Vertex: 1\nList of adjacents: \n2 \n\nVertex: 2\nList of adjacents: \n3 \n\nVertex: 3\nList of adjacents: \n\n";
        String actualOutput = graph.printAdjacentsOfGraph();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintAdjacents3() {
        setupNotDirected();

        String expectedOutput = "";
        String actualOutput = graph.printAdjacentsOfGraph();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintGraph1() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        String expectedOutput = "Value: 1 Distance: 0 Time: 0\nValue: 2 Distance: 0 Time: 0\nValue: 3 Distance: 0 Time: 0\n";
        String actualOutput = graph.printGraph();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintGraph2() {
        setupDirected();
        graph.addVertex(5);
        graph.addVertex(6);
        graph.addVertex(7);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        String expectedOutput = "Value: 5 Distance: 0 Time: 0\nValue: 6 Distance: 0 Time: 0\nValue: 7 Distance: 0 Time: 0\n";
        String actualOutput = graph.printGraph();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testPrintGraph3() {
        setupNotDirected();

        String expectedOutput = "";
        String actualOutput = graph.printGraph();

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testRemoveEdge1() {
        setupDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        graph.removeEdge(2, 3);

        Assert.assertEquals(0, graph.getVertices().get(1).getAdjacents().size());
    }

    @Test
    public void testRemoveEdge2() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        graph.removeEdge(1, 2);

        Assert.assertEquals(0, graph.getVertices().get(0).getAdjacents().size());
        Assert.assertEquals(1, graph.getVertices().get(1).getAdjacents().size());

        Assert.assertEquals((Integer) 3, graph.getVertices().get(1).getAdjacents().get(0).getValue());
    }

    @Test
    public void testRemoveEdge3() {
        setupNotDirected();
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        graph.addEdge(1, 2);

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

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

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

        graph.addEdge(1, 2);

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

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

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

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

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

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

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

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        graph.DFS();

        Assert.assertNull(graph.getVertices().get(0).getPredecessor());
        Assert.assertEquals(graph.getVertices().get(0), graph.getVertices().get(1).getPredecessor());
        Assert.assertEquals(graph.getVertices().get(1), graph.getVertices().get(3).getPredecessor());
        Assert.assertEquals(graph.getVertices().get(3), graph.getVertices().get(4).getPredecessor());
    }

    @Test
    public void testBFS1() {
        GraphMatrix<Integer> graph = new GraphMatrix<>(false);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        graph.BFS(1);

        Assert.assertEquals(0, graph.getVertices().get(0).getDistance());
        Assert.assertEquals(1, graph.getVertices().get(1).getDistance());
        Assert.assertEquals(1, graph.getVertices().get(2).getDistance());
        Assert.assertEquals(2, graph.getVertices().get(3).getDistance());
        Assert.assertEquals(3, graph.getVertices().get(4).getDistance());
    }

    @Test
    public void testBFS2() {
        GraphMatrix<Integer> graph = new GraphMatrix<>(false);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);
        graph.addVertex(5);

        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);

        graph.BFS(1);

        Assert.assertEquals(Color.BLACK, graph.getVertices().get(0).getColor());
        Assert.assertEquals(Color.BLACK, graph.getVertices().get(1).getColor());
        Assert.assertEquals(Color.BLACK, graph.getVertices().get(2).getColor());
        Assert.assertEquals(Color.BLACK, graph.getVertices().get(3).getColor());
        Assert.assertEquals(Color.BLACK, graph.getVertices().get(4).getColor());
    }

    @Test
    public void testBFS3() {
        GraphMatrix<Integer> graph = new GraphMatrix<>(false);
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);
        graph.addVertex(4);

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);

        graph.BFS(1);

        Assert.assertEquals(0, graph.getVertices().get(0).getDistance());
        Assert.assertEquals(1, graph.getVertices().get(1).getDistance());
        Assert.assertEquals(2, graph.getVertices().get(2).getDistance());
        Assert.assertEquals(Integer.MAX_VALUE, graph.getVertices().get(3).getDistance());
    }

}
