package game;

import game.character.Character;
import game.character.PacMan;
import game.levels.Level;
import game.object.PacGomme;
import game.object.SuperPacGomme;
import game.object.Wall;
import game.utility.Direction;
import game.utility.GameState;
import game.utility.Vector2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PacManGame{
    public static int screenWidth;
    public static int screenHeight;
    public static int gameUnit;
    private static int gameDelay;
    private static int score;
    private static Timer timer;
    private static List<GameObject> gameObjects;
    private static GameState gameState = GameState.PAUSED;
    public static PacMan pacMan;
    public static Level lvl = new Level(1);
    public static GamePanel gamePanel;

    public static GamePanel createGame(int w, int h, int d, int u){
        screenWidth = w;
        screenHeight = h;
        gameDelay = d;
        gameUnit = u;
        score = 0;
        gameObjects = new ArrayList<>();
        pacMan = new PacMan(null, new Vector2(14 * gameUnit, 23 * gameUnit));
        GamePanel gp = new GamePanel();
        pacMan.setDirection(Direction.NEUTRAL);
        gamePanel = gp;
        startTheGame();
        return gp;
    }

    public static void startTheGame() {
        PacManGame.gameState = GameState.RUNNING;
        PacManGame.timer = new Timer(PacManGame.gameDelay,gamePanel);
        timer.start();
    }

    public static boolean hasEatenAllThePacGomme(){
        return lvl.getPacGommeCount() == 0;
    }

    public static void moveThePacman(){
        moveTheCharacterByOneStep(PacManGame.pacMan, PacManGame.pacMan.getDirection());
    }

    private static void moveTheCharacterByOneStep(Character character, Direction direction){
        int moveByCases = (PacManGame.gameUnit) * character.getSpeed();
        character.setDirection(direction);
        switch (direction) {
            case UP:
                character.getPosition().addToY(-moveByCases);
                break;
            case DOWN:
                character.getPosition().addToY(+moveByCases);
                break;
            case LEFT:
                character.getPosition().addToX(-moveByCases);
                break;
            case RIGHT:
                character.getPosition().addToX(+moveByCases);
                break;
            case NEUTRAL:
                //do nothing
                break;
        }
    }

    public static void actionPerformed(){
        if (gameState.equals(GameState.RUNNING)) {
            checkCollision();
            moveThePacman();
        }
    }

    private static void checkCollision() {
        checkPacmanCollisions();
    }

    private static void checkPacmanCollisions() {
        int positionX = PacManGame.pacMan.getPosition().x / PacManGame.gameUnit;
        int positionY = PacManGame.pacMan.getPosition().y / PacManGame.gameUnit;
        Direction direction = PacManGame.pacMan.getDirection();
//        checking collisions with wall
        try {
            if (positionX == 0 && pacMan.getDirection().equals(Direction.LEFT)) {
                pacMan.setPosition(new Vector2((PacManGame.lvl.getLevelArray().length - 1) * gameUnit, pacMan.getPosition().y));
            } else if (positionX == (PacManGame.lvl.getLevelArray().length - 1) && pacMan.getDirection().equals(Direction.RIGHT)) {
                pacMan.setPosition(new Vector2(0, pacMan.getPosition().y));
            }

            if (PacManGame.lvl.getLevelArray()[positionY][positionX] == PacGomme.ID) {
                PacManGame.lvl.getLevelArray()[positionY][positionX] = 0;
                score++;
                lvl.setPacGommeCount(lvl.getPacGommeCount()-1);
                if (PacManGame.hasEatenAllThePacGomme()) {
                    System.out.println("YOU WON THE LEVEL ");
                }
                System.out.println("SCORE: " + score);
            }
            if (PacManGame.lvl.getLevelArray()[positionY][positionX] == SuperPacGomme.ID) {
                PacManGame.lvl.getLevelArray()[positionY][positionX] = 0;
                System.out.println("you eat super pacgomme");

            }
            if (direction.equals(Direction.DOWN) && PacManGame.lvl.getLevelArray()[positionY + 1][positionX] == Wall.ID
                    || direction.equals(Direction.UP) && PacManGame.lvl.getLevelArray()[positionY - 1][positionX] == Wall.ID
                    || direction.equals(Direction.LEFT) && PacManGame.lvl.getLevelArray()[positionY][positionX - 1] == Wall.ID
                    || direction.equals(Direction.RIGHT) && PacManGame.lvl.getLevelArray()[positionY][positionX + 1] == Wall.ID) {
                PacManGame.stopPacmanMovement();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            //do nothing
        }
    }

    private static void stopPacmanMovement() {
        //si on a des animation on peut les arreter ici
        pacMan.setDirection(Direction.NEUTRAL);
    }
}

