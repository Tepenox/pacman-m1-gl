package game;

import game.utility.Vector2;

public class GameObject {


    int id;
    private Vector2 position;
    

    public GameObject(int id , Vector2 position) {
        this.position = position;
    }
}
