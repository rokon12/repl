package com.bazlur.repl;

import com.bazlur.repl.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author Bazlur Rahman Rokon
 * @since 8/11/15.
 */
public class Repl {
    private static final Logger LOGGER = LoggerFactory.getLogger(Repl.class);

    public void init() {
        LOGGER.info("Welcome to simple REPL");

        Reader inputReader = new InputStreamReader(System.in);

        try (BufferedReader in = new BufferedReader(inputReader)) {
            String str;
            while ((str = in.readLine()) != null) {
                if (str.equals(":q")) {
                    Util.exitSystem();
                } else {
                    translate(str);
                }
            }
        } catch (Exception e) {
            LOGGER.error("Something went wrong", e);
        }
    }

    public void translate(String commands) {
        LOGGER.info("{}", commands);
    }
}
