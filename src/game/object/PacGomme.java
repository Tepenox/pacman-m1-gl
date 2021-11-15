package game.object;

import game.GameObject;
import game.utility.Vector2;

import java.awt.*;

public class PacGomme extends GameObject {
    public static final int ID = 2;
    public static final int point = 10;
    private int points;
    private Image sprite;
    private Vector2 position;

    public PacGomme (int points, Image sprite, Vector2 position){
        super(position);
        this.points = points;
        this.sprite = sprite;
    }

    public int getPoints(){
        return points;
    }

    public Vector2 getPosition(){
        return position;
    }

}
