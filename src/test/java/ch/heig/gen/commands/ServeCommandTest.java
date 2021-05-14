package ch.heig.gen.commands;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
        assertTrue(err.contains("Incorrect path to site"));
    }

    @Test
    public void ServeCommandNeedsACompiledSite() {
        int retCode = new CommandLine(new MainCommand()).execute("serve", "src/test/");
        assertEquals("Return code is zero", 0, retCode);
        String err = outStream.toString();
        assertTrue(err.contains("The site has not yet been built"));
    }

    @Test
    public void ServeCommandNeedsACorrectlyBuiltSite() {
        File build = new File("src/test/build");
        build.mkdir();
        int retCode = new CommandLine(new MainCommand()).execute("serve", "src/test/");
        assertEquals("Return code is zero", 0, retCode);
        String err = outStream.toString();
        assertTrue(err.contains("There was an error during the site compilation"));
        build.delete();
    }

    @Test
    public void ServeCommandNeedsAContentToShow() {
        File build = new File("src/test/build/");
        build.mkdir();
        File content = new File("src/test/build/content");
        content.mkdir();
        int retCode = new CommandLine(new MainCommand()).execute("serve", "src/test/");
        assertEquals("Return code is zero", 0, retCode);
        String err = outStream.toString();
        assertTrue(err.contains("There is no page to show"));
        build.delete();
        content.delete();
    }

    @Test
    public void ServeCommandShowsSitePerfectly() {
        int retCode = new CommandLine(new MainCommand()).execute("serve", "src/test/resources/");
        assertEquals("Return code is zero", 0, retCode);
    }
}