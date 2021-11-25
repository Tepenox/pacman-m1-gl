package game.object.fruits;
import utility.Vector2;

import java.awt.*;

public class Fruit {
    public String name;
    public int points;
    public Image sprite;
    public Vector2 position;

    public Fruit (String name,int points, Image sprite, Vector2 position){
        this.name = name ;
        this.points = points;
        this.sprite = sprite;
        this.position = position;
    }

}
