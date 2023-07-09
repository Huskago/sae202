package fr.huskago.objects;

import fr.huskago.GameManager;
import fr.huskago.enums.Direction;

import java.util.ArrayList;
import java.util.List;

// Création de la classe Player
public class Player {
    // Déclaration des variables
    GameManager gameManager;
    private Position position;
    private int experience;
    private List<Quest> completedQuests;

    // Constructeur par défaut
    public Player(GameManager gameManager) {
        this.gameManager = gameManager;
        this.position = new Position(0, 0);
        this.experience = 0;
        this.completedQuests = new ArrayList<>();
    }

    // Getters et setters

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void move(Direction direction) {
        switch (direction) {
            case UP -> this.position.move(0, 1);
            case DOWN -> this.position.move(0, -1);
            case LEFT -> this.position.move(-1, 0);
            case RIGHT -> this.position.move(1, 0);
        }
        this.gameManager.addTime(1);
        this.gameManager.getLogger().log("Player\'s moved to " + this.position.getX() + "x" + this.position.getY() + " (" + direction + ") - " + this.gameManager.getTime() + " time units");

        Quest quest = getQuestByPosition(this.position);

        if (quest != null) {
            finishQuest();
        }
    }

    public int getExperience() {
        return this.experience;
    }

    public void addExperience(int experience) {
        this.experience += experience;
    }

    public void addExperience(Quest quest) {
        if (quest.getId() != 0) {
            addExperience(quest.getExperience());
        } else {
            new Exception("Impossible d'ajouter de l'expérience à la quête n°0").printStackTrace();
        }
    }

    public List<Quest> getCompletedQuests() {
        return completedQuests;
    }

    public Quest getQuestByPosition(Position position) {
        for (Quest quest : this.gameManager.getQuests()) {
            if (quest.getPosition().getX() == position.getX() && quest.getPosition().getY() == position.getY()) {
                return quest;
            }
        }
        return null;
    }

    public void finishQuest() {
        Quest quest = getQuestByPosition(this.position);
        if (quest != null) {
            if (Quest.arePreconditionsMet(quest, this.completedQuests)) {
                this.gameManager.getLogger().log("Player\'s finished quest " + quest.getId() + " (" + quest.getName() + ") - " + this.gameManager.getTime() + " time units");
                if (quest.getId() == 0) {
                    if (this.experience >= quest.getExperience()) {
                        this.gameManager.getLogger().log("Player\'s finished the final quest, the game is now over - " + this.gameManager.getTime() + " time units");
                        System.out.println();
                        System.out.println("Vous avez terminé le jeu !  - " + this.gameManager.getTime() + " time units");
                        this.gameManager.end();
                    } else {
                        new Exception("Impossible de terminer la quête n°0 car l'expérience requise n'est pas atteinte").printStackTrace();
                    }
                } else {
                    addExperience(quest);
                    this.completedQuests.add(quest);
                    this.gameManager.addTime(quest.getDuration());
                }
            } else {
                new Exception("Impossible de terminer la quête " + quest.getId() + " (" + quest.getName() + ") car les préconditions ne sont pas remplies").printStackTrace();
            }
        } else {
            new Exception("Impossible de terminer la quête à la position " + this.position.toString()).printStackTrace();
        }
    }
}
