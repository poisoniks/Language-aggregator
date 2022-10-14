package Util;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.OutputStream;

public class DesktopOutputStream extends OutputStream {
    private final JTextArea textArea;

    public DesktopOutputStream(JTextArea output) {
        this.textArea = output;
    }

    @Override
    public void write(int b) throws IOException {
        textArea.append(String.valueOf((char) b));
    }
}
