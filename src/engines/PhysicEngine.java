package engines;

import game.character.Character;
import utility.Direction;
import utility.GameObject;
import utility.Vector2;

public class PhysicEngine {

    protected static double calculateDist(Vector2 from, Vector2 to) {
        return Math.sqrt((to.y - from.y) * (to.y - from.y) + (to.x - from.x) * (to.x - from.x));
    }

    protected static boolean checkCollision(int x1, int y1, int x2, int y2, int hitBoxSize1, int hitBoxSize2) {
        double distX = Math.abs(x1 - x2);
        double distY = Math.abs(y1 - y2);
        double requiredMinDist = hitBoxSize1 + hitBoxSize2;
        return distX < requiredMinDist || distY < requiredMinDist;
    }

    public static boolean willColideHorizentaly(int x1, int x2, int size1, int size2, int step) {
        if (x2 > x1){
            return (x2) - (x1 + size1 + step) < 0;
        }
        if(x2 < x1){
            return (x1 - size1- step) -(x2+size2)  < 0;
        }else{
            return false;
        }
    }

    public static boolean willColideVerticaly(int y1, int y2, int size1, int size2, int step) {
        if (y2 > y1){
            return (y2) - (y1 + size1 + step) < 0;
        }
        if(y2 < y1){
            return (y1 - size1- step) -(y2+size2)  < 0;
        }else{
            return false;
        }
    }

    public static void move(GameObject gameObject, int x , int y){
        gameObject.setPosition(new Vector2(x,y));
    }
    //todo fair la cascade
    public static void moveGameObjectByOneStep(Character character, Direction direction, int step){ switch (direction) {
            case UP:
                PhysicEngine.move(character, character.getPosition().x,character.getPosition().y - step);
                break;
            case DOWN:
                PhysicEngine.move(character, character.getPosition().x,character.getPosition().y + step + step);
                break;
            case LEFT:
                PhysicEngine.move(character, character.getPosition().x - step,character.getPosition().y  );
                break;
            case RIGHT:
                PhysicEngine.move(character, character.getPosition().x + step,character.getPosition().y  );
                break;
            case NEUTRAL:
                //do nothing
                break;
        }
    }
}
