package GameObjects;

import Util.Coordinate;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class HealthBar {

    private ArrayList<Image> heart;
    private Coordinate location;

    private ImageView healthBarView;

    private int maxAmountOfLives;
    private int amountOfLives;

    public HealthBar(ArrayList<Image> hearts, ImageView healthBarView, Coordinate location) {
        this.heart = hearts;
        this.location = location;
        this.maxAmountOfLives = (hearts.size() - 1);
        this.amountOfLives = maxAmountOfLives;


        this.healthBarView = healthBarView;

        this.healthBarView.setY(location.getValueY());
        this.healthBarView.setX(location.getValueX());

        this.setImage();
    }

    public void addHealthPoints(int amount) {
        if (this.amountOfLives + amount <= this.maxAmountOfLives) {
            this.amountOfLives += amount;
        } else {
            this.amountOfLives = 4;
        }
        this.setImage();
    }

    public void decreaseHealthPoints(int amount) {
        if (this.amountOfLives - amount >= 0) {
            this.amountOfLives -= amount;
        } else {
            this.amountOfLives = 0;
        }
        this.setImage();
    }

    public boolean isDead() {
        return this.amountOfLives <= -0;
    }

    private void setImage() {
        this.healthBarView.setImage(this.heart.get(this.maxAmountOfLives - this.amountOfLives));
    }

    public ImageView getHealthBarView() {
        return healthBarView;
    }
}
