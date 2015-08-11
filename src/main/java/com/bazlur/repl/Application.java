package com.bazlur.repl;

import com.bazlur.repl.utils.FileUtils;
import com.bazlur.repl.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Bazlur Rahman Rokon
 * @since 8/10/15.
 */
public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        if (args.length < 1) {
            LOGGER.info("Insufficient Arguments");
            Util.exitSystem();
        } else if (!FileUtils.fileExist(args[0])) {
            LOGGER.info("File doesn't exist");
            Util.exitSystem();
        } else {
            new Repl().init();
        }
    }

}
