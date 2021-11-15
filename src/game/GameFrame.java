package game;

import game.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrame extends JFrame {


//    protected final Level level;
//    protected final int unitSize;
//    private final int widthOffset;
//    private final int heightOffset;

    public GameFrame(JPanel gamePanel){
        this.add(gamePanel);
        Engines.createFrame(this,"Pacman");
    }

public static void main(String[] args) {
        new GameFrame(PacManGame.createGame(840,930,75,30));
}
}
