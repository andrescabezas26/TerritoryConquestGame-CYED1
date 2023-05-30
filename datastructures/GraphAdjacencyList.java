package datastructures;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import com.google.gson.Gson;

/**
 * This is a Java class that represents a graph using an adjacency list and
 * provides methods for
 * breadth-first search (BFS) and depth-first search (DFS).
 *
 * @param <T>
 */
public class GraphAdjacencyList<T> implements IGraph<T> {
    private int time;
    private ArrayList<Vertex<T>> vertices;
    private boolean isDirected;
    private double[][] dist;
    private T[][] next;

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
    public String addVertex(T vertexToAdd) {
        for (Vertex<T> vertex : vertices) {
            if (vertex.getValue().equals(vertexToAdd)) {
                return "Cannot add the vertex (a vertex with the same value already exists)";
            }
        }

        vertices.add(new Vertex<T>(vertexToAdd));
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
        Vertex<T> vertex1 = null;
        Vertex<T> vertex2 = null;

        for (Vertex<T> vertex : vertices) {
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
    public int searchVertex(T value) {
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
        for (Vertex<T> vertex : vertices) {
            vertex.setColor(Color.WHITE);
            vertex.setPredecessor(null);
        }

        time = 0;

        for (Vertex<T> vertex : vertices) {
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
    private List<Edge<T>> primCreator(Vertex<T> startVertex) {
        for (Vertex<T> vertex : vertices) {
            vertex.setKey(Double.POSITIVE_INFINITY);
            vertex.setColor(Color.WHITE);
        }

        startVertex.setKey(0);
        startVertex.setPredecessor(null);

        PriorityQueue<Vertex<T>> queue = new PriorityQueue<>();
        queue.add(startVertex);

        List<Edge<T>> minimumSpanningTree = new ArrayList<>();
        Set<Vertex<T>> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Vertex<T> currentVertex = queue.poll();
            if (visited.contains(currentVertex)) {
                continue; // Skip if already visited
            }
            currentVertex.setColor(Color.BLACK);
            visited.add(currentVertex);

            for (Edge<T> edge : currentVertex.getAdjacents()) {
                Vertex<T> adjacentVertex = edge.getVertex2();
                if (!visited.contains(adjacentVertex) && edge.getWeight() < adjacentVertex.getKey()) {
                    adjacentVertex.setKey(edge.getWeight());
                    adjacentVertex.setPredecessor(currentVertex);
                    queue.remove(adjacentVertex);
                    queue.add(adjacentVertex);
                    minimumSpanningTree.add(edge);
                }
            }
        }

        return minimumSpanningTree;
    }

    // The above code is a Java method that takes a parameter `vertexValue` and
    // returns a list of edges
    // using the Prim's algorithm for finding the minimum spanning tree of a
    // weighted undirected graph.
    // The method starts with a single vertex and iteratively adds the closest
    // vertex to the current
    // tree until all vertices are included in the tree. The list of edges returned
    // represents the
    // minimum set of edges that connect all vertices in the graph.
    @Override
    public List<Edge<T>> prim(T vertexValue) {
        int pos = searchVertex(vertexValue);

        if (pos == -1) {
            return null;
        }

        List<Edge<T>> minimunSpanningTree = (List<Edge<T>>) primCreator(vertices.get(pos));

        return minimunSpanningTree;
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
    public String printPrim(List<Edge<T>> minimunSpanningTree) {

        if (minimunSpanningTree == null) {
            return "El vértice no existe en el grafo.";
        }

        StringBuilder sb = new StringBuilder("Minimun Spanning Tree");

        for (Edge<T> edge : minimunSpanningTree) {

            sb.append("\n Edge: ").append(edge.getVertex1().getValue()).append(" -- ")
                    .append(edge.getVertex2().getValue())
                    .append(" Weight:  ").append(edge.getWeight());

        }

        return sb.toString();

    }

    // The above code is likely implementing the Floyd-Warshall algorithm, which is
    // used to find the
    // shortest path between all pairs of vertices in a weighted graph. However,
    // without the full
    // implementation of the method and context of the program, it is difficult to
    // determine the exact
    // purpose of the code.
    public void floydWarshall() {
        int numVertices = vertices.size();
        this.dist = new double[numVertices][numVertices];
        this.next = (T[][]) new Object[numVertices][numVertices];

        // Inicializar la matriz de distancias con infinito y la matriz de "next" con
        // null
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                dist[i][j] = Double.POSITIVE_INFINITY;
                next[i][j] = null;
            }
        }

        // Actualizar las distancias directas entre los vértices
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                    next[i][j] = vertices.get(i).getValue();
                }
            }
        }

