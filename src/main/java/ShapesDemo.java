import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class ShapesDemo extends JComponent {


    public static void main(String args[]) {

        JFrame mainFrame = new JFrame("Java 2D Shapes Demo");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().add( new ShapesDemo() );
        mainFrame.pack();
        mainFrame.setSize(640,480);
       // mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);

        LineDialog dialog = new LineDialog( mainFrame );
        dialog.pack();
        dialog.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D gd2 = (Graphics2D) g;

       // gd2.setColor( Color.white );
        gd2.setColor(new Color(255,255,255) );

        gd2.fillRect(0,0,
                this.getWidth(),
                this.getHeight() );

        gd2.setColor( Color.RED );

        gd2.draw( new Ellipse2D.Double(50, 100,100,  100) );
        gd2.draw( new Rectangle2D.Double(50, 100,100,  100) );

        float[] lineaPuntoYRaya={20,5,5,5};
        BasicStroke lineaContorno = new BasicStroke(2F, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_MITER, 10F, lineaPuntoYRaya,0);

        gd2.setStroke( lineaContorno );

        gd2.setColor( Color.BLACK );

        gd2.draw( new Line2D.Double(320, 0,320,  480) );
        gd2.draw( new Line2D.Double(0, 240,640,  240) );

        gd2.setColor( Color.BLUE );

        gd2.fill( new Rectangle2D.Double(350, 100,100,  100) );
        gd2.setColor(Color.RED);
        gd2.fill( new Ellipse2D.Double(350, 100,100,  100) );

        gd2.setStroke( new BasicStroke(2) );

        QuadCurve2D c = new QuadCurve2D.Double(0,240,320,240,320,480);
        gd2.draw(c);

    }
}
