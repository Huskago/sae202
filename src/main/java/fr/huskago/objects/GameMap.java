package fr.huskago.objects;

import fr.huskago.GameManager;

import java.util.Arrays;
import java.util.List;

public class GameMap {
    private GameManager gameManager;
    private char[][] map;

    public GameMap(GameManager gameManager, int width, int height) {
        this.gameManager = gameManager;
        this.map = new char[height][width];
        initializeMap();
    }

    public static List<Integer> calculateMapSize(List<Quest> quests) {
        int maxCoordinateX = 0;
        int maxCoordinateY = 0;

        // Trouver les coordonnées maximales des quêtes
        for (Quest quest : quests) {
            int coordinateX = quest.getPosition().getX();
            int coordinateY = quest.getPosition().getY();
            maxCoordinateX = Math.max(maxCoordinateX, coordinateX);
            maxCoordinateY = Math.max(maxCoordinateY, coordinateY);
        }

        return Arrays.asList(maxCoordinateX, maxCoordinateY);
    }

    private void initializeMap() {
        // Remplir la carte avec des caractères par défaut
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                this.map[i][j] = '.';
            }
        }
    }

    public void displayMap() {
        System.out.println();
        System.out.println("Carte chargée : " + this.map.length + "x" + this.map[0].length);
        System.out.println("Position du joueur : " + this.gameManager.getPlayer().getPosition().getX() + "x" + this.gameManager.getPlayer().getPosition().getY());
        System.out.println();

        // Réinitialiser la carte
        initializeMap();

        // Placer les quêtes sur la carte
        for (Quest quest : this.gameManager.getQuests()) {
            char symbol = quest.getId() == 0 ? 'Q' : 'q';
            this.map[this.map.length - quest.getPosition().getY() - 1][quest.getPosition().getX()] = symbol;
        }

        // Placer le joueur sur la carte
        Player player = this.gameManager.getPlayer();
        this.map[this.map.length - player.getPosition().getY() - 1][player.getPosition().getX()] = 'P';

        // Effacer la carte précédente
        System.out.print("\033[H\033[2J");
        System.out.flush();

        // Afficher la carte
        for (int i = 0; i < this.map.length; i++) {
            for (int j = 0; j < this.map[i].length; j++) {
                System.out.print(this.map[i][j]);
            }
            System.out.println(); // Saut de ligne après chaque ligne de la carte
        }
    }

    public int getWidth() {
        return this.map[0].length;
    }

    public int getHeight() {
        return this.map.length;
    }
}
