package game.character.Ghosts;

import game.Engines;
import game.character.PacMan;
import game.levels.Level;
import game.utility.GhostName;
import game.utility.Vector2;

import java.awt.*;

public class Clyde extends Ghost{                       //le fantôme orange.
    public Clyde(Image sprite, Vector2 position) {
        super(sprite, position);
    }

    @Override
    public Vector2 calculateTarget(PacMan pacman, Level level,Vector2 blinkyPos){
        double distanceToChange = 8;
        if(Engines.calculateDist(this.getPosition(),pacman.getPosition()) < distanceToChange){
            return level.getSide(GhostName.CLYDE);
        }else return pacman.getPosition();
    }

}
