package com.bazlur.repl.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

    public static void exitSystem() {
        LOGGER.info("Program has been terminated");
        System.exit(0);
    }
}