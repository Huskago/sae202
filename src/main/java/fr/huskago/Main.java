package fr.huskago;

import fr.huskago.enums.Direction;
import fr.huskago.objects.Quest;
import fr.huskago.utils.Logger;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Créer une instance de Logger pour afficher les logs
        Logger logger = new Logger();

        // Créer une instance de GameManager et initialiser les données du jeu
        GameManager gameManager = new GameManager();
        gameManager.init(logger);

        List<List<Quest>> questsSolutions = QuestsManager.getQuestsSolutions();
        System.out.println("Quests solutions: " + questsSolutions.size());
        System.out.println();
        for (int i = 0; i < questsSolutions.size(); i++) {
            System.out.println("Quests solution " + (i + 1));
            for (Quest quest : questsSolutions.get(i)) {
                System.out.println(quest.getName());
            }
            System.out.println();
        }

        List<List<Direction>> shortestPaths = new ArrayList<>();

        for (List<Quest> questsSolution : questsSolutions) {
            List<Direction> shortestPath = SolutionFinder.findShortestPath(gameManager, questsSolution);
            if (shortestPath != null) {
                shortestPaths.add(shortestPath);
            }
        }

        System.out.println("Shortest paths: " + shortestPaths.size());
        for (int i = 0; i < shortestPaths.size(); i++) {
            System.out.println("Shortest path " + (i + 1));
            for (Direction direction : shortestPaths.get(i)) {
                System.out.print(direction + " ");
            }
            System.out.println();
            System.out.println();
        }

        List<Integer> shortestPathsTime = new ArrayList<>();

        for (List<Direction> shortestPath : shortestPaths) {
            int shortestPathTime = SolutionFinder.calculateTime(gameManager, shortestPath);
            shortestPathsTime.add(shortestPathTime);
        }

        System.out.println("Shortest paths time: " + shortestPathsTime.size());

        for (int i = 0; i < shortestPathsTime.size(); i++) {
            System.out.println("Shortest path time " + (i + 1) + ": " + shortestPathsTime.get(i));
        }
    }
}
