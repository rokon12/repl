package com.bazlur.repl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtils.class);

    public static String readFileAsString(String path) throws IOException {
        Stream<String> lines = Files.lines(Paths.get(path));

        return lines.collect(Collectors.joining("\n"));
    }

    public static void replaceStringInFile(String path, String value) {
        try {
            Files.write(Paths.get(path), value.getBytes());
        } catch (IOException e) {
            LOGGER.error("Unable to write in file", e);
        }
    }

    public static boolean fileExist(String path) {

        return Files.exists(Paths.get(path));
    }
}
