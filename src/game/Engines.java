package game;

import engines.CoreKernel;
import game.utility.Vector2;

import javax.swing.*;
import java.awt.*;

public class Engines {

    public static void setSize(JFrame jFrame, int width, int height){
        CoreKernel.setSize(jFrame, width, height);
    }

    public static void setSize(JPanel jPanel, int width, int height){
        CoreKernel.setSize(jPanel, width, height);
    }

    public static void drawImage(JPanel jPanel, Graphics2D g2d, Image img, Vector2 pos) {
        CoreKernel.drawImage(jPanel, g2d, img, pos);
    }

    public static void fillRect(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        CoreKernel.fillRect(g2d, x, y, widthAndHeight, color);
    }

    public static void fillOval(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        CoreKernel.fillOval(g2d, x+10, y+10, 5, color);
    }

    public static double calculateDist(Vector2 from, Vector2 to) {
        return CoreKernel.calculateDist(from, to);
    }

    public static JFrame createFrame(String gameTitle) {
        return CoreKernel.createFrame(gameTitle);
    }
}
