package game.character.Ghosts;

import game.character.PacMan;
import game.levels.Level;
import utility.Direction;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Inky extends Ghost {
    public Inky(Vector2 position) {
        super(8,GhostState.CHASING,position, new HashMap<>());
        this.sprites.put(Direction.UP,new ImageIcon("src/game/resources/Inky/upInky.png").getImage());
        this.sprites.put(Direction.DOWN,new ImageIcon("src/game/resources/Inky/downInky.png").getImage());
        this.sprites.put(Direction.RIGHT,new ImageIcon("src/game/resources/Inky/rightInky.png").getImage());
        this.sprites.put(Direction.LEFT,new ImageIcon("src/game/resources/Inky/leftInky.png").getImage());
        this.sprites.put(Direction.NEUTRAL,new ImageIcon("src/game/resources/Inky/leftInky.png").getImage());
        super.setSprite(this.sprites.get(Direction.LEFT));
    }

    @Override
    public Vector2 calculateTarget(PacMan pacman, Level level, Vector2 blinkyPos){
        Vector2 point = Vector2.add(pacman.getPosition(),pacman.getDirectionAsVector().multiply(2));   //2 case ahead of pacman
        Vector2 pointToBlinky = new Vector2(
                blinkyPos.x-point.x,
                blinkyPos.y-point.y);                                                       //get Vector from point to blinky
        return new Vector2(
                pacman.getPosition().x - pointToBlinky.x,
                pacman.getPosition().y - pointToBlinky.y);                                  //return the vector from pacman to pointToBlinky inverted
    }
}
