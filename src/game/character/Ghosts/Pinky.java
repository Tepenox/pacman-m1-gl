package game.character.Ghosts;

import game.character.PacMan;
import game.levels.Level;
import utility.Direction;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Pinky extends Ghost {
    public Pinky(Image sprite, Vector2 position) {
        super(9,sprite, position, new HashMap<>());
        this.sprites.put(Direction.UP,new ImageIcon("src/game/resources/Pinky/upPinky.png").getImage());
        this.sprites.put(Direction.DOWN,new ImageIcon("src/game/resources/Pinky/downPinky.png").getImage());
        this.sprites.put(Direction.RIGHT,new ImageIcon("src/game/resources/Pinky/rightPinky.png").getImage());
        this.sprites.put(Direction.LEFT,new ImageIcon("src/game/resources/Pinky/leftPinky.png").getImage());
        this.sprites.put(Direction.NEUTRAL,new ImageIcon("src/game/resources/Pinky/leftPinky.png").getImage());
    }

    @Override
    public Vector2 calculateTarget(PacMan pacman, Level level,Vector2 blinkyPos){
        return Vector2.add(pacman.getPosition(),pacman.getDirectionAsVector().multiply(4));
    }
}
