package game.GameEngine;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacManGameMenu extends JPanel implements ActionListener {

    private JButton buttonStart;
    private JButton buttonScore;
    private JButton buttonExit;

    private final MenuLogic frame;
    private Image titleImg = new ImageIcon(getClass().getResource("/Menu/titleSprite.png")).getImage();

    public PacManGameMenu(int screenWidth, int screenHeight, MenuLogic frame) {
        this.frame = frame;
        this.setBackground(Color.black);
        Border border = BorderFactory.createLineBorder(Color.GRAY,2);
        this.setBorder(border);
        this.setLayout(new BorderLayout());

        this.add(titlePanel(screenWidth,screenHeight/4),BorderLayout.NORTH);
        this.add(emptyPanel(screenWidth/4,1),BorderLayout.EAST);
        this.add(emptyPanel(screenWidth/4,1),BorderLayout.WEST);
        this.add(emptyPanel(1,screenHeight/6),BorderLayout.SOUTH);
        this.add(mainContent(),BorderLayout.CENTER);
        Engines.setSize(this,screenWidth,screenHeight);
    }

    private JPanel mainContent() {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setLayout(new GridLayout(3,1,50,100));

        buttonStart = new JButton("Start");
        buttonStart.setFont(new Font("Arial", Font.BOLD, 60));
        buttonStart.addActionListener(this);
        buttonStart.setForeground(Color.WHITE);
        buttonStart.setBackground(Color.BLACK);
        buttonStart.setBorderPainted(false);
        buttonStart.setFocusable(false);

        buttonExit = new JButton("Exit");
        buttonExit.setFont(new Font("Arial", Font.BOLD, 60));
        buttonExit.addActionListener(this);
        buttonExit.setForeground(Color.WHITE);
        buttonExit.setBackground(Color.BLACK);
        buttonExit.setBorderPainted(false);
        buttonExit.setFocusable(false);

        buttonScore = new JButton("Score");
        buttonScore.setFont(new Font("Arial", Font.BOLD, 60));
        buttonScore.setForeground(Color.WHITE);
        buttonScore.setBackground(Color.BLACK);
        buttonScore.setBorderPainted(false);
        buttonScore.setFocusable(false);

        mainPanel.add(buttonStart);
        mainPanel.add(buttonScore);
        mainPanel.add(buttonExit);
        return mainPanel;
    }

    private JPanel titlePanel(int width, int height) {
        JPanel result = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(titleImg, width/4, height/4,width/2,height/2, null);
            }
        };
        result.setBackground(Color.BLACK);
        result.setPreferredSize(new Dimension(width,height));
        return result;
    }

    private JPanel emptyPanel(int width, int height) {
        JPanel result = new JPanel();
        result.setBackground(Color.black);
        result.setPreferredSize(new Dimension(width, height));
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
