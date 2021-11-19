package game.character.Ghosts;

import game.character.PacMan;
import game.levels.Level;
import utility.Direction;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Blinky extends Ghost{

    public Blinky(Vector2 position) { // todo change
        super(6,GhostState.CHASING,position,new HashMap<>());
        this.sprites.put(Direction.UP,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("Blinky/upBlinky.png").getFile()).getImage());
        this.sprites.put(Direction.DOWN,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("Blinky/upBlinky.png").getFile()).getImage());
        this.sprites.put(Direction.RIGHT,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("Blinky/upBlinky.png").getFile()).getImage());
        this.sprites.put(Direction.LEFT,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("Blinky/upBlinky.png").getFile()).getImage());
        this.sprites.put(Direction.NEUTRAL,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("Blinky/upBlinky.png").getFile()).getImage());
        Image img = sprites.get(Direction.NEUTRAL);
        super.setSprite(img);
    }

    @Override
    public Vector2 calculateTarget(PacMan pacman, Level level,Vector2 blinkyPos){
        return pacman.getPosition();
    }

}
