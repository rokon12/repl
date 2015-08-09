package com.bazlur.repl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * @author Bazlur Rahman Rokon
 * @since 8/10/15.
 */
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        if (args.length < 1) {
            LOGGER.info("Insufficient Arguments!!");
            System.exit(0);
        }

        LOGGER.info("Welcome to simple REPL");

        Reader inputReader = new InputStreamReader(System.in);

        try (BufferedReader in = new BufferedReader(inputReader)) {
            String str;
            while ((str = in.readLine()) != null) {

                if (str.equals(":q")) {
                    System.exit(0);
                }


                LOGGER.info("{}", str);
            }
        } catch (Exception e) {
            LOGGER.error("Something went wrong", e);
        }
    }
}
