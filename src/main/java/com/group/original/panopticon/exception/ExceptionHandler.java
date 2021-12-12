package com.group.original.panopticon.exception;

import com.group.original.panopticon.io.OutputManager;

import java.util.ArrayList;
import java.util.List;

public class ExceptionHandler {
    private static List<OutputManager> outputManagers = new ArrayList<>();

    public static void registerOutputManager(OutputManager outputManager) {
        outputManagers.add(outputManager);
    }

    public static void unregisterOutputManager(OutputManager outputManager) {
        outputManagers.remove(outputManager);
    }

    public static void outputMessage(String message) {
        for (OutputManager manager : outputManagers) {
            manager.print(message);
        }
    }

    public static void outputMessage(Throwable throwable) {
        for (OutputManager manager : outputManagers) {
            manager.print(throwable.getClass().getSimpleName());
        }
    }

    public static void throwException(String description) throws RuntimeException {
        throw new RuntimeException(description);
    }

    public static void throwException(Exception e) throws RuntimeException {
        throw new RuntimeException(e);
    }

    public static void handle(Throwable throwable) {
        //TODO
    }
}
