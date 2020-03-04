package GameObjects;

import GameControllers.GameEngine;
import Interfaces.Updatable;
import Util.Coordinate;

public class CrossHair implements Updatable {

    private final String name = "Crosshair";
    private final int tileIndex = 270;

    private Coordinate tileLocation;

    private GameEngine gameEngine;

    public CrossHair(GameEngine gameEngine) {

        this.tileLocation = new Coordinate(0,0);

        this.gameEngine = gameEngine;

        this.gameEngine.getDrawingController().addOnScreenObject(this.name, this.tileIndex, this.tileLocation);

    }

    public void update(){

        Coordinate mouseLocation = this.gameEngine.getPlayerInputController().getMouseLocation();

        Coordinate mouseTile = this.gameEngine.getGameMap().convertToTileLocation(mouseLocation.getValueX(), mouseLocation.getValueY());

        this.tileLocation = this.gameEngine.getGameMap().getTileLocation(mouseTile.getValueX(), mouseTile.getValueY());

        this.gameEngine.getDrawingController().moveOnScreenObject(this.name, this.tileLocation.getValueX(), this.tileLocation.getValueY());

    }
}
