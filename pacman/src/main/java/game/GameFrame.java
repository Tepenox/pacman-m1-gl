package game;

import game.levels.Level;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GameFrame extends JFrame {

    private static final int screenWidth = 840;
    private static final int screenHeight = 960;
    private static final int gameDelay = 30;
    private static final int gameUnit = 30;

    private JPanel content;

    public GameFrame(){
        showMenu();
        Engines.createFrame(this,"Pacman");
        Engines.setSize(this, screenWidth, screenHeight);

    }

    private void showMenu() {
        removeOldContent();
        content = new PacManMenu(screenWidth,screenHeight,this);
        showNewContent();
    }

    public void startGame(){
        removeOldContent();
        content = PacManGame.createGame(screenWidth,screenHeight,gameDelay,gameUnit);
        showNewContent();
    }

    private void removeOldContent() {
        if(content != null)
            this.remove(content);
    }

    private void showNewContent(){
        this.add(content);
        content.requestFocus();
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}
