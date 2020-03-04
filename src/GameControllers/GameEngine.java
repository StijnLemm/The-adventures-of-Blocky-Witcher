package GameControllers;

import GameObjects.CrossHair;
import GameObjects.Map.GameMap;
import GameObjects.HealthBar;
import GameObjects.Player;
import Interfaces.Updatable;
import Util.Coordinate;
import Util.KeyController;
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

    // This group will have all of the entities.
    private Pane drawSpace;

    private GameMap gameMap;

    private KeyController keyController;

    private ArrayList<Updatable> dynamicEntities;

    private Stage window;

    private CrossHair crossHair;

    private ObjectCollisionController objectCollisionController;

    private  GameRenderer gameRenderer;


    public GameEngine(Stage window) {

        this.window = window;

        //--------------------------------------------------------------------------------------------------------------

        this.dynamicEntities = new ArrayList<>();

        this.drawSpace = new Pane();

        this.gameRenderer = new GameRenderer(this, fps);

        this.gameMap = new GameMap("/map_level_1_V2.0.json", this.drawSpace, 1, this);

        this.crossHair = new CrossHair(new ImageView(this.gameMap.getTiles().get(270)));

        this.keyController = new KeyController(this.window);

        //--------------------------------------------------------------------------------------------------------------

        this.init();
    }

    /**
     * The init method makes the animation timer and has the handler.
     */
    public void init() {

        this.dynamicEntities.add(this.crossHair);

        this.drawMap();

        this.objectCollisionController = new ObjectCollisionController(this.gameMap.getCollisionLayerTiles());

        this.drawSpace.getChildren().add(this.crossHair.getCrossHairImage());

        this.spawnPlayer();

        this.gameRenderer.start();

    }

    /**
     * The animation timer handler will call the update 60 times a second to update all of the entities.
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

        ImageView player = new ImageView(this.gameMap.getTiles().get(199));

        this.drawSpace.getChildren().add(player);

        this.dynamicEntities.add(new Player(
                player,
                this.gameMap.getTileLocation(2, 6),
                this.keyController,
                this.objectCollisionController,
                this.getHealthBar(this.gameMap.getTileLocation(0,0), 250, 255)
        ));

    }

    private void addEnemy(int indexOfAvatar, Coordinate spawnLocation){

    }

    private HealthBar getHealthBar(Coordinate coordinate, int beginTile, int endTile){
        ArrayList<Image> hearts = new ArrayList<>();

        for (int i = beginTile; i < endTile; i++) {
            hearts.add(this.gameMap.getTiles().get(i));
        }

        ImageView heathBarView = new ImageView();

        HealthBar healthBar = new HealthBar(hearts, heathBarView, coordinate);

        this.drawSpace.getChildren().add(healthBar.getHealthBarView());

        return healthBar;
    }

    public void setCrossHair(double x, double y) {

        this.crossHair.setY(y);
        this.crossHair.setX(x);

    }

    /**
     * To display the simulation you can get the draw space here, this space will be updated 60 fps.
     *
     * @return An Group with all of the entities and locations.
     */
    public Pane getDrawSpace() {

        return drawSpace;

    }
}
