package game.character.Ghosts;

import game.GameUtility.CharacterName;
import game.character.PacMan;
import game.levels.Level;
import utility.Direction;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Blinky extends Ghost{

    public Blinky(Vector2 position) { // todo change
        super(6,GhostState.DISPERSION,position,new HashMap<>(), CharacterName.BLINKY);
        this.sprites.put(Direction.UP,new ImageIcon(getClass().getResource("/Blinky/upBlinky.gif")).getImage());
        this.sprites.put(Direction.DOWN,new ImageIcon(getClass().getResource("/Blinky/downBlinky.gif")).getImage());
        this.sprites.put(Direction.RIGHT,new ImageIcon(getClass().getResource("/Blinky/rightBlinky.gif")).getImage());
        this.sprites.put(Direction.LEFT,new ImageIcon(getClass().getResource("/Blinky/leftBlinky.gif")).getImage());
        this.sprites.put(Direction.NEUTRAL,new ImageIcon(getClass().getResource("/Blinky/leftBlinky.gif")).getImage());
        setDirection(Direction.LEFT);
        Image img = sprites.get(Direction.NEUTRAL);
        super.setSprite(img);
    }

    @Override
    public Vector2 calculateTarget(PacMan pacman, Level level,Vector2 blinkyPos){
        return pacman.getPosition();
    }

}
