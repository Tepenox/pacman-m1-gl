package game.character;

import game.GameObject;
import game.utility.Vector2;

import java.awt.*;

public class PacMan extends Character{
    public final static int ID = 2;

    public PacMan(Image appearence, Vector2 position) {
        super(appearence,position);
    }

    @Override
    public void eat(GameObject character) {

    }


}
