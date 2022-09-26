package mars.rover.models;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Rover {

    private static final Map<String, Integer> compassPoints = new HashMap<>() {{
        put("N", 0);
        put("E", 90);
        put("S", 180);
        put("W", 270);
    }};

    public Rover(int x, int y, String orientation) {
        setX(x);
        setY(y);
        setOrientation(orientation);
        setLost(false);
    }

    private String orientation;
    private Integer x;
    private Integer y;
    private boolean isLost = false;

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }

    public Map<String, Integer> moveForward() {
        boolean moveOnX = Objects.equals(this.getOrientation(), "E") || Objects.equals(this.getOrientation(), "W");
        boolean toAdd = Objects.equals(this.getOrientation(), "N") || Objects.equals(this.getOrientation(), "E");
        return setCoordinates(moveOnX, toAdd);

    }

    public Map<String, Integer> setCoordinates(boolean moveOnX, boolean toAdd) {
        Map<String, Integer> coordinates = new HashMap<>();
        coordinates.put("x", this.getX());
        coordinates.put("y", this.getY());

        if (!this.isLost()) {
            if (moveOnX) {
                coordinates.put("x", toAdd ? this.getX() + 1 : this.getX() - 1);
            } else {
                coordinates.put("y", toAdd ? this.getY() + 1 : this.getY() - 1);
            }
        }
        return coordinates;
    }

    public void rotate(boolean isLeft) {
        if(!isLost) {
            int currentPosition = compassPoints.get(this.getOrientation());
            int newPosition = isLeft ? currentPosition - 90 : currentPosition + 90;
            if (newPosition == 360) {
                newPosition = 0;
            }
            if (newPosition < 0) {
                newPosition = 270;
            }
            Integer finalCurrentPosition = newPosition;
            compassPoints.keySet().forEach((key) -> {
                if (compassPoints.get(key).equals(finalCurrentPosition)) {
                    this.setOrientation(key);
                }
            });
        }
    }
}