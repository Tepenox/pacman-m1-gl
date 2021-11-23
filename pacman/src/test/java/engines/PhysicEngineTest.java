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
    void setUp(){
        gameObject = new GameObject(1,new Vector2(0,0));
    }
    @Test
    void testIsInCollision() {
        Assertions.assertFalse(PhysicEngine.isInCollision(0,0,2,2,2,2,2,2));
        Assertions.assertTrue(PhysicEngine.isInCollision(0,0,2,2,3,3,2,2));
    }

    @Test
    void testMoveGameObjectByOneStep() {
        PhysicEngine.moveGameObjectByOneStep(gameObject, Direction.RIGHT,1);
        Assertions.assertEquals(gameObject.getPosition().x,1);
        Assertions.assertEquals(gameObject.getPosition().y,0);
        PhysicEngine.moveGameObjectByOneStep(gameObject, Direction.LEFT,1);
        Assertions.assertEquals(gameObject.getPosition().x,0);
        Assertions.assertEquals(gameObject.getPosition().y,0);
        PhysicEngine.moveGameObjectByOneStep(gameObject, Direction.DOWN,1);
        Assertions.assertEquals(gameObject.getPosition().x,0);
        Assertions.assertEquals(gameObject.getPosition().y,1);
        PhysicEngine.moveGameObjectByOneStep(gameObject, Direction.UP,1);
        Assertions.assertEquals(gameObject.getPosition().x,0);
        Assertions.assertEquals(gameObject.getPosition().y,0);
    }

}
