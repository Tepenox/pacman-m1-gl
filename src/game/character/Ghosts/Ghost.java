package game.character.Ghosts;

import utility.GameObject;
import game.character.Character;
import game.character.PacMan;
import game.levels.Level;
import utility.Direction;
import utility.Vector2;

import java.awt.*;
import java.util.Map;

public abstract class Ghost extends Character {
    private Vector2 target;

    public Ghost(int id, Image sprite, Vector2 position, Map<Direction, Image> sprites) {
        super(id,sprite, position,sprites);
    }

    @Override
    public void eat(GameObject character) {

    }

    public void setTarget(Vector2 newVal){
        this.target = newVal;
    }

    public abstract Vector2 calculateTarget(PacMan pacman, Level level,Vector2 blinkyPos);

}
