package game.object;

import game.GameObject;
import game.utility.Vector2;

import java.awt.*;

public class SuperPacGomme extends GameObject {

    public static final int ID = 3;
    private Image sprite;

    public SuperPacGomme(Vector2 position, Image sprite){
        super(position);
        this.sprite = sprite;
    }

    public Vector2 getPosition(){
        return this.position;
    }

}
