package fr.huskago;

import fr.huskago.objects.Position;
import fr.huskago.objects.Quest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
