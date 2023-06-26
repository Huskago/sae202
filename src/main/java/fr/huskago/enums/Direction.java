package fr.huskago.enums;

public enum Direction {
    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right");

    public final String label;

    private Direction(String label) {
        this.label = label;
    }
}
