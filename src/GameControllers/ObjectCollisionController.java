package GameControllers;

import Util.Coordinate;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class ObjectCollisionController {

    private ArrayList<Shape> collisionObjects;

    private Coordinate locationOfCollision;

    public ObjectCollisionController(ArrayList<Shape> collisionObjects) {

        this.collisionObjects = collisionObjects;
    }

    public boolean isColliding(Shape hitBox) {


        for (Shape object : collisionObjects) {

            if (object.getBoundsInLocal().intersects(hitBox.getBoundsInParent())) {

                this.locationOfCollision = new Coordinate((int)object.getLayoutX(), (int)object.getLayoutY());

                return true;

            }
        }

        return false;
    }

    public void addCollisionObject(Shape hitBox){

        this.collisionObjects.add(hitBox);

    }

    public void removeCollisionObject(Shape hitBox){

        this.collisionObjects.remove(hitBox);

    }

    public Coordinate getLocationOfCollision() {
        return locationOfCollision;
    }
}


