package game;

import engines.GraphicEngine;
import game.GameUtility.GameState;
import game.character.Ghosts.Ghost;
import game.object.PacGomme;
import game.object.SuperPacGomme;
import game.object.Wall;
import utility.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {

    Image heatImage = new ImageIcon(getClass().getResource("/heart.png")).getImage();


    public GamePanel() {
        this.setPreferredSize(new Dimension(PacManGame.screenWidth, PacManGame.screenHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        PacManGame.actionPerformed();
        repaint();
    }

    public void draw(Graphics graphics) {
        int unitSize = PacManGame.gameUnit;
        int[][] maze = PacManGame.lvl.getLevelArray();
        if(PacManGame.gameState.equals(GameState.RUNNING)){
            drawGrid(graphics);
            drawMaze(graphics, unitSize, maze);
            drawCharacters(graphics,unitSize);
            drawHearts(graphics);
            drawTimer(graphics);
        }else if (PacManGame.gameState.equals(GameState.OVER)) {// TODO not using graphics engine  add methode text
            graphics.setColor(Color.red);
            graphics.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics metrics1 = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + PacManGame.score, (PacManGame.screenWidth - metrics1.stringWidth("Score: " + PacManGame.score)) / 2, graphics.getFont().getSize());
            graphics.setColor(Color.red);
            graphics.setFont(new Font("Ink Free", Font.BOLD, 75));
            FontMetrics metrics2 = getFontMetrics(graphics.getFont());
            graphics.drawString("Game Over", (PacManGame.screenWidth - metrics2.stringWidth("Game Over")) / 2, PacManGame.screenHeight/ 2);
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 30));
            FontMetrics metrics3 = getFontMetrics(graphics.getFont());
            graphics.drawString("Press Space to restart", (PacManGame.screenWidth - metrics3.stringWidth("Press Space to restart")) / 2, PacManGame.screenHeight/ 2 + metrics2.getHeight()/2);
        }

//        VisualDebugger.draw(this,graphics);
    }

    private void drawMaze(Graphics graphics, int unitSize, int[][] maze) {
        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == Wall.ID)
                    Engines.fillRect((Graphics2D)graphics,j* unitSize,i* unitSize, unitSize,Color.BLUE);
                if (maze[i][j] == PacGomme.ID)
                    Engines.fillOval((Graphics2D)graphics,j* unitSize +(unitSize /2),i* unitSize +(unitSize /2), unitSize /6,Color.WHITE);
                if (maze[i][j] == SuperPacGomme.ID)
                    Engines.fillOval((Graphics2D)graphics,j* unitSize +(unitSize /2),i* unitSize +(unitSize /2), unitSize /2,Color.WHITE);
            }
    }

    public void drawCharacters(Graphics graphics,int unitSize){
        Engines.drawGameObject(this,(Graphics2D) graphics,PacManGame.pacMan,unitSize);
        for (Ghost ghost : PacManGame.ghosts)
            Engines.drawGameObject(this,(Graphics2D) graphics,ghost,unitSize);
    }

    public void drawGrid(Graphics graphics) {
        int screenWidth = PacManGame.screenWidth;
        int screenHeight = PacManGame.screenHeight;
        int unitSize = PacManGame.gameUnit;
        for (int i = 0; i < screenHeight / unitSize; i++) {
            Engines.drawLine((Graphics2D) graphics, i * unitSize,0,i * unitSize, screenHeight);
            Engines.drawLine((Graphics2D) graphics, 0,i * unitSize,screenWidth, i * unitSize);
        }
    }

    public void drawHearts(Graphics graphics){ // todo maybe draw only when pacman dies
        int heartCount = PacManGame.pacMan.getLives();
        for (int i = 0 ; i < heartCount; i++){
            Engines.drawImage(this,(Graphics2D) graphics, heatImage,  i * PacManGame.gameUnit,PacManGame.screenHeight - PacManGame.gameUnit,PacManGame.gameUnit );
        }
    }

    public void drawTimer (Graphics graphics){
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Arial", Font.BOLD, 20));
        FontMetrics metrics1 = getFontMetrics(graphics.getFont());
        long endTime= System.currentTimeMillis();
        long time = (endTime - PacManGame.startTime);
        int minutes = (int)time / (60 * 1000);
        int seconds = ((int)time / 1000) % 60;
        String str = String.format("%d:%02d", minutes, seconds);
        graphics.drawString ( str, (PacManGame.screenWidth - metrics1.stringWidth(str)) / 2, PacManGame.screenHeight - PacManGame.gameUnit/4 );
    }


    class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT -> PacManGame.setPacmanDir(Direction.LEFT);
                case KeyEvent.VK_RIGHT -> PacManGame.setPacmanDir(Direction.RIGHT);
                case KeyEvent.VK_UP -> PacManGame.setPacmanDir(Direction.UP);
                case KeyEvent.VK_DOWN -> PacManGame.setPacmanDir(Direction.DOWN);
                case KeyEvent.VK_SPACE -> {
                    if (PacManGame.gameState.equals(GameState.OVER)) {
                        PacManGame.startTheGame();
                    }}
            }
        }
//    }
    }

}