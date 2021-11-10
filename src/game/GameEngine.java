package game;

import engines.CoreKernel;
import engines.GraphicEngine;
import game.levels.Level;
import game.utility.GameState;
import game.utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private final JFrame frame;
    private JPanel gamePanel;

    final int screenWidth;
    final int heightWidth;
    final int unitSize;
    final int gameUnits;
    final int delay;
    final int[][] gameSpace;
    List<GameObject> gameObjects;
    GameState gameState = GameState.PAUSED;
    Timer timer;

    public GameEngine(String gameTitle, int width, int height,int speed,int unitSize) {
        this.gamePanel = null;
        this.frame = Engines.createFrame(gameTitle);
        Engines.setSize(this.frame, width, height);
        this.screenWidth = width;
        this.heightWidth = height;
        this.unitSize = unitSize;
        this.gameUnits = (screenWidth * heightWidth) / (this.unitSize * this.unitSize);
        this.delay = speed;
        this.gameSpace = new int[gameUnits][gameUnits];
        this.gameObjects = new ArrayList<>();

    }

    public static void main(String[] args){
        GameEngine gameEngine = new GameEngine("Pacman",1024,1024,6,24);
        try {
            gameEngine.createLevel(3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createLevel(int lvlNumber) throws IOException {
        Level level = new Level(lvlNumber);
        this.gamePanel = new GamePanel(level,this.screenWidth,this.heightWidth-20,this.unitSize);
        this.gamePanel.setPreferredSize(new Dimension(screenWidth, heightWidth));
        this.gamePanel.setBackground(Color.black);
        this.gamePanel.setFocusable(true);
        this.frame.setLocationRelativeTo(null);
        this.frame.add(gamePanel);
    }

}
