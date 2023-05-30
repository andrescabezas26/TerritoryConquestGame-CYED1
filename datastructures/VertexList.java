package datastructures;

import java.util.ArrayList;

/**
 * This is a Java class that represents a vertex in a graph data structure.
 *
 * @param <T>
 */
public class VertexList<T> {
    private T value;
    private Color color;
    private int distance;
    private int time;
    private VertexList<T> predecessor;
    private ArrayList<Edge<T>> adjacents;
    private double key;

    public VertexList(T value) {
        this.value = value;
        this.color = Color.WHITE;
        this.distance = Integer.MAX_VALUE;
        this.time = 0;
        this.predecessor = null;
        this.adjacents = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public VertexList<T> getPredecessor() {
        return predecessor;
    }

    public void setPredecessor(VertexList<T> predecessor) {
        this.predecessor = predecessor;
    }

    public ArrayList<Edge<T>> getAdjacents() {
        return adjacents;
    }

    /**
     * @param value the value to set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * @param adjacents the adjacents to set
     */
    public void setAdjacents(ArrayList<Edge<T>> adjacents) {
        this.adjacents = adjacents;
    }

    /**
     * @return double return the key
     */
    public double getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(double key) {
        this.key = key;
    }

}
