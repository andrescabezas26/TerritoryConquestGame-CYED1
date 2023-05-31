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
public class GraphMatrix<T> {
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

    /*
     * private void initializeDistances() {
     * int numVertices = adjacencyMatrix.length;
     * 
     * for (int i = 0; i < numVertices; i++) {
     * for (int j = 0; j < numVertices; j++) {
     * dist[i][j] = adjacencyMatrix[i][j];
     * 
     * if (i != j && adjacencyMatrix[i][j] != Double.MAX_VALUE) {
     * next[i][j] = j;
     * } else {
     * next[i][j] = -1;
     * }
     * }
     * }
     * }
     */

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

    public void addEdge(T value1, T value2, double weight) {
        int index1 = searchVertexIndex(value1);
        int index2 = searchVertexIndex(value2);

        if (index1 != -1 && index2 != -1) {
            adjacencyMatrix[index1][index2] = weight;

            if (!isDirected) {
                adjacencyMatrix[index2][index1] = weight;
            }
        }
    }

    public int searchVertexIndex(T value) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValue().equals(value)) {
                return i;
            }
        }

        return -1;
    }

    public Vertex<T> searchVertex(T value) {
        for (Vertex<T> vertex : vertices) {
            if (vertex.getValue().equals(value)) {
                return vertex;
            }
        }
        return null;
    }

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

            int currentVertexIndex = searchVertexIndex(currentVertex.getValue());

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

    public List<Edge<T>> prim(T vertexValue, double[][] adjacencyMatrix) {
        int pos = searchVertexIndex(vertexValue);

        if (pos == -1) {
            return null;
        }

        return primCreator(vertices.get(pos), adjacencyMatrix);
    }

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

    public void BFS(T originBFS) {
        Queue<Vertex<T>> vQueue = new LinkedList<>();

        int numVertices = vertices.size();
        int originIndex = searchVertexIndex(originBFS);

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
                int actualIndex = searchVertexIndex(actual.getValue());

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

    private void DFSVisit(Vertex<T> vertex) {
        time++;
        vertex.setDistance(time);
        vertex.setColor(Color.GRAY);

        int vertexIndex = searchVertexIndex(vertex.getValue());
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

    public void removeVertex(T value) {
        int indexToRemove = searchVertexIndex(value);

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

    public void removeEdge(T value1, T value2) {
        int index1 = searchVertexIndex(value1);
        int index2 = searchVertexIndex(value2);

        if (index1 != -1 && index2 != -1) {
            adjacencyMatrix[index1][index2] = Double.POSITIVE_INFINITY;

            if (!isDirected) {
                adjacencyMatrix[index2][index1] = Double.POSITIVE_INFINITY;
            }
        }
    }

    public String printGraph() {
        StringBuilder sb = new StringBuilder();

        for (Vertex<T> vertex : vertices) {
            sb.append(vertex.getValue()).append(": ");

            int index = searchVertexIndex(vertex.getValue());
            double[] row = adjacencyMatrix[index];

            for (int i = 0; i < row.length; i++) {
                double weight = row[i];

                if (weight != Double.POSITIVE_INFINITY) {
                    sb.append(vertices.get(i).getValue()).append("(").append(weight).append(") ");
                }
            }

            sb.append("\n");
        }

        return sb.toString();
    }

    public Map<T, Double> dijkstra(T startVertexValue) {
        Map<T, Double> distances = new HashMap<>();
        PriorityQueue<Vertex<T>> queue = new PriorityQueue<>();

        for (Vertex<T> vertex : vertices) {
            if (vertex.getValue().equals(startVertexValue)) {
                vertex.setDistance(0);
                distances.put(vertex.getValue(), 0.0);
            } else {
                vertex.setDistance(Integer.MAX_VALUE);
                distances.put(vertex.getValue(), Double.POSITIVE_INFINITY);
            }

            queue.add(vertex);
        }

        while (!queue.isEmpty()) {
            Vertex<T> currentVertex = queue.poll();

            int currentIndex = searchVertexIndex(currentVertex.getValue());
            for (int i = 0; i < adjacencyMatrix.length; i++) {
                if (adjacencyMatrix[currentIndex][i] != 0) {
                    Vertex<T> adjacentVertex = vertices.get(i);
                    double weight = adjacencyMatrix[currentIndex][i];
                    double currentDistance = currentVertex.getDistance();

                    if (currentDistance + weight < adjacentVertex.getDistance()) {
                        queue.remove(adjacentVertex);
                        adjacentVertex.setDistance((int) (currentDistance + weight));
                        queue.add(adjacentVertex);
                        distances.put(adjacentVertex.getValue(), currentDistance + weight);
                    }
                }
            }
        }

        return distances;
    }

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
