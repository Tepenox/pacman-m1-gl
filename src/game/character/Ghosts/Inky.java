package game.character.Ghosts;

import game.character.Character;
import game.character.PacMan;
import game.levels.Level;
import game.utility.Vector2;

import javax.swing.*;
import java.awt.*;

public class Inky extends Ghost {
    public Inky(Image sprite, Vector2 position) {
        super(sprite, position);
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
