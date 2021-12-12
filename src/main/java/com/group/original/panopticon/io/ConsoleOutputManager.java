package com.group.original.panopticon.io;

public class ConsoleOutputManager implements OutputManager {
    @Override
    public void print(String message) {
        System.out.println(message);
    }

}
