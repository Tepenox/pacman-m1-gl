package game.levels;

import game.utility.GhostName;
import game.utility.Vector2;

public class Level {
    private Vector2 blinkySide;     //top-right of maze
    private Vector2 pinkySide;      //top-left of maze
    private Vector2 inkySide;       //bottom-right of maze
    private Vector2 clydeSide;      //bottom-left of maze



    public Vector2 getSide(GhostName ghostName){
        return switch (ghostName) {
            case BLINKY -> blinkySide;
            case PINKY -> pinkySide;
            case INKY -> inkySide;
            case CLYDE -> clydeSide;
        };
    }

}
