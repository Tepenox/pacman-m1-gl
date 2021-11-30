package engines;

import utility.GameObject;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

class GraphicEngine {


    protected static JFrame createFrame(JFrame frame, String title){
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    protected static JPanel emptyPanel(JPanel jPanel, int width, int height) {
        if(jPanel == null) jPanel = new JPanel();
        jPanel.setBackground(Color.black);
        jPanel.setPreferredSize(new Dimension(width, height));
        jPanel.setOpaque(true);
        jPanel.setFocusable(true);
        return jPanel;
    }

    protected static JPanel panelWithImg(Image img, int width, int height){
        JPanel result = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img, width/4, height/4,width/2,height/2, null);
            }
        };
        result.setBackground(Color.BLACK);
        result.setPreferredSize(new Dimension(width,height));
        return result;
    }

    protected static JPanel panelWithLayout(JPanel jPanel, LayoutManager layout){
        if(jPanel == null) jPanel = new JPanel();
        jPanel.setLayout(layout);
        jPanel.setBackground(Color.BLACK);
        return jPanel;
    }

    protected static void fillBorderLayout(JPanel objToFill, JComponent north, JComponent center, JComponent south, JComponent east, JComponent west){
        objToFill.add(north,BorderLayout.NORTH);
        objToFill.add(center,BorderLayout.CENTER);
        objToFill.add(south,BorderLayout.SOUTH);
        objToFill.add(east,BorderLayout.EAST);
        objToFill.add(west,BorderLayout.WEST);
    }

    protected static void setBorder(JPanel jPanel, Border border){
        jPanel.setBorder(border);
    }

    protected static void setSize(JFrame jFrame, int width, int height){
        jFrame.setPreferredSize(new Dimension(width, height));
        jFrame.setSize(width, height);
    }

    protected static void setSize(JPanel jPanel, int width, int height){
        jPanel.setPreferredSize(new Dimension(width, height));
        jPanel.setSize(width, height);
    }

    protected static JButton simpleButton(String str, Font font, Color foreground, Color backGround, ActionListener l){
        JButton btn = new JButton(str);
        btn.setFont(font);
        if(l != null)
            btn.addActionListener(l);
        btn.setForeground(foreground);
        btn.setBackground(backGround);
        btn.setBorderPainted(false);
        btn.setFocusable(false);
        return btn;
    }

    protected static void drawImage(JComponent jPanel, Graphics2D g2d, Image img, int x, int y,int widthAndHeight) {
        g2d.drawImage(img, x, y,widthAndHeight,widthAndHeight, jPanel);
    }

    protected static void drawGameObject(JComponent jPanel, Graphics2D g2d, GameObject go, int widthAndHeight) {
        g2d.drawImage(go.getSprite(), go.getPosition().x, go.getPosition().y,widthAndHeight,widthAndHeight, jPanel);
    }

    protected static void fillRect(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        g2d.setColor(color);
        g2d.fillRect(x, y, widthAndHeight, widthAndHeight);
    }

    protected static void fillOval(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        g2d.setColor(color);
        g2d.fillOval(x, y, widthAndHeight, widthAndHeight);
    }

    protected static void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2){
        g2d.drawLine(x1, y1, x2, y2);
    }

    protected static void drawString(Graphics2D g2d, JComponent panel, String string, int x, int y, boolean isCentered, Font font, Color color){
        Color previousColor = g2d.getColor();
        Font previousFont = g2d.getFont();
        g2d.setColor(color);
        g2d.setFont(font);
        FontMetrics metrics = panel.getFontMetrics(g2d.getFont());
        if(isCentered)
            x -= metrics.stringWidth(string)/2;
        g2d.drawString(string, x, y);
        g2d.setColor(previousColor);
        g2d.setFont(previousFont);
    }
}
