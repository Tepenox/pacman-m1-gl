package game;

import game.levels.Level;
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

    private Level lvl;

    public GamePanel(Level lvl) {
        this.lvl = lvl;
        this.setPreferredSize(new Dimension(PacManGame.width, PacManGame.height));
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
        int unitSize = PacManGame.unitToPixel;
        int[][] maze = lvl.getLevelArray();
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
        graphics.fillOval(PacManGame.thePacMan.getPosition().x, PacManGame.thePacMan.getPosition().y, unitSize, unitSize);
    }

    public void drawGrid(Graphics graphics) {
        int screenWidth = PacManGame.width;
        int screenHeight = PacManGame.height;
        int unitSize = PacManGame.unitToPixel;
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
                    PacManGame.thePacMan.setDirection(Direction.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    PacManGame.thePacMan.setDirection(Direction.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    PacManGame.thePacMan.setDirection(Direction.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    PacManGame.thePacMan.setDirection(Direction.DOWN);
                    break;
            }
        }
//    }
    }

}