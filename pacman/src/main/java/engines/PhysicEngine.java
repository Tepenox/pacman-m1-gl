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

    public static boolean willCollide(int cord1, int cord2, int size1, int size2, int step) {
        if (cord2 > cord1){
            return (cord2) - (cord1 + size1 + step) < 0;
        }
        if(cord2 < cord1){
            return (cord1 - size1- step) -(cord2+size2)  < 0;
        }else{
            return false;
        }
    }

    public static boolean isInCollision(int x1, int y1,int x2,int y2, int width1, int height1,int width2, int height2) {
        return x1 < x2 + width2 &&
                x1 + width1 > x2 &&
                y1 < y2 + height2 &&
                y1 + height1 > y2;

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
                PhysicEngine.move(character, character.getPosition().x,character.getPosition().y + step );
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
