package fr.huskago.objects;

import java.util.ArrayList;
import java.util.List;

// Création de la classe Quest
public class Quest {
    // Déclaration des variables
    private int id;
    private Position position;
    private List<List<Integer>> preconditions;
    private int duration;
    private int experience;
    private String name;

    // Constructeur
    public Quest(int id, Position position, List<List<Integer>> preconditions, int duration, int experience, String name) {
        this.id = id;
        this.position = position;
        this.preconditions = preconditions;
        this.duration = duration;
        this.experience = experience;
        this.name = name;
    }

    // Getters et setters
    public int getId() {
        return this.id;
    }

    public Position getPosition() {
        return this.position;
    }

    public List<List<Integer>> getPreconditions() {
        return this.preconditions;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getExperience() {
        return this.experience;
    }

    public String getName() {
        return this.name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setPreconditions(List<List<Integer>> preconditions) {
        this.preconditions = preconditions;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean arePreconditionsMet(Quest quest, List<Quest> completedQuests) {
        List<List<Integer>> preconditions = quest.getPreconditions();

        // Vérifier chaque condition alternative
        for (List<Integer> subPreconditions : preconditions) {
            boolean isSubConditionMet = true;

            // Vérifier chaque quête dans la condition alternative
            for (Integer questId : subPreconditions) {
                boolean isQuestCompleted = false;

                // Vérifier si la quête est complétée
                for (Quest completedQuest : completedQuests) {
                    if (completedQuest.getId() == questId) {
                        isQuestCompleted = true;
                        break;
                    }
                }

                if (!isQuestCompleted) {
                    isSubConditionMet = false;
                    break;
                }
            }

            // Si une condition alternative est remplie, la quête peut être effectuée
            if (isSubConditionMet) {
                return true;
            }
        }

        // Aucune condition alternative n'est remplie, la quête ne peut pas être effectuée
        return false;
    }


    // Méthode pour parser les préconditions
    public static List<List<Integer>> parsePreconditions(String input) {
        List<List<Integer>> preconditions = new ArrayList<>();

        // Supprimer les parenthèses extérieures
        String sanitizedInput = input.substring(1, input.length() - 1);

        // Diviser les préconditions en fonction des virgules et des parenthèses
        String[] conditions = sanitizedInput.split("\\),\\(");

        for (String condition : conditions) {
            List<Integer> subPreconditions = new ArrayList<>();

            // Supprimer les parenthèses intérieures
            String sanitizedCondition = condition.replace("(", "").replace(")", "");

            // Vérifier si la condition n'est pas vide
            if (!sanitizedCondition.isEmpty()) {
                // Diviser les quêtes en fonction des virgules
                String[] quests = sanitizedCondition.split(",");

                for (String quest : quests) {
                    subPreconditions.add(Integer.parseInt(quest));
                }
            }

            preconditions.add(subPreconditions);
        }

        return preconditions;
    }
}
