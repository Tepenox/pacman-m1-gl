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
    private final Panel gamePanel;

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
        this.gamePanel = new Panel();
        this.frame = CoreKernel.createFrame(gameTitle);
        CoreKernel.setSize(this.frame, width, height);
        this.screenWidth = width;
        this.heightWidth = height;
        this.unitSize = unitSize;
        this.gameUnits = (screenWidth * heightWidth) / (this.unitSize * this.unitSize);
        this.delay = speed;
        gamePanel.setPreferredSize(new Dimension(screenWidth, heightWidth));
        this.gameSpace = new int[gameUnits][gameUnits];
        gamePanel.setBackground(Color.black);
        gamePanel.setFocusable(true);
        frame.add(gamePanel);
        this.gameObjects = new ArrayList<>();

    }

    public static void main(String[] args) {

        try {
            Level level = new Level(1);
            Level level2 = new Level(2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setSize(JFrame jFrame, int width, int height){
        CoreKernel.setSize(jFrame, width, height);
    }

    public static void setSize(JPanel jPanel, int width, int height){
        CoreKernel.setSize(jPanel, width, height);
    }

    public static void drawImage(JPanel jPanel, Graphics2D g2d, Image img, Vector2 pos) {
        CoreKernel.drawImage(jPanel, g2d, img, pos);
    }

    public static double calculateDist(Vector2 from, Vector2 to) {
        return CoreKernel.calculateDist(from, to);
    }
}
