package game.character;

import game.utility.Direction;
import game.utility.Vector2;

import java.awt.*;

public abstract class Character {

    public final int speed = 0;
    public Image appearence;
    public Direction currentDirection;
    public Vector2 currentPosition;

    public int getSpeed() {
        return speed;
    }

    public Image getAppearence() {
        return appearence;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }

    public Vector2 getCurrentPosition() {
        return currentPosition;
    }

    public void setAppearence(Image appearence) {
        this.appearence = appearence;
    }

    public void setCurrentDirection(Direction currentDirection) {
        this.currentDirection = currentDirection;
    }

    public void setCurrentPosition(Vector2 currentPosition) {
        this.currentPosition = currentPosition;
    }

    public Character(Image appearence) {
        this.appearence = appearence;
    }

    public void moove(Direction direction){
        switch(direction){
            case UP -> System.out.println();
            case DOWN -> System.out.println(); // TODO
            case LEFT -> System.out.println();
            case RIGHT -> System.out.println();
        }
    }

    public abstract void eat(Character character);

}
