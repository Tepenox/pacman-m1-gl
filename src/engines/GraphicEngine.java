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

    protected static void drawImage(JPanel jPanel, Graphics2D g2d, Image img, Vector2 pos) {
        g2d.drawImage(img, pos.x, pos.y, jPanel);
    }

}
