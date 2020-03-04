package GameControllers;

import Util.Coordinate;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class ObjectCollisionController {

    private ArrayList<Rectangle> collisionObjects;

    private Coordinate locationOfCollision;

    public ObjectCollisionController(ArrayList<Rectangle> collisionObjects) {

        this.collisionObjects = collisionObjects;
    }

    public boolean isColliding(Shape hitBox) {


        for (Shape object : collisionObjects) {

            if (object.getBoundsInLocal().intersects(hitBox.getBoundsInParent())) {

                this.locationOfCollision = new Coordinate(object.getLayoutX(), object.getLayoutY());

                return true;

            }
        }

        return false;
    }

    public Coordinate getLocationOfCollision() {
        return locationOfCollision;
    }
}


