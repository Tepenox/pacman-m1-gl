package game.GameObject.object;

import utility.GameObject;
import utility.Vector2;

public class Wall extends GameObject {
    public static final int ID = 1;

    public Wall(int id, Vector2 position) {
        super(id, position);
    }
}
