package game;

import game.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {

    protected final Level level;
    protected final int unitSize;
    private final int widthOffset;
    private final int heightOffset;

    public GamePanel(Level level, int width, int height, int unitSize){
        this.level = level;
        this.widthOffset = (width - level.getMazeArray().length*unitSize)/2;
        this.heightOffset = (width - level.getMazeArray()[0].length*unitSize)/2;
        Engines.setSize(this,width,height);
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
            Engines.fillRect((Graphics2D) g,this.widthOffset+i*24,this.heightOffset+j*24,24,Color.blue);
        else if(value == 1)
            Engines.fillOval((Graphics2D) g,this.widthOffset+i*24,this.heightOffset+j*24,24,Color.white);
        else Engines.fillRect((Graphics2D) g,this.widthOffset+i*24,this.heightOffset+j*24,24,Color.black);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}
