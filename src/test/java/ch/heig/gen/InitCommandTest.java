package ch.heig.gen;

import ch.heig.gen.commands.MainCommand;
import org.junit.Test;
import picocli.CommandLine;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class InitCommandTest {
    @Test
    public void InitCommandRefuseNoFile() {
        int retCode = new CommandLine(new MainCommand()).execute("init");
        assertNotEquals("Return code is not zero", 0, retCode);
    }

    @Test
    public void InitCommandCleanBeforeCreateFiles() throws Exception {
        String textPath = "windows/mon/site";
        Path path = Path.of(textPath);

        int retCode = new CommandLine(new MainCommand()).execute("init", textPath);
        assertEquals("Return code is zero", 0, retCode);

        Files.createDirectory(path.resolve("elem"));
        Files.createFile(path.resolve("elem/autre.txt"));
        Files.createFile(path.resolve("file.txt"));

        assertTrue(Files.exists(path.resolve("index.md")));
        assertTrue(Files.exists(path.resolve("config.xml")));
        assertTrue(Files.exists(path.resolve("file.txt")));
        assertTrue(Files.exists(path.resolve("elem/autre.txt")));


        retCode = new CommandLine(new MainCommand()).execute("init", textPath);
        assertEquals("Return code is zero", 0, retCode);


        assertTrue(Files.exists(path.resolve("index.md")));
        assertTrue(Files.exists(path.resolve("config.xml")));
        assertFalse(Files.exists(path.resolve("file.txt")));
        assertFalse(Files.exists(path.resolve("elem/autre.txt")));
    }
}