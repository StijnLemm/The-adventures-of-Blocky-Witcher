package GameObjects.Map;

import GameControllers.DrawingController;
import GameControllers.GameEngine;
import GameControllers.ObjectCollisionController;
import Util.Coordinate;
import GameControllers.PlayerInputController;
import javafx.scene.shape.Circle;

public class PlayerMovementController {

    private final int speed = 6;
    private final int playerAvatarIndex = 199;

    private  String playerName;

    private Circle hitBox;
    private PlayerInputController playerInputController;
    private Coordinate location;
    private DrawingController drawingController;
    private ObjectCollisionController objectCollisionController;

    public PlayerMovementController(String name, Circle hitBox, GameEngine gameEngine, Coordinate location) {

        this.playerInputController = gameEngine.getPlayerInputController();
        this.objectCollisionController = gameEngine.getObjectCollisionController();
        this.drawingController = gameEngine.getDrawingController();

        this.playerName = name;
        this.hitBox = hitBox;
        this.location = location;

        this.drawingController.addOnScreenObject(this.playerName , this.playerAvatarIndex, location);

    }

    public void update() {

        this.movementUpdate();

    }

    private void movementUpdate() {

        if (this.playerInputController.isaKeyPressed() && this.playerInputController.iswKeyPressed()) {
            this.addX(-this.speed + 2);
            this.addY(-this.speed + 2);

            this.moveHitBox();
            return;
        }

        if (this.playerInputController.isdKeyPressed() && this.playerInputController.iswKeyPressed()) {
            this.addX(this.speed - 2);
            this.addY(-this.speed + 2);

            this.moveHitBox();
            return;
        }

        if (this.playerInputController.isaKeyPressed() && this.playerInputController.issKeyPressed()) {
            this.addX(-this.speed + 2);
            this.addY(this.speed - 2);

            this.moveHitBox();
            return;
        }

        if (this.playerInputController.isdKeyPressed() && this.playerInputController.issKeyPressed()) {
            this.addX(this.speed - 2);
            this.addY(this.speed - 2);

            this.moveHitBox();
            return;
        }

        if (this.playerInputController.isaKeyPressed()) {
            this.addX(-this.speed);
        }

        if (this.playerInputController.isdKeyPressed()) {
            this.addX(this.speed);
        }

        if (this.playerInputController.issKeyPressed()) {
            this.addY(this.speed);
        }

        if (this.playerInputController.iswKeyPressed()) {
            this.addY(-this.speed);
        }

        this.moveHitBox();
    }

    private void addX(int amount) {

        this.location.setValueX(this.location.getValueX() + amount);

        this.drawingController.addXForOnScreenObject(this.playerName, amount);

        this.moveHitBox();

        if (objectCollisionController.isColliding(this.hitBox)) {
            this.addX(-(amount));
        }

    }

    private void addY(int amount) {

        this.location.setValueY(this.location.getValueY() + amount);

        this.drawingController.addYForOnScreenObject(this.playerName, amount);

        this.moveHitBox();

        if (objectCollisionController.isColliding(this.hitBox)) {
            this.addY(-(amount));
        }

    }

    private void moveHitBox() {

        this.hitBox.setCenterX(this.location.getValueX() + this.hitBox.getRadius() + 7);
        this.hitBox.setCenterY(this.location.getValueY() + this.hitBox.getRadius() + 7);

    }
}
