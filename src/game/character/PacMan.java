package game.character;

import game.GameObject;
import game.utility.Direction;
import game.utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PacMan extends Character{
    public final static int ID = 5;
    public Direction nextDir = Direction.NEUTRAL;

    public PacMan(Image appearence, Vector2 position) {
        super(5,appearence,position, new HashMap<>());
        this.sprites.put(Direction.UP,new ImageIcon("src/game/resources/PacMan/upPacMan.png").getImage());
        this.sprites.put(Direction.DOWN,new ImageIcon("src/game/resources/PacMan/downPacMan.png").getImage());
        this.sprites.put(Direction.RIGHT,new ImageIcon("src/game/resources/PacMan/rightPacMan.png").getImage());
        this.sprites.put(Direction.LEFT,new ImageIcon("src/game/resources/PacMan/leftPacMan.png").getImage());
        this.sprites.put(Direction.NEUTRAL,new ImageIcon("src/game/resources/PacMan/neutralPacMan.png").getImage());
    }

    @Override
    public void eat(GameObject character) {

    }


}
