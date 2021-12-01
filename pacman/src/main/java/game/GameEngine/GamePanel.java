package game.GameEngine;

import game.GameUtility.GameState;
import game.GameUtility.VisualDebugger;
import game.GameObject.character.Ghosts.Ghost;
import game.GameObject.object.PacGomme;
import game.GameObject.object.PinkWall;
import game.GameObject.object.SuperPacGomme;
import game.GameObject.object.Wall;
import utility.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GamePanel extends JPanel {

    Image hearthImage = new ImageIcon(getClass().getResource("/heart.png")).getImage();
    private String messageMiddleScreen = "";
    private PacManKeyListener listener;
    private Color[] colors = {
            new Color(0, 60, 255),
            new Color(129, 147, 10),
            new Color(137, 14, 159),
            new Color(91, 150, 74),
            new Color(27, 143, 3),
            new Color(164, 10, 10),
            new Color(99, 154, 2),
            new Color(1, 40, 217),};


    public GamePanel() {
        EnginesCaller.emptyPanel(this,GameLogic.screenWidth,GameLogic.screenHeight);
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

    public void actionPerformed(ActionEvent e) {
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
            drawFruit(graphics);
            //VisualDebugger.draw(this,graphics);
            drawMsg(graphics);
        }else if (GameLogic.gameState.equals(GameState.OVER)) {// TODO not using graphics engine  add methode text
            drawGameOver(graphics);
        }
    }

    private void drawMsg(Graphics graphics) {
        int x = GameLogic.lvl.getFruitSpawn().x;
        int y = GameLogic.lvl.getFruitSpawn().y + ((2 * GameLogic.gameUnit)/3);
        EnginesCaller.drawString((Graphics2D) graphics,this,messageMiddleScreen,x,y,false,new Font("Arial", Font.BOLD, 20),Color.yellow);
    }


    private void drawGameOver(Graphics graphics) {
        EnginesCaller.drawString((Graphics2D) graphics,this,"Score: " + GameLogic.score,GameLogic.screenWidth / 2,60,true,new Font("Arial", Font.BOLD, 40),Color.red);
        EnginesCaller.drawString((Graphics2D) graphics,this,"Game Over",GameLogic.screenWidth / 2,GameLogic.screenHeight/ 2,true,new Font("Ink Free", Font.BOLD, 75),Color.red);
        EnginesCaller.drawString((Graphics2D) graphics,this,"Press Space to restart ",GameLogic.screenWidth / 2,(GameLogic.screenHeight/ 2) + (GameLogic.gameUnit*2),true,new Font("Arial", Font.BOLD, 30),Color.white);
        EnginesCaller.drawString((Graphics2D) graphics,this,"Press Enter to return to menu",GameLogic.screenWidth / 2,((2*GameLogic.screenHeight)/ 3),true,new Font("Arial", Font.BOLD, 30),Color.white);

    }

    private void drawMaze(Graphics graphics, int unitSize, int[][] maze) {
        Color color = colors[GameLogic.lvl.getLevelNumber()-1];
        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == Wall.ID)
                    EnginesCaller.fillRect((Graphics2D)graphics,j* unitSize,i* unitSize, unitSize,color);
                if (maze[i][j] == PinkWall.ID)
                    EnginesCaller.fillRect((Graphics2D)graphics,j* unitSize,i* unitSize, unitSize,Color.PINK);
                if (maze[i][j] == PacGomme.ID)
                    EnginesCaller.fillOval((Graphics2D)graphics,j* unitSize +(unitSize /2),i* unitSize +(unitSize /2), unitSize /6,Color.WHITE);
                if (maze[i][j] == SuperPacGomme.ID)
                    EnginesCaller.fillOval((Graphics2D)graphics,j* unitSize +(unitSize /2),i* unitSize +(unitSize /2), unitSize /2,Color.WHITE);
            }
    }

    public void drawCharacters(Graphics graphics,int unitSize){
        EnginesCaller.drawGameObject(this,(Graphics2D) graphics, GameLogic.pacMan,unitSize);
        for (Ghost ghost : GameLogic.ghosts)
            EnginesCaller.drawGameObject(this,(Graphics2D) graphics,ghost,unitSize);
    }

    public void drawGrid(Graphics graphics) {
        int screenWidth = GameLogic.screenWidth;
        int screenHeight = GameLogic.screenHeight;
        int unitSize = GameLogic.gameUnit;
        for (int i = 0; i < screenHeight / unitSize; i++) {
            EnginesCaller.drawLine((Graphics2D) graphics, i * unitSize,0,i * unitSize, screenHeight);
            EnginesCaller.drawLine((Graphics2D) graphics, 0,i * unitSize,screenWidth, i * unitSize);
        }
    }

    public void drawHearts(Graphics graphics){ // todo maybe draw only when pacman dies
        int heartCount = GameLogic.pacMan.getLives();
        for (int i = 0 ; i < heartCount; i++){
            EnginesCaller.drawImage(this,(Graphics2D) graphics, hearthImage,  i * GameLogic.gameUnit, GameLogic.screenHeight - 2* GameLogic.gameUnit, GameLogic.gameUnit );
        }
    }

    public void drawTimer (Graphics graphics){
        long endTime= System.currentTimeMillis();
        long time = (endTime - GameLogic.startTime);
        int minutes = (int)time / (60 * 1000);
        int seconds = ((int)time / 1000) % 60;
        String str = String.format("%d:%02d", minutes, seconds);
        int x = GameLogic.screenWidth - 3 * GameLogic.gameUnit;
        int y = GameLogic.screenHeight - 5* GameLogic.gameUnit/4;
        EnginesCaller.drawString((Graphics2D) graphics,this,str,x,y,true,new Font("Arial", Font.BOLD, 20),Color.red);
    }

    public void drawScore(Graphics graphics){
        int x = GameLogic.screenWidth - GameLogic.screenWidth/2;
        int y = GameLogic.screenHeight - 5* GameLogic.gameUnit/4;
        EnginesCaller.drawString((Graphics2D) graphics,this,"score:" + GameLogic.score,x,y,true,new Font("Arial", Font.BOLD, 20),Color.WHITE);
    }

    public void drawLevelCounter(Graphics graphics){
        int x = GameLogic.screenWidth - GameLogic.gameUnit ;
        int y = GameLogic.screenHeight - 5* GameLogic.gameUnit/4;
        EnginesCaller.drawString((Graphics2D) graphics,this,"lvl:" + GameLogic.lvl.getLevelNumber(),x,y,true,new Font("Arial", Font.BOLD, 20),Color.yellow);
    }

    public void drawFruit(Graphics graphics){
        if (GameLogic.fruit != null){
            EnginesCaller.drawImage(this,(Graphics2D) graphics,GameLogic.fruit.sprite,GameLogic.fruit.getPosition().x,GameLogic.fruit.getPosition().y,GameLogic.gameUnit);
        }
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
                        GameLogic.menuLogic.startGame(1,0);
                    }}
                case KeyEvent.VK_ENTER -> {
                    if (GameLogic.gameState.equals(GameState.OVER)) {
                        GameLogic.menuLogic.levelEnded(GameLogic.lvl.getLevelNumber(),false,0);
                    }}
                case KeyEvent.VK_M -> GameLogic.wonLevel();
                case KeyEvent.VK_L -> GameLogic.pacMan.useLife();
            }
        }
    }

}