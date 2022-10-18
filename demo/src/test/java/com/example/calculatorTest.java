package com.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.example.calculator;

/**
 * Unit test for simple App.
 */
public class calculatorTest {
    calculator c = new calculator();

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));

    }

    @Test
    public void shouldAnswerWithTrue() {
        c.processInput("103 + 2");
        c.processInput("10 * 2 + 1");
        assertEquals("The result is 105.0\nThe result is 21.0\n",
                outContent.toString());
    }

    @Test
    public void shouldAnswerWithFalse() {

        c.processInput("10 * / ");
        assertEquals("Expression error.\n", outContent.toString());
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}