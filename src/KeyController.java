public class KeyController {

    private boolean dKeyPressed;
    private boolean sKeyPressed;
    private boolean aKeyPressed;
    private boolean wKeyPressed;
    private boolean spaceKeyPressed;

    public KeyController() {
        this.dKeyPressed = false;
        this.sKeyPressed = false;
        this.aKeyPressed = false;
        this.wKeyPressed = false;
        this.spaceKeyPressed = false;
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
