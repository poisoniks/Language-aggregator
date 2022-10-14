package IO;

import java.io.*;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Writer {

    private static final String DEFAULT_REGEX = "\\w";

    public void write(BufferedReader reader,
                      FileWriter writer,
                      Map<String, String> source,
                      String regex)
            throws IOException {

        String line = reader.readLine();
        Pattern pattern = Pattern.compile(regex);

        int i = 0;
        while (line != null) {
//            System.out.println("---------------");
//            System.out.println(line);

            if (line.isEmpty()) {
//                System.out.println("Empty line");
                writer.write("\n");
            } else {
                Matcher m = pattern.matcher(String.valueOf(line.charAt(0)));
                if (m.matches()) {
//                    System.out.println("Dictionary line");
                    String[] s = line.split("=");
                    String v = source.get(s[0]);
                    if (v != null) {
//                        System.out.println("Found usage");
//                        System.out.println(v);
                        writer.write(s[0] + "=" + v + "\n");
                        i++;
                    } else {
//                        System.out.println("Usage not found");
                        writer.write(line + "\n");
                    }
                } else {
//                    System.out.println("Comment line");
                    writer.write(line + "\n");
                }
            }
            line = reader.readLine();
        }
        System.out.printf("Updated %s lines\n", i);
    }

    public void write(BufferedReader reader,
                      FileWriter writer,
                      Map<String, String> source)
            throws IOException {
        write(reader, writer, source, DEFAULT_REGEX);
    }
}
