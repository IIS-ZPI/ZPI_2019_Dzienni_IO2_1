package com.zpi.currencyapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserConsoleReader implements ConsoleReader {

    private BufferedReader reader;

    public UserConsoleReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() {
        String message = "error message";
        try {
            message = reader.readLine();
        } catch (IOException e) {
            System.out.println("Input reader error");
            e.printStackTrace();
        }
        return message;
    }

}
