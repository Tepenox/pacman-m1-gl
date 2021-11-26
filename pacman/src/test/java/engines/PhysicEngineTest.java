package engines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.Direction;
import utility.GameObject;
import utility.Vector2;

import org.junit.jupiter.api.Assertions;

public class PhysicEngineTest {
    GameObject gameObject;

    @BeforeEach
    void setUp() {
        gameObject = new GameObject(1, new Vector2(0, 0));
    }

    @Test
    void testIsInCollision() {
        Assertions.assertFalse(PhysicEngine.isInCollision(0, 0, 2, 2, 2, 2, 2, 2));
        Assertions.assertTrue(PhysicEngine.isInCollision(0, 0, 2, 2, 3, 3, 2, 2));
        Assertions.assertFalse(PhysicEngine.isInCollision(0, 0, 2, 2, 2, 2, 2, 2));
        Assertions.assertFalse(PhysicEngine.isInCollision(0, 0, 2, 2, 1, 1, 2, 2));
    }

    @Test
    void testMoveGameObjectByOneStep() {
        PhysicEngine.moveGameObjectByOneStep(gameObject, Direction.RIGHT, 1);
        Assertions.assertEquals(gameObject.getPosition().x, 1);
        Assertions.assertEquals(gameObject.getPosition().y, 0);
        PhysicEngine.moveGameObjectByOneStep(gameObject, Direction.LEFT, 1);
        Assertions.assertEquals(gameObject.getPosition().x, 0);
        Assertions.assertEquals(gameObject.getPosition().y, 0);
        PhysicEngine.moveGameObjectByOneStep(gameObject, Direction.DOWN, 1);
        Assertions.assertEquals(gameObject.getPosition().x, 0);
        Assertions.assertEquals(gameObject.getPosition().y, 1);
        PhysicEngine.moveGameObjectByOneStep(gameObject, Direction.UP, 1);
        Assertions.assertEquals(gameObject.getPosition().x, 0);
        Assertions.assertEquals(gameObject.getPosition().y, 0);
    }

    @Test
    void testGetVectorFromDir() {
        Assertions.assertEquals(PhysicEngine.getVectorFromDir(Direction.LEFT,5), new Vector2(-5,0));
        Assertions.assertEquals(PhysicEngine.getVectorFromDir(Direction.RIGHT,5), new Vector2(+5,0));
        Assertions.assertEquals(PhysicEngine.getVectorFromDir(Direction.DOWN,5), new Vector2(0,+5));
        Assertions.assertEquals(PhysicEngine.getVectorFromDir(Direction.UP,5), new Vector2(0,-5));
        Assertions.assertNotEquals(PhysicEngine.getVectorFromDir(Direction.UP,5), new Vector2(0,5));
    }

    @Test
    void testGetDirection(){
        Assertions.assertEquals(PhysicEngine.getDirFromVector(new Vector2(0,-1)), Direction.UP);
        Assertions.assertEquals(PhysicEngine.getDirFromVector(new Vector2(1,0)), Direction.RIGHT);
        Assertions.assertEquals(PhysicEngine.getDirFromVector(new Vector2(0,1)), Direction.DOWN);
        Assertions.assertEquals(PhysicEngine.getDirFromVector(new Vector2(-1,0)), Direction.LEFT);
        Assertions.assertEquals(PhysicEngine.getDirFromVector(new Vector2(0,0)), Direction.NEUTRAL);
    }
    @Test
    void testCalculateDist(){
        Assertions.assertEquals(PhysicEngine.calculateDist(new Vector2(1,1),new Vector2(1,1)), 0);
        Assertions.assertEquals(PhysicEngine.calculateDist(new Vector2(1,1),new Vector2(1,2)), 1);
        Assertions.assertEquals(PhysicEngine.calculateDist(new Vector2(1,1),new Vector2(0,1)), 1);
        Assertions.assertEquals(PhysicEngine.calculateDist(new Vector2(1,1),new Vector2(3,1)), 2);

    }
    @Test
    void testMove() {

    }
}
