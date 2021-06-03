package ch.heig.gen.commands;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

public class ServeCommandTest {
    // Used to restore JVM print streams
    final PrintStream originalOut = System.out;
    final PrintStream originalErr = System.err;

    // Used to collect command output
    final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    final ByteArrayOutputStream errStream = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        outStream.reset();
        errStream.reset();
        System.setOut(new PrintStream(outStream));
        System.setErr(new PrintStream(errStream));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void ServeCommandShowsSitePerfectly() {
        int retCode = new CommandLine(new MainCommand()).execute("serve", "src/test/resources/mon/site");
        String err = errStream.toString();
        assertEquals("Return code is zero", 0, retCode);
    }

    @Test
    public void ServeCommandNeedsAPath() {
        int retCode = new CommandLine(new MainCommand()).execute("serve");
        String err = errStream.toString();
        assertTrue("Return code signals error", retCode != 0);
        assertTrue("Execution asks for required command", err.contains("Missing required parameter"));
    }

    @Test
    public void ServeCommandNeedsACorrectPath() {
        int retCode = new CommandLine(new MainCommand()).execute("serve", "");
        assertEquals("Return code is zero", 0, retCode);
        String err = outStream.toString();
        assertEquals("Incorrect path to site.\r\n", err);
    }

    @Test
    public void ServeCommandNeedsACompiledSite() {
        int retCode = new CommandLine(new MainCommand()).execute("serve", "src/test/resources");
        assertEquals("Return code is zero", 0, retCode);
        String err = outStream.toString();
        assertEquals(err,"The site has not yet been built.\r\n");
    }

    @Test
    public void ServeCommandNeedsACorrectlyBuiltSite() {
        File build = new File("src/test/resources/empty/build");
        build.mkdirs();
        int retCode = new CommandLine(new MainCommand()).execute("serve", "src/test/resources/empty");
        assertEquals("Return code is zero", 0, retCode);

        String err = outStream.toString();
        assertEquals("The site is not correctly built.\r\n", err);
        build.delete();
        new File("src/test/resources/empty").delete();

    }
}