package com.zpi.currencyapp;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class AutomateConsoleReader implements ConsoleReader {

    private Queue<String> commandQueue;

    public AutomateConsoleReader(int queueSize) {
        commandQueue = new ArrayBlockingQueue(queueSize);
    }

    @Override
    public String readLine() {
        return commandQueue.poll();
    }

    public void addCommand(String command) {
        commandQueue.add(command);
    }

    public void addMultipleCommands(Queue<String> commandQueue) {
        this.commandQueue = commandQueue;
    }

}
