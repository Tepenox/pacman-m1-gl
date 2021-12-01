package engines;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.Direction;
import utility.GameObject;
import utility.Vector2;

import java.util.List;

public class AIEngineTest {

    GameObject gameObject;

    @BeforeEach
    void setUp() {
        gameObject = new GameObject(1, new Vector2(0, 0));
    }


    @Test
    void testGetDirReducingDist(){
        List<Direction> possibleCurrentDirections = List.of(Direction.DOWN,Direction.UP,Direction.LEFT);
        Assertions.assertEquals(IAEngine.getDirReducingDist(gameObject,
                new Vector2(0,1), possibleCurrentDirections,1),Direction.DOWN);
        Assertions.assertEquals(IAEngine.getDirReducingDist(gameObject,
                new Vector2(-1,0), possibleCurrentDirections,1),Direction.LEFT);
        //there is no right on available direction
        Assertions.assertNotEquals(IAEngine.getDirReducingDist(gameObject,
                new Vector2(1,0), possibleCurrentDirections,1),Direction.RIGHT);
        possibleCurrentDirections = List.of(Direction.DOWN,Direction.UP,Direction.LEFT,Direction.RIGHT);
        Assertions.assertEquals(IAEngine.getDirReducingDist(gameObject,
                new Vector2(1,0), possibleCurrentDirections,1),Direction.RIGHT);

    }

    @Test
    void testGetDirIncreasingDist(){
        List<Direction> possibleCurrentDirections = List.of(Direction.DOWN,Direction.UP,Direction.LEFT,Direction.RIGHT);
        Assertions.assertEquals(IAEngine.getDirIncreasingDist(gameObject,
                new Vector2(0,1), possibleCurrentDirections,1),Direction.UP);
        Assertions.assertEquals(IAEngine.getDirIncreasingDist(gameObject,
                new Vector2(-1,0), possibleCurrentDirections,1),Direction.RIGHT);
        //there is no right on available direction
        Assertions.assertEquals(IAEngine.getDirIncreasingDist(gameObject,
                new Vector2(1,0), possibleCurrentDirections,1),Direction.LEFT);
        possibleCurrentDirections = List.of(Direction.RIGHT);
        Assertions.assertEquals(IAEngine.getDirIncreasingDist(gameObject,
                new Vector2(1,0), possibleCurrentDirections,1),Direction.RIGHT);

    }


}
