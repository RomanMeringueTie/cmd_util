package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для сортировки содержимого входных файлов по типам
 * @author RomanMeringueTie
 */
public class Filter {

    public static List<String> Integers = new ArrayList<>();
    public static List<String> Floats = new ArrayList<>();
    public static List<String> Strings = new ArrayList<>();

    /**
     * Отсортировать данные по спискам целых чисел, чисел с плавающей точкой и строк
     * @param lines Данные
     */
    public static void filterData(List<String> lines) {
        for (String line: lines) {
            if (!isInt(line)) {
                if (!isFloat(line)) {
                    Strings.add(line);
                }
            }
        }
    }

    /**
     * Проверить, является ли строка целым числом. Если является - добавить в список целых чисел
     * @param line Строка
     * @return true, если строка является целым числом, false - если не является
     */
    private static boolean isInt(String line) {
        try {
            new BigInteger(line);
            Integers.add(line);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Проверить, является ли строка числом с плавающей точкой. Если является - добавить в список чисел с плавающей точкой
     * @param line Строка
     * @return true, если строка является числом с плавающей точкой, false - если не является
     */
    private static boolean isFloat(String line) {
        try {
            Double.parseDouble(line);
            Floats.add(line);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
