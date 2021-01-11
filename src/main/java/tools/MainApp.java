package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author : hoangtq
 * @since : 22:11 11/01/2021, Mon
 **/
public class MainApp {
    public static void main(String[] args) {

//        args = new String[]{
//                "input", "input/input.asc",
//                "output","output/output.asc",
//        };

        String pathToInput = "input.asc";
        String pathToOutput = "output.asc";

        for (int index = 0; index < args.length; index++) {
            String input = args[index];
            if (input.equals("input")) {
                index+=1;
                pathToInput = args[index].trim();
            } else if (input.equals("output")){
                index+=1;
                pathToOutput = args[index].trim();
            }
        }

        System.out.println("Path to input='" + pathToInput + "'");
        System.out.println("Path to output='" + pathToOutput + "'");


        List<List<String>> inputs = FileHelper.readFileString(pathToInput);

        List<List<String>> results = new ArrayList<>();

        List<String> values = new ArrayList<>();

        values.add(inputs.get(0).get(0));

        values.add(String.format("%-15.10f", Double.parseDouble(inputs.get(0).get(1))));
        results.add(values);
        Random rd = new Random();

        for (int index = 1; index < inputs.size(); index += 2) {
            if (index == inputs.size() - 1) {
                break;
            }

            List<String> currLine = inputs.get(index);
            List<String> nextLine = inputs.get(index + 1);
            double bonus = Double.parseDouble(results.get(index - 1).get(1));
            double currValue = Double.parseDouble(currLine.get(1));
            double nextValue = Double.parseDouble(inputs.get(index + 1).get(1));
            String col11 = currLine.get(0);
            String col12 = nextLine.get(0);

            String col21 = String.format("%-15.10f", bonus);

            String col31 = String.format("%.10f", currValue);
            String col32 = String.format("%.10f", nextValue);
            String col41 = String.format("%.10f", (rd.doubles(20L).sum() + 10));
            String col42 = String.format("%.10f", (rd.doubles(20L).sum() + 10));
            String col51 = "Back";
            String col52 = "Fore";

            double delta = currValue - nextValue;
            bonus += delta;

            String col22 = String.format("%-15.10f", bonus);

            String col61 = String.format("%-15s", "--------");
            String col62 = String.format("%-15.10f", delta);
            String col23 = String.format("%-15.10f", bonus);

            List<String> nextLine1 = Arrays.asList(new String[]{
                    col11,
                    col21,
                    col31,
                    col41,
                    col51,
                    col61,
            });

            List<String> nextLine2 = Arrays.asList(new String[]{
                    col12,
                    col22,
                    col32,
                    col42,
                    col52,
                    col62,
            });

            List<String> nextLine3 = Arrays.asList(new String[]{
                    col12,
                    col23,
            });

            results.add(nextLine1);
            results.add(nextLine2);
            results.add(nextLine3);

//            String newLine1 = String.format("%-10s\t%15s\t%15s\t%15s\t%10s\t%15s"
//                    , col11, col21, col31, col41, col51, col61);
//
//            String newLine2 = String.format("%-10s\t%15s\t%15s\t%15s\t%10s\t%15s"
//                    , col12, col22, col32, col42, col52, col62);
//
//            String newLine3 = String.format("%-10s\t%15s"
//                    , col12, col23);
//
//            System.out.println(newLine1);
//            System.out.println(newLine2);
//            System.out.println(newLine3);
        }

        List<String> output = new ArrayList<>();

        output.add("DNTN TM Do dac LE CHANH");
        output.add("www.lechanh.com");

//        String[] headers = new String[]{
//                "Ten diem",
//                "Cao do",
//                "So doc mia",
//                "Khoang cach PP",
//                "Do (mia)",
//                "(B1-F1+B2-F2)/2",
//        };

//        String header = String.format("%-7s\t%-15s\t%-15s\t%-17s\t%-10s\t%-15s",
//                headers[0], headers[1], headers[2], headers[3], headers[4], headers[5]);
//
//        output.add(header);

        output.add("Ten diem\tCao do\tSo doc mia\tKhoang cach\tPP Do (mia)\t(B1-F1+B2-F2)/2");
        output.add("SHAF02-01 \t23        \t11        \t2020      \t705485   ");

        for (int index = 0; index < results.size(); index++) {
            List<String> currLine = results.get(index);
            String line = "";
            if (currLine.size() == 2) {
                line = String.format("%-10s\t%15s"
                        , currLine.get(0), currLine.get(1));
            } else if (currLine.size() == 6) {
                line = String.format("%-10s\t%15s\t%15s\t%17s\t%-10s\t%15s"
                        , currLine.get(0), currLine.get(1), currLine.get(2), currLine.get(3), currLine.get(4), currLine.get(5));
            } else {
                System.out.println("Error, line not support, line='" + line + "'");
                System.exit(1);
            }
            output.add(line);
//            System.out.println(line);
        }

        FileHelper.writeFile(output, pathToOutput);

    }
}
