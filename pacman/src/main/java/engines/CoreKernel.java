package engines;

import game.character.Character;
import utility.Direction;
import utility.GameObject;
import utility.Vector2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class CoreKernel {
    //List of all methods to call for each engine
    //Each method must be public

    //==================================================Graphic======================================

    public static JFrame createFrame(JFrame frame, String title){
        return GraphicEngine.createFrame(frame, title);
    }

    public static JPanel emptyPanel(JPanel jPanel, int width, int height) {
        return GraphicEngine.emptyPanel(jPanel,width,height);
    }

    public static JPanel panelWithImg(Image img, int width, int height){
        return GraphicEngine.panelWithImg(img,width,height);
    }

    public static JPanel panelWithLayout(JPanel jPanel, LayoutManager layout){
        return GraphicEngine.panelWithLayout(jPanel,layout);
    }

    public static void fillBorderLayout(JPanel objToFill, JComponent north, JComponent center, JComponent south, JComponent east, JComponent west){
        GraphicEngine.fillBorderLayout(objToFill, north, center, south, east, west);
    }

    public static void setBorder(JPanel jPanel, Border border){
        GraphicEngine.setBorder(jPanel,border);
    }

    public static void setSize(JFrame jFrame, int width, int height){
        GraphicEngine.setSize(jFrame, width, height);
    }

    public static void setSize(JPanel jPanel, int width, int height){
        GraphicEngine.setSize(jPanel, width, height);
    }

    public static JButton simpleButton(String str, Font font, Color foreground, Color backGround, ActionListener l){
        return GraphicEngine.simpleButton(str, font, foreground, backGround, l);
    }

    public static void drawImage(JComponent jPanel, Graphics2D g2d, Image img, int x, int y,int widthAndHeight) {
        GraphicEngine.drawImage(jPanel, g2d, img, x, y,widthAndHeight);
    }

    public static void drawGameObject(JComponent jPanel, Graphics2D g2d, GameObject go, int widthAndHeight) {
        GraphicEngine.drawGameObject(jPanel,g2d,go,widthAndHeight);
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

    public static void drawString(Graphics2D g2d, JComponent panel, String string, int x, int y, boolean isCentered, Font font, Color color){
        GraphicEngine.drawString(g2d, panel, string, x, y, isCentered, font, color);
    }

    //==================================================Physics======================================

    public static double calculateDist(Vector2 from, Vector2 to){
        return PhysicEngine.calculateDist(from,to);
    }


    public static boolean isInCollision(int x1, int y1,int x2,int y2, int width1, int height1,int width2, int height2){
        return PhysicEngine.isInCollision(x1,y1,x2,y2,width1,height1,width2,height2);
    }

    public static void move(GameObject gameObject, int x , int y){
        PhysicEngine.move(gameObject,x,y);
    }

    public static void moveGameObjectByOneStep(Character character, Direction direction, int step){
        PhysicEngine.moveGameObjectByOneStep(character,direction,step);
    }

    public static Vector2 getVectorFromDir(Direction direction, int amplitude){
        return PhysicEngine.getVectorFromDir(direction,amplitude);
    }

    public static Direction getDirFromVector(Vector2 vector){return PhysicEngine.getDirFromVector(vector);}


    //==================================================IAEngine======================================

    public static Direction getDirReducingDist(GameObject from, Vector2 to, List<Direction> directions, int step){
        return IAEngine.getDirReducingDist(from,to,directions,step);
    }

    public static Direction getDirIncreasingDist(GameObject from, Vector2 to, List<Direction> directions, int step){
        return IAEngine.getDirIncreasingDist(from,to,directions,step);
    }
}
