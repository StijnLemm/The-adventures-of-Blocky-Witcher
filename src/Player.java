import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Player extends Npc implements Updatable {

    private ImageView avatar;
    private Coordinate location;
    private KeyController keyController;

    private HealthBar healthBar;

    private Rectangle hitBox;

    private ObjectCollisionController objectCollisionController;

    private int speed;

    public Player(ImageView avatar, Coordinate location, KeyController keyController, ObjectCollisionController objectCollisionController, HealthBar healthBar) {

        this.speed = 5;

        this.healthBar = healthBar;

        this.avatar = avatar;

        this.location = location;
        this.keyController = keyController;

        this.hitBox = new Rectangle(avatar.getImage().getWidth() - 10, avatar.getImage().getHeight() - 10);

        this.objectCollisionController = objectCollisionController;
    }

    public void update() {



        this.movementUpdate();
    }

    private void movementUpdate(){
        if (this.keyController.isaKeyPressed() && this.keyController.iswKeyPressed()) {
            this.addX(-this.speed + 2);
            this.addY(-this.speed + 2);

            this.setLayout();
            return;
        }

        if (this.keyController.isdKeyPressed() && this.keyController.iswKeyPressed()) {
            this.addX(this.speed - 2);
            this.addY(-this.speed + 2);

            this.setLayout();
            return;
        }

        if (this.keyController.isaKeyPressed() && this.keyController.issKeyPressed()) {
            this.addX(-this.speed + 2);
            this.addY(this.speed - 2);

            this.setLayout();
            return;
        }

        if (this.keyController.isdKeyPressed() && this.keyController.issKeyPressed()) {
            this.addX(this.speed - 2);
            this.addY(this.speed - 2);

            this.setLayout();
            return;
        }

        if (this.keyController.isaKeyPressed()) {
            this.addX(-this.speed);
        }

        if (this.keyController.isdKeyPressed()) {
            this.addX(this.speed);
        }

        if (this.keyController.issKeyPressed()) {
            this.addY(this.speed);
        }

        if (this.keyController.iswKeyPressed()) {
            this.addY(-this.speed);
        }

        this.setLayout();
    }

    private void setLayout(){

        //System.out.println("x: " + this.location.getValueX() + " y: " + this.location.getValueY());
        this.hitBox.setX(this.location.getValueX() + 5);
        this.hitBox.setY(this.location.getValueY() + 5);

        this.avatar.setLayoutX(this.location.getValueX());
        this.avatar.setLayoutY(this.location.getValueY());

    }

    public void addX(double amount) {

        this.location.setValueX(this.location.getValueX() + amount);

        this.setLayout();

        if(objectCollisionController.isColliding(this.hitBox)){
            this.addX(-(amount));
        }

    }

    public void addY(double amount) {

        this.location.setValueY(this.location.getValueY() + amount);

        this.setLayout();

        if(objectCollisionController.isColliding(this.hitBox)) {
            this.addY(-(amount));
        }

    }

    public void setX(double x) {
        this.location.setValueX(x);
    }

    public void setY(double y) {
        this.location.setValueY(y);
    }

    public double getX() {
        return this.location.getValueX();
    }

    public double getY() {
        return this.location.getValueY();
    }
}
