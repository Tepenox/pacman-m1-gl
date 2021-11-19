package game.object;

import utility.GameObject;
import utility.Vector2;

import java.awt.*;

public class SuperPacGomme extends GameObject {
    public static final int ID = 3;
    public static final int point = 50;

    public SuperPacGomme(Vector2 position){
        super(3,position);
    }

    public Vector2 getPosition(){
        return this.position;
    }

}
