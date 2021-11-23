package game.character;

import game.GameUtility.CharacterName;
import utility.GameObject;
import utility.Direction;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class PacMan extends Character {
    public final static int ID = 5;
    public Direction nextDir = Direction.NEUTRAL;
    int lives;

    public PacMan(Vector2 position, int lives) {
        super(5,
                position,
                new HashMap<>(),
                CharacterName.PACMAN);

        this.sprites.put(Direction.UP, new ImageIcon(getClass().getResource("/PacMan/upPacMan.gif")).getImage());
        this.sprites.put(Direction.DOWN, new ImageIcon(getClass().getResource("/PacMan/downPacMan.gif")).getImage());
        this.sprites.put(Direction.RIGHT, new ImageIcon(getClass().getResource("/PacMan/rightPacMan.gif")).getImage());
        this.sprites.put(Direction.LEFT, new ImageIcon(getClass().getResource("/PacMan/leftPacMan.gif")).getImage());
        this.sprites.put(Direction.NEUTRAL, new ImageIcon(getClass().getResource("/PacMan/neutralPacMan.gif")).getImage());
        Image img = sprites.get(Direction.NEUTRAL);
        super.setSprite(img);
        this.lives = lives;
    }

    @Override
    public void eat(GameObject character) {

    }

    public boolean useLife() {
        lives--;
        return lives > 0;
    }

    public int getLives() {
        return lives;
    }
}
