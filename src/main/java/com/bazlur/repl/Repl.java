package com.bazlur.repl;

import com.bazlur.repl.utils.FileUtils;
import com.bazlur.repl.utils.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author Bazlur Rahman Rokon
 * @since 8/11/15.
 */
public class Repl {
    private static final Logger LOGGER = LoggerFactory.getLogger(Repl.class);
    private List<Command> commandList = new ArrayList<>();
    private Integer item = 0;

    private String fileName;

    public void init(String file) {
        this.fileName = file;
        readInitialFile(file);
        initializeCommands();
        runReplLoop();
    }

    private void initializeCommands() {
        commandList.add(new Command("add", true) {
            @Override
            void execute(Supplier<Integer> function) {
                executeAddCmd(function.get());
            }
        });

        commandList.add(new Command("sub", true) {
            @Override
            void execute(Supplier<Integer> function) {
                executeSubCmd(function.get());
            }
        });

        commandList.add(new Command("show", false) {
            @Override
            void execute(Supplier<Integer> function) {
                executeShowCmd();
            }
        });

        commandList.add(new Command("clear", false) {
            @Override
            void execute(Supplier<Integer> function) {
                executeClearCmd();
            }
        });

        commandList.add(new Command("exit", false) {
            @Override
            void execute(Supplier<Integer> function) {
                Util.exitSystem();
            }
        });
    }

    private void executeSubCmd(Integer value) {
        item = (item - value);
        FileUtils.replaceStringInFile(fileName, item.toString());
        LOGGER.info("{}", item);
    }

    private void executeAddCmd(Integer value) {
        item = (item + value);
        FileUtils.replaceStringInFile(fileName, item.toString());
        LOGGER.info("{}", item);
    }

    private void executeShowCmd() {
        Integer value = item;
        LOGGER.info("{}", value);
    }

    private void runReplLoop() {
        LOGGER.info("Welcome to simple REPL");
        Reader inputReader = new InputStreamReader(System.in);

        try (BufferedReader in = new BufferedReader(inputReader)) {
            String str;
            while ((str = in.readLine()) != null) {
                translate(str);
            }
        } catch (Exception e) {
            LOGGER.error("Something went wrong", e);
        }
    }

    private void readInitialFile(String file) {
        try {
            String value = FileUtils.readFileAsString(file);
            item = Integer.parseInt(value);
        } catch (IOException e) {
            LOGGER.error("Unable to read file", e);
            Util.exitSystem();
        }
    }

    public void translate(String commands) {
        String[] split = commands.split(" ");
        String command = split[0];

        Optional<Command> any = commandList.stream().filter(c -> c.getCommand().equals(command)).findAny();
        if (any.isPresent()) {
            Command cmd = any.get();

            if (cmd.isHasArgs()) {
                if (split.length < 2) {
                    LOGGER.info("Insufficient argument");
                } else {
                    String args = split[1];
                    try {
                        int value = Integer.parseInt(args);
                        cmd.execute(() -> value);
                    } catch (Exception e) {
                        LOGGER.info("Invalid argument");
                    }
                }

            } else {
                cmd.execute(null);
            }
        } else {
            LOGGER.info("Invalid Command");
        }
    }

    private void executeClearCmd() {
        item = 0;
        FileUtils.replaceStringInFile(fileName, item.toString());
        LOGGER.info("{}", item);
    }
}
