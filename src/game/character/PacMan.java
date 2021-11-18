package game.character;

import utility.GameObject;
import utility.Direction;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PacMan extends Character{
    public final static int ID = 5;
    public Direction nextDir = Direction.NEUTRAL;
    int lives;
    public PacMan(Image appearence, Vector2 position,int lives) {
        super(5,appearence,position, new HashMap<>());
        this.sprites.put(Direction.UP,new ImageIcon("src/game/resources/PacMan/upPacMan.png").getImage());
        this.sprites.put(Direction.DOWN,new ImageIcon("src/game/resources/PacMan/downPacMan.png").getImage());
        this.sprites.put(Direction.RIGHT,new ImageIcon("src/game/resources/PacMan/rightPacMan.png").getImage());
        this.sprites.put(Direction.LEFT,new ImageIcon("src/game/resources/PacMan/leftPacMan.png").getImage());
        this.sprites.put(Direction.NEUTRAL,new ImageIcon("src/game/resources/PacMan/neutralPacMan.png").getImage());
        this.lives = lives;
    }

    @Override
    public void eat(GameObject character) {

    }

    public void removeOneLife(){
        if (lives > 0)
        lives--;
        else
            throw new IllegalStateException("pac man has no lives left");
    }


}
