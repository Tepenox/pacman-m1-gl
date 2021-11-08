package game.object;

import game.utility.Vector2;

import java.awt.*;

public class SuperPacGomme {

    private Vector2 position;
    private Image sprite;

    public SuperPacGomme(Vector2 position, Image sprite){
        this.position = position;
        this.sprite = sprite;
    }

    public Vector2 getPosition(){
        return position;
    }

}
