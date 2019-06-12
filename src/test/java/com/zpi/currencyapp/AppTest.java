package com.zpi.currencyapp;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

    private App app;
    private AutomateConsoleReader reader;
    private Queue<String> commands;

    @Before
    public void setUp() {
        commands = new ArrayBlockingQueue(10);
        reader = new AutomateConsoleReader(10);
    }

    @Test
    public void shouldCheckIfMainMenuControllWasCalledOnceIfAppStart() throws IOException {
        reader.addCommand("4");
        app = spy(new App(reader));
        app.start();
        verify(app, times(1)).mainMenuControl();
    }

}
