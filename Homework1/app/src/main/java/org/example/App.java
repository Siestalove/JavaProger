/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import java.io.*;
import java.util.*;

public class App {


    public static void main(String[] args) {
        String outputDir = "src/main/resources";
        String prefix = "";
        boolean addwrite = false;

        List<String> inputFiles = new ArrayList<>();

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-o")) {
                if (i + 1 < args.length) {
                    outputDir = args[i + 1];
                    i++;
                } else {
                    System.out.println("Ошибка: укажите папку после -o");
                    return;
                }
            }
            else if (args[i].equals("-p")) {
                if (i + 1 < args.length) {
                    prefix = args[i + 1];
                    i++;
                } else {
                    System.out.println("Ошибка: укажите префикс после -p");
                    return;
                }
            }else if (args[i].equals("-a")) {
                addwrite = true;
            }else {
                    inputFiles.add(args[i]);
                }
        }

        File directory = new File(outputDir);
        if (!directory.exists()) {
            if (directory.mkdirs()) {
                System.out.println("Создана папка для выходных файлов: " + outputDir);
            } else {
                System.err.println("Ошибка: не удалось создать папку " + outputDir);
                return;
            }
        }
            String integersFile = outputDir + "/" + prefix + "integers.txt";
            String floatsFile = outputDir + "/" + prefix + "floats.txt";
            String stringsFile = outputDir + "/" + prefix + "strings.txt";
        try (
                BufferedWriter intWriter = new BufferedWriter(new FileWriter(integersFile, addwrite));
                BufferedWriter floatWriter = new BufferedWriter(new FileWriter(floatsFile, addwrite));
                BufferedWriter stringWriter = new BufferedWriter(new FileWriter(stringsFile, addwrite));
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
