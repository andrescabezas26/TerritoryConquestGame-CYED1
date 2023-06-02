package datastructures;

/**
 * The Edge class represents an edge between two vertices in a graph, with a
 * weight and a method for
 * comparing edges based on their weights.
 */
public class Edge<T> implements Comparable<Edge<T>> {

    private Vertex<T> vertex1;
    private Vertex<T> vertex2;
    private double weight;

    public Edge(Vertex<T> vertex1, Vertex<T> vertex2, double weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    public Vertex<T> getVertex1() {
        return vertex1;
    }

    public Vertex<T> getVertex2() {
        return vertex2;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    // `public int compareTo(Edge<T> otherEdge)` is a method that compares the
    // weight of two edges. It
    // is used to implement the Comparable interface, which allows edges to be
    // sorted based on their
    // weights. The method returns -1 if the weight of the current edge is less than
    // the weight of the
    // other edge, 1 if the weight of the current edge is greater than the weight of
    // the other edge,
    // and 0 if the weights are equal.
    public int compareTo(Edge<T> otherEdge) {
        if (this.weight < otherEdge.weight) {
            return -1;
        } else if (this.weight > otherEdge.weight) {
            return 1;
        } else {
            return 0;
        }
    }
}
