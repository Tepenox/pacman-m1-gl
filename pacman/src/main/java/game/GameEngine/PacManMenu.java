package game.GameEngine;

import game.GameEngine.Engines;
import game.GameEngine.GameFrame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacManMenu extends JPanel implements ActionListener {

    private JButton buttonStart;
    private JButton buttonScore;
    private JButton buttonExit;

    private final GameFrame frame;

    public PacManMenu(int screenWidth, int screenHeight, GameFrame frame) {
        this.frame = frame;
        this.setBackground(Color.black);
        Border border = BorderFactory.createLineBorder(Color.GRAY,2);
        this.setBorder(border);
        this.setLayout(new BorderLayout());

        this.add(titlePanel(),BorderLayout.NORTH);
        this.add(emptyPanel(screenWidth/10),BorderLayout.EAST);
        this.add(emptyPanel(screenWidth/10),BorderLayout.WEST);
        this.add(mainContent(),BorderLayout.CENTER);
        Engines.setSize(this,screenWidth,screenHeight);
    }

    private JPanel mainContent() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3,1));
        buttonStart = new JButton("Start");
        buttonStart.addActionListener(this);
        buttonExit = new JButton("Exit");
        buttonExit.addActionListener(this);
        mainPanel.add(buttonStart);
        mainPanel.add(new JButton("Score"));
        mainPanel.add(buttonExit);
        return mainPanel;
    }

    private JPanel titlePanel() {
        JPanel result = new JPanel();
        JLabel title = new JLabel();
        title.setText("PacMan");
        result.add(title);
        return result;
    }

    private JPanel emptyPanel(int width) {
        JPanel result = new JPanel();
        result.setPreferredSize(new Dimension(width, 200));
        result.setOpaque(true);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonStart){
            this.frame.startGame(1,0);
        }
        if(e.getSource() == buttonExit){
            System.exit(0);
        }
    }
}
