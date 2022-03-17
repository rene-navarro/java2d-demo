import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

public class NewCircle extends JDialog {

    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField xField1;
    private JTextField yField1;
    private JTextField wField;
    private JTextField hField;
    private JPanel controlsPanel;
    private JLabel x1Label;
    private JLabel y1Label;
    private JLabel wLabel;
    private JLabel hLabel;

    private Canvas dibujo;

    public NewCircle(JFrame parent, Canvas canvas) {
        super(parent,"New circle",true);

        dibujo = canvas;

        GridLayout layout = new GridLayout(0,2);

        controlsPanel = new JPanel(layout);

        x1Label = new JLabel("X:");
        x1Label.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(x1Label);
        xField1=new JTextField(4);
        controlsPanel.add(xField1);

        y1Label = new JLabel("Y:");
        y1Label.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(y1Label);
        yField1=new JTextField(4);
        controlsPanel.add(yField1);

        wLabel = new JLabel("Ancho:");
        wLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(wLabel);
        wField =new JTextField(4);
        controlsPanel.add(wField);

        hLabel = new JLabel("Alto:");
        hLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlsPanel.add(hLabel);
        hField =new JTextField(4);
        controlsPanel.add(hField);

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
        double x = Double.parseDouble( xField1.getText() );
        double y = Double.parseDouble( yField1.getText() );

        double w = Double.parseDouble( wField.getText() );
        double h = Double.parseDouble( hField.getText() );

        Ellipse2D.Double elipse = new Ellipse2D.Double(x,y,w,h);
        dibujo.add( elipse );
        dibujo.repaint();
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
