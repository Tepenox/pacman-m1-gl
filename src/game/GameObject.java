package game;

import game.utility.Vector2;

public class GameObject {


    public int id;
    protected Vector2 position;
    

    public GameObject(int id, Vector2 position) {
        this.id = id;
        this.position = position;
    }
}
