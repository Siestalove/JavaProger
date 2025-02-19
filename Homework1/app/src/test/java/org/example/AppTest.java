package org.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class AppTest {

    private String testDir = "src/test/resources";
    private String testPrefix = "test_";
    private String intFile = testDir + "/" + testPrefix + "integers.txt";
    private String floatFile = testDir + "/" + testPrefix + "floats.txt";
    private String stringFile = testDir + "/" + testPrefix + "strings.txt";

    @Before
    public void setUp() throws IOException {
        Files.createDirectories(Paths.get(testDir));
    }

    @After
    public void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(intFile));
        Files.deleteIfExists(Paths.get(floatFile));
        Files.deleteIfExists(Paths.get(stringFile));
    }

    @Test
    public void testWriteToFile() throws IOException {
        Stats intStats = new Stats();
        Stats floatStats = new Stats();
        StringStats stringStats = new StringStats();

        try (BufferedWriter intWriter = new BufferedWriter(new FileWriter(intFile));
             BufferedWriter floatWriter = new BufferedWriter(new FileWriter(floatFile));
             BufferedWriter stringWriter = new BufferedWriter(new FileWriter(stringFile))) {

            App.writeToFile("src/test/resources/input.txt", intWriter, floatWriter, stringWriter, intStats, floatStats, stringStats);
        }

        assertTrue(Files.exists(Paths.get(intFile)));
        assertTrue(Files.exists(Paths.get(floatFile)));
        assertTrue(Files.exists(Paths.get(stringFile)));

        assertTrue(Files.size(Paths.get(intFile)) > 0);
        assertTrue(Files.size(Paths.get(floatFile)) > 0);
        assertTrue(Files.size(Paths.get(stringFile)) > 0);
    }


    @Test
    public void testIsInteger() {
        assertTrue(App.isInteger("123"));
        assertFalse(App.isInteger("12.3"));
        assertFalse(App.isInteger("abc"));
    }

    @Test
    public void testIsFloat() {
        assertTrue(App.isFloat("12.3"));
        assertTrue(App.isFloat("0.1"));
        assertFalse(App.isFloat("123"));
        assertFalse(App.isFloat("abc"));
    }

    @Test
    public void testStats() {
        Stats stats = new Stats();
        stats.update(10);
        stats.update(20);
        stats.update(5);

        assertEquals(3, stats.count);
        assertEquals(35, stats.sum, 0.0001);
        assertEquals(5, stats.min, 0.0001);
        assertEquals(20, stats.max, 0.0001);
    }

    @Test
    public void testStringStats() {
        StringStats stats = new StringStats();
        stats.update("hello");
        stats.update("world");
        stats.update("java");

        assertEquals(3, stats.count);
        assertEquals(4, stats.minLength);
        assertEquals(5, stats.maxLength);
    }
}