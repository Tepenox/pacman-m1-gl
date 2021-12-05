package engines;

import utility.GameObject;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;

class GraphicEngine {
    /**
     * initialisation d'un frame pour un jeu
     * @param frame frame à initialiser
     * @param title titre de frame
     * @return instance de frame initialisée
     */

    protected static JFrame createFrame(JFrame frame, String title){
        frame.setTitle(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    /**
     * initilisation d'un panel vide pour un jeu
     * @param jPanel panel à initialiser
     * @param width largeur du panel
     * @param height hauteur du panel
     * @return instance de jPanel initialisée
     */

    protected static JPanel emptyPanel(JPanel jPanel, int width, int height) {
        if(jPanel == null) jPanel = new JPanel();
        jPanel.setBackground(Color.black);
        jPanel.setPreferredSize(new Dimension(width, height));
        jPanel.setOpaque(true);
        jPanel.setFocusable(true);
        return jPanel;
    }

    /**
     * création d'un panel avec une image
     * @param img image à utiliser dans le panel
     * @param width largeur d'image en panel
     * @param height hauteur d'image en panel
     * @return
     */
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

    /**
     * création de panel avec un layout si Jpanel passé en paramètre est nul
     * si non ajoute d un layout sur le JPanel passé
     * @param jPanel panel à modifier
     * @param layout layout manager
     * @return japnel crée ou modifié
     */

    protected static JPanel panelWithLayout(JPanel jPanel, LayoutManager layout){
        if(jPanel == null) jPanel = new JPanel();
        jPanel.setLayout(layout);
        jPanel.setBackground(Color.BLACK);
        return jPanel;
    }

    /**
     * ajout des borders layouts pour un panel
     * @param objToFill object panel
     * @param north component de bord layout north
     * @param center component de bord layout center
     * @param south component de bord layout south
     * @param east component de bord layout east
     * @param west component de bord layout west
     */
    protected static void fillBorderLayout(JPanel objToFill, JComponent north, JComponent center, JComponent south, JComponent east, JComponent west){
        objToFill.add(north,BorderLayout.NORTH);
        objToFill.add(center,BorderLayout.CENTER);
        objToFill.add(south,BorderLayout.SOUTH);
        objToFill.add(east,BorderLayout.EAST);
        objToFill.add(west,BorderLayout.WEST);
    }

    /**
     * ajout d'un border pour un panel
     * @param jPanel panel
     * @param border border
     */
    protected static void setBorder(JPanel jPanel, Border border){
        jPanel.setBorder(border);
    }


    /**
     * modifier la taille d'un Frame
     * @param jFrame Frame
     * @param width largeur de frame
     * @param height hauteur de frame
     */
    protected static void setSize(JFrame jFrame, int width, int height){
        jFrame.setPreferredSize(new Dimension(width, height));
        jFrame.setSize(width, height);
    }
    /**
     * modifier la taille d'un panel
     * @param jPanel panel
     * @param width largeur de frame
     * @param height hauteur de frame
     */
    protected static void setSize(JPanel jPanel, int width, int height){
        jPanel.setPreferredSize(new Dimension(width, height));
        jPanel.setSize(width, height);
    }

    /**
     * création d'un button
     * @param str texte du bouton
     * @param font police du texte
     * @param foreground couleur de police
     * @param backGround couleur d'arrière plan
     * @param l action à effectuer
     * @return le bouton crée
     */
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

    /**
     * dessiner une image sur un panel
     * @param jPanel panel
     * @param g2d graphics de panel
     * @param img image à dessiner
     * @param x position x d'image
     * @param y position y d'image
     * @param widthAndHeight hauteur et largeur d'image.
     */

    protected static void drawImage(JComponent jPanel, Graphics2D g2d, Image img, int x, int y,int widthAndHeight) {
        g2d.drawImage(img, x, y,widthAndHeight,widthAndHeight, jPanel);
    }

    /**
     * dessiner un gameObject sur un panel
     * @param jPanel  panel
     * @param g2d Graphics de panel
     * @param go gameObject
     * @param widthAndHeight hauteur et largeur
     */

    protected static void drawGameObject(JComponent jPanel, Graphics2D g2d, GameObject go, int widthAndHeight) {
        g2d.drawImage(go.getSprite(), go.getPosition().x, go.getPosition().y,widthAndHeight,widthAndHeight, jPanel);
    }

    /**
     * dessiner un rectangle sur Graphics
     * @param g2d graphics
     * @param x postion x
     * @param y postion y
     * @param widthAndHeight  hauteur et largeur de rectangle
     * @param color couleur de rectangle
     */
    protected static void fillRect(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        g2d.setColor(color);
        g2d.fillRect(x, y, widthAndHeight, widthAndHeight);
    }

    /**
     * dessiner une cercle sur Graphics
     * @param g2d graphics
     * @param x postion x
     * @param y postion y
     * @param widthAndHeight  hauteur et largeur du cercle
     * @param color couleur du cercle
     */

    protected static void fillOval(Graphics2D g2d, int x, int y, int widthAndHeight, Color color) {
        g2d.setColor(color);
        g2d.fillOval(x, y, widthAndHeight, widthAndHeight);
    }

    /**
     * dessiner une ligne sur Graphics
     * @param g2d graphics
     * @param x1 position de depart x
     * @param y1 position de depart y
     * @param x2 position de fin x
     * @param y2 position de fin y
     */
    protected static void drawLine(Graphics2D g2d, int x1, int y1, int x2, int y2){
        g2d.drawLine(x1, y1, x2, y2);
    }

    /**
     * dessinger un string sur Graphics
     * @param g2d graphics
     * @param panel panel
     * @param string string à dessiner
     * @param x postion x
     * @param y positon y
     * @param isCentered centré
     * @param font police utilisée
     * @param color couleur
     */
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
