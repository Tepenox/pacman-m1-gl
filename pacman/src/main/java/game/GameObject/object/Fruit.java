package game.GameObject.object;
import utility.GameObject;
import utility.Vector2;

import java.awt.*;

public class Fruit  extends GameObject {
    public String name;
    public int points;
    public Image sprite;

    public Fruit (String name,int points, Image sprite, Vector2 position){
        super(8,position);
        this.name = name ;
        this.points = points;
        this.sprite = sprite;
    }

}
