package GameControllers;

import Util.Coordinate;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PlayerInputController {

    private Stage window;

    private Coordinate mouseLocation;

    private boolean dKeyPressed;
    private boolean sKeyPressed;
    private boolean aKeyPressed;
    private boolean wKeyPressed;
    private boolean spaceKeyPressed;

    public PlayerInputController(Stage window) {


        this.mouseLocation = new Coordinate(0, 0);

        this.dKeyPressed = false;
        this.sKeyPressed = false;
        this.aKeyPressed = false;
        this.wKeyPressed = false;
        this.spaceKeyPressed = false;

        this.window = window;

        this.addEventHandlers();
    }

    public void addEventHandlers() {
        this.window.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        setwKeyPressed(true);
                        break;
                    case D:
                        setdKeyPressed(true);
                        break;
                    case A:
                        setaKeyPressed(true);
                        break;
                    case S:
                        setsKeyPressed(true);
                        break;
                    case SPACE:
                        setSpaceKeyPressed(true);
                        break;
                }
            }
        });

        this.window.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:
                        setwKeyPressed(false);
                        break;
                    case D:
                        setdKeyPressed(false);
                        break;
                    case A:
                        setaKeyPressed(false);
                        break;
                    case S:
                        setsKeyPressed(false);
                        break;
                    case SPACE:
                        setSpaceKeyPressed(false);
                        break;
                }
            }
        });

        this.window.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                    PlayerInputController.this.mouseLocation.setValueX((int) event.getX());
                    PlayerInputController.this.mouseLocation.setValueY((int) event.getY());

            }
        });
    }

    public boolean isdKeyPressed() {
        return dKeyPressed;
    }

    private void setdKeyPressed(boolean dKeyPressed) {
        this.dKeyPressed = dKeyPressed;
    }

    public boolean issKeyPressed() {
        return sKeyPressed;
    }

    private void setsKeyPressed(boolean sKeyPressed) {
        this.sKeyPressed = sKeyPressed;
    }

    public boolean isaKeyPressed() {
        return aKeyPressed;
    }

    private void setaKeyPressed(boolean aKeyPressed) {
        this.aKeyPressed = aKeyPressed;
    }

    public boolean iswKeyPressed() {
        return wKeyPressed;
    }

    private void setwKeyPressed(boolean wKeyPressed) {
        this.wKeyPressed = wKeyPressed;
    }

    public boolean isSpaceKeyPressed() {
        return spaceKeyPressed;
    }

    private void setSpaceKeyPressed(boolean spaceKeyPressed) {
        this.spaceKeyPressed = spaceKeyPressed;
    }

    public Coordinate getMouseLocation() {
        return mouseLocation;
    }
}
