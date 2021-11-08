package game.character;

import game.GameObject;
import game.utility.Vector2;

import java.awt.*;

public abstract class Character {

    private final int speed = 0;
    private Image sprite;
    private Vector2 direction;
    private Vector2 position;

    public Character(Image sprite, Vector2 position ) {
        this.sprite = sprite;
        this.position = position;
    }

    public int getSpeed() {
        return speed;
    }

    public Image getSprite() {
        return sprite;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public void setDirection(Vector2 direction) {
        this.direction = direction;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void move(Vector2 direction){

    }

    public abstract void eat(GameObject character);

}
