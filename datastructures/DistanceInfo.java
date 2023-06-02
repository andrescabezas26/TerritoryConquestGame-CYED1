package datastructures;

/**
 * The DistanceInfo class stores information about the distance and previous vertex of a vertex in a
 * graph.
 */
public class DistanceInfo<T> {
    private double distance;
    private Vertex<T> previousVertex;

    public DistanceInfo(double distance, Vertex<T> previousVertex) {
        this.distance = distance;
        this.previousVertex = previousVertex;
    }

    /**
     * @return double return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * @return Vertex<T> return the previousVertex
     */
    public Vertex<T> getPreviousVertex() {
        return previousVertex;
    }

    /**
     * @param previousVertex the previousVertex to set
     */
    public void setPreviousVertex(Vertex<T> previousVertex) {
        this.previousVertex = previousVertex;
    }

}