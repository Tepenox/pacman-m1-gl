package game;

import game.character.Character;
import game.character.PacMan;
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

public class PacManGame extends JPanel implements ActionListener {
    final int screenWidth;
    final int screenHeight;
    final int unitSize;
    final int gameUnits;
    final int delay;
    final int[][] gameSpace;
    List<GameObject> gameObjects;
    PacMan pacMan;
    GameState gameState = GameState.PAUSED;

    public PacManGame(int width, int height, int delay, int unitSize) {
//        Engines.createFrame(gameTitle);
//        Engines.setSize(this.frame, width, height);
        this.screenWidth = width;
        this.screenHeight = height;
        this.unitSize = unitSize;
        this.gameUnits = (screenWidth * screenHeight) / (this.unitSize * this.unitSize);
        this.delay = delay;
        this.gameObjects = new ArrayList<>();
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.pacMan = new PacMan(null, new Vector2(14 * unitSize, 20 * unitSize));
        this.gameSpace = new int[][]{
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
        };
        this.pacMan.setDirection(Direction.UP);
//        this.addKeyListener(new MyKeyAdapter());
        startGame();


    }

    public void startGame() {
        this.gameState = GameState.RUNNING;
        Timer timer = new Timer(this.delay, this);
        timer.start();
    }

    public void gameOver() {
    }

    public void movePacman() {
        moveCharacterByOneStep(this.pacMan, pacMan.getDirection());
    }

    private void moveCharacterByOneStep(Character character, Direction direction) {
//        this.gameSpace[character.getPosition().x/unitSize][character.getPosition().y/unitSize] = 0;
        int moveByCases = this.unitSize * character.getSpeed();
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

    private void checkCollisions() {
        checkPacManCollisions();
    }

    private void checkPacManCollisions() {
        int positionX = this.pacMan.getPosition().x/unitSize ;
        int positionY = this.pacMan.getPosition().y /unitSize;
        Direction direction = this.pacMan.getDirection();
//        checking collisions with wall
        try{
            System.out.println(direction);
            if (direction.equals(Direction.RIGHT) && this.gameSpace[positionX + 1][positionY] == Wall.ID
                    || direction.equals(Direction.LEFT) && this.gameSpace[positionX - 1][positionY] == Wall.ID
                    || direction.equals(Direction.DOWN) && this.gameSpace[positionX][positionY + 1] == Wall.ID
                    || direction.equals(Direction.UP) && this.gameSpace[positionX][positionY - 1] == Wall.ID) {
                stopPacManMovement();
            }

        }catch (ArrayIndexOutOfBoundsException e){
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
                        graphics.fillRect(i * unitSize, j * unitSize, this.unitSize, this.unitSize);
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

