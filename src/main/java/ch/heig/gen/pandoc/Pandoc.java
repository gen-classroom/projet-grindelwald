package ch.heig.gen.pandoc;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class Pandoc {
  private static Logger logger = Logger.getLogger(Pandoc.class.getName());

  public static void render(String input, String output) {
    run(input, output);
  }

  private static void run(String input, String output) {
    if (!pandocIsAvailable()) {
      throw new RuntimeException("Pandoc command is unavailable.");
    }
    CommandBuilder pc = new CommandBuilder().withInputFiles(input)
        .withFlags("-s").withOutputFile(output);
    try {
      Process p = Runtime.getRuntime().exec(pc.toCommand());
      p.waitFor();
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Pandoc command failed.", e);
    }
  }

  private static boolean pandocIsAvailable() {
    try {
      Process p = Runtime.getRuntime()
          .exec(new CommandBuilder().withFlags("-v").toCommand());
      p.waitFor();
      if (p.exitValue() != 0)
        return false;
    } catch (IOException e) {
      return false;
    } catch (InterruptedException e) {
      logger.info("Timeout running pandoc to check it availability.");
      return false;
    }
    return true;
  }

  private static class CommandBuilder {
    List<String> inputFiles = new LinkedList<>();
    List<String> flags = new LinkedList<>();
    String outputFile = null;

    public CommandBuilder withInputFiles(String... inputFilesToAdd) {
      for (String s : inputFilesToAdd)
        inputFiles.add(s);
      return this;
    }

    public CommandBuilder withOutputFile(String outputFile) {
      this.outputFile = outputFile;
      return this;
    }

    public CommandBuilder withFlags(String... flagsToAdd) {
      for (String s : flagsToAdd)
        flags.add(s);
      return this;
    }

    public String[] toCommand() {
      LinkedList<String> ret = new LinkedList<>();
      ret.add("pandoc");
      ret.addAll(flags);
      ret.addAll(inputFiles);
      if (outputFile != null) {
        ret.add("-o");
        ret.add(outputFile);
      }
      return ret.toArray(new String[0]);
    }
  }
}
