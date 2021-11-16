package engines;

import utility.GameObject;

import javax.swing.*;
import java.awt.*;

public class GraphicEngine {


    protected static JFrame createFrame(JFrame frame, String title){
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    protected static void setSize(JFrame jFrame, int width, int height){
        jFrame.setSize(width, height);
    }

    protected static void setSize(JPanel jPanel, int width, int height){
        jPanel.setSize(width, height);
    }

    protected static void drawImage(JPanel jPanel, Graphics2D g2d, Image img, int x, int y,int widthAndHeight) {
        g2d.drawImage(img, x, y,widthAndHeight,widthAndHeight, jPanel);
    }

    protected static void drawGameObject(JPanel jPanel, Graphics2D g2d, GameObject go, int widthAndHeight) {
        g2d.drawImage(go.getSprite(), go.getPosition().x, go.getPosition().y,widthAndHeight,widthAndHeight, jPanel);
    }

    protected static void fillRect(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        g2d.setColor(color);
        g2d.fillRect(x, y, widthAndHeight, widthAndHeight);
    }

    protected static void fillOval(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        g2d.setColor(color);
        g2d.fillOval(x, y, widthAndHeight, widthAndHeight);
    }

    protected static void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2){
        g2d.drawLine(x1, y1, x2, y2);
    }

}
