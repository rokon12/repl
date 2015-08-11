package com.bazlur.repl.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Bazlur Rahman Rokon
 * @since 8/11/15.
 */
public class FileUtils {

    public static String readFileAsString(String path) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(path));

        return lines.collect(Collectors.joining("\n"));
    }

    public static boolean fileExist(String path) {

        return Files.exists(Paths.get(path));
    }
}
