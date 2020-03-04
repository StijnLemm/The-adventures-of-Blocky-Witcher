package GameObjects.Map;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import javax.json.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MapLoader {

    private int tileWidth;
    private int tileHeight;

    private int mapWidth;
    private int mapHeight;

    private JsonObject jsonObject;

    private ArrayList<Image> tiles;
    private ArrayList<TileLayer> layers;

    public MapLoader(String jsonFileLocation) {

        this.layers = new ArrayList<>();
        this.tiles = new ArrayList<>();

        JsonReader reader = Json.createReader(getClass().getResourceAsStream(jsonFileLocation));
        this.jsonObject = reader.readObject();

        this.tileHeight = this.jsonObject.getInt("tileheight");
        this.tileWidth = this.jsonObject.getInt("tilewidth");
        this.mapWidth = this.jsonObject.getInt("width");
        this.mapHeight = this.jsonObject.getInt("height");
    }

    /**
     * this method will load each layer into an layer array.
     * @param arrayName
     */
    public void loadLayers(String arrayName){
        for(int i = 0 ; i < this.jsonObject.getJsonArray(arrayName).size(); i++)
        {
            JsonObject layer = this.jsonObject.getJsonArray(arrayName).getJsonObject(i);

            if(layer.getString("type").equals("tilelayer")) {

                this.layers.add(new TileLayer(layer.getString("name"), layer.getBoolean("visible"),
                        this.getDataArray(layer.getJsonArray("data"))));

            }
        }
    }

    /**
     * This method will load the texture png, after that it will slice it up to pieces with the designated size,
     * At last it will add all of the sliced pieces into an array.
     */
    public void loadTiles(String arrayName) {

        for (int i = 0; i < this.jsonObject.getJsonArray(arrayName).size(); i++) {

            JsonObject tileSet = this.jsonObject.getJsonArray(arrayName).getJsonObject(i);

            try {

                BufferedImage tilemap = ImageIO.read(new File("Resources/" + tileSet.getString("image")));

                for (int y = 0; y < (tilemap.getHeight() / this.tileHeight); y++) {

                    for (int x = 0; x < (tilemap.getWidth() / this.tileWidth); x++) {

                        this.tiles.add(SwingFXUtils.toFXImage(tilemap.getSubimage(x * this.tileWidth, y * this.tileHeight,
                                this.tileWidth, this.tileHeight), null));
                    }

                }
            } catch (IOException e) {

                System.out.println("File not found");

            }
        }
    }

    /**
     * This method is to convert an array in a json file to an ArrayList.
     *
     * @param jsonValues the values parameter is the array in the json.
     * @return this method will return an ArrayList with all the data.
     */
    private ArrayList<Integer> getDataArray(JsonArray jsonValues) {

        ArrayList<Integer> tempArray = new ArrayList<>();

        for (JsonValue i : jsonValues) {
            //TODO
            if(Long.parseLong(i.toString()) > 10000){
                tempArray.add(0);
                continue;
            }
            tempArray.add(Integer.parseInt(i.toString()));
        }

        return tempArray;
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getMapWidth() {
        return mapWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public int getMapHeight() {
        return mapHeight;
    }

    public ArrayList<Image> getTiles() {
        return tiles;
    }

    public ArrayList<TileLayer> getLayers() {
        return layers;
    }
}
