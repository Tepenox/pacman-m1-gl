package engines;

import game.GameEngine.GameLogic;
import game.GameEngine.MenuLogic;
import game.GameUtility.GameState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.GameObject;
import utility.Vector2;

public class GameLogicTest {


    @Test
    void testInitialisation(){
        GameLogic.createLevel(new MenuLogic(),1,0,3);
        Assertions.assertEquals(GameLogic.gameState, GameState.STARTING);
        GameLogic.runTheLevel();
        Assertions.assertEquals(GameLogic.gameState, GameState.RUNNING);

    }
    @Test
    void testTermination(){
        GameLogic.createLevel(new MenuLogic(),1,0,3);
        Assertions.assertEquals(GameLogic.gameState, GameState.STARTING);
        GameLogic.runTheLevel();
        GameLogic.looseLevel();
        Assertions.assertEquals(GameLogic.gameState, GameState.OVER);

    }


}
