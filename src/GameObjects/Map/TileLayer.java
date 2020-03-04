package GameObjects.Map;

import java.util.ArrayList;

public class TileLayer {

    private String name;

    private boolean visible;

    private ArrayList<Integer> tiles;

    public TileLayer(String name, boolean visible, ArrayList<Integer> tiles) {
        this.name = name;
        this.visible = visible;
        this.tiles = tiles;
    }

    public String getName() {
        return name;
    }

    public boolean isVisible() {
        return visible;
    }

    public ArrayList<Integer> getTiles() {
        return tiles;
    }
}
