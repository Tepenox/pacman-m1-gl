package engines;

import utility.Direction;
import utility.GameObject;
import utility.Vector2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IAEngine {
    protected static Direction getDirReducingDist(GameObject from, Vector2 to, List<Direction> directions, int step){
        List<Double> listOfDist = new ArrayList<>();
        int indexOfBest = 0;
        for (int i = 0; i < directions.size(); i++) {
            listOfDist.add(CoreKernel.calculateDist(Vector2.add(from.getPosition(),CoreKernel.getVectorFromDir(directions.get(i),step)),to));
            if(listOfDist.get(i) < listOfDist.get(indexOfBest))
                indexOfBest = i;
        }
        return directions.get(indexOfBest);
    }

    protected static Direction getDirIncreasingDist(GameObject from, Vector2 to, List<Direction> directions, int step){
        List<Double> listOfDist = new ArrayList<>();
        int indexOfBest = 0;
        for (int i = 0; i < directions.size(); i++) {
            listOfDist.add(CoreKernel.calculateDist(Vector2.add(from.getPosition(),CoreKernel.getVectorFromDir(directions.get(i),step)),to));
            if(listOfDist.get(i) > listOfDist.get(indexOfBest))
                indexOfBest = i;
        }
        return directions.get(indexOfBest);
    }
}
