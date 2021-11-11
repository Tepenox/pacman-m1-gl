package game.character.Ghosts;

import game.character.PacMan;
import game.levels.Level;
import game.utility.Vector2;

import java.awt.*;

public class Pinky extends Ghost {
    public Pinky(Image sprite, Vector2 position) {
        super(sprite, position);
    }

    @Override
    public Vector2 calculateTarget(PacMan pacman, Level level,Vector2 blinkyPos){
        return Vector2.add(pacman.getPosition(),pacman.getDirectionAsVector().multiply(4));
    }
}
