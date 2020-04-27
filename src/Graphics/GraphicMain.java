import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GraphicMain extends JPanel implements ActionListener{

    Image img = new ImageIcon("2.png").getImage();

    Timer timer = new Timer(20, this);

    JFrame frame;

    public Main(JFrame frame) {
        this.frame = frame;
    }

    public void paint(Graphics g) {
        g.drawImage(img, 0, 0,frame.getWidth(), frame.getHeight(), null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        repaint();
    }

}