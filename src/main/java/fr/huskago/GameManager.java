package fr.huskago;

import fr.huskago.objects.GameMap;
import fr.huskago.objects.Player;
import fr.huskago.objects.Quest;
import fr.huskago.utils.Logger;

import java.util.List;
import java.util.Objects;

public class GameManager {
    private Logger logger;
    private GameMap gameMap;
    private Player player;
    private List<Quest> quests;
    private int time;

    public void init(Logger logger) {
        this.logger = logger;
        player = new Player(this);
        quests = QuestsManager.getQuests();
        List<Integer> coordinates = GameMap.calculateMapSize(Objects.requireNonNull(quests));
        gameMap = new GameMap(this , coordinates.get(0) + 1, coordinates.get(1) + 1);
        time = 0;
        displayQuests();
        gameMap.displayMap();
        logger.log("Game's initialized.");
    }

    public void end() {
        logger.log("Game's ended.");
        logger.closeLogger();

        System.exit(0);
    }

    private void displayQuests() {
        System.out.println("Quêtes chargées : " + quests.size());
        QuestsManager.drawQuestsInterface(quests);
    }

    public Logger getLogger() {
        return logger;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Quest> getQuests() {
        return quests;
    }

    public void addTime(int time) {
        this.time += time;
    }

    public int getTime() {
        return time;
    }
}
