import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.HashMap;

public class GameEngine {

    // We chose to have the simulation running at 60 frames per second.
    private static final short fps = 60;

    // This timer will show when to update all of the entities to limit to the amount of fps given.
    private Timer fpsTimer;

    // This variable shows the running state of the simulation.
    private Boolean isRunning;

    // This group will have all of the entities.
    private Pane drawSpace;

    private AnimationTimer animationTimer;

    private GameMap gameMap;

    private KeyController keyController;

    private ArrayList<Updatable> dynamicEntities;

    private Stage window;

    private CrossHair crossHair;

    private ObjectCollisionController objectCollisionController;


    public GameEngine(Stage window) {

        this.dynamicEntities = new ArrayList<>();

        this.keyController = new KeyController();

        this.drawSpace = new Pane();

        this.gameMap = new GameMap("map.json", "Textures", this.drawSpace, 1, this);

        this.crossHair = new CrossHair(new ImageView(this.gameMap.getTiles().get(270)));

        this.dynamicEntities.add(this.crossHair);

        this.isRunning = false;

        this.window = window;

        // To make sure the simulation is 60 fps, we wait for an update 1000 millis divided by the given fps.
        this.fpsTimer = new Timer((long) 1000.0 / fps);

        this.init();

        this.spawnPlayer();
    }

    /**
     * The init method makes the animation timer and has the handler.
     */
    public void init() {

        this.initKeyController();

        this.drawMap();

        this.objectCollisionController = new ObjectCollisionController(this.gameMap.getCollisionLayerTiles());

        this.drawSpace.getChildren().add(this.crossHair.getCrossHairImage());

        this.animationTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (fpsTimer.timeout()) {

                    update();

                    fpsTimer.reset();
                }

            }
        };
    }

    public void initKeyController() {
        this.window.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        keyController.setwKeyPressed(true);
                        break;
                    case D:
                        keyController.setdKeyPressed(true);
                        break;
                    case A:
                        keyController.setaKeyPressed(true);
                        break;
                    case S:
                        keyController.setsKeyPressed(true);
                        break;
                    case SPACE:
                        keyController.setSpaceKeyPressed(true);
                        break;
                }
            }
        });

        this.window.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        keyController.setwKeyPressed(false);
                        break;
                    case D:
                        keyController.setdKeyPressed(false);
                        break;
                    case A:
                        keyController.setaKeyPressed(false);
                        break;
                    case S:
                        keyController.setsKeyPressed(false);
                        break;
                    case SPACE:
                        keyController.setSpaceKeyPressed(false);
                        break;
                }
            }
        });
    }

    /**
     * The animation timer handler will call the update 60 times a second to update all of the entities.
     */
    private void update() {

        for (Updatable updatable : this.dynamicEntities) {
            updatable.update();
        }

    }

    private void drawMap() {

        this.gameMap.drawLayer("FloorLayer", false);
        this.gameMap.drawLayer("WallsLayer", true);
        this.gameMap.drawLayer("InteriorLayer", true);

    }

    public void spawnPlayer() {

        ImageView player = new ImageView(this.gameMap.getTiles().get(199));

        this.drawSpace.getChildren().add(player);

        this.dynamicEntities.add(new Player(
                player,
                this.gameMap.getTileLocation(2, 9),
                this.keyController,
                this.objectCollisionController,
                this.getHealthBar(this.gameMap.getTileLocation(2,14), 250, 255)
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
     * The start method will start the animation timer, also the state of the simulation is changed to true here.
     */
    public void start() {

        this.isRunning = true;

        this.animationTimer.start();
    }

    /**
     * The start method will stop the animation timer, also the state of the simulation is changed to false here.
     */
    public void stop() {

        this.isRunning = false;

        this.animationTimer.stop();

    }

    /**
     * An getter for the state of the simulator
     *
     * @return running = true, stopped = false.
     */
    public boolean isRunning() {

        return isRunning;

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
