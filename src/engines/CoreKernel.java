package engines;

import game.utility.Vector2;

import javax.swing.*;
import java.awt.*;

public class CoreKernel {
    //List of all methods to call for each engine
    //Each method must be public

    //==================================================Graphic======================================

    public static JFrame createFrame(String title){
        return GraphicEngine.createFrame(title);
    }

    public static void setSize(JFrame jFrame, int width, int height){
        GraphicEngine.setSize(jFrame, width, height);
    }

    public static void setSize(JPanel jPanel, int width, int height){
        GraphicEngine.setSize(jPanel, width, height);
    }

    public static void drawImage(JPanel jPanel, Graphics2D g2d, Image img, Vector2 pos) {
        GraphicEngine.drawImage(jPanel, g2d, img, pos);
    }

    public static void fillRect(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        GraphicEngine.fillRect(g2d, x, y, widthAndHeight, color);
    }

    public static void fillOval(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        GraphicEngine.fillOval(g2d, x, y, widthAndHeight, color);
    }

    //==================================================Physics======================================

    public static double calculateDist(Vector2 from, Vector2 to){
        return PhysicEngine.calculateDist(from,to);
    }
}
