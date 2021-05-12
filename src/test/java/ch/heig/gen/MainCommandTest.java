package ch.heig.gen;

import ch.heig.gen.commands.MainCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import picocli.CommandLine;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainCommandTest {
    // Used to restore JVM print streams
    final PrintStream originalOut = System.out;
    final PrintStream originalErr = System.err;

    // Used to collect command output
    final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    final ByteArrayOutputStream errStream = new ByteArrayOutputStream();

    final List<String> subcommands = List.of("new", "clean", "build", "serve");

    private static boolean strContains(String str, Iterable<String> expected) {
        for (String s : expected)
            if (!str.contains(s))
                return false;
        return true;
    }

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
    public void commandHelpContainsSubcommands() {
        int retCode = new CommandLine(new MainCommand()).execute("--help");
        assertEquals("Return code is zero", 0, retCode);
        String out = outStream.toString();
        assertTrue("Help message contains subcommands", strContains(out, subcommands));
    }

    @Test
    public void helpIsDisplayedWhenNoSubcommandIsGiven() {
        int retCode = new CommandLine(new MainCommand()).execute();
        String err = errStream.toString();
        assertTrue("Return code signals error", retCode != 0);
        assertTrue("Execution asks for required command", err.contains("Missing required subcommand"));
    }
}