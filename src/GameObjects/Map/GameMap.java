package GameObjects.Map;


import GameControllers.GameEngine;
import Util.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;

public class GameMap {

    private int tileWidth;
    private int tileHeight;
    private int mapWidth;
    private int mapHeight;
    private int SCALE;

    private ImageView crossHair;

    private GameEngine gameEngine;

    private Pane drawField;

    private MapLoader mapLoader;

    private ArrayList<Rectangle> collisionLayerTiles;

    private ArrayList<Image> tiles;
    private ArrayList<TileLayer> layers;

    public GameMap(String filePathJson, Pane drawField, int scale, GameEngine gameEngine) {


        this.collisionLayerTiles = new ArrayList<>();
        this.tiles = new ArrayList<>();
        this.layers = new ArrayList<>();

        this.mapLoader = new MapLoader(filePathJson);

        this.gameEngine = gameEngine;

        this.drawField = drawField;

        this.SCALE = scale;

        // Load essential data from the json file.
        this.tileHeight = this.mapLoader.getTileHeight();
        this.tileWidth = this.mapLoader.getTileWidth();
        this.mapWidth = this.mapLoader.getMapWidth();
        this.mapHeight = this.mapLoader.getMapHeight();

        // At last we add all of the tiles to the tiles Array.
        // At last we add all of the tiles to the tiles Array.
        this.mapLoader.loadTiles("tilesets");
        this.mapLoader.loadLayers("layers");

        this.tiles = this.mapLoader.getTiles();
        this.layers = this.mapLoader.getLayers();

        this.crossHair = new ImageView(this.tiles.get(270));
    }

    /**
     * This method will add the map to the given Pane.
     */
    public void drawLayers() {

        for (TileLayer layer : this.mapLoader.getLayers()) {
            if (layer.isVisible()) {
                this.drawLayer(this.drawField, layer.getTiles());
            } else {
                this.setCollisionLayer(layer.getTiles());
            }
        }

    }

    private void setCollisionLayer(ArrayList<Integer> layerData) {

        int x = 0;
        int y = 0;

        for (int i : layerData) {

            if (x == this.mapWidth * this.tileWidth * this.SCALE) {

                y += this.tileHeight * this.SCALE;

                x = 0;
            }

            // In the data the 0's will be empty space, so it will be skipped but it will add x to the iteration.
            if (i == 0) {

                x += this.tileWidth * this.SCALE;

                continue;
            }

            Rectangle rectangle = new Rectangle();

            rectangle.setX(x);
            rectangle.setY(y);

            rectangle.setWidth((this.tileWidth * this.SCALE));
            rectangle.setHeight((this.tileHeight * this.SCALE));

            this.collisionLayerTiles.add(rectangle);

            x += this.mapLoader.getTileWidth() * this.SCALE;
        }
    }

    /**
     * This method is to draw each individual layer of the map.
     *
     * @param drawMap   the pane where the layer will be drawn.
     * @param layerData the data to decide which pane goes where.
     */
    private void drawLayer(Pane drawMap, ArrayList<Integer> layerData) {

        int x = 0;
        int y = 0;

        for (int i : layerData) {

            if (x == this.mapWidth * this.tileWidth * this.SCALE) {

                y += this.tileHeight * this.SCALE;

                x = 0;
            }

            // In the data the 0's will be empty space, so it will be skipped but it will add to the iteration.
            if (i == 0) {

                x += this.tileWidth * this.SCALE;

                continue;
            }

            // The data in our tiles array begins with index 0, the data from the json begins with 1, the -1 will prevent misplacement.
            Image tempImage = this.tiles.get(i - 1);

            ImageView imageView = new ImageView(tempImage);

            imageView.setOnMouseEntered((event -> {
                this.gameEngine.setCrossHair(imageView.getX(), imageView.getY());
            }));

            imageView.setFitHeight(this.tileHeight * this.SCALE);
            imageView.setFitWidth(this.tileWidth * this.SCALE);

            imageView.setX(x);
            imageView.setY(y);

            drawMap.getChildren().add(imageView);

            x += this.tileWidth * this.SCALE;
        }
    }

    public ArrayList<Rectangle> getCollisionLayerTiles() {

        return collisionLayerTiles;

    }

    public Coordinate getTileLocation(int tileX, int tileY) {

        return new Coordinate(tileX * this.tileWidth, tileY * this.tileHeight);

    }

    public ArrayList<Image> getTiles() {

        return tiles;

    }

    public void setSCALE(int SCALE) {

        this.SCALE = SCALE;

        this.drawField.getChildren().clear();

    }
}
