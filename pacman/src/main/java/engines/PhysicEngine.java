package engines;

import game.character.Character;
import utility.Direction;
import utility.GameObject;
import utility.Vector2;

public class PhysicEngine {

    protected static double calculateDist(Vector2 from, Vector2 to) {
        return Math.sqrt((to.y - from.y) * (to.y - from.y) + (to.x - from.x) * (to.x - from.x));
    }

    protected static boolean willCollide(int cord1, int cord2, int size1, int size2, int step) {
        if (cord2 > cord1){
            return (cord2) - (cord1 + size1 + step) < 0;
        }
        if(cord2 < cord1){
            return (cord1 - size1- step) -(cord2+size2)  < 0;
        }else{
            return false;
        }
    }

    protected static boolean isInCollision(int x1, int y1,int x2,int y2, int width1, int height1,int width2, int height2) {
        return x1 < x2 + width2 &&
                x1 + width1 > x2 &&
                y1 < y2 + height2 &&
                y1 + height1 > y2;

    }

    protected static void move(GameObject gameObject, int x , int y){
        gameObject.setPosition(new Vector2(x,y));
    }

    protected static void moveGameObjectByOneStep(GameObject gameObject, Direction direction, int step){
        switch (direction) {
            case UP:
                PhysicEngine.move(gameObject, gameObject.getPosition().x,gameObject.getPosition().y - step);
                break;
            case DOWN:
                PhysicEngine.move(gameObject, gameObject.getPosition().x,gameObject.getPosition().y + step );
                break;
            case LEFT:
                PhysicEngine.move(gameObject, gameObject.getPosition().x - step,gameObject.getPosition().y  );
                break;
            case RIGHT:
                PhysicEngine.move(gameObject, gameObject.getPosition().x + step,gameObject.getPosition().y  );
                break;
            case NEUTRAL:
                //do nothing
                break;
        }
    }

    protected static Vector2 getVectorFromDir(Direction direction, int amplitude){//TODO : faire la cascade + bouger vers physics engine
        return switch (direction){
            case LEFT -> new Vector2(-amplitude,0);
            case NEUTRAL -> new Vector2(0,0);
            case RIGHT -> new Vector2(amplitude,0);
            case UP -> new Vector2(0,-amplitude);
            case DOWN -> new Vector2(0,amplitude);
        };
    }

    protected static Direction getDirFromVector(Vector2 vector){//TODO : faire la cascade + bouger vers physics engine
        if(vector.x > 0 && vector.y == 0) return Direction.RIGHT;
        if(vector.x < 0 && vector.y == 0) return Direction.LEFT;
        if(vector.x == 0 && vector.y < 0) return Direction.UP;
        if(vector.x == 0 && vector.y > 0) return Direction.DOWN;
        return Direction.NEUTRAL;
    }
}
