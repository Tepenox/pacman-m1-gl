package game;

import game.GameEngine.EnginesCalller;
import game.GameEngine.GameLogic;
import game.character.Ghosts.*;

import javax.swing.*;
import java.awt.*;

public class VisualDebugger {
    public static void draw(JPanel jPanel, Graphics graphics) {
        for (Ghost ghost: GameLogic.ghosts) {
            if(ghost instanceof Blinky)
                EnginesCalller.drawImage(jPanel,(Graphics2D) graphics,
                   new ImageIcon(VisualDebugger.class.getResource("/croixRouge.png")).getImage(),
                   ghost.target.x,ghost.target.y, GameLogic.gameUnit);
            if(ghost instanceof Pinky)
                EnginesCalller.drawImage(jPanel,(Graphics2D) graphics,
                        new ImageIcon(VisualDebugger.class.getResource("/croixRose.png")).getImage(),
                        ghost.target.x,ghost.target.y, GameLogic.gameUnit);
            if(ghost instanceof Inky)
                EnginesCalller.drawImage(jPanel,(Graphics2D) graphics,
                        new ImageIcon(VisualDebugger.class.getResource("/croixBleu.png")).getImage(),
                        ghost.target.x,ghost.target.y, GameLogic.gameUnit);
            if(ghost instanceof Clyde)
                EnginesCalller.drawImage(jPanel,(Graphics2D) graphics,
                        new ImageIcon(VisualDebugger.class.getResource("/croixOrange.png")).getImage(),
                        ghost.target.x,ghost.target.y, GameLogic.gameUnit);
        }

    }
}
