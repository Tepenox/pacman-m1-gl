package game;

import game.character.Character;
import game.character.Ghosts.*;
import game.character.PacMan;
import game.levels.Level;
import game.object.PacGomme;
import game.object.SuperPacGomme;
import game.object.Wall;
import game.utility.CharacterName;
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
    public static int gameDelay;
    public static int score;
    public static Timer timer;
    public static List<Ghost> ghosts;
    public static List<GameObject> gameObjects;
    public static GameState gameState = GameState.PAUSED;
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
        pacMan = new PacMan(new ImageIcon("src/game/resources/PacMan/neutralPacMan.png").getImage(), lvl.getSpawn(CharacterName.PACMAN).multiply(gameUnit));//todo refactor
        GamePanel gp = new GamePanel();
        pacMan.setDirection(Direction.NEUTRAL);
        gamePanel = gp;
        ghosts = new ArrayList<>();
        ghosts.add(new Blinky(new ImageIcon("src/game/resources/Blinky/leftBlinky.png").getImage(),lvl.getSpawn(CharacterName.BLINKY).multiply(gameUnit)));
        ghosts.add(new Clyde(new ImageIcon("src/game/resources/Clyde/leftClyde.png").getImage(),lvl.getSpawn(CharacterName.CLYDE).multiply(gameUnit)));
        ghosts.add(new Inky(new ImageIcon("src/game/resources/Inky/leftInky.png").getImage(),lvl.getSpawn(CharacterName.INKY).multiply(gameUnit)));
        ghosts.add(new Pinky(new ImageIcon("src/game/resources/Pinky/leftPinky.png").getImage(),lvl.getSpawn(CharacterName.PINKY).multiply(gameUnit)));
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
        int positionX = pacMan.getPosition().x / PacManGame.gameUnit;
        int positionY = pacMan.getPosition().y / PacManGame.gameUnit;
//        checking collisions with wall
        try {
            checkTeleportOtherSide(positionX);
            checkEating(PacGomme.ID,PacGomme.point,positionX,positionY);
            checkEating(SuperPacGomme.ID,SuperPacGomme.point,positionX,positionY);
            if(!checkWall(pacMan.nextDir,positionX,positionY))
                pacMan.setDirection(pacMan.nextDir);
            if(checkWall(pacMan.getDirection(),positionX,positionY))
                PacManGame.stopPacmanMovement();
        } catch (ArrayIndexOutOfBoundsException e) {
            //do nothing
        }
    }

    private static void checkEating(int ID, int point,int x, int y) {
        if(PacManGame.lvl.getLevelArray()[y][x] == ID){
            score += point;
            if(ID==3 || ID==2)
                lvl.removePacGomme(x,y,0);
            System.out.println("SCORE: "+score);
            if (PacManGame.hasEatenAllThePacGomme())
                System.out.println("YOU WON THE LEVEL ");
        }
    }

    private static void checkTeleportOtherSide(int posX) {//TODO : make generic (with y axis teleport)
        if (posX == 0 && pacMan.getDirection().equals(Direction.LEFT)) {//Todo : make a getter x and y in level
            pacMan.setPosition(new Vector2((PacManGame.lvl.getLevelArray()[0].length - 1) * gameUnit, pacMan.getPosition().y));
        } else if (posX == (PacManGame.lvl.getLevelArray()[0].length - 1) && pacMan.getDirection().equals(Direction.RIGHT)) {
            pacMan.setPosition(new Vector2(0, pacMan.getPosition().y));
        }
    }

    private static boolean checkWall(Direction direction, int positionX, int positionY) {//TODO : when implementing smooth movement use Engines.checkCollision()
        switch (direction) {
            case DOWN:
                if (PacManGame.lvl.getLevelArray()[positionY + 1][positionX] == Wall.ID){
                    return true;
                }
                break;
            case UP :
                if(PacManGame.lvl.getLevelArray()[positionY - 1][positionX] == Wall.ID){
                    return true;
                }
                break;
            case LEFT :
                if(PacManGame.lvl.getLevelArray()[positionY][positionX-1] == Wall.ID){
                    return true;
                }
                break;
            case RIGHT :
                if(PacManGame.lvl.getLevelArray()[positionY][positionX+1] == Wall.ID){
                    return true;
                }
                break;
        }
        return false;
    }

    private static void stopPacmanMovement() {
        //si on a des animation on peut les arreter ici
        pacMan.setDirection(Direction.NEUTRAL);
    }

    public static void setPacmanDir(Direction dir) {
        pacMan.nextDir = dir;
        int positionX = PacManGame.pacMan.getPosition().x / PacManGame.gameUnit;
        int positionY = PacManGame.pacMan.getPosition().y / PacManGame.gameUnit;
        if(checkWall(dir,positionX,positionY)){
            return;
        }
        pacMan.setDirection(dir);
    }
}

