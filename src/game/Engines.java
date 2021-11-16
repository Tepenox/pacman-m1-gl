package game;

import engines.CoreKernel;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;

public class Engines {

    public static void setSize(JFrame jFrame, int width, int height){
        CoreKernel.setSize(jFrame, width, height);
    }

    public static void setSize(JPanel jPanel, int width, int height){
        CoreKernel.setSize(jPanel, width, height);
    }

    public static void drawImage(JPanel jPanel, Graphics2D g2d, Image img, int x, int y,int widthAndHeight) {
        CoreKernel.drawImage(jPanel, g2d, img, x,y,widthAndHeight);
    }

    public static void fillRect(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        CoreKernel.fillRect(g2d, x, y, widthAndHeight, color);
    }

    public static void fillOval(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        CoreKernel.fillOval(g2d, x-widthAndHeight/2, y-widthAndHeight/2, widthAndHeight, color);
    }

    public static void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2){
        CoreKernel.drawLine(g2d,x1, y1, x2, y2);
    }

    public static double calculateDist(Vector2 from, Vector2 to) {
        return CoreKernel.calculateDist(from, to);
    }

    public static boolean checkCollision(int x1, int y1, int x2, int y2, int hitBoxSize1, int hitBoxSize2){
        return CoreKernel.checkCollision(x1, y1, x2, y2, hitBoxSize1, hitBoxSize2);
    }

    public static JFrame createFrame(JFrame frame, String gameTitle) {
        return CoreKernel.createFrame(frame, gameTitle);
    }
}
