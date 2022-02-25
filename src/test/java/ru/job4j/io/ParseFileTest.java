package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.PrintWriter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 3.1.3. Синхронизация ресурсов.
 * 1. Visibility. Общий ресурс вне критической секции [#1102].
 * Test. ParseFile.
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.02.2022
 */
public class ParseFileTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenParseFileReadAllBytes() throws Exception {
        File source = folder.newFile("source.txt");
        String expected = "test text";
        try (PrintWriter out = new PrintWriter(source)) {
            out.print(expected);
        }
        ParseFile parseFile = new ParseFile(source);
        StringBuffer result = new StringBuffer();
        Thread thread = new Thread(
                () -> result.append(parseFile.getContent(c -> true)));
        Thread thread1 = new Thread(
                () -> result.append(parseFile.getContent(c -> true)));
        thread1.start();
        thread.start();
        thread1.join();
        thread.join();
        assertThat(expected + expected, is(result.toString()));
    }

    @Test
    public void whenParseFileReadOnlyNumberBytes() throws Exception {
        File source = folder.newFile("source.txt");
        String expected = "123123";
        try (PrintWriter out = new PrintWriter(source)) {
            out.print("123one1two2three3");
        }
        ParseFile parseFile = new ParseFile(source);
        StringBuffer result = new StringBuffer();
        Thread thread = new Thread(
                () ->
                        result.append(parseFile.getContent(c -> c > 0x002F && c < 0x003A)));
        Thread thread1 = new Thread(
                () ->
                        result.append(parseFile.getContent(c -> c > 0x002F && c < 0x003A)));
        thread1.start();
        thread.start();
        thread.join();
        thread1.join();
        assertThat(expected + expected, is(result.toString()));
    }
}