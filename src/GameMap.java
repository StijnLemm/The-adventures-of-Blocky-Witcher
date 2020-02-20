import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import javax.imageio.ImageIO;
import javax.json.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    private JsonObject mapDataJson;

    private ArrayList<ImageView> collisionLayerTiles;

    private ArrayList<Image> tiles;

    public GameMap(String filePathJson, String textureSource, Pane drawField, int scale, GameEngine gameEngine) {

        this.collisionLayerTiles = new ArrayList<>();

        this.tiles = new ArrayList<>();

        this.gameEngine = gameEngine;

        this.drawField = drawField;

        this.SCALE = scale;

        // Create a json reader to pull all of the data from our json file.
        JsonReader reader = Json.createReader(getClass().getResourceAsStream(filePathJson));
        this.mapDataJson = reader.readObject();

        // Load essential data from the json file.
        this.tileHeight = this.mapDataJson.getInt("TileHeight");
        this.tileWidth = this.mapDataJson.getInt("TileWidth");
        this.mapWidth = this.mapDataJson.getInt("MapWidth");
        this.mapHeight = this.mapDataJson.getInt("MapHeight");

        // At last we add all of the tiles to the tiles Array.
        this.loadTiles(textureSource);

        this.crossHair = new ImageView(this.tiles.get(270));
    }

    /**
     * This method will load the texture png, after that it will slice it up to pieces with the designated size,
     * At last it will add all of the sliced pieces into an array.
     */
    private void loadTiles(String source) {
        try {

            BufferedImage tilemap = ImageIO.read(new File(this.mapDataJson.getString(source)));

            for (int y = 0; y < (tilemap.getHeight() / this.tileHeight); y++) {

                for (int x = 0; x < (tilemap.getWidth() / this.tileWidth); x++) {

                    this.tiles.add(SwingFXUtils.toFXImage(tilemap.getSubimage(x * this.tileWidth, y * this.tileHeight, this.tileWidth, this.tileHeight), null));
                }

            }

        } catch (IOException e) {

            System.out.println("File not found");

        }
    }

    /**
     * \
     * This method will add the map to the given Pane.
     *
     * @param layerName this is the label of the array in the json file.
     */
    public void drawLayer(String layerName, boolean collisionLayer) {

        this.drawLayer(this.drawField, this.getDataArray(layerName), collisionLayer);

    }

    /**
     * This method is to draw each individual layer of the map.
     *
     * @param drawMap   the pane where the layer will be drawn.
     * @param layerData the data to decide which pane goes where.
     */
    private void drawLayer(Pane drawMap, ArrayList<Integer> layerData, boolean collisionLayer) {

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

            if(collisionLayer){
                this.collisionLayerTiles.add(imageView);
            }

            imageView.setOnMouseEntered((event -> {
                this.gameEngine.setCrossHair(imageView.getX() , imageView.getY());
            }));

            imageView.setFitHeight(this.tileHeight * this.SCALE);
            imageView.setFitWidth(this.tileWidth * this.SCALE);

            imageView.setX(x);
            imageView.setY(y);

            drawMap.getChildren().add(imageView);

            x += this.tileWidth * this.SCALE;
        }
    }

    /**
     * This method is to convert an array in a json file to an ArrayList.
     *
     * @param locationInJson the string parameter is the label of the array in the json.
     * @return this method will return an ArrayList with all the data.
     */
    private ArrayList<Integer> getDataArray(String locationInJson) {

        ArrayList<Integer> tempArray = new ArrayList<>();

        JsonArray jsonArray = this.mapDataJson.getJsonArray(locationInJson);

        for (JsonValue i : jsonArray) {
            tempArray.add(Integer.parseInt(i.toString()));
        }

        return tempArray;
    }

    public ArrayList<ImageView> getCollisionLayerTiles() {
        return collisionLayerTiles;
    }

    public Coordinate getTileLocation(int tileX, int tileY){

        return new Coordinate(tileX*this.tileWidth, tileY*this.tileHeight);

    }

    public void setSCALE(int SCALE) {
        this.SCALE = SCALE;

        this.drawField.getChildren().clear();
    }

    public ArrayList<Image> getTiles() {
        return tiles;
    }
}
