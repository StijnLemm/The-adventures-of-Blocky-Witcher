package GameObjects;

import GameControllers.GameEngine;
import GameControllers.ObjectCollisionController;
import Util.Coordinate;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Enemy extends Npc {

    private ImageView avatar;

    private Coordinate location;
    private Coordinate target;

    private Rectangle hitBox;

    private ObjectCollisionController objectCollisionController;

    private boolean hasTarget;

    public Enemy(ImageView avatar, Coordinate location, GameEngine gameEngine) {

        this.avatar = avatar;

        this.location = location;

        this.objectCollisionController = gameEngine.getObjectCollisionController();

        this.hitBox = new Rectangle(avatar.getImage().getWidth(), avatar.getImage().getHeight());

        this.hasTarget = false;
    }

    public void update(){
        if(this.hasTarget){

        }
    }

    private void setLayout(){

        //System.out.println("x: " + this.location.getValueX() + " y: " + this.location.getValueY());
        this.hitBox.setX(this.location.getValueX() + 5);
        this.hitBox.setY(this.location.getValueY() + 5);

        this.avatar.setLayoutX(this.location.getValueX());
        this.avatar.setLayoutY(this.location.getValueY());

    }

    public void setTarget(Coordinate coordinate){
        this.target = coordinate;
        this.hasTarget = true;
    }

    public void addX(int amount) {
        this.location.setValueX(this.location.getValueX() + amount);

        this.setLayout();

        if(objectCollisionController.isColliding(this.hitBox)){
            this.addX(-(amount));
        }
    }

    public void addY(int amount) {
        this.location.setValueY(this.location.getValueY() + amount);

        this.setLayout();

        if(objectCollisionController.isColliding(this.hitBox)) {
            this.addY(-(amount));
        }

    }
}
