package GameObjects;

import GameControllers.GameEngine;
import GameObjects.Map.PlayerMovementController;
import Interfaces.Updatable;
import Util.Coordinate;
import javafx.scene.shape.Circle;

public class Player extends Npc implements Updatable {

    private String playerName;

    private Coordinate location;
    private HealthBar healthBar;

    private PlayerMovementController playerMovementController;

    public Player(String name, Coordinate location, GameEngine gameEngine) {

        this.healthBar = gameEngine.getPlayerHealthBar(gameEngine.getGameMap().getTileLocation(0, 0), 250, 255);

        //--------------------------------------------------------------------------------------------------------------

        this.playerName = name;
        this.location = location;

        //--------------------------------------------------------------------------------------------------------------

        Circle hitBox = new Circle((gameEngine.getGameMap().getTiles().get(0).getWidth() / 2) - 7);
        this.playerMovementController = new PlayerMovementController(name, hitBox, gameEngine, this.location);

    }

    public void update() {

        this.playerMovementController.update();

    }

    public int getAmountOfHealth(){

       return this.healthBar.getAmountOfLives();

    }

    public void setX(int x) {
        this.location.setValueX(x);
    }

    public void setY(int y) {
        this.location.setValueY(y);
    }

    public double getX() {
        return this.location.getValueX();
    }

    public double getY() {
        return this.location.getValueY();
    }
}
