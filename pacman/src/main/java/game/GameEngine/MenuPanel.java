package game.GameEngine;

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
        EnginesCalller.setBorder(this,BorderFactory.createLineBorder(Color.GRAY,2));
        EnginesCalller.panelWithLayout(this,new BorderLayout());
        EnginesCalller.fillBorderLayout(this,
                EnginesCalller.panelWithImg(titleImg,screenWidth,screenHeight/4),
                mainContent(),
                EnginesCalller.emptyPanel(null,1,screenHeight/6),
                EnginesCalller.emptyPanel(null,screenWidth/4,1),
                EnginesCalller.emptyPanel(null,screenWidth/4,1));
        EnginesCalller.setSize(this,screenWidth,screenHeight);
    }

    private JPanel mainContent() {
        JPanel mainPanel = EnginesCalller.panelWithLayout(null,new GridLayout(3,1,50,100));

        mainPanel.add(buttonStart = EnginesCalller.simpleButton("Start",new Font("Arial", Font.BOLD, 60),Color.WHITE,Color.BLACK,this));
        mainPanel.add(buttonScore = EnginesCalller.simpleButton("Score",new Font("Arial", Font.BOLD, 60),Color.WHITE,Color.BLACK,this));
        mainPanel.add(buttonExit = EnginesCalller.simpleButton("Exit",new Font("Arial", Font.BOLD, 60),Color.WHITE,Color.BLACK,this));
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
