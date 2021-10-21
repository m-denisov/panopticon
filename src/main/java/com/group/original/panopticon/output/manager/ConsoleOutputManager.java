package com.group.original.panopticon.output.manager;

public class ConsoleOutputManager implements OutputManager {
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}
