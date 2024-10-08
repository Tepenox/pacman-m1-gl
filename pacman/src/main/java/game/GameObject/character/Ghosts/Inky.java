package game.GameObject.character.Ghosts;

import game.GameUtility.CharacterName;
import game.GameObject.character.PacMan;
import game.gamespace.Level;
import utility.Direction;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Inky extends Ghost {
    public Inky(Vector2 position) {
        super(8,GhostState.DISPERSION,position, new HashMap<>(), CharacterName.INKY);
        this.sprites.put(Direction.UP,new ImageIcon(getClass().getResource("/Inky/upInky.gif")).getImage());
        this.sprites.put(Direction.DOWN,new ImageIcon(getClass().getResource("/Inky/downInky.gif")).getImage());
        this.sprites.put(Direction.RIGHT,new ImageIcon(getClass().getResource("/Inky/rightInky.gif")).getImage());
        this.sprites.put(Direction.LEFT,new ImageIcon(getClass().getResource("/Inky/leftInky.gif")).getImage());
        this.sprites.put(Direction.NEUTRAL,new ImageIcon(getClass().getResource("/Inky/leftInky.gif")).getImage());
        setDirection(Direction.UP);
        setState(GhostState.REGENERATING);
        Image img = sprites.get(Direction.UP);
        super.setSprite(img);
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
