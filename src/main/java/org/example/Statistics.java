package org.example;

import java.math.BigInteger;
import java.util.*;

import static org.example.Filter.*;

/**
 * Класс для сбора и отображения статистики
 * @author RomanMeringueTie
 */
public class Statistics {

    private static Boolean isFullStats = null;

    public static void setIsFullStats(Boolean isFullStats) {
        Statistics.isFullStats = isFullStats;
    }

    /**
     * Вывод статистики на экран, если необходимо
     */
    public static void printStatistics() {
        if (isFullStats != null)
            printSimpleStatistics();
        else
            return;

        if  (isFullStats) {
            printFullNumberStatistics();
            printFullStringStatistics();
        }
    }

    /**
     * Вывод краткой статистики
     */
    private static void printSimpleStatistics() {
        System.out.printf("Integers: %d lines\nFloats: %d lines\nStrings: %d lines\n",
                Integers.size(), Floats.size(), Strings.size());
    }

    /**
     * Вывод полной статистики чисел
     */
    private static void printFullNumberStatistics() {
        if (!(Integers.isEmpty() && Floats.isEmpty())) {
            System.out.printf("Sum of numbers: %.3f\nMax number: %.3f\nAverage number: %.3f\n",
                    getSumOfNumbers(), getMaxNumber(), getAverageNumber());
        }
    }

    /**
     * Выаод полной статистики строк
     */
    private static void printFullStringStatistics() {
        if (!Strings.isEmpty()) {
            System.out.printf("Longest string: %s (length = %d)\nShortest string: %s (length = %d)\n",
                    getLongestString(), getLongestString().length(),
                    getShortestString(), getShortestString().length());
        }
    }

    /**
     * Получить среднее фрифметическое число из входных файлов
     * @return Среднее фрифметическое число из входных файлов
     */
    private static double getAverageNumber() {
        double sum = getSumOfNumbers();
        int sizeOfNumbers = Integers.size() + Floats.size();
        return sum / sizeOfNumbers;
    }

    /**
     * Получить сумму чисел из входных файлов
     * @return Сумма чисел из входных файлов
     */
    private static double getSumOfNumbers() {
        double sum = 0;

        for (String num: Integers) {
            sum += new BigInteger(num).doubleValue();
        }
        for (String num: Floats) {
            sum += Double.parseDouble(num);
        }

        return sum;
    }

    /**
     * Получить максимальное число из входных файлов
     * @return Максимальное число из входных файлов
     */
    private static double getMaxNumber() {
        List<BigInteger> intList = new ArrayList<>();
        List<Double> floatList = new ArrayList<>();

        BigInteger maxInteger = new BigInteger("0");
        double maxFloat = 0;

        if (!Integers.isEmpty()) {
            for (String num : Integers) {
                intList.add(new BigInteger(num));
            }
            maxInteger = intList.stream().max(BigInteger::compareTo).get();
        }

        if (!Floats.isEmpty()) {
            for (String num : Floats) {
                floatList.add(Double.parseDouble(num));
            }
            maxFloat = floatList.stream().mapToDouble(Double::doubleValue).max().getAsDouble();
        }

        return Math.max(maxInteger.doubleValue(), maxFloat);
    }

    /**
     * Получить самую длинную строку из входных файлов
     * @return Самая длинная строка из входных файлов
     */
    private static String getLongestString() {
        String longestString = Strings.getFirst();
        for (String string: Strings) {
            if (string.length() > longestString.length()) {
                longestString = string;
            }
        }
        return longestString;
    }

    /**
     * Получить самую короткую строку из входных файлов
     * @return Самая короткая строка из входных файлов
     */
    private static String getShortestString() {
        String shortestString = Strings.getFirst();
        for (String string: Strings) {
            if (string.length() < shortestString.length()) {
                shortestString = string;
            }
        }
        return shortestString;
    }

}
