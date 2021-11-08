package engines;

import javax.swing.*;

public class GraphicEngine {


    protected static JFrame createFrame(int width, int height,String title){
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setTitle(title);
        jFrame.setSize(width,height);
        jFrame.setLocationRelativeTo(null);
        return jFrame;
    }


}
