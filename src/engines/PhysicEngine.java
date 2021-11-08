package engines;

import game.utility.Vector2;

public class PhysicEngine {

    protected static double calculateDist(Vector2 from, Vector2 to){
        return Math.sqrt((to.y - from.y) * (to.y - from.y) + (to.x - from.x) * (to.x - from.x));
    }


}
