package engines;

import utility.Vector2;

public class PhysicEngine {

    protected static double calculateDist(Vector2 from, Vector2 to){
        return Math.sqrt((to.y - from.y) * (to.y - from.y) + (to.x - from.x) * (to.x - from.x));
    }

    protected static boolean checkCollision(int x1, int y1, int x2, int y2, int hitBoxSize1, int hitBoxSize2){
        double distX = Math.abs(x1-x2);
        double distY = Math.abs(y1-y2);
        double requiredMinDist = hitBoxSize1 + hitBoxSize2;
        return distX < requiredMinDist || distY < requiredMinDist;
    }

}
