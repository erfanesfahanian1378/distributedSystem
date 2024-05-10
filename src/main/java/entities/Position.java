package entities;

import org.codehaus.jackson.annotate.JsonProperty;

public class Position {
    @JsonProperty
    private int x;

    @JsonProperty
    private int y;

    // No-argument constructor
    public Position() {
    }

    // Argument constructor
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Getters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    // ToString method for better logging
    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
