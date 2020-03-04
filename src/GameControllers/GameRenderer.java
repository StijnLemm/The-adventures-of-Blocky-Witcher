package GameControllers;

import Util.Timer;
import javafx.animation.AnimationTimer;

public class GameRenderer {

    private AnimationTimer animationTimer;
    private GameEngine gameEngine;

    // This timer will show when to update all of the entities to limit to the amount of fps given.
    private Timer fpsTimer;

    private boolean isRunning;

    private int fps;

    public GameRenderer(GameEngine gameEngine, int fps) {

        this.gameEngine = gameEngine;
        this.fps = fps;

        // To make sure the simulation is 60 fps, we wait for an update 1000 millis divided by the given fps.
        this.fpsTimer = new Timer((long) 1000.0 / fps);

        this.isRunning = false;

        this.init();
    }

    private void init(){
        this.animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if(fpsTimer.timeout()){

                    update();

                    fpsTimer.reset();
                }
            }
        };
    }

    private void update(){
        this.gameEngine.update();
    }

    /**
     * The start method will stop the animation timer, also the state of the simulation is changed to false here.
     */
    public void start(){

        this.isRunning = true;
        this.animationTimer.start();
    }

    /**
     * The stop method will stop the animation timer, also the state of the simulation is changed to false here.
     */
    public void stop(){

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

    public int getFps() {
        return fps;
    }

    public void setFps(int fps) {
        this.fps = fps;

        this.fpsTimer = new Timer((long) 1000.0 / fps);
    }
}
