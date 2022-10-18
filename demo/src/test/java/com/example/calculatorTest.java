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
        c.processInput("103 + 3");
        c.processInput("10 * 2 + 1");
        c.processInput("12435 + 34569 - 12345 * 10 + 50"); // This highlights how Bomdas is taken into account when
                                                           // calculating the sum
        assertEquals("The result is 106.0\nThe result is 21.0\nThe result is -76396.0\n",
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
