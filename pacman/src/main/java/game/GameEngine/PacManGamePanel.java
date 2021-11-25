package game.GameEngine;

import game.GameUtility.GameState;
import game.VisualDebugger;
import game.character.Ghosts.Ghost;
import game.object.PacGomme;
import game.object.PinkWall;
import game.object.SuperPacGomme;
import game.object.Wall;
import utility.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PacManGamePanel extends JPanel implements ActionListener {

    Image hearthImage = new ImageIcon(getClass().getResource("/heart.png")).getImage();
    private String messageMiddleScreen = "";
    private PacManKeyListener listener;


    public PacManGamePanel() {
        this.setPreferredSize(new Dimension(GameLogic.screenWidth, GameLogic.screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
    }

    public void addInputListener(){
        removeListener();
        listener = new PacManKeyListener();
        this.addKeyListener(listener);
    }

    public void removeListener(){
        if(listener != null){
            this.removeKeyListener(listener);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameLogic.actionPerformed();
        repaint();
    }

    public void setMessageMiddleScreen(String str) {
        this.messageMiddleScreen = str;
    }

    public void draw(Graphics graphics) {
        int unitSize = GameLogic.gameUnit;
        int[][] maze = GameLogic.lvl.getLevelArray();
        if(GameLogic.gameState.equals(GameState.RUNNING) || GameLogic.gameState.equals(GameState.STARTING) ){
            drawGrid(graphics);
            drawMaze(graphics, unitSize, maze);
            drawCharacters(graphics,unitSize);
            drawHearts(graphics);
            drawTimer(graphics);
            drawScore(graphics);
            drawLevelCounter(graphics);
            VisualDebugger.draw(this,graphics);
            drawMsg(graphics);
        }else if (GameLogic.gameState.equals(GameState.OVER)) {// TODO not using graphics engine  add methode text
            drawGameOver(graphics);
        }
    }

    private void drawMsg(Graphics graphics) {
        FontMetrics metrics1 = getFontMetrics(graphics.getFont());
        graphics.drawString(messageMiddleScreen,(GameLogic.screenWidth - (metrics1.stringWidth(messageMiddleScreen))) / 2, GameLogic.lvl.getFruitSpawn().y + 2* GameLogic.gameUnit/3);
    }


    private void drawGameOver(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Arial", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(graphics.getFont());
        graphics.drawString("Score: " + GameLogic.score, (GameLogic.screenWidth - metrics1.stringWidth("Score: " + GameLogic.score)) / 2, graphics.getFont().getSize());
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over", (GameLogic.screenWidth - metrics2.stringWidth("Game Over")) / 2, GameLogic.screenHeight/ 2);
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 30));
        FontMetrics metrics3 = getFontMetrics(graphics.getFont());
        graphics.drawString("Press Space to restart ", (GameLogic.screenWidth - metrics3.stringWidth("Press Space to restart")) / 2, GameLogic.screenHeight/ 2 + metrics2.getHeight()/2);
        graphics.setFont(new Font("Arial", Font.BOLD, 30));
        FontMetrics metrics4 = getFontMetrics(graphics.getFont());
        graphics.drawString("Press Enter to return to menu", (GameLogic.screenWidth-metrics4.stringWidth("Press Enter to return to menu"))/2, 2*GameLogic.screenHeight/ 3);

    }

    private void drawMaze(Graphics graphics, int unitSize, int[][] maze) {
        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == Wall.ID)
                    Engines.fillRect((Graphics2D)graphics,j* unitSize,i* unitSize, unitSize,Color.BLUE);
                if (maze[i][j] == PinkWall.ID)
                    Engines.fillRect((Graphics2D)graphics,j* unitSize,i* unitSize, unitSize,Color.PINK);
                if (maze[i][j] == PacGomme.ID)
                    Engines.fillOval((Graphics2D)graphics,j* unitSize +(unitSize /2),i* unitSize +(unitSize /2), unitSize /6,Color.WHITE);
                if (maze[i][j] == SuperPacGomme.ID)
                    Engines.fillOval((Graphics2D)graphics,j* unitSize +(unitSize /2),i* unitSize +(unitSize /2), unitSize /2,Color.WHITE);
            }
    }

    public void drawCharacters(Graphics graphics,int unitSize){
        Engines.drawGameObject(this,(Graphics2D) graphics, GameLogic.pacMan,unitSize);
        for (Ghost ghost : GameLogic.ghosts)
            Engines.drawGameObject(this,(Graphics2D) graphics,ghost,unitSize);
    }

    public void drawGrid(Graphics graphics) {
        int screenWidth = GameLogic.screenWidth;
        int screenHeight = GameLogic.screenHeight;
        int unitSize = GameLogic.gameUnit;
        for (int i = 0; i < screenHeight / unitSize; i++) {
            Engines.drawLine((Graphics2D) graphics, i * unitSize,0,i * unitSize, screenHeight);
            Engines.drawLine((Graphics2D) graphics, 0,i * unitSize,screenWidth, i * unitSize);
        }
    }

    public void drawHearts(Graphics graphics){ // todo maybe draw only when pacman dies
        int heartCount = GameLogic.pacMan.getLives();
        for (int i = 0 ; i < heartCount; i++){
            Engines.drawImage(this,(Graphics2D) graphics, hearthImage,  i * GameLogic.gameUnit, GameLogic.screenHeight - 2* GameLogic.gameUnit, GameLogic.gameUnit );
        }
    }

    public void drawTimer (Graphics graphics){
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics metrics1 = getFontMetrics(graphics.getFont());
        long endTime= System.currentTimeMillis();
        long time = (endTime - GameLogic.startTime);
        int minutes = (int)time / (60 * 1000);
        int seconds = ((int)time / 1000) % 60;
        String str = String.format("%d:%02d", minutes, seconds);
        graphics.drawString ( str, GameLogic.screenWidth - metrics1.stringWidth(str) - 3* GameLogic.gameUnit, GameLogic.screenHeight - 5* GameLogic.gameUnit/4 );
    }

    public void drawScore(Graphics graphics){
        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("score:" + GameLogic.score, GameLogic.screenWidth-(GameLogic.screenWidth/2 - metrics.stringWidth("score:"+ GameLogic.score)), GameLogic.screenHeight - 5* GameLogic.gameUnit/4);
    }

    public void drawLevelCounter(Graphics graphics){
        graphics.setColor(Color.yellow);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("lvl:" + GameLogic.lvl.getLevelNumber(), GameLogic.screenWidth - metrics.stringWidth("lvl:" + GameLogic.lvl.getLevelNumber()) - GameLogic.gameUnit , GameLogic.screenHeight - 5* GameLogic.gameUnit/4);
    }


    static class PacManKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> GameLogic.setPacmanDir(Direction.LEFT);
                case KeyEvent.VK_RIGHT -> GameLogic.setPacmanDir(Direction.RIGHT);
                case KeyEvent.VK_UP -> GameLogic.setPacmanDir(Direction.UP);
                case KeyEvent.VK_DOWN -> GameLogic.setPacmanDir(Direction.DOWN);
                case KeyEvent.VK_SPACE -> {
                    if (GameLogic.gameState.equals(GameState.OVER)) {
                        GameLogic.getMenuLogic().startGame(1,0);
                    }}
                case KeyEvent.VK_ENTER -> {
                    if (GameLogic.gameState.equals(GameState.OVER)) {
                        GameLogic.getMenuLogic().levelEnded(GameLogic.lvl.getLevelNumber(),false,0);
                    }}
                case KeyEvent.VK_M -> {
                    GameLogic.wonLevel();
                }
                case KeyEvent.VK_L -> {
                    GameLogic.pacMan.useLife();
                }
            }
        }
    }

}