        // Calcular las distancias mínimas entre los vértices utilizando el algoritmo de
        // Floyd Warshall
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
                }
            }
        }
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
        Queue<Vertex<T>> vQueue = new LinkedList<>();
        Vertex<T> originBFSVertex = new Vertex<T>(originBFS);

        for (Vertex<T> vertex : vertices) {
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
            Vertex<T> actual = vQueue.poll();

            if (actual != null) {
                for (Edge<T> edge : actual.getAdjacents()) {
                    Vertex<T> vertex = edge.getVertex2();
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
    private void DFSVisit(Vertex<T> vertex) {
        time++;
        vertex.setDistance(time);
        vertex.setColor(Color.GRAY);

        for (Edge<T> edge : vertex.getAdjacents()) {
            Vertex<T> v = edge.getVertex2();
            if (v.getColor().equals(Color.WHITE)) {
                v.setPredecessor(vertex);
                DFSVisit(v);
            }
        }

        vertex.setColor(Color.BLACK);
        time++;
        vertex.setTime(time);
    }

    public ArrayList<Vertex<T>> getVertices() {
        return vertices;
    }

    /**
     * This function removes a vertex and all edges associated with it from a graph.
     *
     * @param value The value of the vertex that needs to be removed from the graph.
     */
    @Override
    public void removeVertex(T value) {
        Vertex<T> vertexToRemove = null;

        for (Vertex<T> vertex : vertices) {
            if (vertex.getValue().equals(value)) {
                vertexToRemove = vertex;
                break;
            }
        }

        if (vertexToRemove != null) {
            vertices.remove(vertexToRemove);

            for (Vertex<T> vertex : vertices) {
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
        Vertex<T> vertex1 = null;
        Vertex<T> vertex2 = null;

        for (Vertex<T> vertex : vertices) {
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
        for (Vertex<T> vertex : vertices) {
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
        int pos = searchVertex(vertexValue);

        if (pos == -1) {
            return "El vértice no existe en el grafo.";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Vértice: ").append(vertices.get(pos).getValue()).append("\n");
        stringBuilder.append("Adyacentes: ");

        for (Edge<T> edge : vertices.get(pos).getAdjacents()) {
            stringBuilder.append(edge.getVertex2().getValue()).append(" ");
        }

        return stringBuilder.toString();
    }

    /**
     * @param vertices the vertices to set
     */
    public void setVertices(ArrayList<Vertex<T>> vertices) {
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
     * @return T[][] return the next
     */
    public T[][] getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(T[][] next) {
        this.next = next;
    }

    @Override
    public String toString() {
        // Devuelve una representación en cadena del grafo
        // Aquí puedes definir la lógica para convertir el grafo a una cadena
        // con el formato deseado
        // Por ejemplo, podrías recorrer los vértices y las aristas y construir una
        // cadena con esa información
        // y retornarla

        // Ejemplo:
        StringBuilder sb = new StringBuilder();
        for (Vertex<T> vertex : vertices) {
            sb.append(vertex.getValue()).append(": ");
            for (Edge<T> edge : vertex.getAdjacents()) {
                sb.append(edge.getVertex2().getValue()).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length()); // Eliminar la última coma y el espacio
            sb.append("\n");
        }
        return sb.toString();
    }

    public String toJson() {
        // Utiliza Gson para convertir el objeto a una cadena JSON
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static GraphAdjacencyList<String> fromJson(String json) {
        // Utiliza Gson para convertir una cadena JSON en un objeto GraphAdjacencyList
        Gson gson = new Gson();
        return gson.fromJson(json, GraphAdjacencyList.class);
    }

}
