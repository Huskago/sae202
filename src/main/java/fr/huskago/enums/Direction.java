package fr.huskago.enums;

public enum Direction {
    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right"),
    NONE("none");

    public final String label;

    private Direction(String label) {
        this.label = label;
    }
}
