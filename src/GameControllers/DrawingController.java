package GameControllers;

import Util.Coordinate;
import com.sun.istack.internal.NotNull;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

public class DrawingController {

    private Pane drawSpace;

    private ArrayList<Image> tiles;

    private HashMap<String, Node> onScreenObjects;

    public DrawingController(Pane drawSpace, ArrayList<Image> tiles) {

        this.drawSpace = drawSpace;

        this.tiles = tiles;

        this.onScreenObjects = new HashMap<>();
    }

    public void removeOnScreenObject(String name) {

        this.removeFromHashMap(name);

        this.drawSpace.getChildren().remove(this.getFromHashMap(name));

    }

    public void addOnScreenObject(String name, int tileIndex, Coordinate location) {

        this.addToHashMap(name, this.getTile(tileIndex));

        this.drawTile(name, location);

    }

    public void addXForOnScreenObject(String name, int amountX){

        this.moveOnScreenObject(name,
                ((int)this.onScreenObjects.get(name).getLayoutX() + amountX),
                (int)this.onScreenObjects.get(name).getLayoutY());

    }

    public void addYForOnScreenObject(String name, int amountY){

        this.moveOnScreenObject(name,
                ((int)this.onScreenObjects.get(name).getLayoutX()),
                (int)this.onScreenObjects.get(name).getLayoutY() + amountY);

    }

    public void moveOnScreenObject(String name, int x, int y) {

        Node node = this.getFromHashMap(name);

        if (node != null) {

            node.setLayoutX(x);
            node.setLayoutY(y);

        }

    }

    private void drawTile(String name, Coordinate location) {

        this.moveOnScreenObject(name, (int)location.getValueX(), (int)location.getValueY());

        this.drawSpace.getChildren().add(this.onScreenObjects.get(name));

    }

    private Node getTile(int index) {

        try {

            return new ImageView(this.tiles.get(index));

        } catch (IndexOutOfBoundsException e) {

            System.out.println("The index of the draw-call in DrawingController is NA in the tileArray, index: " + index);

        }
        return null;
    }

    private Node getFromHashMap(@NotNull String key) {

        try {
            return this.onScreenObjects.get(key);
        } catch (Exception e) {

            System.out.println("The key given to the DrawingController is NA in the HashMap, key: " + key);

        }

        return null;
    }

    private void addToHashMap(@NotNull String key, Node node) {

        try {

            this.onScreenObjects.put(key, node);

        } catch (Exception e) {

            System.out.println("Name of the object trying to add is already in use, name: " + key);

        }
    }

    private void removeFromHashMap(@NotNull String key) {

        try {
            this.onScreenObjects.remove(key);
        } catch (Exception e) {

            System.out.println("The key given to the DrawingController is NA in the HashMap, key: " + key);

        }

    }
}
