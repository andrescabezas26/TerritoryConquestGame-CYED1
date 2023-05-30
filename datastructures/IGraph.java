package datastructures;

import java.util.List;

// Declaring a generic interface named `Graph` with a type parameter `T`. This means that any class
// that implements this interface will have to specify the type of `T` they want to use. The methods
// declared in this interface are related to graph traversal and manipulation.
public interface IGraph<T> {

    /**
     * This function performs a breadth-first search starting from a given origin
     * node.
     * 
     * @param origin The parameter "origin" in the method signature represents the
     *               starting node or
     *               vertex from where the Breadth First Search (BFS) algorithm will
     *               begin traversing the graph or
     *               tree. The BFS algorithm is used to traverse a graph or tree in
     *               a breadth-first manner, i.e., it
     *               visits all the
     */
    public void BFS(T origin);

    /**
     * The function DFS() is declared in Java.
     */
    public void DFS();

    /**
     * This function adds a new vertex with a given value to a graph data structure
     * and returns the
     * added vertex.
     * 
     * @param newValue The parameter `newValue` is of type `T`, which means it can
     *                 be any data type
     *                 that extends the `Object` class. It represents the value that
     *                 will be added as a new vertex to a
     *                 graph data structure.
     * @return A String value is being returned.
     */
    public String addVertex(T newValue);

    /**
     * This function adds an edge between two nodes with a specified weight in a
     * graph.
     * 
     * @param value1 The value of the first vertex in the edge being added.
     * @param value2 The parameter "value2" is of type T, which means it can be any
     *               data type that is
     *               specified when the method is called. It represents the second
     *               vertex or node in the edge that is
     *               being added to a graph, along with the first vertex represented
     *               by "value1". The method also
     * @param weight The weight parameter in the addEdge method refers to the
     *               numerical value assigned
     *               to the edge between two vertices in a graph. It can represent
     *               various things depending on the
     *               context of the graph, such as distance, cost, time, or any
     *               other relevant metric. The weight is
     *               used to determine the shortest path
     */
    public void addEdge(T value1, T value2, double weight);

    /**
     * The function "printGraph" returns a string representation of a graph.
     * 
     * @return A String value is being returned.
     */
    public String printGraph();

    /**
     * The function removes an edge between two nodes with values value1 and value2.
     * 
     * @param value1 The first value of the edge to be removed. In a graph, an edge
     *               connects two
     *               vertices, and this parameter specifies the value of the first
     *               vertex of the edge to be removed.
     * @param value2 The parameter value2 is a generic type T representing the value
     *               of the second
     *               vertex in the edge that needs to be removed from the graph.
     */
    public void removeEdge(T value1, T value2);

    /**
     * The function removes a vertex with a given value from a graph.
     * 
     * @param value The parameter "value" represents the value of the vertex that
     *              needs to be removed
     *              from the graph. In other words, this method removes a vertex
     *              with the specified value from the
     *              graph.
     */
    public void removeVertex(T value);

    /**
     * The function "prim" takes a starting vertex and returns a list of edges using
     * the Prim's
     * algorithm.
     * 
     * @param startVertex The startVertex parameter is a Vertex object that
     *                    represents the starting
     *                    vertex for the Prim's algorithm. The algorithm will start
     *                    building the minimum spanning tree
     *                    from this vertex.
     * @return A list of edges that form a minimum spanning tree starting from the
     *         given startVertex
     *         using the Prim's algorithm.
     */
    public List<Edge<T>> prim(Vertex<T> startVertex);

    /**
     * The function searches for a vertex with a given value in a graph.
     * 
     * @param value The value parameter in the method signature represents the value
     *              of the vertex that
     *              we want to search for in the graph. The method will search for
     *              the vertex with the given value
     *              in the graph and return it if found.
     * @return The method is not returning anything, but it is declaring that it
     *         will return a Vertex
     *         object with a generic type T.
     */
    public int searchVertex(T value);

    /**
     * This function prints the minimum spanning tree of a graph starting from a
     * specified vertex.
     * 
     * @param positionVertex The parameter "positionVertex" is an integer that
     *                       represents the position
     *                       or index of a vertex in a graph. It is used in a method
     *                       called "printMST" which is likely used
     *                       to print the minimum spanning tree (MST) of a graph
     *                       starting from a specific vertex. The method
     *                       may
     * @return The method `printMST` is expected to return a `String` value.
     */
    public String printMST(int positionVertex);

    /**
     * This function returns a string representation of the adjacent vertices of a
     * given vertex.
     * 
     * @param vertexValue The value of the vertex for which we want to find the
     *                    adjacent vertices.
     * @return A string representation of the adjacent vertices of the vertex with
     *         the given value.
     */
    public String getAdjacentVerticesAsString(T vertexValue);

}