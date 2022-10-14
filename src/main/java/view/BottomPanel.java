package view;

import Util.Processor;
import control.DesktopAppController;

import javax.swing.*;
import java.awt.*;

public class BottomPanel extends JPanel {
    private JPanel subPanel1;
    private JPanel subPanel2;
    private JTextArea outputText;
    private JButton runButton;

    public BottomPanel() {
        init();
        initSubPanel1();
        initSubPanel2();
        setLayout(new BorderLayout());
        add(subPanel1, BorderLayout.WEST);
        add(subPanel2, BorderLayout.EAST);
    }

    private void init() {
        subPanel1 = new JPanel();
        subPanel2 = new JPanel();
        outputText = new JTextArea(30, 60);
        runButton = new JButton("Run");
    }

    private void initSubPanel1() {
        JScrollPane scrollPane = new JScrollPane(outputText);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollBar bar = new JScrollBar();
        bar.setVisible(true);
        scrollPane.setVerticalScrollBar(bar);
        scrollPane.setVisible(true);

        outputText.setVisible(true);
        outputText.setEditable(false);

        subPanel1.add(scrollPane);
    }

    private void initSubPanel2() {
        runButton.setVisible(true);
        runButton.addActionListener((e)->DesktopAppController.getInstance().proceed());
        subPanel2.add(runButton);
    }

    public JTextArea getOutputArea() {
        return outputText;
    }
}
