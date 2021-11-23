package game.GameEngine;

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

public class GameLogic {
    private static MenuLogic menuLogic;
    public static int screenWidth;
    public static int screenHeight;
    public static int gameUnit;
    public static int gameDelay;
    public static int score;
    public static Timer timer;
    public static List<Ghost> ghosts;
    public static GameState gameState;
    public static PacMan pacMan;
    public static Blinky blinky;
    public static Level lvl;
    public static PacManGamePanel pacManGamePanel;
    public static GhostState ghostPhase;
    public static java.util.Timer ghostPhaseTimer;
    public static int numberOfPhaseLeft;
    public static long startTime;


    public static PacManGamePanel createGame(MenuLogic gamef, int lvlNumber, int startScore) {
        menuLogic = gamef;
        screenWidth = gamef.screenWidth;
        screenHeight = gamef.screenHeight;
        gameDelay = gamef.gameDelay;
        gameUnit = gamef.gameUnit;
        score = startScore;
        timer = null;
        ghosts = null;
        gameState = null;
        pacMan = null;
        blinky = null;
        lvl = new Level(lvlNumber,gameUnit);
        pacManGamePanel = null;
        ghostPhase = GhostState.CHASING;
        ghostPhaseTimer = null;
        numberOfPhaseLeft = 7;
        startTime = System.currentTimeMillis();
        PacManGamePanel gp = new PacManGamePanel();
        pacManGamePanel = gp;
        startTheGame();
        return gp;
    }

