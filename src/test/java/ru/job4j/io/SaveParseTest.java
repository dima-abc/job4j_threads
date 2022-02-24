package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Files;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * 3.1.3. Синхронизация ресурсов.
 * 1. Visibility. Общий ресурс вне критической секции [#1102].
 * Test. SaveParse
 *
 * @author Dmitry Stepanov, user Dmitry
 * @since 24.02.2022
 */
public class SaveParseTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void whenSaveParseOneThread() throws Exception {
        File source = folder.newFile("source.txt");
        SaveParse saveParse = new SaveParse(source);
        String content = "SaveParse";
        Thread thread = new Thread(
                () -> saveParse.saveContent(content));
        thread.start();
        thread.join();
        assertThat(content, is(Files.readString(source.toPath())));
    }

    @Test
    public void whenSaveParseTwoThread() throws Exception {
        File source = folder.newFile("source.txt");
        SaveParse saveParse = new SaveParse(source);
        String content = "SaveParse";
        Thread thread = new Thread(
                () -> saveParse.saveContent(content));
        Thread thread1 = new Thread(
                () -> saveParse.saveContent(content));
        thread.start();
        thread1.start();
        thread1.join();
        thread.join();
        assertThat(content + content, is(Files.readString(source.toPath())));
    }

}