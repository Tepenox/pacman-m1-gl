package game;

import engines.PhysicEngine;
import game.character.Character;
import game.character.Ghosts.*;
import game.character.PacMan;
import game.levels.Level;
import game.object.PacGomme;
import game.object.SuperPacGomme;
import game.object.Wall;
import game.GameUtility.CharacterName;
import utility.Direction;
import game.GameUtility.GameState;
import utility.Vector2;
import utility.GameObject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PacManGame {
    public static int screenWidth;
    public static int screenHeight;
    public static int gameUnit;
    public static int gameDelay;
    public static int score;
    public static Timer timer;
    public static List<Ghost> ghosts;
    public static List<GameObject> gameObjects;
    public static GameState gameState = GameState.PAUSED;
    public static PacMan pacMan;
    public static Blinky blinky;
    public static Level lvl;
    public static GamePanel gamePanel;


    public static GamePanel createGame(int w, int h, int d, int u) {
        screenWidth = w;
        screenHeight = h;
        gameDelay = d;
        gameUnit = u;
        score = 0;
        lvl = new Level(1,gameUnit);
        gameObjects = new ArrayList<>();
        pacMan = new PacMan(lvl.getSpawn(CharacterName.PACMAN), 3);//todo refactor
        GamePanel gp = new GamePanel();
        pacMan.setDirection(Direction.NEUTRAL);
        gamePanel = gp;
        ghosts = new ArrayList<>();
        blinky = new Blinky(lvl.getSpawn(CharacterName.BLINKY));
        ghosts.add(blinky);
        //TODO : test movement from other ghosts + ADD place where pacman can't walk (entry of ghost spawn) + ADD place where only pacman can walk (ex : tunnel from both side) or just handle exception
        ghosts.add(new Clyde(lvl.getSpawn(CharacterName.CLYDE)));
        ghosts.add(new Inky(lvl.getSpawn(CharacterName.INKY)));
        ghosts.add(new Pinky(lvl.getSpawn(CharacterName.PINKY)));
        startTheGame();
        return gp;
    }

    public static void startTheGame() {
        PacManGame.gameState = GameState.RUNNING;
        PacManGame.timer = new Timer(PacManGame.gameDelay, gamePanel);
        timer.start();
    }

    public static boolean hasEatenAllThePacGomme() {
        return lvl.getPacGommeCount() == 0;
    }

    public static void moveThePacman() {
        PhysicEngine.moveGameObjectByOneStep(PacManGame.pacMan, PacManGame.pacMan.getDirection(), pacMan.getSpeed());
    }

    private static void moveTheGhost() {
        for (Ghost ghost:ghosts) {
            int posX = ghost.getPosition().x / PacManGame.gameUnit;
            int posY = ghost.getPosition().y / PacManGame.gameUnit;
            if (ghost.getPosition().x % gameUnit == 0 && ghost.getPosition().y % gameUnit == 0) {
                List<Direction> directions = new ArrayList<>();
                for (Direction dir : Direction.values()) {
                    if (dir == Direction.NEUTRAL) continue;
                    try{
                        if (!checkWall(dir, posX, posY)) directions.add(dir);
                    }catch (ArrayIndexOutOfBoundsException e) {
                        PhysicEngine.moveGameObjectByOneStep(ghost, ghost.getDirection(), ghost.getSpeed());
                        checkTeleportOtherSide(ghost,posX);
                        return;
                    }

                }
                ghost.thinkNextDirection(lvl, blinky, directions);
            }
            PhysicEngine.moveGameObjectByOneStep(ghost, ghost.getDirection(), ghost.getSpeed());
            checkTeleportOtherSide(ghost,posX);
        }
    }

    public static void actionPerformed() {
        if (gameState.equals(GameState.RUNNING)) {
            checkCollision();
            moveThePacman();
            moveTheGhost();
        }
    }

    private static void checkCollision() {
        checkPacmanCollisions();
    }

    private static void checkPacmanCollisions() {
        if (pacMan.getPosition().x % gameUnit == 0 && pacMan.getPosition().y % gameUnit == 0) {
            int positionX = pacMan.getPosition().x / PacManGame.gameUnit;
            int positionY = pacMan.getPosition().y / PacManGame.gameUnit;
//        checking collisions with wall
            try {
                checkTeleportOtherSide(pacMan,positionX);
                checkPacManEating(PacGomme.ID, PacGomme.point, positionX, positionY);
                checkPacManEating(SuperPacGomme.ID, SuperPacGomme.point, positionX, positionY);
                if (pacMan.nextDir != null && !checkWall(pacMan.nextDir, positionX, positionY))
                    pacMan.setDirection(pacMan.nextDir);
                if (checkWall(pacMan.getDirection(), positionX, positionY))
                    PacManGame.stopPacmanMovement();
                if (pacManIsInCollisionWithGhost())
                    try {//todo set to if
                        pacMan.removeOneLife();
                        pacMan.setDirection(Direction.NEUTRAL);
                        Vector2 pacManSpawn = lvl.getSpawn(CharacterName.PACMAN);
                        System.out.println(pacManSpawn.x + " "+pacManSpawn.y);
                        PhysicEngine.move(pacMan,pacManSpawn.x , pacManSpawn.y);
                    } catch (IllegalStateException e) {
                        gameState = GameState.OVER;
                    }
            } catch (ArrayIndexOutOfBoundsException e) {
                //do nothing
            }
        }
    }

    private static void checkPacManEating(int ID, int point, int x, int y) {
        if (PacManGame.lvl.getLevelArray()[y][x] == ID) {
            score += point;
            if (ID == 3 || ID == 2)
                lvl.removePacGomme(x, y, 0);
            System.out.println("SCORE: " + score);
            if (PacManGame.hasEatenAllThePacGomme())
                System.out.println("YOU WON THE LEVEL ");
            //next Game
        }
    }
    private static void respawnPacMan(){

    }

    private static void checkTeleportOtherSide(Character c, int posX) {//TODO : make generic (with y axis teleport)
        if (posX == 0 && c.getDirection().equals(Direction.LEFT)) {//Todo : make a getter x and y in level
            c.setPosition(new Vector2((PacManGame.lvl.getLevelArray()[0].length - 1) * gameUnit, c.getPosition().y));
        } else if (posX == (PacManGame.lvl.getLevelArray()[0].length - 1) && c.getDirection().equals(Direction.RIGHT)) {
            c.setPosition(new Vector2(0, c.getPosition().y));
        }
    }

    private static boolean checkWall(Direction direction, int positionX, int positionY) {
        switch (direction) {
            case DOWN:
                if (PacManGame.lvl.getLevelArray()[positionY + 1][positionX] == Wall.ID) {
                    if (PhysicEngine.willCollide(positionY, positionY + 1, 1, 1, 1))
                        return true;
                }
                break;
            case UP:
                if (PacManGame.lvl.getLevelArray()[positionY - 1][positionX] == Wall.ID) {
                    if (PhysicEngine.willCollide(positionY, positionY - 1, 1, 1, 1))
                        return true;
                }
                break;
            case LEFT:
                if (PacManGame.lvl.getLevelArray()[positionY][positionX - 1] == Wall.ID) {
                    if (PhysicEngine.willCollide(positionX, positionX - 1, 1, 1, 1))
                        return true;
                }
                /*
                if(PacManGame.lvl.getLevelArray()[positionY][positionX-1] == Wall.ID){
                    return true;
                }*/
                break;
            case RIGHT:
                if (PacManGame.lvl.getLevelArray()[positionY][positionX + 1] == Wall.ID) {
                    if (PhysicEngine.willCollide(positionX, positionX + 1, 1, 1, 1))
                        return true;
                }
                break;
        }
        return false;
    }

    public static boolean pacManIsInCollisionWithGhost() {
        for (Ghost ghost : ghosts) {
            if (PhysicEngine.isInCollision(ghost.getPosition().x, ghost.getPosition().y, pacMan.getPosition().x, pacMan.getPosition().y, gameUnit, gameUnit, gameUnit, gameUnit)) {
                System.out.println("Bruh u touched a ghost u are gay");
                return true;
            }
        }
        return false;
    }

    private static void stopPacmanMovement() {
        //si on a des animation on peut les arreter ici
        pacMan.setDirection(Direction.NEUTRAL);
    }

    public static void setPacmanDir(Direction dir) {
        if(pacMan.getDirection() == Direction.NEUTRAL) {
            pacMan.setDirection(dir);
            pacMan.nextDir = null;
        }else pacMan.nextDir = dir;
    }
}

