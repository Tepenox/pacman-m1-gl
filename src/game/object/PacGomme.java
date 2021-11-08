package game.object;

import game.utility.Vector2;

import java.awt.*;

public class PacGomme {
    private int points;
    private Image sprite;
    private Vector2 position;

    public PacGomme (int points, Image sprite, Vector2 position){
        this.points = points;
        this.sprite = sprite;
        this.position = position;
    }

    public int getPoints(){
        return points;
    }

    public Vector2 getPosition(){
        return position;
    }

}
