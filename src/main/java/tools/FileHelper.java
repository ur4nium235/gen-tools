package tools;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author : hoangtq
 * @since : 22:11 11/01/2021, Mon
 **/
public class FileHelper {

    public static File initPathToFile(String pathToFile) {
        Path path = Paths.get(pathToFile);
//        if (!path.toFile().exists()) {
//            try {
//                Files.createDirectories(path.getParent());
//                Files.createFile(path);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return path.toFile();
    }

    public static List<List<String>> readFileString(String pathToFile) {
        File file = initPathToFile(pathToFile);
        Map<String, Integer> countToWord = new HashMap<>();
        List<List<String>> result = new ArrayList<>();

        // auto close with any exception
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file));) {
            String inLine;
            List<String> lines = new ArrayList<>();
            int count = 1;
            while ((inLine = reader.readLine()) != null) {
                count++;
                if (!inLine.equals("")) {
                    String words[] = inLine.trim().split("\\s++");
                    if (words.length == 2) {
                        lines = Arrays.asList(words);
                        result.add(lines);
                    } else {
                        String log = String.format("Split error, size split not equal 2, line %d, content='%s'", count, inLine);
                        System.out.println(log);
                        System.exit(1);
                    }
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public static void writeFile(List<String> list, String pathToFile) {
        File file = initPathToFile(pathToFile);

        // auto close with any exception
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            for (String line : list) {
                writer.write(line);
                writer.newLine();
            }
            writer.flush();

            System.out.println("---> Write file to path '" + pathToFile + "' done!!!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
