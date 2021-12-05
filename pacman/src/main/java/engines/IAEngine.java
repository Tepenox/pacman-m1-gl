package engines;

import utility.Direction;
import utility.GameObject;
import utility.Vector2;

import java.util.ArrayList;
import java.util.List;

class IAEngine {
    /**
     * retourne la direction qui va mettre un gameObject plus proche a une postion donné
     * @param gameObject gameobject
     * @param to postion
     * @param directions les directions possible
     * @param step talle de pas pas
     * @return direction a suivre pour s approcher a postion
     */
    protected static Direction getDirReducingDist(GameObject gameObject, Vector2 to, List<Direction> directions, int step){
        List<Double> listOfDist = new ArrayList<>();
        int indexOfBest = 0;
        for (int i = 0; i < directions.size(); i++) {
            listOfDist.add(CoreKernel.calculateDist(Vector2.add(gameObject.getPosition(),CoreKernel.getVectorFromDir(directions.get(i),step)),to));
            if(listOfDist.get(i) < listOfDist.get(indexOfBest))
                indexOfBest = i;
        }
        return directions.get(indexOfBest);
    }
    /**
     * retourne la direction qui va mettre un gameObject plus loin a une postion donné
     * @param gameObject gameobject
     * @param to postion
     * @param directions les directions possible
     * @param step talle de pas pas
     * @return direction a suivre pour s eloigner a postion
     */
    protected static Direction getDirIncreasingDist(GameObject gameObject, Vector2 to, List<Direction> directions, int step){
        List<Double> listOfDist = new ArrayList<>();
        int indexOfBest = 0;
        for (int i = 0; i < directions.size(); i++) {
            listOfDist.add(CoreKernel.calculateDist(Vector2.add(gameObject.getPosition(),CoreKernel.getVectorFromDir(directions.get(i),step)),to));
            if(listOfDist.get(i) > listOfDist.get(indexOfBest))
                indexOfBest = i;
        }
        return directions.get(indexOfBest);
    }
}
