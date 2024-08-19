package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с файлами
 * @author RomanMeringueTie
 */
public class File {

    private final static String projectPath = System.getProperty("user.dir");
    private final static String resourcesPath = "/src/main/resources/";
    private static String filenamePrefix;
    private static String resultPath = resourcesPath;
    private static boolean isFileAppendable = false;

    public static void setFilenamePrefix(String filenamePrefix) {
        File.filenamePrefix = filenamePrefix;
    }

    public static void setResultPath(String resultPath) {
        String resultFullPath;

        if (resultPath.charAt(0) == '/' || resultPath.charAt(0) == '\\')
            resultFullPath = resultPath;
        else
            resultFullPath = '/' + resultPath;
        if (resultPath.charAt(resultPath.length() - 1) != '/' && resultPath.charAt(resultPath.length() - 1) != '\\')
            resultFullPath = resultFullPath + '/';

        File.resultPath = resultFullPath;
    }

    public static void setIsFileAppendable(boolean isFileAppendable) {
        File.isFileAppendable = isFileAppendable;
    }

    /**
     * Сохранить содержимое файла в список
     * @param filenames Список входных файлов
     * @return Список со строками из всех входных файлов
     */
    public static List<String> toList(List<String> filenames) {
        List<String> input = new ArrayList<>();

        for (String filename : filenames) {
            input.addAll(readLines(filename));
        }

        return input;
    }



    /**
     * Записать данные в файл
     * @param filename Имя файла
     * @param lines Данные
     */
    public static void writeLines(String filename, List<String> lines) {
        try {
            if (!lines.isEmpty()) {
                Files.createDirectories(Paths.get(projectPath + resultPath));
                FileWriter fileWriter = new FileWriter(getOutputPath(filename), isFileAppendable);
                for (String line : lines) {
                    fileWriter.write(line + "\n");
                }
                fileWriter.close();
            }
        }
        catch (IOException io) {
            System.out.printf("Не удалось создать выходной файл %s", filename);
        }

    }

    /**
     * Считать данные из файла
     * @param filename Имя файла
     * @return Список с содержимым файла
     */
    private static List<String> readLines(String filename) {
        List<String> lines = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(getInputPath(filename));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            bufferedReader.close();
            fileReader.close();
        }
        catch (IOException io) {
            System.out.printf("Не удалось открыть файл %s", filename);
        }

        return lines;
    }

    /**
     * Получить путь к выходному файлу
     * @param filename Имя выходного файла
     * @return Строка, содержащая путь к выходному файлу
     */
    private static String getOutputPath(String filename) {
        return projectPath + resultPath + (filenamePrefix != null ? filenamePrefix : "") + filename;
    }

    /**
     * Получить путь к входному файлу
     * @param filename Имя входного файла
     * @return Строка, содержащая путь к входному файлу
     */
    private static String getInputPath(String filename) {
        return projectPath + resourcesPath + filename;
    }

}