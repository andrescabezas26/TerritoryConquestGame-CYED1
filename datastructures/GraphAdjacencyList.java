package datastructures;

import java.util.*;

/**
 * This is a Java class that represents a graph using an adjacency list and
 * provides methods for
 * breadth-first search (BFS) and depth-first search (DFS).
 *
 * @param <T>
 */
public class GraphAdjacencyList<T> implements IGraph<T> {
    private int time;
    private ArrayList<Vertex<T>> vertexes;
    private boolean isDirected;

    public GraphAdjacencyList(boolean isDirected) {
        this.time = 0;
        this.isDirected = isDirected;
        this.vertexes = new ArrayList<>();
    }

    /**
     * This function adds a new vertex with a given value to a list of vertexes.
     *
     * @param vertexToAdd The value of the new vertex that is being added to the
     *                    graph.
     */
    @Override
    public String addVertex(T vertexToAdd) {
        for (Vertex<T> vertex : vertexes) {
            if (vertex.getValue().equals(vertexToAdd)) {
                return "Cannot add the vertex (a vertex with the same value already exists)";
            }
        }

        vertexes.add(new Vertex<T>(vertexToAdd));
        return "The vertex has been added";
    }

    // The `addEdge` method is adding an edge between two vertexes in the graph
    // represented by their
    // values `value1` and `value2`, with an optional weight specified by the
    // `weight` parameter. If
    // the graph is undirected, it also adds a reverse edge between the two
    // vertexes.
    @Override
    public void addEdge(T value1, T value2, double weight) {
        Vertex<T> vertex1 = null;
        Vertex<T> vertex2 = null;

        for (Vertex<T> vertex : vertexes) {
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
        for (int i = 0; i < vertexes.size(); i++) {
            if (vertexes.get(i).getValue().equals(value)) {
                pos = i;
            }

        }

        return pos;
    }

    /**
     * This function performs a depth-first search on a graph represented by
     * vertexes and sets their
     * colors and predecessors.
     */
    @Override
    public void DFS() {
        for (Vertex<T> vertex : vertexes) {
            vertex.setColor(Color.WHITE);
            vertex.setPredecessor(null);
        }

        time = 0;

        for (Vertex<T> vertex : vertexes) {
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
        for (Vertex<T> vertex : vertexes) {
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
    // tree until all vertexes are included in the tree. The list of edges returned
    // represents the
    // minimum set of edges that connect all vertexes in the graph.
    @Override
    public List<Edge<T>> prim(T vertexValue) {
        int pos = searchVertex(vertexValue);

        if (pos == -1) {
            return null;
        }

        return primCreator(vertexes.get(pos));

    }

    /**
     * The function prints the minimum spanning tree of a graph starting from a
     * given vertex.
     * 
     * @param positionVertex The index position of the starting vertex in the list
     *                       of vertexes. This is
     *                       used as the starting point for the Prim's algorithm to
     *                       find the minimum spanning tree.
     * @return The method `printMST` returns a string representation of the minimum
     *         spanning tree of a
     *         graph, starting from a specified vertex. The string contains
     *         information about each edge in the
     *         tree, including the vertexes it connects and its weight.
     */
    @Override
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

    /**
     * This function applies the Floyd-Warshall algorithm to find the shortest paths
     * between all pairs of vertexes in the graph.
     * 
     * @return A 2D array of doubles representing the shortest distances between all
     *         pairs of vertexes. If there is no path between two vertexes, the
     *         distance is set to Double.POSITIVE_INFINITY.
     */
    @Override
    public double[][] floydWarshall() {
        int numVertices = vertexes.size();
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

        // Update distances matrix with edge weights
        for (int i = 0; i < numVertices; i++) {
            Vertex<T> vertex = vertexes.get(i);
            for (Edge<T> edge : vertex.getAdjacents()) {
                Vertex<T> adjacent = edge.getVertex2();
                int j = vertexes.indexOf(adjacent);
                distances[i][j] = edge.getWeight();
            }
        }

        // Apply Floyd-Warshall algorithm
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

    /**
     * This function implements Dijkstra's algorithm to find the shortest path from
     * a given vertex to all other vertexes in the graph.
     *
     * @param startVertexValue The value of the starting vertex.
     * @return A map containing the shortest distances from the starting vertex to
     *         all other vertexes in the graph.
     */
    @Override
    public Map<T, DistanceInfo<T>> dijkstra(T startVertexValue) {
        Map<T, DistanceInfo<T>> distances = new HashMap<>();
        PriorityQueue<Vertex<T>> queue = new PriorityQueue<>();

        for (Vertex<T> vertex : vertexes) {
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

            for (Edge<T> edge : currentVertex.getAdjacents()) {
                Vertex<T> adjacentVertex = edge.getVertex2();
                double weight = edge.getWeight();
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

        return distances;
    }

    // The above code is a method in Java that searches for an edge between two
    // vertices. It takes two
    // parameters, vertex1 and vertex2, which are the vertices between which the
    // edge is to be
    // searched. The method returns an object of type Edge<T>, which represents the
    // edge between the
    // two vertices.
    @Override
    public Edge<T> searchEdge(T previousVertex, T currentVertex) {
        for (Edge<T> edge : vertexes.get(searchVertex(previousVertex)).getAdjacents()) {
            if (edge.getVertex2().equals(vertexes.get(searchVertex(currentVertex)))) {
                return edge;
            }
        }
        return null;
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

        for (Vertex<T> vertex : vertexes) {
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

    public ArrayList<Vertex<T>> getVertexes() {
        return vertexes;
    }

    /**
     * This function removes a vertex and all edges associated with it from a graph.
     *
     * @param value The value of the vertex that needs to be removed from the graph.
     */
    @Override
    public void removeVertex(T value) {
        Vertex<T> vertexToRemove = null;

        for (Vertex<T> vertex : vertexes) {
            if (vertex.getValue().equals(value)) {
                vertexToRemove = vertex;
                break;
            }
        }

        if (vertexToRemove != null) {
            vertexes.remove(vertexToRemove);

            for (Vertex<T> vertex : vertexes) {
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
     * This function removes an edge between two vertexes in a graph.
     *
     * @param value1 The value of the first vertex in the edge to be removed.
     * @param value2 The value of the second vertex in the edge that needs to be
     *               removed.
     */
    @Override
    public void removeEdge(T value1, T value2) {
        Vertex<T> vertex1 = null;
        Vertex<T> vertex2 = null;

        for (Vertex<T> vertex : vertexes) {
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
     * The function prints out information about the vertexes and their adjacent
     * vertexes in a graph.
     *
     * @return The method `printGraph()` returns a string that contains information
     *         about each vertex
     *         in the graph, including its value, distance, time, and adjacent
     *         vertexes.
     */
    @Override
    public String printGraph() {
        StringBuilder sb = new StringBuilder();
        for (Vertex<T> vertex : vertexes) {
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
     * This Java function returns a string representation of the adjacent vertexes
     * of a given vertex in
     * a graph.
     * 
     * @param vertexValue The value of the vertex for which we want to get the
     *                    adjacent vertexes.
     * @return The method returns a string representation of the adjacent vertexes
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
        stringBuilder.append("Vértice: ").append(vertexes.get(pos).getValue()).append("\n");
        stringBuilder.append("Adyacentes: ");

        for (Edge<T> edge : vertexes.get(pos).getAdjacents()) {
            stringBuilder.append(edge.getVertex2().getValue()).append(" ");
        }

        return stringBuilder.toString();
    }

    /**
     * This function applies Kruskal's algorithm to find the minimum spanning tree
     * of the graph.
     *
     * @return The list of edges that form the minimum spanning tree.
     */
    @Override
    public List<Edge<T>> kruskal() {
        List<Edge<T>> allEdges = new ArrayList<>();

        // Collect all edges from the graph
        for (Vertex<T> vertex : vertexes) {
            for (Edge<T> edge : vertex.getAdjacents()) {
                allEdges.add(edge);
            }
        }

        // Sort edges in ascending order by weight
        Collections.sort(allEdges);

        // Create a disjoint set to track the connected components
        DisjointSet<T> disjointSet = new DisjointSet<>(vertexes);

        List<Edge<T>> minimumSpanningTree = new ArrayList<>();

        for (Edge<T> edge : allEdges) {
            Vertex<T> vertex1 = edge.getVertex1();
            Vertex<T> vertex2 = edge.getVertex2();

            // Check if adding this edge creates a cycle
            if (!disjointSet.find(vertex1).equals(disjointSet.find(vertex2))) {
                // The edge does not create a cycle, add it to the minimum spanning tree
                minimumSpanningTree.add(edge);

                // Merge the sets of the two vertexes
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

        public DisjointSet(List<Vertex<T>> vertexes) {
            parent = new HashMap<>();
            makeSets(vertexes);
        }

        private void makeSets(List<Vertex<T>> vertexes) {
            for (Vertex<T> vertex : vertexes) {
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
     * @param vertexes the vertexes to set
     */
    public void setVertices(ArrayList<Vertex<T>> vertexes) {
        this.vertexes = vertexes;
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

}
