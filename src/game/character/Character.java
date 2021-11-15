package game.character;

import game.GameObject;
import game.utility.Direction;
import game.utility.Vector2;
import java.awt.*;


public abstract class Character extends GameObject{

    private Image sprite;
    private Direction direction;
    private Vector2 position;
    private int speed = 1;


    public Character(Image sprite, Vector2 position) {
        super(position);
        this.direction = Direction.NEUTRAL;
        this.sprite = sprite;
        this.position = position;
    }


    public Image getSprite() {
        return sprite;
    }

    public Direction getDirection() {
        return direction;
    }

    public Vector2 getDirectionAsVector(){
        return new Vector2(0,0);// todo implement cases
}

    public Vector2 getPosition() {
        return position;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void move(Vector2 direction){

    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public abstract void eat(GameObject character);

}
