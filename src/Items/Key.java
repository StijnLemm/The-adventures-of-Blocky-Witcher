package Items;

import GameControllers.GameEngine;
import GameControllers.ObjectCollisionController;
import Interfaces.Updatable;
import Util.Coordinate;
import javafx.scene.shape.Circle;

public class Key implements Updatable {

    private String name;

    private Circle hitBox;
    private Coordinate location;
    private GameEngine gameEngine;

    private boolean inventoryItem;

    public Key(GameEngine gameEngine, Coordinate tileLocation, String name, int tileIndex) {

        this.name = name;

        this.gameEngine = gameEngine;

        this.inventoryItem = false;

        this.location = this.gameEngine.getGameMap().getTileLocation(tileLocation.getValueX(), tileLocation.getValueY());

        System.out.println(location);

        this.hitBox = new Circle((double)this.gameEngine.getGameMap().getMapLoader().getTileWidth() / 2 - 8);

        this.moveHitBox();

        gameEngine.getDrawingController().addOnScreenObject(name, tileIndex,
                new Coordinate(this.location.getValueX(), this.location.getValueY()));


    }


    @Override
    public void update() {
        if(ObjectCollisionController.isCollidingWith(this.gameEngine.getPlayer().getHitBox(), this.hitBox)){

            this.inventoryItem = true;
            this.gameEngine.getDrawingController().removeOnScreenObject(this.name);

        }
    }

    private void moveHitBox(){

        this.hitBox.setCenterX(this.location.getValueX() + this.hitBox.getRadius());
        this.hitBox.setCenterY(this.location.getValueY() + this.hitBox.getRadius());

    }
}
