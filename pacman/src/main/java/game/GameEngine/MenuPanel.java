package game.GameEngine;

import engines.GraphicEngine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel implements ActionListener {

    private JButton buttonStart;
    private JButton buttonScore;
    private JButton buttonExit;

    private final MenuLogic frame;
    private Image titleImg = new ImageIcon(getClass().getResource("/Menu/titleSprite.png")).getImage();

    public MenuPanel(int screenWidth, int screenHeight, MenuLogic frame) {
        this.frame = frame;
        Engines.setBorder(this,BorderFactory.createLineBorder(Color.GRAY,2));
        Engines.panelWithLayout(this,new BorderLayout());
        Engines.fillBorderLayout(this,
                Engines.panelWithImg(titleImg,screenWidth,screenHeight/4),
                mainContent(),
                Engines.emptyPanel(null,1,screenHeight/6),
                Engines.emptyPanel(null,screenWidth/4,1),
                Engines.emptyPanel(null,screenWidth/4,1));
        Engines.setSize(this,screenWidth,screenHeight);
    }

    private JPanel mainContent() {
        JPanel mainPanel = Engines.panelWithLayout(null,new GridLayout(3,1,50,100));

        mainPanel.add(buttonStart = Engines.simpleButton("Start",new Font("Arial", Font.BOLD, 60),Color.WHITE,Color.BLACK,this));
        mainPanel.add(buttonScore = Engines.simpleButton("Score",new Font("Arial", Font.BOLD, 60),Color.WHITE,Color.BLACK,this));
        mainPanel.add(buttonExit = Engines.simpleButton("Exit",new Font("Arial", Font.BOLD, 60),Color.WHITE,Color.BLACK,this));
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonStart){
            this.frame.startGame(1,0);
        }
        if(e.getSource() == buttonScore){
            System.out.println("Sorry, Score Board is not yet implemented");
        }
        if(e.getSource() == buttonExit){
            System.exit(0);
        }
    }
}
