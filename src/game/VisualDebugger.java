package game;

import game.GameUtility.GameState;
import game.character.Ghosts.*;

import javax.swing.*;
import java.awt.*;

public class VisualDebugger {
    public static void draw(JPanel jPanel, Graphics graphics) {
        for (Ghost ghost:PacManGame.ghosts) {
            if(ghost instanceof Blinky)
                Engines.drawImage(jPanel,(Graphics2D) graphics,
                   new ImageIcon("src/game/resources/croixRouge.png").getImage(),
                   ghost.target.x,ghost.target.y,PacManGame.gameUnit);
            if(ghost instanceof Pinky)
                Engines.drawImage(jPanel,(Graphics2D) graphics,
                        new ImageIcon("src/game/resources/croixRose.png").getImage(),
                        ghost.target.x,ghost.target.y,PacManGame.gameUnit);
            if(ghost instanceof Inky)
                Engines.drawImage(jPanel,(Graphics2D) graphics,
                        new ImageIcon("src/game/resources/croixBleu.png").getImage(),
                        ghost.target.x,ghost.target.y,PacManGame.gameUnit);
            if(ghost instanceof Clyde)
                Engines.drawImage(jPanel,(Graphics2D) graphics,
                        new ImageIcon("src/game/resources/croixOrange.png").getImage(),
                        ghost.target.x,ghost.target.y,PacManGame.gameUnit);
        }

    }
}
