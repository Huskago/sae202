package fr.huskago.objects;

// Création d'un objet Position
public class Position {
    // Création d'un objet Tuple
    private final Tuple<Integer, Integer> coordinates;

    // Constructeur de l'objet Position
    public Position(int x, int y) {
        this.coordinates = new Tuple<>(x, y);
    }

    // Getters et setters
    public int getX() { return this.coordinates.getItem1(); }

    public int getY() { return this.coordinates.getItem2(); }

    public void setX(int x) { this.coordinates.setItem1(x); }

    public void setY(int y) { this.coordinates.setItem2(y); }


    // Méthode pour analyser la chaîne de position
    public static Position parsePosition(String positionData) {
        // Supposons que la chaîne de position est au format (x, y)
        positionData = positionData.replace("(", "").replace(")", "");
        String[] coordinates = positionData.split(",");
        int x = Integer.parseInt(coordinates[0].trim());
        int y = Integer.parseInt(coordinates[1].trim());
        return new Position(x, y);
    }
}
