package org.example;

import java.util.List;

import static org.example.Arguments.*;
import static org.example.Filter.*;
import static org.example.File.toList;
import static org.example.File.writeLines;

public class Main {

    public static void main(String[] args) {

        setArgs(args);

        if (isArgumentsEmpty())
            return;

        parseFlags();

        List<String> filenames = getFilenames();

        List<String> input = toList(filenames);

        filterData(input);

        writeLines("integers.txt", Integers);
        writeLines("floats.txt", Floats);
        writeLines("strings.txt", Strings);

        Statistics.printStatistics();

    }

}
