package org.example;

import java.util.ArrayList;
import java.util.List;

import static org.example.File.*;

/**
 * Класс для работы с аргументами командной строки
 * @author RomanMeringueTie
 */
public class Arguments {

    private static String[] args;

    public static void setArgs(String[] args) {
        Arguments.args = args;
    }

    /**
     * Проверка, нет ли аргументов командной строки
     * @return true, если аргументов нет, true - если есть
     */
    public static boolean isArgumentsEmpty() {
        if (args.length == 0) {
            System.out.println("Не введены имена файлов для фильтрации");
            return true;
        }
        else
            return false;
    }

    /**
     * Обработать флаги, указанные в аргументах
     */
    public static void parseFlags() {
        parseFlagsWithArguments();
        parseFlagsWithoutArguments();
    }

    /**
     * Получить из аргументов список имён входных файлов
     * @return Список имён входных файлов
     */
    public static List<String> getFilenames() {
        List<String> filenames = new ArrayList<>();

        boolean flag = true;
        for (String line: args) {
            if (!isFlagWithArgument(line)) {
                if (flag && !isFlagWithoutArgument(line))
                    filenames.add(line);
                flag = true;
            }
            else
                flag = false;
        }

        if (filenames.isEmpty())
            System.out.println("Не введены имена файлов для фильтрации");

        return filenames;
    }

    /**
     * Обработать флаги, за которыми должны следовать аргументы
     */
    private static void parseFlagsWithArguments() {
        for (int i = 0; i < args.length - 1; i++) {
            switch (args[i]) {
                case "-p" -> setFilenamePrefix(args[i + 1]);
                case "-o" -> setResultPath(args[i + 1]);
            }
        }
        if (isFlagWithArgument(args[args.length - 1])) {
            System.out.printf("Флаг %s будет проигнорирован, отсутствует аргумент\n", args[args.length - 1]);
        }
    }

    /**
     * Обработать флаги, за которыми не должны следовать аргументы
     */
    private static void parseFlagsWithoutArguments() {
        for (int i = 1; i < args.length; i++) {
            if (!isFlagWithArgument(args[i - 1])) {
                switch (args[i]) {
                    case "-s" -> Statistics.setIsFullStats(false);
                    case "-f" -> Statistics.setIsFullStats(true);
                    case "-a" -> setIsFileAppendable(true);
                }
            }
        }
    }

    /**
     * Проверить, является ли строка флагом, за которым должны следовать аргументы
     * @param line Строка
     * @return true, если строка является флагом, за которым должны следовать аргументы, false - если не является
     */
    private static boolean isFlagWithArgument(String line) {
        return line.equals("-p") || line.equals("-o");
    }

    /**
     * Проверить, является ли строка флагом, за которым не должны следовать аргументы
     * @param line Строка
     * @return true, если строка является флагом, за которым не должны следовать аргументы, false - если не является
     */
    private static boolean isFlagWithoutArgument(String line) {
        return line.equals("-a") || line.equals("-s") || line.equals("-f");
    }

}
