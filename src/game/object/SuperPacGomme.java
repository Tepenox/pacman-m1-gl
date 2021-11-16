package game.object;

import utility.GameObject;
import utility.Vector2;

import java.awt.*;

public class SuperPacGomme extends GameObject {

    public static final int ID = 3;
    public static final int point = 50;
    private Image sprite;

    public SuperPacGomme(Vector2 position, Image sprite){
        super(3,position);
        this.sprite = sprite;
    }

    public Vector2 getPosition(){
        return this.position;
    }

}
