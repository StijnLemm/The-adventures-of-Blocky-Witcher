import javafx.scene.image.ImageView;

public class CrossHair implements Updatable {

    private Coordinate location;
    private ImageView crossHairImage;
    private Coordinate tileLocation;

    public CrossHair(ImageView crossHairImage) {

        this.location = new Coordinate(10,10);
        this.crossHairImage = crossHairImage;

    }

    public void update(){

        this.crossHairImage.setX(this.location.getValueX());
        this.crossHairImage.setY(this.location.getValueY());

    }

    public ImageView getCrossHairImage() {
        return crossHairImage;
    }

    public void setX(double x){
        this.location.setValueX(x);
    }

    public void setY(double y){
        this.location.setValueY(y);
    }
}
