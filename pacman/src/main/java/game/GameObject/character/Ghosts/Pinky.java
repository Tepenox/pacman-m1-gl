package game.GameObject.character.Ghosts;

import game.GameEngine.EnginesCaller;
import game.GameUtility.CharacterName;
import game.GameObject.character.PacMan;
import game.gamespace.Level;
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
        setDirection(Direction.DOWN);
        setState(GhostState.REGENERATING);
        Image img = sprites.get(Direction.DOWN);
        super.setSprite(img);
    }

    @Override
    public Vector2 calculateTarget(PacMan pacman, Level level, Vector2 blinkyPos){
        return Vector2.add(pacman.getPosition(), EnginesCaller.getVectorFromDir(pacman.getDirection(), level.getGameUnit()*3));
    }
}
