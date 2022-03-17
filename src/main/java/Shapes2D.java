import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Shapes2D extends JComponent {


    public static void main(String args[]) {

        JFrame mainFrame = new JFrame("Java2D Demo");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add(new Shapes2D());
        mainFrame.pack();
        mainFrame.setSize(640,480);
        mainFrame.setLocationRelativeTo(null);

        mainFrame.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D gd2 = (Graphics2D) g;

        gd2.setColor(Color.white);

        gd2.fillRect(0,0,
                this.getWidth(),
                this.getHeight());

        gd2.setColor(Color.RED);

        ArrayList<Shape> dibujo = new ArrayList<>();

        dibujo.add( new Ellipse2D.Double(50, 100,100,  100) );
        dibujo.add( new Rectangle2D.Double(50, 100,100,  100) );
        dibujo.add( new Line2D.Double(320, 0,320,  480) );
        dibujo.add( new Line2D.Double(0, 240,640,  240) );

        for (Shape shape: dibujo) {
            gd2.draw( shape );
        }

    }
}
