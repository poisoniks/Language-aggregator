package control;

import IO.LineReader;
import IO.StaticFileFinder;
import IO.Writer;
import Util.Parser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ConsoleAppController extends AbstractController{

    private static final String OUTPUT_FILE_NAME = "output.lang";

    @Override
    public void run() {
        LineReader lineReader = new LineReader();
        Parser parser = new Parser();
        Writer writer = new Writer();
        String input;
        String source;

        try {
            StaticFileFinder staticFileFinder = new StaticFileFinder("input", "output", "source");
            input = staticFileFinder.getInput().getName();
            source = staticFileFinder.getSource().getName();
        } catch (IOException e) {
            System.out.println("No files to proceed");
            confirm();
            return;
        } catch (Exception e) {
            e.printStackTrace();
            confirm();
            return;
        }

//        try (BufferedReader bf = new BufferedReader(new FileReader("source/"+source, StandardCharsets.UTF_8));
//             BufferedReader bf1 = new BufferedReader(new FileReader("input/"+input, StandardCharsets.UTF_8))) {

        try (BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream("source/"+source), StandardCharsets.UTF_8));
             BufferedReader bf1 = new BufferedReader(new InputStreamReader(new FileInputStream("input/"+input), StandardCharsets.UTF_8))) {
//            FileInputStream s = new FileInputStream("source"+source);

//            byte[] bytes = s.readAllBytes();
//            for (byte b: bytes) {
//                char ch = (char) b;
//                System.out.println(ch);
//            }


            FileWriter fileWriter = new FileWriter("output/"+OUTPUT_FILE_NAME);
            System.out.println("---------------");
            System.out.println("Reading input file");
            List<String> sourceFile = lineReader.getStrings(bf);
            System.out.println(sourceFile);

            System.out.println("---------------");
            System.out.println("Parsing data...");
            Map<String, String> mapRu = parser.map(sourceFile);
            System.out.println("Success");

            System.out.println("---------------");
            System.out.println("Writing output file...");
            writer.write(bf1, fileWriter, mapRu);

            fileWriter.close();

        } catch (FileNotFoundException e) {
            System.out.println("Unable to found input file(s)");
            e.printStackTrace();
            cleanOut();
        } catch (IOException e) {
            System.out.println("Files can't be proceeded for some reason");
            cleanOut();
        } catch (Exception e) {
            System.out.println("Internal error");
            //magicbees.species=
            System.out.println(e.getMessage());
            cleanOut();
        }

        System.out.println("~~~~~~~~~~~");
        System.out.println("Success");
        System.out.println("~~~~~~~~~~~");

        confirm();
    }

    private static void cleanOut() {
        File file = new File("output/"+OUTPUT_FILE_NAME);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    private static void confirm() {
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}
