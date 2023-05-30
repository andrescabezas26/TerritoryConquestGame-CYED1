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
public class GraphAdjacencyList<T> implements IGraph<T> {
    private int time;
    private ArrayList<VertexList<T>> vertices;
    private boolean isDirected;

    public GraphAdjacencyList(boolean isDirected) {
        this.time = 0;
        this.isDirected = isDirected;
        this.vertices = new ArrayList<>();
    }

    /**
     * This function adds a new vertex with a given value to a list of vertices.
     *
     * @param vertexToAdd The value of the new vertex that is being added to the
     *                    graph.
     */
    @Override
    public String addVertexList(T vertexToAdd) {
        for (VertexList<T> vertex : vertices) {
            if (vertex.getValue().equals(vertexToAdd)) {
                return "Cannot add the vertex (a vertex with the same value already exists)";
            }
        }

        vertices.add(new VertexList<T>(vertexToAdd));
        return "The vertex has been added";
    }

    // The `addEdge` method is adding an edge between two vertices in the graph
    // represented by their
    // values `value1` and `value2`, with an optional weight specified by the
    // `weight` parameter. If
    // the graph is undirected, it also adds a reverse edge between the two
    // vertices.
    @Override
    public void addEdge(T value1, T value2, double weight) {
        VertexList<T> vertex1 = null;
        VertexList<T> vertex2 = null;

        for (VertexList<T> vertex : vertices) {
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

    // The above code is a method in Java that searches for a vertex in a graph data
    // structure based on
    // a given value. The method takes a parameter of type T (which is a generic
    // type) representing the
    // value to search for. The method returns an integer representing the index of
    // the vertex in the
    // graph that matches the given value. If the vertex is not found, the method
    // returns -1.
    @Override
    public int searchVertexList(T value) {
        int pos = -1;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValue().equals(value)) {
                pos = i;
            }

        }

        return pos;
    }

