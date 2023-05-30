package datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This is a Java class that represents a graph using an adjacency list and
 * provides methods for
 * breadth-first search (BFS) and depth-first search (DFS).
 *
 * @param <T>
 */
public class GraphMatrix<T> implements IGraph<T> {
    private int time;
    private ArrayList<VertexMatrix<T>> vertices;
    private boolean isDirected;
    private int[][] adjacentMatrix;

    public GraphMatrix(boolean isDirected) {
        this.time = 0;
        this.isDirected = isDirected;
        this.vertices = new ArrayList<>();
        this.adjacentMatrix = new int[50][50];
    }

    /**
     * This function adds a new vertex with a given value to a list of vertices.
     *
     * @param vertexToAdd The value of the new vertex that is being added to the
     *                    graph.
     */
    @Override
    public String addVertex(T vertexToAdd) {
        for (VertexMatrix<T> vertex : vertices) {
            if (vertex.getValue().equals(vertexToAdd)) {
                return "Cannot add the vertex (a vertex with the same value already exists)";
            }
        }

        vertices.add(new VertexMatrix<T>(vertexToAdd));
        return "The vertex has been added";
    }

    @Override
    // The `addEdge` method is adding an edge between two vertices in the graph
    // represented by their
    // values `value1` and `value2`, with an optional weight specified by the
    // `weight` parameter. If
    // the graph is undirected, it also adds a reverse edge between the two
    // vertices.
    public void addEdge(T value1, T value2, double weight) {
        VertexMatrix<T> vertex1 = null;
        VertexMatrix<T> vertex2 = null;

        for (VertexMatrix<T> vertex : vertices) {
            if (vertex.getValue().equals(value1)) {
                vertex1 = vertex;
            }

            if (vertex.getValue().equals(value2)) {
                vertex2 = vertex;
            }
        }

        if (vertex1 != null && vertex2 != null) {
            Edge<T> edge = new Edge<>(vertex1, vertex2, weight);
            vertex1.getAdjacents().add(edge);

            if (!isDirected) {
                Edge<T> reverseEdge = new Edge<>(vertex2, vertex1, weight);
                vertex2.getAdjacents().add(reverseEdge);
            }
        }
    }

    /**
     * This function searches for a vertex with a specific value in a list of
     * vertices and returns it
     * if found, otherwise returns null.
     * 
     * @param value The value of the vertex that we are searching for in the graph.
     * @return The method `searchVertex` returns a `Vertex<T>` object that matches
     *         the given value, or
     *         `null` if no such vertex is found in the list of vertices.
     */
    @Override
    public VertexMatrix<T> searchVertex(T value) {
        for (VertexMatrix<T> vertex : vertices) {
            if (vertex.getValue().equals(value)) {
                return vertex;
            }
        }
        return null;
    }

    /**
     * This function performs a depth-first search on a graph represented by
     * vertices and sets their
     * colors and predecessors.
     */
    @Override
    public void DFS() {
        for (VertexMatrix<T> vertex : vertices) {
            vertex.setColor(Color.WHITE);
            vertex.setPredecessor(null);
        }

        time = 0;

        for (VertexMatrix<T> vertex : vertices) {
            if (vertex.getColor() == Color.WHITE) {
                DFSVisit(vertex);
            }
        }
    }

