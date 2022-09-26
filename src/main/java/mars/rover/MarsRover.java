package mars.rover;

import mars.rover.models.Rover;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MarsRover {

    private Integer xSize;
    private Integer ySize;
    private final List<Rover> rovers = new ArrayList<>();

    public String moveRovers(String commandLine) {
        String[] commands = this.splitCommand(commandLine);
        setUpGrid(commands[0]);
        for (int i = 0; i < commands.length - 1; i++) {
            setUpAndMoveRover(commands[i + 1]);
        }
        return getRoversList();
    }

    public String[] splitCommand(String input) {
        return input.split("\n");
    }
    public void setUpGrid(String size) {
        String[] dimensions = size.split(" ");
        this.xSize = Integer.parseInt(dimensions[0]);
        this.ySize = Integer.parseInt(dimensions[1]);
    }

    public void setUpAndMoveRover(String command) {
        String position = command.substring(command.indexOf("(")+1, command.indexOf(") "));
        String[] roverInitial = position.split(", ");
        Rover rover = new Rover(Integer.parseInt(roverInitial[0]), Integer.parseInt(roverInitial[1]), roverInitial[2]);
        char[] commands = (command.replace("(" + position + ") ", "")).toCharArray();
        for (char item : commands) {
            Map<String, Integer> newCoordinates = null;
            switch (String.valueOf(item)) {
                case "F" -> newCoordinates = rover.moveForward();
                case "L" -> rover.rotate(true);
                case "R" -> rover.rotate(false);
            }
            if (newCoordinates != null) {
                updateXandY(rover, newCoordinates);
            }
        }
        rovers.add(rover);
    }

    public String getRoversList() {
        StringBuilder roverPositions = new StringBuilder();
        rovers.forEach(rover -> {
            String output = "(" + rover.getX() + ", " + rover.getY() + ", " + rover.getOrientation() + ")";
            if (rover.isLost()) {
                output += " LOST";
            }
            roverPositions.append(output).append("\n");
        });
        return roverPositions.toString();
    }

    public void updateXandY(Rover rover, Map<String, Integer> newCoordinates) {
        Integer newX = newCoordinates.get("x");
        Integer newY = newCoordinates.get("y");
        if (newX >= 0 && newX <= xSize && newY >= 0 && newY <= ySize) {
            rover.setX(newX);
            rover.setY(newY);
        } else {
            rover.setLost(true);
        }
    }

}