    public static void startTheGame() {
        createGameCharacters(lvl, 3);
        GameLogic.gameState = GameState.STARTING;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        runTheGame();
                    }
                },
                5000
        );
    }

    public static void runTheGame(){
        pacManGamePanel.addInputListener();
        GameLogic.gameState = GameState.RUNNING;
        pacManGamePanel.setMessageMiddleScreen("");
        timer = null;
        timer = new Timer(GameLogic.gameDelay, pacManGamePanel);
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
        int totalDist = pacMan.getSpeed();
        while (totalDist > 0){
            int distToTravel = progressiveMovement(pacMan, Engines.getVectorFromDir(pacMan.getDirection(),1),totalDist);
            Engines.moveGameObjectByOneStep(pacMan, pacMan.getDirection(), distToTravel);
            checkPacmanCollisions();
            totalDist -= distToTravel;
        }
    }

    private static int progressiveMovement(GameObject go, Vector2 dir, int amount){ //give a dist to travel (in pixel) till it reach a %gameUnit==0
        if(go.getPosition().x % gameUnit == 0 && go.getPosition().y % gameUnit == 0){
            return amount;
        }
        if(dir.x != 0 && dir.y == 0){//horizontal mov
            int distBeforeGameUnitMultiple;
            if(dir.x > 0){
                distBeforeGameUnitMultiple = gameUnit - go.getPosition().x % gameUnit;
            }else distBeforeGameUnitMultiple = go.getPosition().x % gameUnit;
            return Math.min(distBeforeGameUnitMultiple,amount);
        }
        if(dir.x == 0 && dir.y != 0){//vertical mov
            int distBeforeGameUnitMultiple;
            if(dir.y > 0){
                distBeforeGameUnitMultiple = gameUnit - go.getPosition().y % gameUnit;
            }else distBeforeGameUnitMultiple = go.getPosition().y % gameUnit;
            return Math.min(distBeforeGameUnitMultiple,amount);
        }
        return amount;//case of neutral mov
    }

    private static void moveTheGhost() {
        for (Ghost ghost:ghosts) {
            int totalDist = ghost.getSpeed();
            while (totalDist > 0){
                int distToTravel = progressiveMovement(ghost,Engines.getVectorFromDir(ghost.getDirection(),1),totalDist);
                if(distToTravel == 0) break;
                Engines.moveGameObjectByOneStep(ghost, ghost.getDirection(), distToTravel);
                checkGhostCollisions(ghost);
                totalDist -= distToTravel;
            }
        }
    }

    private static void checkGhostCollisions(Ghost ghost){
        if (ghost.getPosition().x % gameUnit == 0 && ghost.getPosition().y % gameUnit == 0) {
            int positionX = ghost.getPosition().x / GameLogic.gameUnit;
            int positionY = ghost.getPosition().y / GameLogic.gameUnit;
            checkTeleportOtherSide(ghost,positionX,positionY);
            List<Direction> directions = new ArrayList<>();
            for (Direction dir : Direction.values()) {
                if (dir == Direction.NEUTRAL) continue;
                if (!checkWall(dir, positionX, positionY)) directions.add(dir);
            }
            ghost.thinkNextDirection(lvl, blinky, directions);
        }
    }

    private static void checkPacmanCollisions() {
        if (pacMan.getPosition().x % gameUnit == 0 && pacMan.getPosition().y % gameUnit == 0) {
            int positionX = pacMan.getPosition().x / GameLogic.gameUnit;
            int positionY = pacMan.getPosition().y / GameLogic.gameUnit;
            checkTeleportOtherSide(pacMan,positionX,positionY);
            checkPacManEating(PacGomme.ID, PacGomme.point, positionX, positionY);
            checkPacManEating(SuperPacGomme.ID, SuperPacGomme.point, positionX, positionY);
            checkPacmanCanChangeDir(positionX,positionY);
        }
        checkCollWithGhost();
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
            GameLogic.stopPacmanMovement();
    }

    private static void checkPacManEating(int ID, int point, int x, int y) {
        if (GameLogic.lvl.getLevelArray()[y][x] == ID) {
            score += point;
            if (ID == 3 || ID == 2)
                lvl.removePacGomme(x, y, 0);
            System.out.println("SCORE: " + score);
            if (GameLogic.hasEatenAllThePacGomme())
                wonLevel();
            if(ID == 3)
                superPacGommeEffect();
        }
    }

    public static void wonLevel() {
        timer.stop();   //remove the Action listener on GamePanel, so that it reset correctly on new game
        menuLogic.startGame(lvl.getLevelNumber()+1,score);
    }

    private static void superPacGommeEffect() {
        for (Ghost ghost:ghosts) {
            ghost.setState(GhostState.FRIGHTENED);
        }

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        for (Ghost ghost:ghosts) {
                            ghost.setState(GhostState.TWINKLING);
                        }
                    }
                },
                6000
        );
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        for (Ghost ghost:ghosts) {
                            ghost.setState(ghostPhase);
                        }
                    }
                },
                10000
        );
    }

    private static void checkTeleportOtherSide(Character c, int posX, int posY) {
        if (posX == 0 && c.getDirection().equals(Direction.LEFT)) {//Todo : make a getter x and y in level
            c.setPosition(new Vector2((GameLogic.lvl.getMazeWidth() - 1) * gameUnit, c.getPosition().y));
        } else if (posX == (GameLogic.lvl.getMazeWidth() - 1) && c.getDirection().equals(Direction.RIGHT)) {
            c.setPosition(new Vector2(0, c.getPosition().y));
        }
        if (posY == 0 && c.getDirection().equals(Direction.UP)) {//Todo : make a getter x and y in level
            c.setPosition(new Vector2(c.getPosition().x,(GameLogic.lvl.getMazeHeight() - 1) * gameUnit));
        } else if (posY == (GameLogic.lvl.getMazeHeight() - 1) && c.getDirection().equals(Direction.DOWN)) {
            c.setPosition(new Vector2(c.getPosition().x,0));
        }
    }

    private static boolean checkWall(Direction direction, int positionX, int positionY) {
        try {
            Vector2 dir = Engines.getVectorFromDir(direction,1);
            return GameLogic.lvl.getLevelArray()[positionY + dir.y][positionX + dir.x] == Wall.ID;
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

