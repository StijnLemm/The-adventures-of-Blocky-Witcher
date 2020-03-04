package Util;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class KeyController {

    private Stage window;

    private boolean dKeyPressed;
    private boolean sKeyPressed;
    private boolean aKeyPressed;
    private boolean wKeyPressed;
    private boolean spaceKeyPressed;

    public KeyController(Stage window) {

        this.dKeyPressed = false;
        this.sKeyPressed = false;
        this.aKeyPressed = false;
        this.wKeyPressed = false;
        this.spaceKeyPressed = false;

        this.window = window;

        this.init();
    }

    public void init(){
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
    }

    public boolean isdKeyPressed() {
        return dKeyPressed;
    }

    public void setdKeyPressed(boolean dKeyPressed) {
        this.dKeyPressed = dKeyPressed;
    }

    public boolean issKeyPressed() {
        return sKeyPressed;
    }

    public void setsKeyPressed(boolean sKeyPressed) {
        this.sKeyPressed = sKeyPressed;
    }

    public boolean isaKeyPressed() {
        return aKeyPressed;
    }

    public void setaKeyPressed(boolean aKeyPressed) {
        this.aKeyPressed = aKeyPressed;
    }

    public boolean iswKeyPressed() {
        return wKeyPressed;
    }

    public void setwKeyPressed(boolean wKeyPressed) {
        this.wKeyPressed = wKeyPressed;
    }

    public boolean isSpaceKeyPressed() {
        return spaceKeyPressed;
    }

    public void setSpaceKeyPressed(boolean spaceKeyPressed) {
        this.spaceKeyPressed = spaceKeyPressed;
    }
}
