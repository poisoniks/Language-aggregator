package IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class DynamicFileFinder extends AbstractFileFinder{
    private File in;
    private File src;

    public DynamicFileFinder() {
        in = null;
        src = null;
    }

    @Override
    public File getInput() throws IOException {
        if(in!=null) {
            return in;
        }

        throw new FileNotFoundException("Unable to find input file");
    }

    @Override
    public File getSource() throws IOException {
        if(src!=null) {
            return src;
        }

        throw new FileNotFoundException("Unable to find source file");
    }

    public void setInput(String path) {
        File f = new File(path);
        in = f.exists() ? f : null;
    }

    public void setSource(String path) {
        File f = new File(path);
        src = f.exists() ? f : null;
    }
}
