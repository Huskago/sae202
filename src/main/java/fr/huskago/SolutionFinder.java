package fr.huskago;

import fr.huskago.enums.Direction;
import fr.huskago.objects.Player;
import fr.huskago.objects.Position;
import fr.huskago.objects.Quest;

import java.util.ArrayList;
import java.util.List;

public class SolutionFinder {
    public static List<Direction> findShortestPath(GameManager gameManager, List<Quest> questList) {
        List<Direction> shortestPath = new ArrayList<>();

        Position currentPosition = gameManager.getPlayer().getPosition();
        for (Quest quest : questList) {
            List<Direction> pathToQuest = findShortestPathBetweenPositions(gameManager, currentPosition, quest.getPosition());
            if (pathToQuest == null) {
                return null; // Impossible de trouver un chemin
            }
            shortestPath.addAll(pathToQuest);
            currentPosition = quest.getPosition();
        }

        return shortestPath;
    }

    private static List<Direction> findShortestPathBetweenPositions(GameManager gameManager, Position startPosition, Position targetPosition) {
        // Implémentation du code pour trouver le chemin le plus court entre deux positions
        // Utilisez une approche de recherche de chemin telle que l'algorithme A* ou la recherche en largeur (BFS)

        // Exemple de code factice pour retourner un chemin direct entre les positions
        List<Direction> path = new ArrayList<>();
        int diffX = targetPosition.getX() - startPosition.getX();
        int diffY = targetPosition.getY() - startPosition.getY();

        if (diffX > 0) {
            for (int i = 0; i < diffX; i++) {
                path.add(Direction.RIGHT);
            }
        } else if (diffX < 0) {
            for (int i = 0; i > diffX; i--) {
                path.add(Direction.LEFT);
            }
        }

        if (diffY > 0) {
            for (int i = 0; i < diffY; i++) {
                path.add(Direction.UP);
            }
        } else if (diffY < 0) {
            for (int i = 0; i > diffY; i--) {
                path.add(Direction.DOWN);
            }
        }

        return path;
    }

    public static int calculateTime(GameManager gameManager, List<Direction> path) {
        Player player = gameManager.getPlayer();
        player.setPosition(new Position(0, 0));
        gameManager.resetTime();
        player.getCompletedQuests().clear();
        for (Direction direction : path) {
            player.move(direction);
        }
        return gameManager.getTime();
    }
}