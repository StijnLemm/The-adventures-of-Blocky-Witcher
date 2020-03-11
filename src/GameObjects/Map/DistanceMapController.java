package GameObjects.Map;

import Util.Coordinate;

import java.util.ArrayList;

public class DistanceMapController {

    private int mapWidth, mapHeight;

    private ArrayList<ArrayList<Boolean>> collisionMap;

    public DistanceMapController(int mapWidth, int mapHeight, ArrayList<Integer> collisionLayer) {

        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;

        this.collisionMap = generateCollisionMap(collisionLayer);

    }

    public DistanceMap getDistanceMap(Coordinate center) {

        DistanceMap distanceMap = new DistanceMap(center, this.mapWidth, this.mapHeight);

        ArrayList<Coordinate> toCheck = new ArrayList<>();

        ArrayList<Coordinate> surroundingNodes = new ArrayList<>();

        int counter = 0;

        toCheck.add(center);

        do {

            for (Coordinate point : toCheck) {

                distanceMap.addPoint(point.getValueX(), point.getValueY(), counter);

                surroundingNodes.addAll(this.getSurroundingNodes(surroundingNodes, distanceMap, point));
            }

            toCheck = new ArrayList<>(surroundingNodes);

            surroundingNodes.clear();

            counter++;

        } while (!toCheck.isEmpty());

        return distanceMap;
    }

    public ArrayList<Coordinate> getSurroundingNodes(ArrayList<Coordinate> alreadySurroundingNodes, DistanceMap distanceMap, Coordinate point) {

        int x = point.getValueX();
        int y = point.getValueY();

        boolean left = true;
        boolean right = true;
        boolean under = true;
        boolean up = true;

        ArrayList<Coordinate> surroundingAccessibleNodes = new ArrayList<>();

        for (Coordinate node : alreadySurroundingNodes) {
            if (node.getValueY() == y - 1 && node.getValueX() == x) {
                up = false;
            }

            if (node.getValueY() == y + 1 && node.getValueX() == x ) {
                under = false;
            }

            if (node.getValueY() == y && node.getValueX() == x + 1 ) {
                right = false;
            }

            if (node.getValueY() == y && node.getValueX() == x - 1 ) {
                left = false;
            }
        }

        if (y != 0 && up) {
            if (this.collisionMap.get(y - 1).get(x) && distanceMap.getMap().get(y - 1).get(x) == -1) {
                surroundingAccessibleNodes.add(new Coordinate(x, y - 1));
            }
        }
        if (y < this.mapHeight - 1 && under) {
            if (this.collisionMap.get(y + 1).get(x) && distanceMap.getMap().get(y + 1).get(x) == -1) {
                surroundingAccessibleNodes.add(new Coordinate(x, y + 1) );
            }
        }
        if (x != 0 && left) {
            if (this.collisionMap.get(y).get(x - 1) && distanceMap.getMap().get(y).get(x - 1) == -1) {
                surroundingAccessibleNodes.add(new Coordinate(x - 1, y) );
            }
        }
        if (x < this.mapWidth -1 && right) {
            if (this.collisionMap.get(y).get(x + 1) && distanceMap.getMap().get(y).get(x + 1) == -1) {
                surroundingAccessibleNodes.add(new Coordinate(x + 1, y));
            }
        }

        return surroundingAccessibleNodes;
    }

    /**
     * this algorithm will make an 2d ArrayList with booleans, each boolean stands for a tile on the current map. The
     * boolean indicates if a player or enemy can walk there. If the boolean is true, its walkable and if not the tile is
     * collide-able.
     *
     * @param collisionLayer this is the layer that the maploader extracts from the json file, this layer contains enough
     *                       information to create a map where to walk and not. This array has integers, when the integers
     *                       are != 0 then its an collisionTile, so the boolean on that location will be false.
     * @return the method will return an 2d array with booleans, these booleans indicate if a player or enemy can walk there.
     */
    private ArrayList<ArrayList<Boolean>> generateCollisionMap(ArrayList<Integer> collisionLayer) {

        int counter = 0;

        ArrayList<ArrayList<Boolean>> accessibleTiles = new ArrayList<>();

        for (int y = 0; y < collisionLayer.size() / this.mapWidth; y++) {

            accessibleTiles.add(new ArrayList<>());

            for (int x = 0; x < this.mapHeight; x++) {

                accessibleTiles.get(y).add(collisionLayer.get(counter) == 0);

                counter++;
            }
        }

        return accessibleTiles;
    }
}
