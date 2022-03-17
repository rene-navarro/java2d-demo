import javax.swing.*;
import java.awt.*;

public class Dibujo extends JComponent {


    public static void main(String args[]) {

        JFrame mainFrame = new JFrame("Java2D Demo");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add(new Dibujo());
        mainFrame.pack();
        mainFrame.setSize(640,480);
        mainFrame.setLocationRelativeTo(null);

        mainFrame.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.white);

        g.fillRect(0,0,
                this.getWidth(),
                this.getHeight());

        g.setColor(Color.red);

        g.drawOval(20,20,200,100);
        g.setColor(Color.black);
        g.drawRect(20,20,200,100);

    }
}
