package datastructures;

import java.util.ArrayList;

/**
 * This is a Java class that represents a vertex in a graph data structure.
 *
 * @param <T>
 */
public class Vertex<T> implements Comparable<Vertex<T>> {
    private T value;
    private Color color;
    private int distance;
    private int time;
    private Vertex<T> predecessor;
    private ArrayList<Edge<T>> adjacents;
    private double key;

    public Vertex(T value) {
        this.value = value;
        this.color = Color.WHITE;
        this.distance = Integer.MAX_VALUE;
        this.time = 0;
        this.predecessor = null;
        this.adjacents = new ArrayList<>();
    }

    /**
     * This function returns a value of type T.
     * 
     * @return The method is returning the value of type T. The actual value being returned depends on
     * the implementation of the class that contains this method.
     */
    public T getValue() {
        return value;
    }

    /**
     * This function returns the color.
     * 
     * @return The method `getColor()` is returning a `Color` object.
     */
    public Color getColor() {
        return color;
    }

    /**
     * This Java function sets the color of an object.
     * 
     * @param color The parameter "color" is of type "Color" and is used to set the color of an object.
     * The "this.color" refers to the color property of the current object being worked on.
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * The function returns the value of the distance variable.
     * 
     * @return The method `getDistance()` is returning the value of the variable `distance`.
     */
    public int getDistance() {
        return distance;
    }

    /**
     * The function sets the value of the distance variable.
     * 
     * @param distance distance is an integer variable that represents the distance value to be set.
     * The method setDistance() sets the value of the distance variable to the provided value.
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * The function returns the value of the "time" variable.
     * 
     * @return The method `getTime()` is returning an integer value which is stored in the variable
     * `time`.
     */
    public int getTime() {
        return time;
    }

    /**
     * This Java function sets the value of a variable called "time".
     * 
     * @param time The "time" parameter is an integer value that represents a certain amount of time,
     * which is being set by the method. The method sets the value of the "time" instance variable of
     * the object to the value passed as the parameter.
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * This function returns the predecessor vertex of a given vertex.
     * 
     * @return The method is returning a vertex object of type T, which represents the predecessor of
     * the current vertex.
     */
    public Vertex<T> getPredecessor() {
        return predecessor;
    }

    /**
     * This Java function sets the predecessor of a vertex.
     * 
     * @param predecessor The parameter "predecessor" is of type Vertex<T>, which represents the
     * previous vertex in a graph traversal algorithm. It is used to keep track of the path taken to
     * reach a particular vertex in the graph.
     */
    public void setPredecessor(Vertex<T> predecessor) {
        this.predecessor = predecessor;
    }

    /**
     * The function returns an ArrayList of adjacent edges.
     * 
     * @return An ArrayList of Edge objects named "adjacents".
     */
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

    @Override
    // `public int compareTo(Vertex<T> other)` is a method that is used to compare two Vertex objects
    // based on their `key` values. It is used in sorting algorithms such as Dijkstra's algorithm and
    // Prim's algorithm. The method returns an integer value that indicates the order of the two
    // objects being compared. If the current object's `key` value is less than the other object's
    // `key` value, it returns a negative integer. If the current object's `key` value is greater than
    // the other object's `key` value, it returns a positive integer. If the two objects have the same
    // `key` value, it returns 0.
    public int compareTo(Vertex<T> other) {
        return Double.compare(this.key, other.key);
    }
}
