package game.GameEngine;

import engines.*;
import game.character.Character;
import utility.Direction;
import utility.GameObject;
import utility.Vector2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class EnginesCaller {

    //==================================================Graphic======================================

    public static JFrame createFrame(JFrame frame, String gameTitle) {
        return CoreKernel.createFrame(frame, gameTitle);
    }

    public static JPanel emptyPanel(JPanel jPanel, int width, int height) {
        return CoreKernel.emptyPanel(jPanel,width,height);
    }

    public static JPanel panelWithImg(Image img, int width, int height){
        return CoreKernel.panelWithImg(img,width,height);
    }

    public static JPanel panelWithLayout(JPanel jPanel, LayoutManager layout){
        return CoreKernel.panelWithLayout(jPanel,layout);
    }

    public static void fillBorderLayout(JPanel objToFill, JComponent north, JComponent center, JComponent south, JComponent east, JComponent west){
        CoreKernel.fillBorderLayout(objToFill, north, center, south, east, west);
    }

    public static void setBorder(JPanel jPanel, Border border){
        CoreKernel.setBorder(jPanel,border);
    }

    public static void setSize(JFrame jFrame, int width, int height){
        CoreKernel.setSize(jFrame, width, height);
    }

    public static void setSize(JPanel jPanel, int width, int height){
        CoreKernel.setSize(jPanel, width, height);
    }

    public static JButton simpleButton(String str, Font font, Color foreground, Color backGround, ActionListener l){
        return CoreKernel.simpleButton(str, font, foreground, backGround, l);
    }

    public static void drawImage(JComponent jPanel, Graphics2D g2d, Image img, int x, int y,int widthAndHeight) {
        CoreKernel.drawImage(jPanel, g2d, img, x,y,widthAndHeight);
    }

    public static void drawGameObject(JComponent jPanel, Graphics2D g2d, GameObject go, int widthAndHeight) {
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

    public static void drawString(Graphics2D g2d, JComponent panel, String string, int x, int y, boolean isCentered, Font font, Color color){
        CoreKernel.drawString(g2d, panel, string, x, y, isCentered, font, color);
    }

    //==================================================Physics======================================

    public static double calculateDist(Vector2 from, Vector2 to){
        return CoreKernel.calculateDist(from,to);
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

    //==================================================SoundEngine======================================
    public static void playSfx(String path) {
        CoreKernel.playSfx(path);
    }

    public static void playSfxOnLoop(String path) {
        CoreKernel.playSfxOnLoop(path);
    }

    public static void stopAllActiveSounds(){
        CoreKernel.stopAllActiveSounds();
    }
    public static void stopSfx(String path){
        CoreKernel.stopSfx(path);
    }
}
