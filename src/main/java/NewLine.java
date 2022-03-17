import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.io.StringWriter;
import java.io.Writer;

public class NewLine extends JDialog {

    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField xField1;
    private JTextField yField1;
    private JTextField xField2;
    private JTextField yField2;
    private JPanel controlsPanel;
    private JLabel x1Label;
    private JLabel y1Label;
    private JLabel x2Label;
    private JLabel y2Label;

    private Canvas dibujo;

    public NewLine(JFrame parent, Canvas canvas) {
        super(parent,"New line",true);

        dibujo = canvas;

        GridLayout layout = new GridLayout(0,2);

        controlsPanel = new JPanel(layout);

        x1Label = new JLabel("X1:");
        x1Label.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(x1Label);
        xField1=new JTextField(4);
        controlsPanel.add(xField1);

        y1Label = new JLabel("Y1:");
        y1Label.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(y1Label);
        yField1=new JTextField(4);
        controlsPanel.add(yField1);

        x2Label = new JLabel("X2:");
        x2Label.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(x2Label);
        xField2=new JTextField(4);
        controlsPanel.add(xField2);

        y2Label = new JLabel("Y2:");
        y2Label.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(y2Label);
        yField2=new JTextField(4);
        controlsPanel.add(yField2);

        buttonOK = new JButton("Ok");
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });
        controlsPanel.add(buttonOK);

        buttonCancel= new JButton("Cancel");
        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
        controlsPanel.add(buttonCancel);
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        this.setContentPane(controlsPanel);

        setSize(200,400);
        setResizable(false);
        pack();

    }

    private void onOK() {
        // add your code here

        String sx1 =  xField1.getText();
        String sy1 = yField1.getText() ;
        String sx2 = xField2.getText();
        String sy2 =  yField2.getText();

        double x1 = Double.parseDouble( sx1 );
        double y1 = Double.parseDouble( sy1 );
        double x2 = Double.parseDouble( sx2 );
        double y2 = Double.parseDouble( sy2 );

        Line2D.Double l = new Line2D.Double(x1,y1,x2,y2);

        Element line = dibujo.getSVG().createElement("line");
        line.setAttribute("x1",sx1);
        line.setAttribute("y1",sy1);
        line.setAttribute("x2",sx2);
        line.setAttribute("y2",sy2);
        line.setAttribute("stroke","black");
        line.setAttribute("stroke-width","2");
        dibujo.addElement(line);

        dibujo.add( l );
        dibujo.repaint();
        try {
            prettyPrint(dibujo.getSVG());
        } catch (Exception e) {
            e.printStackTrace();
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public void prettyPrint(Document xml) throws Exception {
        Transformer tf = TransformerFactory.newInstance().newTransformer();
        tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        tf.setOutputProperty(OutputKeys.INDENT, "yes");
        Writer out = new StringWriter();
        tf.transform(new DOMSource(xml), new StreamResult(out));
        System.out.println(out.toString());
    }

}
