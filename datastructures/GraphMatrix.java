package datastructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * This is a Java class that represents a graph using an adjacency list and
 * provides methods for
 * breadth-first search (BFS) and depth-first search (DFS).
 * ss
 * 
 * @param <T>
 */
public class GraphMatrix<T> implements IGraph<T> {
    private int time;
    private ArrayList<Vertex<T>> vertices;
    private boolean isDirected;
    private double[][] adjacencyMatrix;

    public GraphMatrix(boolean isDirected) {
        this.time = 0;
        this.isDirected = isDirected;
        this.vertices = new ArrayList<>();
        this.adjacencyMatrix = new double[0][0];
    }

    /**
     * This function adds a new vertex to a graph represented by an adjacency
     * matrix, checking if a
     * vertex with the same value already exists.
     * 
     * @param vertexToAdd The vertex that needs to be added to the graph. It is of
     *                    type T, which means
     *                    it can be any data type such as Integer, String, or custom
     *                    objects.
     * @return The method returns a String message indicating whether the vertex was
     *         successfully added
     *         or not. If a vertex with the same value already exists, the message
     *         "Cannot add the vertex (a
     *         vertex with the same value already exists)" is returned. Otherwise,
     *         the message "The vertex has
     *         been added" is returned.
     */
    @Override
    public String addVertex(T vertexToAdd) {
        for (Vertex<T> vertex : vertices) {
            if (vertex.getValue().equals(vertexToAdd)) {
                return "Cannot add the vertex (a vertex with the same value already exists)";
            }
        }

        int oldSize = vertices.size();
        int newSize = oldSize + 1;

        double[][] newMatrix = new double[newSize][newSize];

        for (int i = 0; i < oldSize; i++) {
            System.arraycopy(adjacencyMatrix[i], 0, newMatrix[i], 0, oldSize);
            newMatrix[i][newSize - 1] = Double.POSITIVE_INFINITY;
            newMatrix[newSize - 1][i] = Double.POSITIVE_INFINITY;
        }

        this.adjacencyMatrix = newMatrix;
        vertices.add(new Vertex<>(vertexToAdd));

        return "The vertex has been added";
    }

    /**
     * This function adds an edge between two vertices with a given weight in an
     * adjacency matrix
     * representation of a graph.
     * 
     * @param value1 The value of the first vertex in the edge to be added.
     * @param value2 The value of the second vertex to be connected by the edge.
     * @param weight The weight parameter represents the weight or cost of the edge
     *               between two
     *               vertices in a graph. It is a numerical value that can be used
     *               to represent the distance, time,
     *               or any other metric associated with the edge.
     */
    @Override
    public void addEdge(T value1, T value2, double weight) {
        int index1 = searchVertex(value1);
        int index2 = searchVertex(value2);

        if (index1 != -1 && index2 != -1) {
            adjacencyMatrix[index1][index2] = weight;

            if (!isDirected) {
                adjacencyMatrix[index2][index1] = weight;
            }
        }
    }

