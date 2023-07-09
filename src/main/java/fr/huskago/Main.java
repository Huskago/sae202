package fr.huskago;

import fr.huskago.objects.Quest;
import fr.huskago.utils.Logger;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Créer une instance de Logger pour afficher les logs
        Logger logger = new Logger();

        // Créer une instance de GameManager et initialiser les données du jeu
        GameManager gameManager = new GameManager();
        gameManager.init(logger);

        List<List<Quest>> solutions = QuestsManager.getQuestsSolutions();

        System.out.println("Nombre de solutions : " + solutions.size());

        // Parcours de toutes les solutions
        for (List<Quest> solution : solutions) {
            // Affichage de la solution
            System.out.println("Solution :");
            for (Quest quest : solution) {
                System.out.println(quest.getName());
            }
            System.out.println();
        }
    }
}
