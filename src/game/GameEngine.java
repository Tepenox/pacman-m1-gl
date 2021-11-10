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
        this.frame = CoreKernel.createFrame(gameTitle);
        CoreKernel.setSize(this.frame, width, height);
        this.screenWidth = width;
        this.heightWidth = height;
        this.unitSize = unitSize;
        this.gameUnits = (screenWidth * heightWidth) / (this.unitSize * this.unitSize);
        this.delay = speed;
        this.gameSpace = new int[gameUnits][gameUnits];
        this.gameObjects = new ArrayList<>();

    }

    public static void main(String[] args){
        GameEngine gameEngine = new GameEngine("Pacman",480,320,6,24);
        try {
            gameEngine.createLevel(1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createLevel(int level) throws IOException {
        Level level1 = new Level(level);
        this.gamePanel = new GamePanel(level1,this.screenWidth,this.heightWidth-20,this.unitSize);
        this.gamePanel.setPreferredSize(new Dimension(screenWidth, heightWidth));
        this.gamePanel.setBackground(Color.gray);
        this.gamePanel.setFocusable(true);
        this.frame.add(gamePanel);
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

    public static void fillRect(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        CoreKernel.fillRect(g2d, x, y, widthAndHeight, color);
    }

    public static void fillOval(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        CoreKernel.fillOval(g2d, x, y, widthAndHeight, color);
    }

    public static double calculateDist(Vector2 from, Vector2 to) {
        return CoreKernel.calculateDist(from, to);
    }
}
