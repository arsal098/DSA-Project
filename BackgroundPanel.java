import javax.swing.*;
import java.awt.*;

class BackgroundPanel extends JPanel {

    Image bg;

    BackgroundPanel(String path) {
        bg = new ImageIcon(path).getImage();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
    }
}