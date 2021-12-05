package engines;

import utility.Direction;
import utility.GameObject;
import utility.Vector2;

class PhysicEngine {


    /**
     * retoutne la distance entere deux postions
     * @param from postion de debut
     * @param to position de fin
     * @return la distance entre les deux position
     */
    protected static double calculateDist(Vector2 from, Vector2 to) {
        return Math.sqrt((to.y - from.y) * (to.y - from.y) + (to.x - from.x) * (to.x - from.x));
    }

    /**
     * verifie si deux objects sont en collision
     * @param x1 position x d'objet 1
     * @param y1 position y d'objet 1
     * @param x2 position x d'objet 2
     * @param y2 positon y d'objet 2
     * @param width1 largeur d'objet 1
     * @param height1 hauteur d'objet 1
     * @param width2 largeur d'objet 2
     * @param height2 hauteur d'objet 2
     * @return objet sont en collistion
     */
    protected static boolean isInCollision(int x1, int y1,int x2,int y2, int width1, int height1,int width2, int height2) {
        return x1 < x2 + width2 &&
                x1 + width1 > x2 &&
                y1 < y2 + height2 &&
                y1 + height1 > y2;

    }

    /**
     * bouger une gameObject a une positon
     * @param gameObject gameObject à bouger
     * @param x positon x
     * @param y postion y
     */

    protected static void move(GameObject gameObject, int x , int y){
        gameObject.setPosition(new Vector2(x,y));
    }

    /**
     * bouger un gameobjet avec un pas
     * @param gameObject gameObjet à bouger
     * @param direction direction de mouvement
     * @param step taille de pas
     */

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

    /**
     * convertir une direction a un vecteur
     * @param direction direction
     * @param amplitude amplitude de vecteur qui va etre retouné
     * @return vecteur convertit à partir d une direciton
     */

    protected static Vector2 getVectorFromDir(Direction direction, int amplitude){
        return switch (direction){
            case LEFT -> new Vector2(-amplitude,0);
            case NEUTRAL -> new Vector2(0,0);
            case RIGHT -> new Vector2(amplitude,0);
            case UP -> new Vector2(0,-amplitude);
            case DOWN -> new Vector2(0,amplitude);
        };
    }

    /**
     * convertir un vecteur à une direction
     * @param vector vecteur
     * @return direciton convertit à partir d'un vecteur
     */

    protected static Direction getDirFromVector(Vector2 vector){
        if(vector.x > 0 && vector.y == 0) return Direction.RIGHT;
        if(vector.x < 0 && vector.y == 0) return Direction.LEFT;
        if(vector.x == 0 && vector.y < 0) return Direction.UP;
        if(vector.x == 0 && vector.y > 0) return Direction.DOWN;
        return Direction.NEUTRAL;
    }
}