    /**
     * The function implements Prim's algorithm to find the minimum spanning tree of
     * a graph starting
     * from a given vertex.
     * 
     * @param startVertex The starting vertex for the Prim's algorithm to find the
     *                    minimum spanning
     *                    tree.
     * @return The method is returning a list of edges that form the minimum
     *         spanning tree of the
     *         graph, starting from the specified start vertex using the Prim's
     *         algorithm.
     */
    @Override
    public List<Edge<T>> prim(VertexMatrix<T> startVertex) {
        for (VertexMatrix<T> vertex : vertices) {
            vertex.setKey(Double.POSITIVE_INFINITY);
            vertex.setColor(Color.WHITE);
        }

        startVertex.setKey(0);
        startVertex.setPredecessor(null);

        PriorityQueue<VertexMatrix<T>> queue = new PriorityQueue<>();
        queue.add(startVertex);

        List<Edge<T>> minimumSpanningTree = new ArrayList<>();

        while (!queue.isEmpty()) {
            VertexMatrix<T> currentVertex = queue.poll();
            currentVertex.setColor(Color.BLACK);

            for (Edge<T> edge : currentVertex.getAdjacents()) {
                VertexMatrix<T> adjacentVertex = edge.getVertex2();
                if (adjacentVertex.getColor() == Color.WHITE && edge.getWeight() < adjacentVertex.getKey()) {
                    adjacentVertex.setKey(edge.getWeight());
                    adjacentVertex.setPredecessor(currentVertex);
                    queue.remove(adjacentVertex);
                    queue.add(adjacentVertex);
                }
            }
        }

        for (VertexMatrix<T> vertex : vertices) {
            VertexMatrix<T> predecessor = vertex.getPredecessor();
            if (predecessor != null) {
                for (Edge<T> edge : vertex.getAdjacents()) {
                    if (edge.getVertex2().equals(predecessor)) {
                        minimumSpanningTree.add(edge);
                        break;
                    }
                }
            }
        }

        return minimumSpanningTree;
    }

    /**
     * The function prints the minimum spanning tree of a graph starting from a
     * given vertex.
     * 
     * @param positionVertex The index position of the starting vertex in the list
     *                       of vertices. This is
     *                       used as the starting point for the Prim's algorithm to
     *                       find the minimum spanning tree.
     * @return The method `printMST` returns a string representation of the minimum
     *         spanning tree of a
     *         graph, starting from a specified vertex. The string contains
     *         information about each edge in the
     *         tree, including the vertices it connects and its weight.
     */
    @Override
    public String printMST(int positionVertex) {

        ArrayList<Edge<T>> minimunSpanningTree = (ArrayList<Edge<T>>) prim(vertices.get(positionVertex));

        StringBuilder sb = new StringBuilder("Minimun Spanning Tree");

        for (Edge<T> edge : minimunSpanningTree) {

            sb.append("\n Edge: ").append(edge.getVertex1()).append(" -- ").append(edge.getVertex2())
                    .append(" Weight:  ").append(edge.getWeight());

        }

        return sb.toString();

    }

    /**
     * This is a BFS (Breadth-First Search) algorithm implemented in Java to
     * traverse a graph starting
     * from a given originBFS vertex.
     *
     * @param originBFS The starting vertex for the BFS traversal.
     */
    @Override
    public void BFS(T originBFS) {
        Queue<VertexMatrix<T>> vQueue = new LinkedList<>();
        VertexMatrix<T> originBFSVertex = new VertexMatrix<T>(originBFS);

        for (VertexMatrix<T> vertex : vertices) {
            if (vertex.getValue() != originBFS) {
                vertex.setColor(Color.WHITE);
                vertex.setDistance(Integer.MAX_VALUE);
                vertex.setPredecessor(null);
            } else {
                vertex.setColor(Color.GRAY);
                vertex.setDistance(0);
                vertex.setPredecessor(null);
                originBFSVertex = vertex;
            }
        }

        vQueue.add(originBFSVertex);

        while (!vQueue.isEmpty()) {
            VertexMatrix<T> actual = vQueue.poll();

            if (actual != null) {
                for (Edge<T> edge : actual.getAdjacents()) {
                    VertexMatrix<T> vertex = edge.getVertex2();
                    if (vertex.getColor() == Color.WHITE) {
                        vertex.setColor(Color.GRAY);
                        vertex.setDistance(actual.getDistance() + 1);
                        vertex.setPredecessor(actual);
                        vQueue.add(vertex);
                    }
                }

                actual.setColor(Color.BLACK);
            }
        }
    }

    /**
     * This is a Depth First Search algorithm implementation in Java that visits
     * each vertex in a graph
     * and sets its distance, color, and timestamp.
     *
     * @param vertex The vertex that is currently being visited in the depth-first
     *               search algorithm.
     */
    private void DFSVisit(VertexMatrix<T> vertex) {
        time++;
        vertex.setDistance(time);
        vertex.setColor(Color.GRAY);

        for (Edge<T> edge : vertex.getAdjacents()) {
            VertexMatrix<T> v = edge.getVertex2();
            if (v.getColor().equals(Color.WHITE)) {
                v.setPredecessor(vertex);
                DFSVisit(v);
            }
        }

        vertex.setColor(Color.BLACK);
        time++;
        vertex.setTime(time);
    }

