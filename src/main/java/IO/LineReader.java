package IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LineReader extends Reader{
    public List<String> getStrings(BufferedReader bf) throws IOException {
        List<String> file = new ArrayList<>();
        String line = bf.readLine();
        int i = 0;

        while (line != null) {
            i++;
            file.add(line);
            line = bf.readLine();
        }

        System.out.println("Found "+i+" dictionary lines");

        return file;
    }

    @Override
    List<String> getStrings() {
        return null;
    }
}
