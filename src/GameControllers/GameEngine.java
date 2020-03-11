package GameControllers;

import GameObjects.CrossHair;
import GameObjects.Map.GameMap;
import GameObjects.HealthBar;
import GameObjects.Player;
import Interfaces.Updatable;
import Items.Key;
import Util.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameEngine {

    // We chose to have the simulation running at 60 frames per second.
    private static final short fps = 60;

    // This variable shows the running state of the simulation.
    private Boolean isRunning;

    // This group will have all the entities.
    private Pane drawSpace;

    private GameMap gameMap;

    private PlayerInputController playerInputController;

    private ArrayList<Updatable> dynamicEntities;

    private Stage window;

    private CrossHair crossHair;

    private GameRenderer gameRenderer;

    private ObjectCollisionController objectCollisionController;

    private DrawingController drawingController;

    private Player player;

    public GameEngine(Stage window) {

        this.window = window;

        this.window.setTitle("The Blocky-Witcher!");

        this.init();

        Key key = new Key(this, new Coordinate(14, 1), "key1", 81);

        this.dynamicEntities.add(key);
    }

    public void init() {


        this.dynamicEntities = new ArrayList<>();

        this.drawSpace = new Pane();

        this.gameRenderer = new GameRenderer(this, fps);

        this.gameMap = new GameMap("/map_level_1_V2.0.json", this.drawSpace, 1, this);

        this.drawingController = new DrawingController(this.drawSpace, this.gameMap.getTiles());

        this.playerInputController = new PlayerInputController(this.window);

        this.objectCollisionController = new ObjectCollisionController(this.gameMap.getCollisionLayerTiles());

        this.drawMap();

        this.crossHair = new CrossHair(this);

        this.dynamicEntities.add(this.crossHair);

        this.spawnPlayer();

        this.gameRenderer.start();

    }

    /**
     * The animation timer handler will call the update 60 times a second to update all the entities.
     */
    public void update() {

        for (Updatable updatable : this.dynamicEntities) {
            updatable.update();
        }

    }

    private void drawMap() {

        this.gameMap.drawLayers();

    }

    public void spawnPlayer() {

        this.player = new Player("Player", this.gameMap.getTileLocation(2, 6), this);

        this.dynamicEntities.add(this.player);

    }

    private void addEnemy(int indexOfAvatar, Coordinate spawnLocation) {



    }

    public DrawingController getDrawingController() {
        return drawingController;
    }

    public GameMap getGameMap() {
        return this.gameMap;
    }

    public PlayerInputController getPlayerInputController() {
        return playerInputController;
    }

    public ObjectCollisionController getObjectCollisionController() {
        return objectCollisionController;
    }

    public HealthBar getPlayerHealthBar(Coordinate coordinate, int beginTile, int endTile) {
        ArrayList<Image> hearts = new ArrayList<>();

        for (int i = beginTile; i < endTile; i++) {
            hearts.add(this.gameMap.getTiles().get(i));
        }

        ImageView heathBarView = new ImageView();

        HealthBar healthBar = new HealthBar(hearts, heathBarView, coordinate);

        this.drawSpace.getChildren().add(healthBar.getHealthBarView());

        return healthBar;
    }

    public Player getPlayer(){

        return this.player;

    }

    /**
     * To display the simulation you can get the draw space here, this space will be updated (60 times each second).
     *
     * @return An Group with all the entities and locations.
     */
    public Pane getDrawSpace() {

        return drawSpace;

    }
}