    public ArrayList<VertexMatrix<T>> getVertices() {
        return vertices;
    }

    /**
     * This function removes a vertex and all edges associated with it from a graph.
     *
     * @param value The value of the vertex that needs to be removed from the graph.
     */
    @Override
    public void removeVertex(T value) {
        VertexMatrix<T> vertexToRemove = null;

        for (VertexMatrix<T> vertex : vertices) {
            if (vertex.getValue().equals(value)) {
                vertexToRemove = vertex;
                break;
            }
        }

        if (vertexToRemove != null) {
            vertices.remove(vertexToRemove);

            for (VertexMatrix<T> vertex : vertices) {
                ArrayList<Edge<T>> adjacents = vertex.getAdjacents();

                for (Edge<T> edge : new ArrayList<>(adjacents)) {
                    if (edge.getVertex2().equals(vertexToRemove)) {
                        adjacents.remove(edge);
                    }
                }
            }
        }
    }

    /**
     * This function removes an edge between two vertices in a graph.
     *
     * @param value1 The value of the first vertex in the edge to be removed.
     * @param value2 The value of the second vertex in the edge that needs to be
     *               removed.
     */
    @Override
    public void removeEdge(T value1, T value2) {
        VertexMatrix<T> vertex1 = null;
        VertexMatrix<T> vertex2 = null;

        for (VertexMatrix<T> vertex : vertices) {
            if (vertex.getValue().equals(value1)) {
                vertex1 = vertex;
            }

            if (vertex.getValue().equals(value2)) {
                vertex2 = vertex;
            }
        }

        if (vertex1 != null && vertex2 != null) {
            ArrayList<Edge<T>> adjacents1 = vertex1.getAdjacents();
            ArrayList<Edge<T>> adjacents2 = vertex2.getAdjacents();

            Edge<T> edgeToRemove1 = null;
            Edge<T> edgeToRemove2 = null;

            for (Edge<T> edge : adjacents1) {
                if (edge.getVertex2().equals(vertex2)) {
                    edgeToRemove1 = edge;
                    break;
                }
            }

            for (Edge<T> edge : adjacents2) {
                if (edge.getVertex2().equals(vertex1)) {
                    edgeToRemove2 = edge;
                    break;
                }
            }

            if (edgeToRemove1 != null) {
                adjacents1.remove(edgeToRemove1);
            }

            if (edgeToRemove2 != null && !isDirected) {
                adjacents2.remove(edgeToRemove2);
            }
        }
    }

    /**
     * The function prints out information about the vertices and their adjacent
     * vertices in a graph.
     *
     * @return The method `printGraph()` returns a string that contains information
     *         about each vertex
     *         in the graph, including its value, distance, time, and adjacent
     *         vertices.
     */
    @Override
    public String printGraph() {
        StringBuilder sb = new StringBuilder();
        for (VertexMatrix<T> vertex : vertices) {
            sb.append("Vertex: ").append(vertex.getValue()).append("\n");
            sb.append("Distance: ").append(vertex.getDistance()).append("\n");
            sb.append("Time: ").append(vertex.getTime()).append("\n");
            sb.append("Adjacents: ");
            for (Edge<T> edge : vertex.getAdjacents()) {
                sb.append(edge.getVertex2().getValue()).append("(").append(edge.getWeight()).append(") ");
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }

    /**
     * @param vertices the vertices to set
     */
    public void setVertices(ArrayList<VertexMatrix<T>> vertices) {
        this.vertices = vertices;
    }

    /**
     * @return boolean return the isDirected
     */
    public boolean isIsDirected() {
        return isDirected;
    }

    /**
     * @param isDirected the isDirected to set
     */
    public void setIsDirected(boolean isDirected) {
        this.isDirected = isDirected;
    }

    /**
     * @return int[][] return the adjacentMatrix
     */
    public int[][] getAdjacentMatrix() {
        return adjacentMatrix;
    }

    /**
     * @param adjacentMatrix the adjacentMatrix to set
     */
    public void setAdjacentMatrix(int[][] adjacentMatrix) {
        this.adjacentMatrix = adjacentMatrix;
    }

}
