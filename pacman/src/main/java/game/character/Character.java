package game.character;

import engines.IAEngine;
import engines.PhysicEngine;
import game.Engines;
import utility.GameObject;
import utility.Direction;
import utility.Vector2;
import java.awt.*;
import java.util.Map;


public abstract class Character extends GameObject{

    private Direction direction;
    private Vector2 position;
    private int speed = 10;
    public Map<Direction , Image> sprites;


    public Character(int id,Vector2 position,Map<Direction,Image> sprites) {
        super(id,position);
        this.direction = Direction.NEUTRAL;
        this.position = position;
        this.sprites = sprites;
        super.setSprite(sprites.get(Direction.NEUTRAL));
    }


    public Image getSprite() {
        return super.getSprite();
    }

    public Direction getDirection() {
        return direction;
    }

    public Vector2 getDirectionAsVector(){
        return Engines.getVectorFromDir(this.direction,this.speed);
}

    public Vector2 getPosition() {
        return position;
    }

    public void setSprite(Image sprite) {
        super.setSprite(sprite);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        super.setSprite(sprites.get(direction));
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
