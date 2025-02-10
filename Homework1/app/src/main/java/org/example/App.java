/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.io.*;
import java.util.*;

public class App {


    public static void main(String[] args) {

        List<String> inputFiles = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            inputFiles.add(args[i]);
        }

        String integersFile ="integers.txt";
        String floatsFile ="floats.txt";
        String stringsFile ="strings.txt";
        try (
                BufferedWriter intWriter = new BufferedWriter(new FileWriter(integersFile));
                BufferedWriter floatWriter = new BufferedWriter(new FileWriter(floatsFile));
                BufferedWriter stringWriter = new BufferedWriter(new FileWriter(stringsFile))
        ) {
            for (String fileName : inputFiles) {
                writeToFile(fileName, intWriter, floatWriter, stringWriter);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файлы: " + e.getMessage());
        }
    }


    private static void writeToFile(String filename, BufferedWriter intWriter, BufferedWriter floatWriter, BufferedWriter stringWriter) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (isInteger(line)) {
                    intWriter.write(line);
                    intWriter.newLine();
                } else if (isFloat(line)) {
                    floatWriter.write(line);
                    floatWriter.newLine();
                } else {
                    stringWriter.write(line);
                    stringWriter.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + filename + " - " + e.getMessage());
        }
    }

    private static boolean isInteger(String line) {
        try {
            Integer.parseInt(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isFloat(String line) {
        try {
            Float.parseFloat(line);
            return line.contains(".") || line.contains(",");
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
