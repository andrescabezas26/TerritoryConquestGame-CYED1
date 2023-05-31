package datastructures;

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
