package com.bazlur.repl;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Bazlur Rahman Rokon
 * @date 8/12/15.
 */
public abstract class Command {
    private String command;
    private boolean hasArgs;

    abstract void execute(Supplier<Integer> function);

    public Command(String command, boolean hasArgs) {
        this.command = command;
        this.hasArgs = hasArgs;
    }

    public String getCommand() {
        return command;
    }

    public boolean isHasArgs() {
        return hasArgs;
    }
}
