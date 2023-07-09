package fr.huskago;

import fr.huskago.objects.Position;
import fr.huskago.objects.Quest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class QuestsManager {
    // Méthode pour récupérer les quêtes
    public static List<Quest> getQuests() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/quests.txt"))) {
            List<Quest> quests = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\\|");

                // Extraction des données de la ligne
                int id = Integer.parseInt(data[0]);
                Position position = Position.parsePosition(data[1]);
                List<List<Integer>> preconditions = Quest.parsePreconditions(data[2]);
                int duration = Integer.parseInt(data[3]);
                int experience = Integer.parseInt(data[4]);
                String name = data[5];

                // Création de l'objet Quest et ajout à la liste
                quests.add(new Quest(id, position, preconditions, duration, experience, name));
            }

            return quests;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Méthode pour trouver les solutions
    private static List<List<Quest>> findQuestSolutions(Quest currentQuest, List<Quest> quests, List<Quest> completedQuests, List<Quest> path, List<List<Quest>> solutions) {
        // Copie du chemin actuel et ajout de la quête courante
        List<Quest> newPath = new ArrayList<>(path);
        newPath.add(currentQuest);

        // Vérifier si les conditions préalables de la quête courante sont remplies
        if (Quest.arePreconditionsMet(currentQuest, completedQuests)) {
            // Vérifier si la quête courante est la quête finale
            if (currentQuest.getId() == 0) {
                // Ajouter le chemin actuel à la liste des solutions
                solutions.add(newPath);
                return solutions;
            }

            // Copie des quêtes complétées avec ajout de la quête courante
            List<Quest> newCompletedQuests = new ArrayList<>(completedQuests);
            newCompletedQuests.add(currentQuest);

            // Parcourir toutes les quêtes possibles après la quête courante
            for (Quest nextQuest : quests) {
                // Vérifier si la quête suivante peut être effectuée après la quête courante
                if (!completedQuests.contains(nextQuest) && !newPath.contains(nextQuest) && Quest.arePreconditionsMet(nextQuest, newCompletedQuests)) {
                    // Appel récursif pour trouver les solutions à partir de la quête suivante
                    findQuestSolutions(nextQuest, quests, newCompletedQuests, newPath, solutions);
                }
            }
        }

        return solutions;
    }

    // Méthode pour récupérer les solutions
    public static List<List<Quest>> getQuestsSolutions() {
        // Récupérer la liste des quêtes
        List<Quest> quests = getQuests();
        // Liste pour stocker les solutions
        List<List<Quest>> solutions = new ArrayList<>();

        // Parcourir toutes les quêtes pour trouver les quêtes de départ
        for (Quest quest : quests) {
            // Vérifier si les conditions préalables de la quête de départ sont remplies
            if (Quest.arePreconditionsMet(quest, new ArrayList<>())) {
                // Appel récursif pour trouver les solutions à partir de la quête de départ
                findQuestSolutions(quest, quests, new ArrayList<>(), new ArrayList<>(), solutions);
            }
        }

        return solutions;
    }

    // Méthode pour dessiner l'interface des quêtes dans la console
    public static void drawQuestsInterface(List<Quest> quests) {
        // Définir les caractères ASCII pour le cadre
        char topLeftCorner = '┌';
        char topRightCorner = '┐';
        char horizontalLine = '─';
        char verticalLine = '│';
        char bottomLeftCorner = '└';
        char bottomRightCorner = '┘';

        // Trouver la longueur maximale du nom de quête
        int maxQuestNameLength = 0;
        for (Quest quest : quests) {
            int questNameLength = quest.getName().length();
            if (questNameLength > maxQuestNameLength) {
                maxQuestNameLength = questNameLength;
            }
        }

        // Dessiner le cadre supérieur
        System.out.println();
        System.out.print(topLeftCorner);
        for (int i = 0; i < maxQuestNameLength + 2; i++) {
            System.out.print(horizontalLine);
        }
        System.out.print(topRightCorner);
        System.out.println();

        // Dessiner les quêtes
        System.out.print(verticalLine + " Liste des quêtes :");
        for (int i = 0; i < maxQuestNameLength - 18; i++) {
            System.out.print(" ");
        }
        System.out.print(" " + verticalLine);
        System.out.println();
        for (Quest quest : quests) {
            System.out.print(verticalLine + " " + quest.getName());
            for (int i = 0; i < maxQuestNameLength - quest.getName().length(); i++) {
                System.out.print(" ");
            }
            System.out.print(" " + verticalLine);
            System.out.println();
        }

        // Dessiner le cadre inférieur
        System.out.print(bottomLeftCorner);
        for (int i = 0; i < maxQuestNameLength + 2; i++) {
            System.out.print(horizontalLine);
        }
        System.out.print(bottomRightCorner);
        System.out.println();
    }
}
