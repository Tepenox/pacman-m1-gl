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
                if(maze[j][i]==0)
                    GameEngine.fillRect((Graphics2D) g,i*24,j*24,24,Color.blue);
                else GameEngine.fillOval((Graphics2D) g,i*24,j*24,24,Color.white);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
