package game.character.Ghosts;

import game.GameEngine.Engines;
import game.GameUtility.CharacterName;
import game.character.PacMan;
import game.levels.Level;
import utility.Direction;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Pinky extends Ghost {
    public Pinky(Vector2 position) {
        super(9,GhostState.DISPERSION,position, new HashMap<>(), CharacterName.PINKY);
        this.sprites.put(Direction.UP,new ImageIcon(getClass().getResource("/Pinky/upPinky.gif")).getImage());
        this.sprites.put(Direction.DOWN,new ImageIcon(getClass().getResource("/Pinky/downPinky.gif")).getImage());
        this.sprites.put(Direction.RIGHT,new ImageIcon(getClass().getResource("/Pinky/rightPinky.gif")).getImage());
        this.sprites.put(Direction.LEFT,new ImageIcon(getClass().getResource("/Pinky/leftPinky.gif")).getImage());
        this.sprites.put(Direction.NEUTRAL,new ImageIcon(getClass().getResource("/Pinky/leftPinky.gif")).getImage());
        setDirection(Direction.LEFT);
        Image img = sprites.get(Direction.NEUTRAL);
        super.setSprite(img);
    }

    @Override
    public Vector2 calculateTarget(PacMan pacman, Level level,Vector2 blinkyPos){
        return Vector2.add(pacman.getPosition(), Engines.getVectorFromDir(pacman.getDirection(),level.getGameUnit()*3));
    }
}
