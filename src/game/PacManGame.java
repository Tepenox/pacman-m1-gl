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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PacManGame extends JPanel implements ActionListener {//Must be static
    final int screenWidth;
    final int screenHeight;
    final int unitSize;
    final int delay;
    private int score;
    private int pacGommeCount;
    final int[][] gameSpace;
    List<GameObject> gameObjects;
    PacMan pacMan;
    GameState gameState = GameState.PAUSED;

    //=========Refractor
    public static int width = 630;
    public static int height = 700;
    public static int unitToPixel = 30;
    private static int gameDelay;
    private static int gameScore;
    private static List<GameObject> GamegameObjects;
    private static GameState GamegameState = GameState.PAUSED;
    public static PacMan thePacMan = new PacMan(null, new Vector2(10 * unitToPixel, 15 * unitToPixel));
    public static Level lvl = new Level(1);

    public PacManGame(int width, int height, int delay, int unitSize) {
//        Engines.createFrame(gameTitle);
//        Engines.setSize(this.frame, width, height);
        this.screenWidth = width;
        this.screenHeight = height;
        this.unitSize = unitSize;
//        this.gameUnits = (screenWidth * screenHeight) / (this.unitSize * this.unitSize);
        this.delay = delay;
        System.out.println(screenHeight / this.unitSize);
        System.out.println(screenWidth / this.unitSize);
        this.gameObjects = new ArrayList<>();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.pacMan = new PacMan(null, new Vector2(10 * unitSize, 15 * unitSize));
        this.gameSpace = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 3, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1},
                {1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 2, 1},
                {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
                {1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1},
                {1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1},
                {1, 1, 1, 1, 1, 2, 1, 1, 1, 2, 1, 2, 1, 1, 1, 2, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 0, 0, 0, 1},
                {0, 0, 0, 0, 0, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 0, 0, 0, 0, 0},
                {1, 0, 0, 0, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 0, 0, 0, 1},
                {1, 0, 0, 0, 1, 2, 1, 2, 2, 2, 2, 2, 2, 2, 1, 2, 1, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1},
                {1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1},
                {1, 2, 1, 1, 2, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 2, 1, 1, 2, 1},
                {1, 2, 2, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 1},
                {1, 1, 2, 1, 2, 2, 1, 2, 1, 1, 1, 1, 1, 2, 1, 2, 2, 1, 2, 1, 1},
                {1, 2, 2, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 1, 2, 2, 2, 2, 2, 1},
                {1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 2, 1},
                {1, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 3, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},

        };
        this.pacGommeCount = getPacGommeCounter();
        this.score = 0;
        this.pacMan.setDirection(Direction.NEUTRAL);
//        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public static GamePanel createGame(int w, int h, int d, int u){
        width = w;
        height = h;
        gameDelay = d;
        unitToPixel = u;
        gameScore = 0;
        GamegameObjects = new ArrayList<>();
        GamePanel gp = new GamePanel(new Level(1));
        thePacMan.setDirection(Direction.NEUTRAL);
        return gp;
    }

    public void startGame() {
        this.gameState = GameState.RUNNING;
        Timer timer = new Timer(this.delay, this);
        timer.start();
    }

    public void gameOver() {
    }


    public int getPacGommeCounter() {
        int count = 0;
        for (int i = 0; i < this.gameSpace.length; i++)
            for (int j = 0; j < this.gameSpace[0].length; j++) {
                if (gameSpace[i][j] == PacGomme.ID) {
                    count++;
                }
            }
        return count;
    }

    public boolean hasEatenAllPacGomme() {
        return pacGommeCount == 0;
    }

    public void movePacman() {
        moveCharacterByOneStep(this.pacMan, pacMan.getDirection());
    }

    private void moveCharacterByOneStep(Character character, Direction direction) {
//        this.gameSpace[character.getPosition().x/unitSize][character.getPosition().y/unitSize] = 0;
        int moveByCases = (this.unitSize) * character.getSpeed();
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
//        this.gameSpace[character.getPosition().x/unitSize][character.getPosition().y/unitSize] = 2;

    }


    private void createLevel(int lvlNumber) throws IOException {
//        Level level = new Level(lvlNumber);
////        this.gamePanel = new GameFrame(level,this.screenWidth,this.heightWidth-20,this.unitSize);
//        this.gamePanel.setPreferredSize(new Dimension(screenWidth, heightWidth));
//        this.gamePanel.setBackground(Color.black);
//        this.gamePanel.setFocusable(true);
//        this.frame.setLocationRelativeTo(null);
//        this.frame.add(gamePanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.gameState.equals(GameState.RUNNING)) {
            checkCollisions();
            movePacman();
        }
        repaint();
    }

    //refractor
    public static void actionPerformed(){
        System.out.println("performing action");
        /*
        if (this.gameState.equals(GameState.RUNNING)) {
            checkCollisions();
            movePacman();
        }*/
    }

    private void checkCollisions() {
        checkPacManCollisions();
    }

    private void checkPacManCollisions() {
        int positionX = this.pacMan.getPosition().x / unitSize;
        int positionY = this.pacMan.getPosition().y / unitSize;
        Direction direction = this.pacMan.getDirection();
//        checking collisions with wall
        try {
            if (positionX == 0 && pacMan.getDirection().equals(Direction.LEFT)) {
                pacMan.setPosition(new Vector2((this.gameSpace[0].length - 1) * unitSize, this.pacMan.getPosition().y));
            } else if (positionX == (this.gameSpace[0].length - 1) && pacMan.getDirection().equals(Direction.RIGHT)) {
                pacMan.setPosition(new Vector2(0, this.pacMan.getPosition().y));
            }

            if (this.gameSpace[positionY][positionX] == PacGomme.ID) {
                this.gameSpace[positionY][positionX] = 0;
                this.score++;
                this.pacGommeCount--;
                if (hasEatenAllPacGomme()) {
                    System.out.println("YOU WON THE LEVEL ");
                }
                System.out.println("SCORE: " + score);

            }
            if (this.gameSpace[positionY][positionX] == SuperPacGomme.ID) {
                this.gameSpace[positionY][positionX] = 0;
                System.out.println("you eat super pacgomme");

            }
            if (direction.equals(Direction.DOWN) && this.gameSpace[positionY + 1][positionX] == Wall.ID
                    || direction.equals(Direction.UP) && this.gameSpace[positionY - 1][positionX] == Wall.ID
                    || direction.equals(Direction.LEFT) && this.gameSpace[positionY][positionX - 1] == Wall.ID
                    || direction.equals(Direction.RIGHT) && this.gameSpace[positionY][positionX + 1] == Wall.ID) {
                stopPacManMovement();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            //do nothing
        }

    }

    private void stopPacManMovement() {
        //si on a des animation on peut les arreter ici
        this.pacMan.setDirection(Direction.NEUTRAL);
    }

    public void draw(Graphics graphics) {
        drawGrid(graphics);
        if (this.gameState.equals(GameState.RUNNING)) {
            for (int i = 0; i < this.gameSpace.length; i++)
                for (int j = 0; j < this.gameSpace[0].length; j++) {
                    if (gameSpace[i][j] == Wall.ID) {
                        graphics.setColor(Color.BLUE);
                        graphics.fillRect(j * unitSize, i * unitSize, this.unitSize, this.unitSize);
                    }
                    if (gameSpace[i][j] == PacGomme.ID) {
                        graphics.setColor(Color.RED);
                        graphics.fillRect(j * unitSize + unitSize / 2 - 3, i * unitSize + unitSize / 2 - 3, this.unitSize / 6, this.unitSize / 6);
                    }
                    if (gameSpace[i][j] == SuperPacGomme.ID) {
                        graphics.setColor(Color.white);
                        graphics.fillOval(j * unitSize + unitSize / 3 - 3, i * unitSize + unitSize / 3 - 3, this.unitSize - unitSize / 2, this.unitSize - unitSize / 2);
                    }
                }
        }

        graphics.setColor(Color.yellow);
        graphics.fillOval(pacMan.getPosition().x, pacMan.getPosition().y, unitSize, unitSize);
    }

    public void drawGrid(Graphics graphics) {
        for (int i = 0; i < this.screenHeight / this.unitSize; i++) {
            graphics.drawLine(i * this.unitSize, 0, i * this.unitSize, this.screenHeight);
            graphics.drawLine(0, i * this.unitSize, this.screenWidth, i * this.unitSize);

        }
    }


    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }


    class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    pacMan.setDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    pacMan.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    pacMan.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    pacMan.setDirection(Direction.DOWN);
                    break;
            }
        }
//    }
    }
}

