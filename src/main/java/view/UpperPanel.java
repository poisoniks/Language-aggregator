package view;

import IO.FileOpener;
import control.DesktopAppController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UpperPanel extends JPanel  implements FileOpener {
    private final int INPUT_TYPE = 0;
    private final int SOURCE_TYPE = 1;

    private JPanel subPanel1;
    private JPanel subPanel2;
    private JButton openInputButton;
    private JButton openSourceButton;
    private JLabel inputFileLabel;
    private JLabel sourceFileLabel;
    private JTextField inputField;
    private JTextField sourceField;
    public UpperPanel() {
        init();
        initSubPanel1();
        initSubPanel2();
        setLayout(new BorderLayout());
        add(subPanel1, BorderLayout.NORTH);
        add(subPanel2, BorderLayout.SOUTH);
    }

    private void initSubPanel1() {
        subPanel1.add(inputFileLabel, BorderLayout.LINE_START);
        subPanel1.add(inputField, BorderLayout.CENTER);
        subPanel1.add(openInputButton, BorderLayout.LINE_END);

        inputFileLabel.setLabelFor(openInputButton);
        inputFileLabel.setVisible(true);
        openInputButton.addActionListener(getOpenFileListener(INPUT_TYPE));
        openInputButton.setVisible(true);
        inputField.setVisible(true);
    }

    private void initSubPanel2() {
        subPanel2.add(sourceFileLabel, BorderLayout.LINE_START);
        subPanel2.add(sourceField, BorderLayout.CENTER);
        subPanel2.add(openSourceButton, BorderLayout.LINE_END);

        sourceFileLabel.setLabelFor(openSourceButton);
        sourceFileLabel.setVisible(true);
        openSourceButton.addActionListener(getOpenFileListener(SOURCE_TYPE));
        openSourceButton.setVisible(true);
        sourceField.setVisible(true);
    }

    private void init() {
        subPanel1 = new JPanel();
        subPanel2 = new JPanel();
        openInputButton = new JButton("Browse");
        openSourceButton = new JButton("Browse");
        inputFileLabel = new JLabel("Input file:");
        sourceFileLabel = new JLabel("Source file:");
        inputField = new JTextField(48);
        sourceField = new JTextField(48);
    }

    private ActionListener getOpenFileListener(int label) {
        return e -> {
            String path = openFile();
            if(label==0) {
                DesktopAppController.getInstance().setIn(path);
                inputField.setText(path);
            } else if(label==1){
                DesktopAppController.getInstance().setSrc(path);
                sourceField.setText(path);
            }
        };
    }

    @Override
    public String openFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this.getParent());
        if (result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
}
