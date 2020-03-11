package GameObjects.Map;

import Util.Coordinate;

import java.util.ArrayList;


public class DistanceMap {

    private ArrayList<ArrayList<Integer>> map;
    private Coordinate centerOfDistanceMap;

    public DistanceMap(Coordinate centerOfDistanceMap, int mapWidth, int mapHeight) {

        this.map = new ArrayList<>();

        //fill the array to the size it will get.
        this.fillInitArray(mapHeight, mapWidth);

        this.centerOfDistanceMap = centerOfDistanceMap;

    }

    public void addPoint(int x, int y, int steps){
        this.map.get(y).set(x, steps);
    }

    public ArrayList<ArrayList<Integer>> getMap() {
        return map;
    }

    private void fillInitArray(int mapHeight, int mapWidth){
        for (int y = 0; y < mapHeight; y++) {

            this.map.add(new ArrayList<>());

            for (int x = 0; x < mapWidth; x++) {
                this.map.get(y).add(-1);
            }
        }
    }
}