    /**
     * This function searches for a vertex with a given value in a list of vertices
     * and returns its
     * index, or -1 if it is not found.
     * 
     * @param value The value of the vertex that we are searching for in the list of
     *              vertices.
     * @return The method is returning an integer value which represents the index
     *         of the vertex in the
     *         list of vertices. If the vertex with the given value is found, its
     *         index is returned. If it is
     *         not found, the method returns -1.
     */
    @Override
    public int searchVertex(T value) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValue().equals(value)) {
                return i;
            }
        }

        return -1;
    }

    /**
     * This function implements Prim's algorithm to find the minimum spanning tree
     * of a graph
     * represented by an adjacency matrix.
     * 
     * @param startVertex     The starting vertex for the Prim's algorithm to find
     *                        the minimum spanning
     *                        tree.
     * @param adjacencyMatrix The adjacency matrix is a 2D array that represents the
     *                        weighted edges
     *                        between vertices in a graph. The rows and columns of
     *                        the matrix correspond to the vertices, and
     *                        the values in the matrix represent the weights of the
     *                        edges between those vertices. If there is
     *                        no edge between two vertices, the corresponding value
     * @return The method is returning a list of edges that form the minimum
     *         spanning tree of a graph,
     *         using the Prim's algorithm.
     */
    private List<Edge<T>> primCreator(Vertex<T> startVertex, double[][] adjacencyMatrix) {
        int numVertices = adjacencyMatrix.length;
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

            int currentVertexIndex = searchVertex(currentVertex.getValue());

            for (int i = 0; i < numVertices; i++) {
                if (adjacencyMatrix[currentVertexIndex][i] != 0 && i != currentVertexIndex) {
                    Vertex<T> adjacentVertex = vertices.get(i);
                    double weight = adjacencyMatrix[currentVertexIndex][i];

                    if (!visited.contains(adjacentVertex) && weight < adjacentVertex.getKey()) {
                        adjacentVertex.setKey(weight);
                        adjacentVertex.setPredecessor(currentVertex);
                        queue.remove(adjacentVertex);
                        queue.add(adjacentVertex);

                        minimumSpanningTree.add(new Edge<>(currentVertex, adjacentVertex, weight));
                    }
                }
            }
        }

        return minimumSpanningTree;
    }

    @Override
    // The above code is a method signature in Java for implementing the Prim's
    // algorithm for finding
    // the minimum spanning tree of a graph. It takes a vertex value as input and
    // returns a list of
    // edges that form the minimum spanning tree.
    public List<Edge<T>> prim(T vertexValue) {
        int pos = searchVertex(vertexValue);

        if (pos == -1) {
            return null;
        }

        return primCreator(vertices.get(pos), adjacencyMatrix);
    }

    @Override
    // The above code is a method signature in Java that takes a List of Edge
    // objects as input and
    // returns a String. The method is likely intended to print out the minimum
    // spanning tree
    // represented by the input list of edges. However, the implementation of the
    // method is not
    // provided in the code snippet.
    public String printPrim(List<Edge<T>> minimumSpanningTree) {
        if (minimumSpanningTree == null) {
            return "El vértice no existe en el grafo.";
        }

        StringBuilder sb = new StringBuilder("Minimum Spanning Tree");

        for (Edge<T> edge : minimumSpanningTree) {
            sb.append("\n Edge: ").append(edge.getVertex1().getValue()).append(" -- ")
                    .append(edge.getVertex2().getValue())
                    .append(" Weight:  ").append(edge.getWeight());
        }

        return sb.toString();
    }

    @Override
    // The above code is implementing the Floyd-Warshall algorithm for finding the
    // shortest path
    // between all pairs of vertices in a weighted graph. It returns a 2D array of
    // doubles where the
    // value at index [i][j] represents the shortest distance between vertex i and
    // vertex j.
    public double[][] floydWarshall() {
        int numVertices = vertices.size();
        double[][] distances = new double[numVertices][numVertices];

        // Initialize distances matrix with initial values
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (i == j) {
                    distances[i][j] = 0;
                } else {
                    distances[i][j] = Double.POSITIVE_INFINITY;
                }
            }
        }

        // Update distances based on the edges
        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                if (adjacencyMatrix[i][j] != 0) {
                    distances[i][j] = adjacencyMatrix[i][j];
                }
            }
        }

        // Perform the Floyd-Warshall algorithm
        for (int k = 0; k < numVertices; k++) {
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (distances[i][k] + distances[k][j] < distances[i][j]) {
                        distances[i][j] = distances[i][k] + distances[k][j];
                    }
                }
            }
        }

        return distances;
    }

    // The above code is defining a method called BFS (Breadth-First Search) that
    // takes a parameter of
    // type T (generic type) called originBFS. The method is likely implementing the
    // BFS algorithm to
    // traverse a graph or tree data structure starting from the origin node
    // specified by the
    // parameter. However, without the full implementation of the method, it is not
    // possible to
    // determine the exact functionality of the code.
    @Override
    public void BFS(T originBFS) {
        Queue<Vertex<T>> vQueue = new LinkedList<>();

        int numVertices = vertices.size();
        int originIndex = searchVertex(originBFS);

        for (int i = 0; i < numVertices; i++) {
            if (i != originIndex) {
                Vertex<T> vertex = vertices.get(i);
                vertex.setColor(Color.WHITE);
                vertex.setDistance(Integer.MAX_VALUE);
                vertex.setPredecessor(null);
            } else {
                Vertex<T> vertex = vertices.get(i);
                vertex.setColor(Color.GRAY);
                vertex.setDistance(0);
                vertex.setPredecessor(null);
                vQueue.add(vertex);
            }
        }

        while (!vQueue.isEmpty()) {
            Vertex<T> actual = vQueue.poll();

            if (actual != null) {
                int actualIndex = searchVertex(actual.getValue());

                for (int i = 0; i < numVertices; i++) {
                    if (adjacencyMatrix[actualIndex][i] != 0) {
                        Vertex<T> vertex = vertices.get(i);
                        if (vertex.getColor() == Color.WHITE) {
                            vertex.setColor(Color.GRAY);
                            vertex.setDistance(actual.getDistance() + 1);
                            vertex.setPredecessor(actual);
                            vQueue.add(vertex);
                        }
                    }
                }

                actual.setColor(Color.BLACK);
            }
        }
    }

    // The above code is defining a method called DFS, which is likely short for
    // Depth-First Search.
    // However, without the implementation details of the method, it is difficult to
    // determine exactly
    // what it does.
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

    // The above code is defining a private method called "DFSVisit" that takes a
    // parameter of type
    // "Vertex<T>". This method is likely a part of a Depth-First Search algorithm
    // implementation. The
    // code inside the method is not shown, but it is expected to contain the logic
    // for visiting the
    // given vertex and recursively visiting its adjacent vertices in a depth-first
    // manner.
    private void DFSVisit(Vertex<T> vertex) {
        time++;
        vertex.setDistance(time);
        vertex.setColor(Color.GRAY);

        int vertexIndex = searchVertex(vertex.getValue());
        int numVertices = vertices.size();

        for (int i = 0; i < numVertices; i++) {
            if (adjacencyMatrix[vertexIndex][i] != 0) {
                Vertex<T> v = vertices.get(i);
                if (v.getColor().equals(Color.WHITE)) {
                    v.setPredecessor(vertex);
                    DFSVisit(v);
                }
            }
        }

        vertex.setColor(Color.BLACK);
        time++;
        vertex.setTime(time);
    }

    // The above code is a method in a Java class that removes a vertex (node) with
    // a specific value
    // from a graph data structure. The method takes a parameter "value" which
    // represents the value of
    // the vertex to be removed. The implementation of the method depends on the
    // specific graph data
    // structure being used.
    @Override
    public void removeVertex(T value) {
        int indexToRemove = searchVertex(value);

        if (indexToRemove != -1) {
            int oldSize = vertices.size();
            int newSize = oldSize - 1;

            double[][] newMatrix = new double[newSize][newSize];

            int rowIndex = 0;
            int colIndex;

            for (int i = 0; i < oldSize; i++) {
                if (i != indexToRemove) {
                    colIndex = 0;

                    for (int j = 0; j < oldSize; j++) {
                        if (j != indexToRemove) {
                            newMatrix[rowIndex][colIndex] = adjacencyMatrix[i][j];
                            colIndex++;
                        }
                    }

                    rowIndex++;
                }
            }

            vertices.remove(indexToRemove);
            adjacencyMatrix = newMatrix;
        }
    }

    // The above code is defining a method called `removeEdge` that takes two
    // parameters of type `T`
    // (generic type) called `value1` and `value2`. The purpose of this method is to
    // remove an edge
    // between two vertices in a graph data structure.
    @Override
    public void removeEdge(T value1, T value2) {
        int index1 = searchVertex(value1);
        int index2 = searchVertex(value2);

        if (index1 != -1 && index2 != -1) {
            adjacencyMatrix[index1][index2] = Double.POSITIVE_INFINITY;

            if (!isDirected) {
                adjacencyMatrix[index2][index1] = Double.POSITIVE_INFINITY;
            }
        }
    }

    // The above code is a Java method named `printGraph()` that returns a `String`.
    // The method likely
    // contains code to generate and format a graph or chart, and the returned
    // `String` represents the
    // visual representation of the graph. However, without seeing the
    // implementation of the method, it
    // is impossible to determine exactly what the code is doing.
    @Override
    public String printGraph() {
        StringBuilder sb = new StringBuilder();

        for (Vertex<T> vertex : vertices) {

            int index = searchVertex(vertex.getValue());
            double[] row = adjacencyMatrix[index];

            for (int i = 0; i < row.length; i++) {
                double weight = row[i];

                if (weight != Double.POSITIVE_INFINITY) {
                    sb.append(vertex.getValue()).append(" ---").append("(").append(weight).append(")---> ")
                            .append(vertices.get(i).getValue()).append("\n");
                }
            }

        }

        return sb.toString();
    }

    // The above code is a Java method that searches for an edge between two
    // vertices in a graph. It
    // takes two parameters, vertex1 and vertex2, which represent the two vertices
    // that the edge
    // connects. The method returns an Edge object that represents the edge between
    // the two vertices,
    // if it exists in the graph.
    public Edge<T> searchEdge(T vertex1, T vertex2) {
        int index1 = searchVertex(vertex1);
        int index2 = searchVertex(vertex2);

        if (index1 != -1 && index2 != -1 && adjacencyMatrix[index1][index2] != 0) {
            double weight = adjacencyMatrix[index1][index2];
            Vertex<T> v1 = vertices.get(index1);
            Vertex<T> v2 = vertices.get(index2);
            return new Edge<>(v1, v2, weight);
        }

        return null;
    }

    // The above code is defining a method named "dijkstra" that takes a parameter
    // of type T (which is
    // a generic type) representing the value of the starting vertex. The method
    // returns a Map object
    // that maps each vertex to its shortest distance from the starting vertex. This
    // method implements
    // Dijkstra's algorithm, which is a graph traversal algorithm that finds the
    // shortest path between
    // nodes in a weighted graph.
    @Override
    public Map<T, DistanceInfo<T>> dijkstra(T startVertexValue) {
        Map<T, DistanceInfo<T>> distances = new HashMap<>();
        PriorityQueue<Vertex<T>> queue = new PriorityQueue<>();

        for (Vertex<T> vertex : vertices) {
            if (vertex.getValue().equals(startVertexValue)) {
                vertex.setDistance(0);
                distances.put(vertex.getValue(), new DistanceInfo<T>(0.0, null));
            } else {
                vertex.setDistance(Integer.MAX_VALUE);
                distances.put(vertex.getValue(), new DistanceInfo<T>(Double.POSITIVE_INFINITY, null));
            }

            queue.add(vertex);
        }

        while (!queue.isEmpty()) {
            Vertex<T> currentVertex = queue.poll();

            int currentIndex = searchVertex(currentVertex.getValue());
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[currentIndex][i] != 0) {
                    Vertex<T> adjacentVertex = vertices.get(i);
                    double weight = adjacencyMatrix[currentIndex][i];
                    double currentDistance = currentVertex.getDistance();

                    if (currentDistance + weight < adjacentVertex.getDistance()) {
                        queue.remove(adjacentVertex);
                        adjacentVertex.setDistance((int) (currentDistance + weight));
                        queue.add(adjacentVertex);
                        distances.put(adjacentVertex.getValue(),
                                new DistanceInfo<T>(currentDistance + weight, currentVertex));
                    }
                }
            }
        }

        return distances;
    }

    // The above code is a method in Java that implements Kruskal's algorithm for
    // finding the minimum
    // spanning tree of a graph. It returns a list of edges that form the minimum
    // spanning tree.
    @Override
    public List<Edge<T>> kruskal() {
        List<Edge<T>> allEdges = new ArrayList<>();

        // Collect all edges from the graph
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = 0; j < vertices.size(); j++) {
                double weight = adjacencyMatrix[i][j];
                if (weight != Double.POSITIVE_INFINITY) {
                    allEdges.add(new Edge<>(vertices.get(i), vertices.get(j), weight));
                }
            }
        }

        // Sort edges in ascending order by weight
        Collections.sort(allEdges);

        // Create a disjoint set to track the connected components
        DisjointSet<T> disjointSet = new DisjointSet<>(vertices);

        List<Edge<T>> minimumSpanningTree = new ArrayList<>();

        for (Edge<T> edge : allEdges) {
            Vertex<T> vertex1 = edge.getVertex1();
            Vertex<T> vertex2 = edge.getVertex2();

            // Check if adding this edge creates a cycle
            if (!disjointSet.find(vertex1).equals(disjointSet.find(vertex2))) {
                // The edge does not create a cycle, add it to the minimum spanning tree
                minimumSpanningTree.add(edge);

                // Merge the sets of the two vertices
                disjointSet.union(vertex1, vertex2);
            }
        }

        return minimumSpanningTree;
    }

    public String printTerritories() {
        StringBuilder sb = new StringBuilder();

        for (Vertex<T> vertex : vertices) {

            int index = searchVertex(vertex.getValue());
            double[] row = adjacencyMatrix[index];

            for (int i = 0; i < row.length; i++) {
                double weight = row[i];

                if (weight != Double.POSITIVE_INFINITY) {
                    sb.append(vertex.getValue()).append(" ---").append("(").append(weight).append(")---> ")
                            .append(vertices.get(i).getValue()).append("\n");
                }
            }

        }

        return sb.toString();
    }

    /**
     * This class represents a disjoint set data structure used in Kruskal's
     * algorithm.
     *
     * @param <T> The type of the elements in the disjoint set.
     */
    private static class DisjointSet<T> {
        private Map<Vertex<T>, Vertex<T>> parent;

        public DisjointSet(List<Vertex<T>> vertices) {
            parent = new HashMap<>();
            makeSets(vertices);
        }

        private void makeSets(List<Vertex<T>> vertices) {
            for (Vertex<T> vertex : vertices) {
                parent.put(vertex, vertex);
            }
        }

        public Vertex<T> find(Vertex<T> vertex) {
            if (parent.get(vertex) == vertex) {
                return vertex;
            }

            // Path compression
            Vertex<T> root = find(parent.get(vertex));
            parent.put(vertex, root);
            return root;
        }

        public void union(Vertex<T> vertex1, Vertex<T> vertex2) {
            Vertex<T> root1 = find(vertex1);
            Vertex<T> root2 = find(vertex2);
            parent.put(root1, root2);
        }
    }

    @Override
    public String getAdjacentVerticesAsString(T vertexValue) {
        int pos = searchVertex(vertexValue);

        if (pos == -1) {
            return "El vértice no existe en el grafo.";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Vértice: ").append(vertices.get(pos).getValue()).append("\n");
        stringBuilder.append("Adyacentes: ");

        // Obtener la fila correspondiente a vertexValue en la matriz de adyacencia
        double[] adjacencyRow = adjacencyMatrix[pos];

        // Recorrer los valores de la fila para encontrar los adyacentes
        for (int i = 0; i < adjacencyRow.length; i++) {
            if (adjacencyRow[i] != Double.POSITIVE_INFINITY) {
                // El valor 1 indica que hay una conexión entre los vértices
                T adjacentVertexValue = vertices.get(i).getValue();
                stringBuilder.append(adjacentVertexValue).append(" ");
            }
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
     * @return int[][] return the adjacencyMatrix
     */
    public double[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }

    /**
     * @param adjacencyMatrix the adjacencyMatrix to set
     */
    public void setAdjacencyMatrix(double[][] adjacencyMatrix) {
        this.adjacencyMatrix = adjacencyMatrix;
    }

    public ArrayList<Vertex<T>> getVertices() {
        return vertices;
    }

}
