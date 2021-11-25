package game.GameEngine;

import javax.swing.*;

public class MenuLogic extends JFrame {

    public final int screenWidth = 840;
    public final int screenHeight = 990;
    public final int gameDelay = 30;
    public final int gameUnit = 30;

    private final int numberOfLvl = 8;

    private JPanel content;

    public MenuLogic(){
        showMenu();
        Engines.createFrame(this,"Pacman");
        Engines.setSize(this, screenWidth+10, screenHeight+10);

    }

    public static void main(String[] args) {
        new MenuLogic();
    }

    //============================================Content Logic=================================================

    public void levelEnded(int lvl, Boolean hasWon, int scoreObtained){
        if(hasWon){
            if(lvl < numberOfLvl) {
                startGame(lvl + 1, scoreObtained);
            }else {
                showMenu();
            }
        }
        if(!hasWon){
            showMenu();
        }
    }

    //============================================Content setter================================================

    private void showMenu() {
        removeOldContent();
        content = new MenuPanel(screenWidth,screenHeight,this);
        showNewContent();
    }

    public void startGame(int level, int startScore){
        removeOldContent();
        content = GameLogic.createGame(this,level,startScore,3);
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

}