    /**
     * This function performs a depth-first search on a graph represented by
     * vertices and sets their
     * colors and predecessors.
     */
    @Override
    public void DFS() {
        for (VertexList<T> vertex : vertices) {
            vertex.setColor(Color.WHITE);
            vertex.setPredecessor(null);
        }

        time = 0;

        for (VertexList<T> vertex : vertices) {
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
     * @param startVertexList The starting vertex for the Prim's algorithm to find
     *                        the
     *                        minimum spanning
     *                        tree.
     * @return The method is returning a list of edges that form the minimum
     *         spanning tree of the
     *         graph, starting from the specified start vertex using the Prim's
     *         algorithm.
     */
    @Override
    public List<Edge<T>> prim(VertexList<T> startVertexList) {
        for (VertexList<T> vertex : vertices) {
            vertex.setKey(Double.POSITIVE_INFINITY);
            vertex.setColor(Color.WHITE);
        }

        startVertexList.setKey(0);
        startVertexList.setPredecessor(null);

        PriorityQueue<VertexList<T>> queue = new PriorityQueue<>();
        queue.add(startVertexList);

        List<Edge<T>> minimumSpanningTree = new ArrayList<>();

        while (!queue.isEmpty()) {
            VertexList<T> currentVertexList = queue.poll();
            currentVertexList.setColor(Color.BLACK);

            for (Edge<T> edge : currentVertexList.getAdjacents()) {
                VertexList<T> adjacentVertexList = edge.getVertexList2();
                if (adjacentVertexList.getColor() == Color.WHITE && edge.getWeight() < adjacentVertexList.getKey()) {
                    adjacentVertexList.setKey(edge.getWeight());
                    adjacentVertexList.setPredecessor(currentVertexList);
                    queue.remove(adjacentVertexList);
                    queue.add(adjacentVertexList);
                }
            }
        }

        for (VertexList<T> vertex : vertices) {
            VertexList<T> predecessor = vertex.getPredecessor();
            if (predecessor != null) {
                for (Edge<T> edge : vertex.getAdjacents()) {
                    if (edge.getVertexList2().equals(predecessor)) {
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
     * @param positionVertexList The index position of the starting vertex in the
     *                           list
     *                           of vertices. This is
     *                           used as the starting point for the Prim's algorithm
     *                           to
     *                           find the minimum spanning tree.
     * @return The method `printMST` returns a string representation of the minimum
     *         spanning tree of a
     *         graph, starting from a specified vertex. The string contains
     *         information about each edge in the
     *         tree, including the vertices it connects and its weight.
     */
    @Override
    public String printMST(int positionVertexList) {

        ArrayList<Edge<T>> minimunSpanningTree = (ArrayList<Edge<T>>) prim(vertices.get(positionVertexList));

        StringBuilder sb = new StringBuilder("Minimun Spanning Tree");

        for (Edge<T> edge : minimunSpanningTree) {

            sb.append("\n Edge: ").append(edge.getVertexList1()).append(" -- ").append(edge.getVertexList2())
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
        Queue<VertexList<T>> vQueue = new LinkedList<>();
        VertexList<T> originBFSVertexList = new VertexList<T>(originBFS);

        for (VertexList<T> vertex : vertices) {
            if (vertex.getValue() != originBFS) {
                vertex.setColor(Color.WHITE);
                vertex.setDistance(Integer.MAX_VALUE);
                vertex.setPredecessor(null);
            } else {
                vertex.setColor(Color.GRAY);
                vertex.setDistance(0);
                vertex.setPredecessor(null);
                originBFSVertexList = vertex;
            }
        }

        vQueue.add(originBFSVertexList);

        while (!vQueue.isEmpty()) {
            VertexList<T> actual = vQueue.poll();

            if (actual != null) {
                for (Edge<T> edge : actual.getAdjacents()) {
                    VertexList<T> vertex = edge.getVertexList2();
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
    private void DFSVisit(VertexList<T> vertex) {
        time++;
        vertex.setDistance(time);
        vertex.setColor(Color.GRAY);

        for (Edge<T> edge : vertex.getAdjacents()) {
            VertexList<T> v = edge.getVertexList2();
            if (v.getColor().equals(Color.WHITE)) {
                v.setPredecessor(vertex);
                DFSVisit(v);
            }
        }

        vertex.setColor(Color.BLACK);
        time++;
        vertex.setTime(time);
    }

    public ArrayList<VertexList<T>> getVertices() {
        return vertices;
    }

    /**
     * This function removes a vertex and all edges associated with it from a graph.
     *
     * @param value The value of the vertex that needs to be removed from the graph.
     */
    @Override
    public void removeVertexList(T value) {
        VertexList<T> vertexToRemove = null;

        for (VertexList<T> vertex : vertices) {
            if (vertex.getValue().equals(value)) {
                vertexToRemove = vertex;
                break;
            }
        }

        if (vertexToRemove != null) {
            vertices.remove(vertexToRemove);

            for (VertexList<T> vertex : vertices) {
                ArrayList<Edge<T>> adjacents = vertex.getAdjacents();

                for (Edge<T> edge : new ArrayList<>(adjacents)) {
                    if (edge.getVertexList2().equals(vertexToRemove)) {
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
        VertexList<T> vertex1 = null;
        VertexList<T> vertex2 = null;

        for (VertexList<T> vertex : vertices) {
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
                if (edge.getVertexList2().equals(vertex2)) {
                    edgeToRemove1 = edge;
                    break;
                }
            }

            for (Edge<T> edge : adjacents2) {
                if (edge.getVertexList2().equals(vertex1)) {
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
        for (VertexList<T> vertex : vertices) {
            sb.append("VertexList: ").append(vertex.getValue()).append("\n");
            sb.append("Distance: ").append(vertex.getDistance()).append("\n");
            sb.append("Time: ").append(vertex.getTime()).append("\n");
            sb.append("Adjacents: ");
            for (Edge<T> edge : vertex.getAdjacents()) {
                sb.append(edge.getVertexList2().getValue()).append("(").append(edge.getWeight()).append(") ");
            }
            sb.append("\n\n");
        }
        return sb.toString();
    }

    /**
     * This Java function returns a string representation of the adjacent vertices
     * of a given vertex in
     * a graph.
     * 
     * @param vertexValue The value of the vertex for which we want to get the
     *                    adjacent vertices.
     * @return The method returns a string representation of the adjacent vertices
     *         of a given vertex in
     *         a graph. If the given vertex does not exist in the graph, the method
     *         returns a message
     *         indicating that the vertex does not exist.
     */
    @Override
    public String getAdjacentVerticesAsString(T vertexValue) {
        int pos = searchVertexList(vertexValue);

        if (pos == -1) {
            return "El vértice no existe en el grafo.";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Vértice: ").append(vertices.get(pos).getValue()).append("\n");
        stringBuilder.append("Adyacentes: ");

        for (Edge<T> edge : vertices.get(pos).getAdjacents()) {
            stringBuilder.append(edge.getVertexList2().getValue()).append(" ");
        }

        return stringBuilder.toString();
    }

}
