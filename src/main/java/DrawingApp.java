import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class DrawingApp extends JFrame {

    public static final String TAG = DrawingApp.class.getSimpleName();
    private static final Logger LOGGER = Logger.getLogger(TAG);

    private JMenuBar menuBar;
    private Canvas dibujo;

    Document svgDoc = null;

    public DrawingApp() {
        super("Drawing SVG");
        svgDoc = getNewDocument();
        buildGUI();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DrawingApp().setVisible(true);
            }
        });
    }

    private Document getNewDocument() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();

            doc = builder.newDocument();
            Element root = doc.createElement("svg");

            doc.appendChild(root);
           root.setAttribute("width", "640");
            root.setAttribute("height", "480");
            root.setAttribute("version", "1.1");
            root.setAttribute("xmlns", "http://www.w3.org/2000/svg");;
        } catch (ParserConfigurationException e) {
            LOGGER.severe(e.getMessage());
            doc = null;
        }
        return doc;
    }

    private void buildGUI() {
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem newItem = new JMenuItem("New");
        newItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                svgDoc = getNewDocument();
                dibujo.setSvgDoc(svgDoc);
            }
        });
        fileMenu.add(newItem);

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final JFileChooser fc = new JFileChooser();
                fc.setCurrentDirectory(new File( System.getProperty("user.dir")));

                int returnVal = fc.showSaveDialog(DrawingApp.this);

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();

                    saveDocument(svgDoc,file);

                    LOGGER.info(file.getName());
                } else {
                    LOGGER.info("Cancel saving");
                }

            }
        });
        fileMenu.add(saveItem);

        JMenuItem quitItem = new JMenuItem("Quit",
                KeyEvent.VK_Q);
        quitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        fileMenu.add(quitItem);

        JMenu shapeMenu = new JMenu("Shape");
        menuBar.add(shapeMenu);

        JMenuItem lineItem = new JMenuItem("Line",
                KeyEvent.VK_L);
        lineItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewLine dialog = new NewLine(DrawingApp.this, dibujo);
                dialog.setVisible(true);
            }
        });
        shapeMenu.add(lineItem);

        JMenuItem circleItem = new JMenuItem("Circle",
                KeyEvent.VK_C);
        circleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewCircle dialog = new NewCircle(DrawingApp.this, dibujo);
                dialog.setVisible(true);
            }
        });
        shapeMenu.add(circleItem);

        dibujo = new Canvas(svgDoc);
        this.getContentPane().add(dibujo);

        // dibujo.add(new Rectangle2D.Double(50,50,100,100));

        this.setJMenuBar(menuBar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(640, 480);
    }

    public  final void saveDocument(Document xml, File file) {
        Transformer tf = null;

        FileWriter out = null;
        try {
            tf = TransformerFactory.newInstance().newTransformer();
            tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tf.setOutputProperty(OutputKeys.INDENT, "yes");

            out = new FileWriter(file);
            tf.transform(new DOMSource(xml), new StreamResult(out));
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        } catch (TransformerException e) {
            LOGGER.severe(e.getMessage());
        }
    }

}
