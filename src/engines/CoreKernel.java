package engines;

import utility.Vector2;

import javax.swing.*;
import java.awt.*;

public class CoreKernel {
    //List of all methods to call for each engine
    //Each method must be public

    //==================================================Graphic======================================

    public static JFrame createFrame(JFrame frame, String title){
        return GraphicEngine.createFrame(frame, title);
    }

    public static void setSize(JFrame jFrame, int width, int height){
        GraphicEngine.setSize(jFrame, width, height);
    }

    public static void setSize(JPanel jPanel, int width, int height){
        GraphicEngine.setSize(jPanel, width, height);
    }

    public static void drawImage(JPanel jPanel, Graphics2D g2d, Image img, int x, int y,int widthAndHeight) {
        GraphicEngine.drawImage(jPanel, g2d, img, x, y,widthAndHeight);
    }

    public static void fillRect(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        GraphicEngine.fillRect(g2d, x, y, widthAndHeight, color);
    }

    public static void fillOval(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        GraphicEngine.fillOval(g2d, x, y, widthAndHeight, color);
    }

    public static void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2){
        GraphicEngine.drawLine(g2d,x1, y1, x2, y2);
    }

    //==================================================Physics======================================

    public static double calculateDist(Vector2 from, Vector2 to){
        return PhysicEngine.calculateDist(from,to);
    }

    public static boolean checkCollision(int x1, int y1, int x2, int y2, int hitBoxSize1, int hitBoxSize2){
        return PhysicEngine.checkCollision(x1, y1, x2, y2, hitBoxSize1, hitBoxSize2);
    }
}
