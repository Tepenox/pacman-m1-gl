package game.object;

import utility.GameObject;
import utility.Vector2;

import java.awt.*;

public class PacGomme extends GameObject {
    public static final int ID = 2;
    public static final int point = 10;

    public PacGomme (Image sprite, Vector2 position){
        super(2,position,sprite);
    }

}
