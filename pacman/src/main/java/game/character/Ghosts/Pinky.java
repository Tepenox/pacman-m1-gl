package game.character.Ghosts;

import engines.IAEngine;
import game.Engines;
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
        this.sprites.put(Direction.UP,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("Pinky/upPinky.png").getFile()).getImage());
        this.sprites.put(Direction.DOWN,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("Pinky/downPinky.png").getFile()).getImage());
        this.sprites.put(Direction.RIGHT,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("Pinky/rightPinky.png").getFile()).getImage());
        this.sprites.put(Direction.LEFT,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("Pinky/leftPinky.png").getFile()).getImage());
        this.sprites.put(Direction.NEUTRAL,new ImageIcon(ClassLoader.getSystemClassLoader().getResource("Pinky/leftPinky.png").getFile()).getImage());
    }

    @Override
    public Vector2 calculateTarget(PacMan pacman, Level level,Vector2 blinkyPos){
        return Vector2.add(pacman.getPosition(), Engines.getVectorFromDir(pacman.getDirection(),level.getGameUnit()*3));
    }
}
