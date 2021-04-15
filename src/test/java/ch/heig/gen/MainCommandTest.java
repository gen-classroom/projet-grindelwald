package ch.heig.gen;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import ch.heig.gen.commands.MainCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import picocli.CommandLine;

public class MainCommandTest {
  // Used to restore JVM print streams
  final PrintStream originalOut = System.out;
  final PrintStream originalErr = System.err;

  // Used to collect command output
  final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
  final ByteArrayOutputStream errStream = new ByteArrayOutputStream();

  final List<String> subcommands = List.of("new", "clean", "build", "serve");

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

  private static boolean strContains(String str, Iterable<String> expected) {
    for (String s : expected)
      if (!str.contains(s))
        return false;
    return true;
  }

  @Test
  public void commandHelpContainsSubcommands() {
    int retCode = new CommandLine(new MainCommand()).execute("--help");
    assertTrue("Return code is zero", retCode == 0);
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

  @Test
  public void subcommandsAreValid() {
    for (String cmd : subcommands) {
      int retCode = new CommandLine(new MainCommand()).execute(cmd);
      assertTrue("Return code is zero", retCode == 0);
    }
  }
}
