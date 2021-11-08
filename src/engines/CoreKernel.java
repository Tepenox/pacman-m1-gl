package engines;

import javax.swing.*;

public class CoreKernel {
    //List of all methods to call for each engine
    //Each method must be public

    public static JFrame createFrame(int width, int height,String title){
        return GraphicEngine.createFrame(width, height,title);
    }
}
