package game.character.Ghosts;

import game.character.PacMan;
import game.levels.Level;
import game.utility.Direction;
import game.utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Blinky extends Ghost{

    public Blinky(Image sprite, Vector2 position) { // todo change
        super(6,sprite, position,new HashMap<>());
        this.sprites.put(Direction.UP,new ImageIcon("src/game/resources/Blinky/upBlinky.png").getImage());
        this.sprites.put(Direction.DOWN,new ImageIcon("src/game/resources/Blinky/downBlinky.png").getImage());
        this.sprites.put(Direction.RIGHT,new ImageIcon("src/game/resources/Blinky/rightBlinky.png").getImage());
        this.sprites.put(Direction.LEFT,new ImageIcon("src/game/resources/Blinky/leftBlinky.png").getImage());
        this.sprites.put(Direction.NEUTRAL,new ImageIcon("src/game/resources/Blinky/leftBlinky.png").getImage());

    }

    @Override
    public Vector2 calculateTarget(PacMan pacman, Level level,Vector2 blinkyPos){
        return pacman.getPosition();
    }

}
