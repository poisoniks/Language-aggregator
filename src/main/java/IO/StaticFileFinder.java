package IO;

import java.io.File;
import java.io.IOException;

public class StaticFileFinder extends AbstractFileFinder{

    private final File inDirectory;
    private final File outDirectory;
    private final File srcDirectory;

    private File in;
    private File src;

    public StaticFileFinder(String inDir, String outDir, String srcDir) throws IOException {
        inDirectory = new File(inDir);
        srcDirectory = new File(srcDir);
        outDirectory = new File(outDir);

        if (check()) {
            File[] ins = inDirectory.listFiles();
            File[] srcs = srcDirectory.listFiles();

            if (ins != null
                    && srcs != null) {
                if (ins.length>=1
                        && srcs.length>=1) {
                    in = ins[0];
                    src = srcs[0];
                }
            }

            clearOutput();
        }
    }

    private void clearOutput() {
        File[] outs = outDirectory.listFiles();
        if (outs != null && outs.length>1) {
            System.out.println("---------------");
            System.out.println("Found files in output folder");
            for (File el: outs) {
                el.delete();
            }
            System.out.println("Cleared");
        }
    }

    public File getInput() throws IOException {
        if(in!=null) {
            return in;
        }

        throw new IOException();
    }

    public File getSource() throws IOException {
        if(src!=null) {
            return src;
        }

        throw new IOException();
    }

    private boolean check() {
        if (!inDirectory.exists()
                && !outDirectory.exists()
                && !srcDirectory.exists()) {
            System.out.println("In root folder must be input, output, source folders");
            return false;
        }

//        if (inDirectory.isDirectory()
//                && outDirectory.isDirectory()
//                && srcDirectory.isDirectory()) {
//            System.out.println("Input, output, source files must be folders");
//            return false;
//        }

        return true;
    }
}
