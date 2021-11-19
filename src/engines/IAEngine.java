package engines;

import utility.Direction;
import utility.GameObject;
import utility.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IAEngine {
    public static Direction getDirReducingDist(GameObject from, Vector2 to, List<Direction> directions, int step){ //TODO : faire la cascade
        List<Double> listOfDist = new ArrayList<>();
        int indexOfBest = 0;
        for (int i = 0; i < directions.size(); i++) {
            listOfDist.add(CoreKernel.calculateDist(Vector2.add(from.getPosition(),getVectorFromDir(directions.get(i),step)),to));
            if(listOfDist.get(i) < listOfDist.get(indexOfBest))
                indexOfBest = i;
        }
        return directions.get(indexOfBest);
    }

    public static Direction getDirIncreasingDist(GameObject from, Vector2 to, List<Direction> directions, int step){ //TODO : faire la cascade
        List<Double> listOfDist = new ArrayList<>();
        int indexOfBest = 0;
        for (int i = 0; i < directions.size(); i++) {
            listOfDist.add(CoreKernel.calculateDist(Vector2.add(from.getPosition(),getVectorFromDir(directions.get(i),step)),to));
            if(listOfDist.get(i) > listOfDist.get(indexOfBest))
                indexOfBest = i;
        }
        return directions.get(indexOfBest);
    }

    public static void main(String[] args) {
        GameObject pacman = new GameObject(1,new Vector2(420,690));
        GameObject ghost = new GameObject(1,new Vector2(450,330));


        System.out.println(getDirReducingDist(ghost,pacman.getPosition(),List.of(Direction.RIGHT,Direction.UP),10));
    }

    public static Vector2 getVectorFromDir(Direction direction, int amplitude){//TODO : faire la cascade
        return switch (direction){
            case LEFT -> new Vector2(-amplitude,0);
            case NEUTRAL -> new Vector2(0,0);
            case RIGHT -> new Vector2(amplitude,0);
            case UP -> new Vector2(0,-amplitude);
            case DOWN -> new Vector2(0,amplitude);
        };
    }

    public static Direction getDirFromVector(Vector2 vector){//TODO : faire la cascade
        if(vector.x > 0 && vector.y == 0) return Direction.RIGHT;
        if(vector.x < 0 && vector.y == 0) return Direction.LEFT;
        if(vector.x == 0 && vector.y < 0) return Direction.UP;
        if(vector.x == 0 && vector.y > 0) return Direction.DOWN;
        return Direction.NEUTRAL;
    }
}
