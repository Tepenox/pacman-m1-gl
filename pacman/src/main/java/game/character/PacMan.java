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
    public PacMan(Vector2 position,int lives) {
        super(5,position, new HashMap<>());
        this.sprites.put(Direction.UP,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("PacMan/upPacMan.png").getFile()).getImage());
        this.sprites.put(Direction.DOWN,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("PacMan/downPacMan.png").getFile()).getImage());
        this.sprites.put(Direction.RIGHT,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("PacMan/rightPacMan.png").getFile()).getImage());
        this.sprites.put(Direction.LEFT,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("PacMan/leftPacMan.png").getFile()).getImage());
        this.sprites.put(Direction.NEUTRAL,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("PacMan/neutralPacMan.png").getFile()).getImage());
        this.lives = lives;
    }

    @Override
    public void eat(GameObject character) {

    }

    public boolean useLife(){
        lives --;
        return lives > 0;
    }

    public int getLives() {
        return lives;
    }
}
