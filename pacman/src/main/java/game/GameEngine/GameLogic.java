package game.GameEngine;

import engines.CoreKernel;
import game.AutoChangeGhostsState;
import game.character.Character;
import game.character.Ghosts.*;
import game.character.PacMan;
import game.levels.GhostBase;
import game.levels.Level;
import game.object.*;
import game.GameUtility.CharacterName;
import game.object.fruits.Fruit;
import utility.Direction;
import game.GameUtility.GameState;
import utility.Vector2;
import utility.GameObject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class GameLogic {
    public static MenuLogic menuLogic;
    public static int screenWidth;
    public static int screenHeight;
    public static int gameUnit;
    public static int gameDelay;
    public static int score;
    public static Timer timer;
    public static List<Ghost> ghosts;
    public static Fruit fruit;
    public static GameState gameState;
    public static PacMan pacMan;
    public static Blinky blinky;
    public static Level lvl;
    public static GamePanel gamePanel;
    public static GhostState ghostPhase;
    public static long startTime;
    public static GhostBase ghostBase;
    private static java.util.Timer frightenedTimer;
    private static java.util.Timer twinklingTimer;
    public static int eatenGhostInARow;
    public static int ghostValue;
    public static boolean fruitAvailableTimeRunOut = false;
    public static java.util.Timer fruitSpawnerTimer = new java.util.Timer();
    public static boolean hasEatenFruitLevel = false;


    //======================================================= Game Initialiser =======================================================

    public static GamePanel createLevel(MenuLogic menuLogic, int lvlNumber, int startScore, int life) {
        EnginesCaller.stopAllActiveSounds();
        GameLogic.menuLogic = menuLogic;
        screenWidth = menuLogic.screenWidth;
        screenHeight = menuLogic.screenHeight;
        gameDelay = menuLogic.gameDelay;
        gameUnit = menuLogic.gameUnit;
        score = startScore;
        ghosts = null;
        gameState = null;
        pacMan = null;
        blinky = null;
        fruit = null;
        lvl = new Level(lvlNumber, gameUnit);
        ghostBase = new GhostBase(lvl.getSpawn(CharacterName.BLINKY));
        gamePanel = null;
        startTime = System.currentTimeMillis();
        GamePanel gp = new GamePanel();
        gamePanel = gp;
        cancelGhostTimer();
        frightenedTimer = null;
        twinklingTimer = null;
        eatenGhostInARow = 0;
        ghostValue = 200;
        startTheLevel(life);
        return gp;
    }

    public static void startTheLevel(int lives) {
        EnginesCaller.stopAllActiveSounds();
        EnginesCaller.playSfx("/sounds/game_start.wav");
        gamePanel.removeListener();
        AutoChangeGhostsState.stop();
        createGameCharacters(lvl, lives);
        if (!hasEatenFruitLevel && !fruitAvailableTimeRunOut){
            createFruit(lvl.getLevelNumber());
        }

        gamePanel.setMessageMiddleScreen("Ready !!!");
        GameLogic.gameState = GameState.STARTING;
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        runTheLevel();
                    }
                },
                5000
        );
    }

    public static void createGameCharacters(Level level, int livesOfPacMan) {
        pacMan = new PacMan(level.getSpawn(CharacterName.PACMAN), livesOfPacMan);
        pacMan.setDirection(Direction.NEUTRAL);
        ghosts = new ArrayList<>();
        blinky = new Blinky(level.getSpawn(CharacterName.BLINKY));    //need to create blinky separately from other ghost for AI
        ghosts.add(new Pinky(level.getSpawn(CharacterName.PINKY)));
        ghosts.add(new Clyde(level.getSpawn(CharacterName.CLYDE)));
        ghosts.add(new Inky(level.getSpawn(CharacterName.INKY)));
        ghostBase.addAllGhostToBase(ghosts);
        ghosts.add(blinky);
        ghostPhase = GhostState.DISPERSION;
    }

    public static void createFruit(Integer lvlNumber) {
        TimerTask spawn = new TimerTask() {
            @Override
            public void run() {
                fruit = Level.levelToFruit.get(lvlNumber);
            }
        };

        TimerTask despawn = new TimerTask() {
            @Override
            public void run() {
                fruit = null;
                fruitAvailableTimeRunOut = true;
            }
        };

        fruitSpawnerTimer.schedule(spawn,10000);
        fruitSpawnerTimer.schedule(despawn,20000);




    }

    //======================================================= Game Launcher =======================================================

    public static void runTheLevel() {
        EnginesCaller.playSfxOnLoop("/sounds/siren_1.wav");
        setPacmanDir(Direction.LEFT);
        gamePanel.addInputListener();
        GameLogic.gameState = GameState.RUNNING;
        gamePanel.setMessageMiddleScreen("");
        ghostPhase = GhostState.DISPERSION;
        if (timer != null) {
            timer.stop();
            timer = null;
        }
        timer = new Timer(GameLogic.gameDelay, gamePanel);
        timer.start();
        AutoChangeGhostsState.start();
        ghostBase.startRegenTimer();
    }

    //======================================================= Game Execution on each Frame =============================================

    public static void actionPerformed() {
        if (gameState.equals(GameState.RUNNING)) {
            checkCollWithGhost();
            checkPacmanCanChangeDir();
            moveThePacman();
            moveTheGhost();
        }
    }

    //======================================================= Moving PacMan =============================================

    public static void moveThePacman() {
        int totalDist = pacMan.getSpeed();
        while (totalDist > 0 && pacMan.getDirection() != Direction.NEUTRAL) {
            int distToTravel = progressiveMovement(pacMan, EnginesCaller.getVectorFromDir(pacMan.getDirection(), 1), totalDist);
            if (distToTravel == 0) {
                pacMan.setDirection(Direction.NEUTRAL);
                System.err.println("There was an error when moving PacMan");
                break;
            }
            EnginesCaller.moveGameObjectByOneStep(pacMan, pacMan.getDirection(), distToTravel);
            checkPacmanUtility();
            totalDist -= distToTravel;
        }
    }

    private static int progressiveMovement(GameObject go, Vector2 dir, int amount) { //give a dist to travel (in pixel) till it reach a %gameUnit==0
        if (go.getPosition().x % gameUnit == 0 && go.getPosition().y % gameUnit == 0) {
            return Math.min(gameUnit, amount);
        }
        if (dir.x != 0 && dir.y == 0) {//horizontal mov
            int distBeforeGameUnitMultiple;
            if (dir.x > 0) {
                distBeforeGameUnitMultiple = gameUnit - go.getPosition().x % gameUnit;
            } else distBeforeGameUnitMultiple = go.getPosition().x % gameUnit;
            return Math.min(distBeforeGameUnitMultiple, amount);
        }
        if (dir.x == 0 && dir.y != 0) {//vertical mov
            int distBeforeGameUnitMultiple;
            if (dir.y > 0) {
                distBeforeGameUnitMultiple = gameUnit - go.getPosition().y % gameUnit;
            } else distBeforeGameUnitMultiple = go.getPosition().y % gameUnit;
            return Math.min(distBeforeGameUnitMultiple, amount);
        }
        return 0;//case of neutral mov
    }

    //======================================================= Moving Each Ghost =============================================

    private static void moveTheGhost() {
        for (Ghost ghost : ghosts) {
            if (ghost.state == GhostState.REGENERATING) {
                continue;
            }
            int totalDist = ghost.getSpeed();
            while (totalDist > 0) {
                int distToTravel = progressiveMovement(ghost, EnginesCaller.getVectorFromDir(ghost.getDirection(), 1), totalDist);
                if (distToTravel == 0) break;
                EnginesCaller.moveGameObjectByOneStep(ghost, ghost.getDirection(), distToTravel);
                checkGhostCollisions(ghost);
                if (ghost.state == GhostState.EATEN && checkGhostCloseBase(ghost) < 10) {
                    ghostBase.addGhostApproxPos(ghost);
                    break;
                }
                totalDist -= distToTravel;
            }
        }
        ghostBase.regenerateGhost();
    }

    private static int checkGhostCloseBase(Ghost ghost) {
        return (int) EnginesCaller.calculateDist(ghost.getPosition(), ghostBase.getBaseEntry());
    }

    //======================================================= Collisions =============================================

    private static void checkGhostCollisions(Ghost ghost) {
        if (ghost.getPosition().x % gameUnit == 0 && ghost.getPosition().y % gameUnit == 0) {
            int positionX = ghost.getPosition().x / GameLogic.gameUnit;
            int positionY = ghost.getPosition().y / GameLogic.gameUnit;
            checkTeleportOtherSide(ghost, positionX, positionY);
            List<Direction> directions = new ArrayList<>();
            for (Direction dir : Direction.values()) {
                if (dir == Direction.NEUTRAL) continue;
                if (!checkWall(dir, positionX, positionY)) directions.add(dir);
            }
            ghost.thinkNextDirection(lvl, blinky, directions);
        }
    }

    private static void checkCollWithGhost() {
        Ghost ghostTouching = pacManIsInCollisionWithGhost();
        if (ghostTouching != null) {
            if (ghostTouching.state == GhostState.CHASING || ghostTouching.state == GhostState.DISPERSION) {
                if (pacMan.useLife()) {
                    System.out.println("Ghost killed Pacman");
                    startTheLevel(pacMan.getLives());
                } else looseLevel();
            } else if (ghostTouching.state == GhostState.FRIGHTENED || ghostTouching.state == GhostState.TWINKLING) {
                eatenGhostInARow++;
                score += ghostValue;
                ghostValue *= 2;
                if (eatenGhostInARow >= 4) {
                    eatenGhostInARow = 0;
                    ghostValue = 200;
                }
                EnginesCaller.playSfx("/sounds/retreating.wav");
                ghostTouching.setState(GhostState.EATEN);
            }
        }
    }

    private static boolean checkWall(Direction direction, int positionX, int positionY) {
        try {
            Vector2 dir = EnginesCaller.getVectorFromDir(direction, 1);
            int ID = GameLogic.lvl.getLevelArray()[positionY + dir.y][positionX + dir.x];
            return ID == Wall.ID || ID == PinkWall.ID;
        } catch (ArrayIndexOutOfBoundsException e) {                  //used when the Object reached Limit of maze array (ex : when reaching tunnel on side)
            return false;
        }
    }

    public static Ghost pacManIsInCollisionWithGhost() {
        for (Ghost ghost : ghosts) {
            if (EnginesCaller.isInCollision(ghost.getPosition().x, ghost.getPosition().y, pacMan.getPosition().x, pacMan.getPosition().y, gameUnit / 2, gameUnit / 2, gameUnit / 2, gameUnit / 2)) {
                return ghost;
            }
        }
        return null;
    }

    public static boolean pacmanIsInCollisionWithFruit() {
        if (fruit != null)
            return EnginesCaller.isInCollision(fruit.getPosition().x, fruit.getPosition().y
                    , pacMan.getPosition().x, pacMan.getPosition().y, gameUnit,
                    gameUnit, gameUnit, gameUnit);

        return false;
    }

    //======================================================= PacMan Utility =============================================

    private static void checkPacmanUtility() {
        if (pacMan.getPosition().x % gameUnit == 0 && pacMan.getPosition().y % gameUnit == 0) {
            int positionX = pacMan.getPosition().x / GameLogic.gameUnit;
            int positionY = pacMan.getPosition().y / GameLogic.gameUnit;
            checkTeleportOtherSide(pacMan, positionX, positionY);
            checkPacManEating(PacGomme.ID, PacGomme.point, positionX, positionY);
            checkPacManEating(SuperPacGomme.ID, SuperPacGomme.point, positionX, positionY);
            checkPacmanCanChangeDir(positionX, positionY);
        }
    }

    private static void checkPacmanCanChangeDir(int positionX, int positionY) {
        if (pacMan.nextDir != null && !checkWall(pacMan.nextDir, positionX, positionY))
            pacMan.setDirection(pacMan.nextDir);
        if (checkWall(pacMan.getDirection(), positionX, positionY))
            pacMan.setDirection(Direction.NEUTRAL);
    }

    private static void checkPacmanCanChangeDir() {
        if (pacMan.getPosition().x % gameUnit == 0 && pacMan.getPosition().y % gameUnit == 0) {
            int positionX = pacMan.getPosition().x / GameLogic.gameUnit;
            int positionY = pacMan.getPosition().y / GameLogic.gameUnit;
            checkPacmanCanChangeDir(positionX,positionY);
        }
    }

    private static void checkPacManEating(int ID, int point, int x, int y) {
        if (GameLogic.lvl.getLevelArray()[y][x] == ID) {
            score += point;
            if (ID == 3 || ID == 2){
                lvl.removePacGomme(x, y, -1);

                EnginesCaller.playSfx("/sounds/munch_1.wav");
            }

            if (GameLogic.hasEatenAllThePacGomme())
                wonLevel();
            if (ID == 3)
                superPacGommeEffect();
        }
        if (pacmanIsInCollisionWithFruit()) {
            score = score + fruit.points;
            fruit = null;
            hasEatenFruitLevel = true;
            EnginesCaller.playSfx("/sounds/eat_fruit.wav");



        }
    }

    private static void checkTeleportOtherSide(Character c, int posX, int posY) {
        if (posX == 0 && c.getDirection().equals(Direction.LEFT)) {
            c.setPosition(new Vector2((GameLogic.lvl.getMazeWidth() - 1) * gameUnit, c.getPosition().y));
        } else if (posX == (GameLogic.lvl.getMazeWidth() - 1) && c.getDirection().equals(Direction.RIGHT)) {
            c.setPosition(new Vector2(0, c.getPosition().y));
        }
        if (posY == 0 && c.getDirection().equals(Direction.UP)) {
            c.setPosition(new Vector2(c.getPosition().x, (GameLogic.lvl.getMazeHeight() - 1) * gameUnit));
        } else if (posY == (GameLogic.lvl.getMazeHeight() - 1) && c.getDirection().equals(Direction.DOWN)) {
            c.setPosition(new Vector2(c.getPosition().x, 0));
        }
    }

    private static void superPacGommeEffect() {
        cancelGhostTimer();
        EnginesCaller.stopSfx("/sounds/siren_1.wav");
        EnginesCaller.playSfxOnLoop("/sounds/power_pellet.wav");
        for (Ghost ghost : ghosts) {
            if (ghost.state != GhostState.REGENERATING)
                ghost.setState(GhostState.FRIGHTENED);
        }

        frightenedTimer = new java.util.Timer();
        frightenedTimer.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        for (Ghost ghost : ghosts) {
                            if (ghost.state == GhostState.FRIGHTENED)
                                ghost.setState(GhostState.TWINKLING);
                        }
                    }
                },
                6000
        );
        twinklingTimer = new java.util.Timer();
        twinklingTimer.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        int ghostScared = 0;
                        for (Ghost ghost : ghosts) {
                            if (ghost.state == GhostState.TWINKLING)
                                ghost.setState(ghostPhase);
                            if(ghost.state == GhostState.TWINKLING || ghost.state == GhostState.FRIGHTENED)
                                ghostScared++;
                        }
                        if(ghostScared == 0){
                            EnginesCaller.stopSfx("/sounds/power_pellet.wav");
                            EnginesCaller.playSfxOnLoop("/sounds/siren_1.wav");
                        }
                        eatenGhostInARow = 0;
                    }
                },
                10000
        );
    }

    private static void cancelGhostTimer(){
        if(frightenedTimer != null){
            frightenedTimer.cancel();
            frightenedTimer.purge();
        }
        if(twinklingTimer != null){
            twinklingTimer.cancel();
            twinklingTimer.purge();
        }
    }

    //======================================================= Win Condition And Process =============================================

    public static boolean hasEatenAllThePacGomme() {
        return lvl.getPacGommeCount() == 0;
    }

    public static void wonLevel() {
        EnginesCaller.stopAllActiveSounds();
        resetFruitSpawner();
        menuLogic.levelEnded(lvl.getLevelNumber(), true, score);
    }

    public static void looseLevel() {
        EnginesCaller.stopAllActiveSounds();
        EnginesCaller.playSfx("/sounds/death_1.wav");
        resetFruitSpawner();
        gameState = GameState.OVER;
//        SoundEngine.stopAllActiveSounds();


    }

    private static void resetFruitSpawner() {
        hasEatenFruitLevel = false;
        fruitAvailableTimeRunOut = false;
        fruitSpawnerTimer.cancel();
        fruitSpawnerTimer.purge();
        fruitSpawnerTimer = new java.util.Timer();
    }

    //======================================================= Input Processing =============================================

    public static void setPacmanDir(Direction dir) {
        if (pacMan.getDirection() == Direction.NEUTRAL) {
            pacMan.setDirection(dir);
            pacMan.nextDir = null;
        } else pacMan.nextDir = dir;
    }

}


