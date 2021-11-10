package game;

import game.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GamePanel extends JPanel implements ActionListener {

    protected final Level level;
    protected final int unitSize;

    public GamePanel(Level level, int width, int height, int unitSize){
        this.level = level;
        GameEngine.setSize(this,width,height);
        this.unitSize = unitSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int[][] maze = level.getMazeArray();
        for (int j = 0; j < maze.length; j++) {
            for (int i = 0; i < maze[0].length; i++) {
                drawCase(g,j,i,maze[j][i]);
            }
        }
    }

    private void drawCase(Graphics g, int j, int i, int value) {
        if(value == 0)
            GameEngine.fillRect((Graphics2D) g,i*24,j*24,24,Color.blue);
        else if(value == 1)
            GameEngine.fillOval((Graphics2D) g,i*24,j*24,24,Color.white);
        else GameEngine.fillRect((Graphics2D) g,i*24,j*24,24,Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
