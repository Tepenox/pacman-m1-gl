package game.object;

import utility.GameObject;
import utility.Vector2;

import java.awt.*;

public class Wall extends GameObject {
    public static final int ID = 1;

    public Wall(int id, Vector2 position, Image img) {
        super(id, position, img);
    }
}
