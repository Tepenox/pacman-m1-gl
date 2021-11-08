package game;

import engines.CoreKernel;
import game.utility.GameState;
import game.utility.Vector2;

import javax.swing.*;
import java.awt.*;
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

    }
}
