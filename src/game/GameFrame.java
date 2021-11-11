package game;

import game.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {


//    protected final Level level;
//    protected final int unitSize;
//    private final int widthOffset;
//    private final int heightOffset;

    public GameFrame(JPanel gamePanel){
        this.add(gamePanel);
        this.setTitle("PacMan");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
//        this.level = level;
//        this.widthOffset = (width - level.getLevelArray().length*unitSize)/2;
//        this.heightOffset = (width - level.getLevelArray()[0].length*unitSize)/2;
//        Engines.setSize(this,width,height);
//        this.unitSize = unitSize;
    }
//
public static void main(String[] args) {
    new GameFrame(new PacManGame(630,700,75,30));
}
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//        int[][] maze = level.getLevelArray();
//        for (int j = 0; j < maze.length; j++) {
//            for (int i = 0; i < maze[0].length; i++) {
//                drawCase(g,j,i,maze[j][i]);
//            }
//        }
//    }
//
//    private void drawCase(Graphics g, int j, int i, int value) {
//        if(value == 0)
//            Engines.fillRect((Graphics2D) g,this.widthOffset+i*24,this.heightOffset+j*24,24,Color.blue);
//        else if(value == 1)
//            Engines.fillOval((Graphics2D) g,this.widthOffset+i*24,this.heightOffset+j*24,24,Color.white);
//        else Engines.fillRect((Graphics2D) g,this.widthOffset+i*24,this.heightOffset+j*24,24,Color.black);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        repaint();
//    }
}
