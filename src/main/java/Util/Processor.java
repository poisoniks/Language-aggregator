package Util;

import IO.DynamicFileFinder;
import IO.LineReader;
import IO.StaticFileFinder;
import IO.Writer;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class Processor {
    private static String OUTPUT_NAME="result.lang";

    public void proceed(String in, String out) {
        System.out.println("~~~~~~~~~~~");
        System.out.println("Running...");
        System.out.println("~~~~~~~~~~~");

        cleanOut();
        LineReader lineReader = new LineReader();
        Parser parser = new Parser();
        Writer writer = new Writer();
        String input;
        String source;

        try {
            DynamicFileFinder finder = new DynamicFileFinder();
            finder.setInput(in);
            finder.setSource(out);
            input = finder.getInput().getAbsolutePath();
            source = finder.getSource().getAbsolutePath();
        } catch (IOException e) {
            System.out.println("~~~~~~~~~~~");
            System.out.println("No files to proceed");
            System.out.println("~~~~~~~~~~~");
            return;
        } catch (Exception e) {
            System.out.println("~~~~~~~~~~~");
            e.printStackTrace();
            System.out.println("~~~~~~~~~~~");
            return;
        }

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(source), StandardCharsets.UTF_8));
             BufferedReader bf1 = new BufferedReader(new InputStreamReader(new FileInputStream(input), StandardCharsets.UTF_8))) {

            FileWriter fileWriter = new FileWriter(OUTPUT_NAME);
            System.out.println("---------------");
            System.out.println("Reading input file...");
            List<String> sourceFile = lineReader.getStrings(bf);

            System.out.println("---------------");
            System.out.println("Parsing data...");
            Map<String, String> mapSrc = parser.map(sourceFile);

            System.out.println("---------------");
            System.out.println("Writing output file...");
            writer.write(bf1, fileWriter, mapSrc);

            fileWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("~~~~~~~~~~~");
            System.out.println("Unable to found input file(s)");
            System.out.println("~~~~~~~~~~~");
            e.printStackTrace();
            cleanOut();
            return;
        } catch (IOException e) {
            System.out.println("~~~~~~~~~~~");
            System.out.println("Files can't be proceeded for some reason");
            System.out.println("~~~~~~~~~~~");
            cleanOut();
            return;
        } catch (Exception e) {
            System.out.println("~~~~~~~~~~~");
            System.out.println("Internal error");
            System.out.println("~~~~~~~~~~~");
            //TODO
            //magicbees.species=
            System.out.println(e.getMessage());
            cleanOut();
            return;
        }

        System.out.println("~~~~~~~~~~~");
        System.out.println("Success");
        System.out.println("~~~~~~~~~~~");
    }

    private static void cleanOut() {
        File file = new File(OUTPUT_NAME);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }
}
