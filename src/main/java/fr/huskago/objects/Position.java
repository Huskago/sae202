package fr.huskago.objects;

// Création d'un objet Position
public class Position {
    // Création d'un objet Tuple
    private final Tuple<Integer, Integer> coordinates;

    // Constructeur de l'objet Position
    public Position(int x, int y) {
        this.coordinates = new Tuple<>(x, y);
    }

    // Getters pour les coordonnées
    public int getX() {
        return this.coordinates.getItem1();
    }

    public int getY() {
        return this.coordinates.getItem2();
    }

    // Méthode pour déplacer la position
    public void move(int deltaX, int deltaY) {
        int newX = getX() + deltaX;
        int newY = getY() + deltaY;
        this.coordinates.setItem1(newX);
        this.coordinates.setItem2(newY);
    }

    // Méthode pour analyser la chaîne de position
    public static Position parsePosition(String positionData) {
        // Supposons que la chaîne de position est au format (x, y)
        positionData = positionData.replace("(", "").replace(")", "");
        String[] coordinates = positionData.split(",");
        int x = Integer.parseInt(coordinates[0].trim());
        int y = Integer.parseInt(coordinates[1].trim());
        return new Position(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Position other = (Position) obj;
        return getX() == other.getX() && getY() == other.getY();
    }
}
