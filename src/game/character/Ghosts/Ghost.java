package game.character.Ghosts;

import game.GameObject;
import game.character.Character;
import game.character.PacMan;
import game.levels.Level;
import game.utility.Vector2;

import java.awt.*;

public abstract class Ghost extends Character {
    private Vector2 target;

    public Ghost(Image sprite, Vector2 position) {
        super(sprite, position);
    }

    @Override
    public void eat(GameObject character) {

    }

    public void setTarget(Vector2 newVal){
        this.target = newVal;
    }

    public abstract Vector2 calculateTarget(PacMan pacman, Level level,Vector2 blinkyPos);

}
