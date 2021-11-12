package game;

import game.object.PacGomme;
import game.object.SuperPacGomme;
import game.object.Wall;
import game.utility.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {

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
        drawGrid(graphics);
        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == Wall.ID) {
                    graphics.setColor(Color.BLUE);
                    graphics.fillRect(j * unitSize, i * unitSize, unitSize, unitSize);
                }
                if (maze[i][j] == PacGomme.ID) {
                    graphics.setColor(Color.RED);
                    graphics.fillRect(j * unitSize + unitSize / 2 - 3, i * unitSize + unitSize / 2 - 3, unitSize / 6, unitSize / 6);
                }
                if (maze[i][j] == SuperPacGomme.ID) {
                    graphics.setColor(Color.white);
                    graphics.fillOval(j * unitSize + unitSize / 3 - 3, i * unitSize + unitSize / 3 - 3, unitSize - unitSize / 2, unitSize - unitSize / 2);
                }
            }

        graphics.setColor(Color.yellow);
        graphics.fillOval(PacManGame.pacMan.getPosition().x, PacManGame.pacMan.getPosition().y, unitSize, unitSize);
    }

    public void drawGrid(Graphics graphics) {
        int screenWidth = PacManGame.screenWidth;
        int screenHeight = PacManGame.screenHeight;
        int unitSize = PacManGame.gameUnit;
        for (int i = 0; i < screenHeight / unitSize; i++) {
            graphics.drawLine(i * unitSize, 0, i * unitSize, screenHeight);
            graphics.drawLine(0, i * unitSize, screenWidth, i * unitSize);

        }
    }

    class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    PacManGame.pacMan.setDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    PacManGame.pacMan.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    PacManGame.pacMan.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    PacManGame.pacMan.setDirection(Direction.DOWN);
                    break;
            }
        }
//    }
    }

}