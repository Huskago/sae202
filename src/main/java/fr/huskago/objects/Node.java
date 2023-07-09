package fr.huskago.objects;

import fr.huskago.enums.Direction;

import java.util.Objects;

// Classe interne représentant un noeud dans l'algorithme A*
public class Node implements Comparable<Node> {
    private Position position;
    private Direction direction;
    private Node parent;
    private int gScore;
    private int hScore;
    private int fScore;

    public Node(Position position, Direction direction, int gScore, int hScore) {
        this.position = position;
        this.direction = direction;
        this.gScore = gScore;
        this.hScore = hScore;
        this.fScore = gScore + hScore;
    }

    // Getters et setters
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getGScore() {
        return gScore;
    }

    public void setGScore(int gScore) {
        this.gScore = gScore;
    }

    public int getHScore() {
        return hScore;
    }

    public void setHScore(int hScore) {
        this.hScore = hScore;
    }

    public int getFScore() {
        return fScore;
    }

    public void setFScore(int fScore) {
        this.fScore = fScore;
    }

    // Méthode pour comparer les noeuds en fonction du fScore
    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.fScore, other.fScore);
    }

    // Méthode pour vérifier l'égalité des noeuds en fonction de la position
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Node other = (Node) obj;
        return Objects.equals(position, other.position);
    }

    // Méthode pour générer le hashcode en fonction de la position
    @Override
    public int hashCode() {
        return Objects.hash(position);
    }
}
