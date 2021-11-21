package game.character;

import game.GameFrame;
import game.GameUtility.CharacterName;
import utility.GameObject;
import utility.Direction;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
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

        this.sprites.put(Direction.UP, new ImageIcon(getClass().getResource("/PacMan/upPacMan.png")).getImage());
        this.sprites.put(Direction.DOWN, new ImageIcon(getClass().getResource("/PacMan/downPacMan.png")).getImage());
        this.sprites.put(Direction.RIGHT, new ImageIcon(getClass().getResource("/PacMan/rightPacMan.png")).getImage());
        this.sprites.put(Direction.LEFT, new ImageIcon(getClass().getResource("/PacMan/leftPacMan.png")).getImage());
        this.sprites.put(Direction.NEUTRAL, new ImageIcon(getClass().getResource("/PacMan/neutralPacMan.png")).getImage());
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
