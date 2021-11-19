package game;

import game.GameUtility.GameState;
import game.character.Ghosts.Ghost;

import javax.swing.*;
import java.awt.*;

public class VisualDebugger {
    public static void draw(JPanel jPanel, Graphics graphics) {
        for (Ghost ghost:PacManGame.ghosts) {
           Engines.drawImage(jPanel,(Graphics2D) graphics,
                   new ImageIcon("src/game/resources/croix.png").getImage(),
                   ghost.target.x,ghost.target.y,PacManGame.gameUnit);
        }

    }
}
