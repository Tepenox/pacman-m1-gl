package engines;

import game.utility.Vector2;

import javax.swing.*;

public class CoreKernel {
    //List of all methods to call for each engine
    //Each method must be public

    public static JFrame createFrame(String title){
        return GraphicEngine.createFrame(title);
    }

    public static double calculateDist(Vector2 from, Vector2 to){
        return PhysicEngine.calculateDist(from,to);
    }
}
