package game.character.Ghosts;

import game.GameEngine;
import game.character.PacMan;
import game.levels.Level;
import game.utility.GhostName;
import game.utility.Vector2;

import java.awt.*;

public class Blinky extends Ghost{
    public Blinky(Image sprite, Vector2 position) {
        super(sprite, position);
    }

    @Override
    public Vector2 calculateTarget(PacMan pacman, Level level){
        return pacman.getPosition();
    }

}
