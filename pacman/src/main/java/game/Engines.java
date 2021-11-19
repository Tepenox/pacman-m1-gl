package game;

import engines.CoreKernel;
import engines.GraphicEngine;
import engines.IAEngine;
import engines.PhysicEngine;
import game.character.Character;
import utility.Direction;
import utility.GameObject;
import utility.Vector2;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Engines {

    //==================================================Graphic======================================

    public static void setSize(JFrame jFrame, int width, int height){
        CoreKernel.setSize(jFrame, width, height);
    }

    public static void setSize(JPanel jPanel, int width, int height){
        CoreKernel.setSize(jPanel, width, height);
    }

    public static void drawImage(JPanel jPanel, Graphics2D g2d, Image img, int x, int y,int widthAndHeight) {
        CoreKernel.drawImage(jPanel, g2d, img, x,y,widthAndHeight);
    }

    public static void drawGameObject(JPanel jPanel, Graphics2D g2d, GameObject go, int widthAndHeight) {
        CoreKernel.drawGameObject(jPanel,g2d,go,widthAndHeight);
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

    public static JFrame createFrame(JFrame frame, String gameTitle) {
        return CoreKernel.createFrame(frame, gameTitle);
    }

    //==================================================Physics======================================

    public static double calculateDist(Vector2 from, Vector2 to){
        return CoreKernel.calculateDist(from,to);
    }

    public static boolean willCollide(int cord1, int cord2, int size1, int size2, int step){
        return CoreKernel.willCollide(cord1,cord2,size1,size2,step);
    }

    public static boolean isInCollision(int x1, int y1,int x2,int y2, int width1, int height1,int width2, int height2){
        return CoreKernel.isInCollision(x1,y1,x2,y2,width1,height1,width2,height2);
    }

    public static void move(GameObject gameObject, int x , int y){
        CoreKernel.move(gameObject,x,y);
    }

    public static void moveGameObjectByOneStep(Character character, Direction direction, int step){
        CoreKernel.moveGameObjectByOneStep(character,direction,step);
    }

    public static Vector2 getVectorFromDir(Direction direction, int amplitude){
        return CoreKernel.getVectorFromDir(direction,amplitude);
    }

    public static Direction getDirFromVector(Vector2 vector){return CoreKernel.getDirFromVector(vector);}


    //==================================================IAEngine======================================

    public static Direction getDirReducingDist(GameObject from, Vector2 to, java.util.List<Direction> directions, int step){
        return CoreKernel.getDirReducingDist(from,to,directions,step);
    }

    public static Direction getDirIncreasingDist(GameObject from, Vector2 to, List<Direction> directions, int step){
        return CoreKernel.getDirIncreasingDist(from,to,directions,step);
    }
}
