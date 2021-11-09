package engines;

import game.utility.Vector2;

import javax.swing.*;
import java.awt.*;

public class GraphicEngine {


    protected static JFrame createFrame(String title){
        JFrame jFrame = new JFrame();
        jFrame.setTitle(title);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        return jFrame;
    }

    protected static void setSize(JFrame jFrame, int width, int height){
        jFrame.setSize(width, height);
    }

    protected static void setSize(JPanel jPanel, int width, int height){
        jPanel.setSize(width, height);
    }

    protected static void drawImage(JPanel jPanel, Graphics2D g2d, Image img, Vector2 pos) {
        g2d.drawImage(img, pos.x, pos.y, jPanel);
    }

    protected static void fillRect(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        g2d.setColor(color);
        g2d.fillRect(x, y, widthAndHeight, widthAndHeight);
    }

    protected static void fillOval(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        g2d.setColor(color);
        g2d.fillOval(x, y, widthAndHeight, widthAndHeight);
    }

}
