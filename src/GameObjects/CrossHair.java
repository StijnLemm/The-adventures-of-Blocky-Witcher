package GameObjects;

import GameControllers.GameEngine;
import Interfaces.Updatable;
import Util.Coordinate;
import Util.Timer;

public class CrossHair implements Updatable {

    private final String name = "Crosshair";
    private final int tileIndex = 270;

    private Timer timer;

    private Coordinate location;
    private Coordinate mouseLocation;
    private Coordinate tileLocation;

    private GameEngine gameEngine;

    public CrossHair(GameEngine gameEngine) {

        this.timer = new Timer(50);

        this.tileLocation = new Coordinate(0,0);

        this.location = new Coordinate(0,0);

        this.gameEngine = gameEngine;

        this.gameEngine.getDrawingController().addOnScreenObject(this.name, this.tileIndex, this.location);

    }

    public void update(){

        this.mouseLocation = this.gameEngine.getPlayerInputController().getMouseLocation();

        //Coordinate mouseTile = this.gameEngine.getGameMap().convertToTileLocation(mouseLocation.getValueX(), mouseLocation.getValueY());

        //this.tileLocation = this.gameEngine.getGameMap().getTileLocation(mouseTile.getValueX(), mouseTile.getValueY());

        this.gameEngine.getDrawingController().moveOnScreenObject(this.name, this.mouseLocation.getValueX(), this.mouseLocation.getValueY());

    }
}
