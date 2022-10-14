package control;

import IO.FileOpener;
import Util.DesktopOutputStream;
import Util.Processor;
import view.BottomPanel;
import view.UpperPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.PrintStream;

public class DesktopAppController extends AbstractController {
    private static DesktopAppController instance;
    private Processor processor;
    private JFrame mainFrame;
    private JPanel filePanel;
    private BottomPanel outputPanel;

    private String in;
    private String src;

    public DesktopAppController () {
        instance = this;
        processor = new Processor();
        init();
        System.setOut(new PrintStream(new DesktopOutputStream(getOutputField())));
    }

    public static DesktopAppController getInstance() {
        return instance;
    }

    @Override
    public void run() {
        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(filePanel, BorderLayout.NORTH);
        mainFrame.add(outputPanel, BorderLayout.SOUTH);

        mainFrame.setVisible(true);
    }

    private void init() {
        mainFrame = new JFrame("Language aggregator");
        filePanel = new UpperPanel();
        outputPanel = new BottomPanel();

        mainFrame.setSize(new Dimension(800, 650));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
    }

    private JTextArea getOutputField() {
        return outputPanel.getOutputArea();
    }

    public void proceed() {
        if(in!=null && src!=null) {
            processor.proceed(in, src);
        }
    }

    public void setIn(String in) {
        this.in = in;
    }

    public void setSrc(String src) {
        this.src = src;
    }
}
