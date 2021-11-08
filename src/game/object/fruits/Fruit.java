package game.object.fruits;
import game.utility.Vector2;

import java.awt.*;

public class Fruit {
    private int points;
    private Image sprite;
    private Vector2 position;

    public Fruit (int points, Image sprite, Vector2 position){
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
