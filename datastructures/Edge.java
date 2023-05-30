package datastructures;

/**
 * This is a Java class that represents an edge in a graph data structure.
 *
 * @param <T>
 */
public class Edge<T> {

    private Vertex<T> vertex1;
    private Vertex<T> vertex2;
    private double weight;

    public Edge(Vertex<T> vertex1, Vertex<T> vertex2, double weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    /**
     * @return Vertex<T> return the vertex1
     */
    public Vertex<T> getVertex1() {
        return vertex1;
    }

    /**
     * @param vertex1 the vertex1 to set
     */
    public void setVertex1(Vertex<T> vertex1) {
        this.vertex1 = vertex1;
    }

    /**
     * @return Vertex<T> return the vertex2
     */
    public Vertex<T> getVertex2() {
        return vertex2;
    }

    /**
     * @param vertex2 the vertex2 to set
     */
    public void setVertex2(Vertex<T> vertex2) {
        this.vertex2 = vertex2;
    }

    /**
     * @return double return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

}
