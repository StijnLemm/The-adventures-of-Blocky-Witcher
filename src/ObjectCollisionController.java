import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;
import java.util.ArrayList;

public class ObjectCollisionController {

    private ArrayList<ImageView> collisionObjects;

    private Coordinate locationOfCollision;

    public ObjectCollisionController(ArrayList<ImageView> collisionObjects) {

        this.collisionObjects = collisionObjects;
    }

    public boolean isColliding(Shape hitBox) {


        for (ImageView object : collisionObjects) {

            if (object.getBoundsInParent().intersects(hitBox.getBoundsInParent())) {

                this.locationOfCollision = new Coordinate(object.getX(), object.getY());

                return true;

            }
        }

        return false;
    }

    public Coordinate getLocationOfCollision() {
        return locationOfCollision;
    }
}


