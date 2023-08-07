package fr.huskago.objects;

import fr.huskago.enums.Direction;

import java.util.List;

public class Node {
    private Position position;
    private List<Direction> directions;

    public Node(Position position, List<Direction> directions) {
        this.position = position;
        this.directions = directions;
    }

    public Position getPosition() {
        return position;
    }

    public List<Direction> getDirections() {
        return directions;
    }
}
