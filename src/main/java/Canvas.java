import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Canvas extends JComponent {

    private ArrayList<Shape> dibujo = new ArrayList<>();
    private Document svgDoc = null;

    public Canvas() {
        super();
    }

    public Canvas(Document document) {
        super();
        svgDoc = document;
    }

    public Document getSVG() {
        return  svgDoc;
    }

    public void setSvgDoc(Document svgDoc) {
        this.svgDoc = svgDoc;
    }

    public void add(Shape figura) {
        dibujo.add(figura);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D gd2 = (Graphics2D) g;

        gd2.setColor(Color.white);

        gd2.fillRect(0, 0,
                this.getWidth(),
                this.getHeight());

        gd2.setColor( Color.RED );

        for (Shape shape : dibujo) {
            gd2.draw(shape);
        }
    }

    public void addElement(Element e) {
       Element root = svgDoc.getDocumentElement();
        root.appendChild(e);
    }

}
