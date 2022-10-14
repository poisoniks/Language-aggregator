package IO;

import java.io.File;
import java.io.IOException;

public abstract class AbstractFileFinder {

    public abstract File getInput() throws IOException;

    public abstract File getSource() throws IOException;

}
