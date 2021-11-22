package game;

import game.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GameFrame extends JFrame {

    public GameFrame(JPanel gamePanel){
        this.add(gamePanel);
        Engines.createFrame(this,"Pacman");
    }

    public static void main(String[] args) {
        new GameFrame(PacManGame.createGame(840,960,40,30));
    }
}
