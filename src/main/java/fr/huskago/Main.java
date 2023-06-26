package fr.huskago;

import fr.huskago.enums.Direction;
import fr.huskago.utils.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger();
        GameManager gameManager = new GameManager();
        gameManager.init(logger);

//        Pour déplacer le joueur :
//        gameManager.getPlayer().move(Direction.UP);
//        gameManager.getPlayer().move(Direction.DOWN);
//        gameManager.getPlayer().move(Direction.LEFT);
//        gameManager.getPlayer().move(Direction.RIGHT);
//
//        Pour afficher la map :
//        gameManager.getGameMap().displayMap();
//        Pour effectuer la quête :
//        gameManager.getPlayer().finishQuest(gameManager);
    }
}
