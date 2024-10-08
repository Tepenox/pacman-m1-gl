package game.GameUtility;

import game.GameEngine.EnginesCaller;
import game.GameEngine.GameLogic;
import game.GameObject.character.Ghosts.*;

import javax.swing.*;
import java.awt.*;

public class VisualDebugger {
    public static void draw(JPanel jPanel, Graphics graphics) {
        for (Ghost ghost: GameLogic.ghosts) {
            if(ghost instanceof Blinky)
                EnginesCaller.drawImage(jPanel,(Graphics2D) graphics,
                   new ImageIcon(VisualDebugger.class.getResource("/croixRouge.png")).getImage(),
                   ghost.target.x,ghost.target.y, GameLogic.gameUnit);
            if(ghost instanceof Pinky)
                EnginesCaller.drawImage(jPanel,(Graphics2D) graphics,
                        new ImageIcon(VisualDebugger.class.getResource("/croixRose.png")).getImage(),
                        ghost.target.x,ghost.target.y, GameLogic.gameUnit);
            if(ghost instanceof Inky)
                EnginesCaller.drawImage(jPanel,(Graphics2D) graphics,
                        new ImageIcon(VisualDebugger.class.getResource("/croixBleu.png")).getImage(),
                        ghost.target.x,ghost.target.y, GameLogic.gameUnit);
            if(ghost instanceof Clyde)
                EnginesCaller.drawImage(jPanel,(Graphics2D) graphics,
                        new ImageIcon(VisualDebugger.class.getResource("/croixOrange.png")).getImage(),
                        ghost.target.x,ghost.target.y, GameLogic.gameUnit);
        }

    }
}
