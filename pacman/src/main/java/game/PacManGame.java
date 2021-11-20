package game;

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
    public static GhostState ghostPhase = GhostState.CHASING;
    public static java.util.Timer ghostPhaseTimer;
    public static int numberOfPhaseLeft;


    public static GamePanel createGame(int w, int h, int d, int u) {
        screenWidth = w;
        screenHeight = h;
        gameDelay = d;
        gameUnit = u;
        score = 0;
        lvl = new Level(2,gameUnit);
        numberOfPhaseLeft = 7;
        gameObjects = new ArrayList<>();
        GamePanel gp = new GamePanel();
        gamePanel = gp;
        createGameCharacters(lvl, 3);
        //TODO : ADD place where pacman can't walk (entry of ghost spawn)
        startTheGame();
        return gp;
    }

    public static void startTheGame() {
        PacManGame.gameState = GameState.RUNNING;
        PacManGame.timer = new Timer(PacManGame.gameDelay, gamePanel);
        timer.start();
        AutoChangeGhostsState.createPhaseTimer(6000);
    }



    public static boolean hasEatenAllThePacGomme() {
        return lvl.getPacGommeCount() == 0;
    }

    public static void actionPerformed() {
        if (gameState.equals(GameState.RUNNING)) {
            checkPacmanCollisions();
            moveThePacman();
            moveTheGhost();
        }
    }

    public static void moveThePacman() {
        Engines.moveGameObjectByOneStep(PacManGame.pacMan, PacManGame.pacMan.getDirection(), pacMan.getSpeed());
    }

    private static void moveTheGhost() {
        for (Ghost ghost:ghosts) {
            int posX = ghost.getPosition().x / PacManGame.gameUnit;
            int posY = ghost.getPosition().y / PacManGame.gameUnit;
            if (ghost.getPosition().x % gameUnit == 0 && ghost.getPosition().y % gameUnit == 0) {
                List<Direction> directions = new ArrayList<>();
                for (Direction dir : Direction.values()) {
                    if (dir == Direction.NEUTRAL) continue;
                    if (!checkWall(dir, posX, posY)) directions.add(dir);
                }
                ghost.thinkNextDirection(lvl, blinky, directions);
            }
            Engines.moveGameObjectByOneStep(ghost, ghost.getDirection(), ghost.getSpeed());
            checkTeleportOtherSide(ghost,posX,posY);
        }
    }

    private static void checkPacmanCollisions() {
        if (pacMan.getPosition().x % gameUnit == 0 && pacMan.getPosition().y % gameUnit == 0) {
            int positionX = pacMan.getPosition().x / PacManGame.gameUnit;
            int positionY = pacMan.getPosition().y / PacManGame.gameUnit;
            checkTeleportOtherSide(pacMan,positionX,positionY);
            checkPacManEating(PacGomme.ID, PacGomme.point, positionX, positionY);
            checkPacManEating(SuperPacGomme.ID, SuperPacGomme.point, positionX, positionY);
            checkPacmanCanChangeDir(positionX,positionY);
            checkCollWithGhost();
        }
    }

    private static void checkCollWithGhost() {
        if (pacManIsInCollisionWithGhost()){
            if(pacMan.useLife()){
                createGameCharacters(lvl,pacMan.getLives());
            }else gameState = GameState.OVER;
        }
    }

    private static void checkPacmanCanChangeDir(int positionX,int positionY) {
        if (pacMan.nextDir != null && !checkWall(pacMan.nextDir, positionX, positionY))
            pacMan.setDirection(pacMan.nextDir);
        if (checkWall(pacMan.getDirection(), positionX, positionY))
            PacManGame.stopPacmanMovement();
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

    private static void checkTeleportOtherSide(Character c, int posX, int posY) {
        if (posX == 0 && c.getDirection().equals(Direction.LEFT)) {//Todo : make a getter x and y in level
            c.setPosition(new Vector2((PacManGame.lvl.getMazeWidth() - 1) * gameUnit, c.getPosition().y));
        } else if (posX == (PacManGame.lvl.getMazeWidth() - 1) && c.getDirection().equals(Direction.RIGHT)) {
            c.setPosition(new Vector2(0, c.getPosition().y));
        }
        if (posY == 0 && c.getDirection().equals(Direction.UP)) {//Todo : make a getter x and y in level
            c.setPosition(new Vector2(c.getPosition().x,(PacManGame.lvl.getMazeHeight() - 1) * gameUnit));
        } else if (posY == (PacManGame.lvl.getMazeHeight() - 1) && c.getDirection().equals(Direction.DOWN)) {
            c.setPosition(new Vector2(c.getPosition().x,0));
        }
    }

    private static boolean checkWall(Direction direction, int positionX, int positionY) {
        try {
            Vector2 dir = Engines.getVectorFromDir(direction,1);
            return PacManGame.lvl.getLevelArray()[positionY + dir.y][positionX + dir.x] == Wall.ID;
            /*
            switch (direction) {
                case DOWN:
                    if (PacManGame.lvl.getLevelArray()[positionY + 1][positionX] == Wall.ID) {
                        if (Engines.willCollide(positionY, positionY + 1, 1, 1, 1))
                            return true;
                    }
                    break;
                case UP:
                    if (PacManGame.lvl.getLevelArray()[positionY - 1][positionX] == Wall.ID) {
                        if (Engines.willCollide(positionY, positionY - 1, 1, 1, 1))
                            return true;
                    }
                    break;
                case LEFT:
                    if (PacManGame.lvl.getLevelArray()[positionY][positionX - 1] == Wall.ID) {
                        if (Engines.willCollide(positionX, positionX - 1, 1, 1, 1))
                            return true;
                    }
                    break;
                case RIGHT:
                    if (PacManGame.lvl.getLevelArray()[positionY][positionX + 1] == Wall.ID) {
                        if (Engines.willCollide(positionX, positionX + 1, 1, 1, 1))
                            return true;
                    }
                    break;
            }
            return false;*/
        }catch (ArrayIndexOutOfBoundsException e){                  //used when the Object reached Limit of maze array (ex : when reaching tunnel on side)
            return false;
        }
    }

    public static boolean pacManIsInCollisionWithGhost() {
        for (Ghost ghost : ghosts) {
            if (Engines.isInCollision(ghost.getPosition().x, ghost.getPosition().y, pacMan.getPosition().x, pacMan.getPosition().y, gameUnit, gameUnit, gameUnit, gameUnit)) {
                System.out.println("Ghost killed Pacman");
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

    public static void createGameCharacters(Level level, int livesOfPacMan){
        pacMan = new PacMan(level.getSpawn(CharacterName.PACMAN), livesOfPacMan);
        pacMan.setDirection(Direction.NEUTRAL);
        ghosts = new ArrayList<>();
        blinky = new Blinky(level.getSpawn(CharacterName.BLINKY));    //need to create blinky separately from other ghost for AI
        ghosts.add(blinky);
        ghosts.add(new Clyde(level.getSpawn(CharacterName.CLYDE)));
        ghosts.add(new Inky(level.getSpawn(CharacterName.INKY)));
        ghosts.add(new Pinky(level.getSpawn(CharacterName.PINKY)));
        ghostPhase = GhostState.DISPERSION;
    }
}

