package datastructures;

/**
 * This is a Java class that represents a vertex in a graph data structure.
 *
 * @param <T>
 */
public class VertexMatrix<T> {
    private T value;
    private Color color;
    private int distance;
    private int time;
    private VertexMatrix<T> predecessor;
    private double key;

    public VertexMatrix(T value) {
        this.value = value;
        this.color = Color.WHITE;
        this.distance = Integer.MAX_VALUE;
        this.time = 0;
        this.predecessor = null;
    }

    /**
     * @return T return the value
     */
    public T getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * @return Color return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * @return int return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * @param distance the distance to set
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * @return int return the time
     */
    public int getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * @return VertexMatrix<T> return the predecessor
     */
    public VertexMatrix<T> getPredecessor() {
        return predecessor;
    }

    /**
     * @param predecessor the predecessor to set
     */
    public void setPredecessor(VertexMatrix<T> predecessor) {
        this.predecessor = predecessor;
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
