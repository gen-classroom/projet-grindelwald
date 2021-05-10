package ch.heig.gen;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.heig.gen.pandoc.Pandoc;

public class PandocCommandTest {
  Path tmpdir;

  @Before
  public void prepare() throws IOException {
    tmpdir = Files.createTempDirectory("arancia-test");
  }

  private static void deleteDirectory(File directory) {
    File[] contents = directory.listFiles();
    if (contents != null)
      for (File f : contents)
        deleteDirectory(f);
    directory.delete();
  }

  @After
  public void cleanup() throws IOException {
    if (tmpdir != null)
      deleteDirectory(tmpdir.toFile());
  }

  @Test
  public void convertsTrivialMarkdown() throws Exception {
    String sampleText = "Culpa magni amet laborum dolores tempore illum.";
    Path inputFile = tmpdir.resolve("input.md");
    Path outputFile = tmpdir.resolve("output.html");
    Files.writeString(inputFile, sampleText);
    Files.createFile(outputFile);
    Pandoc.render(inputFile.toString(), outputFile.toString());

    String outputFileContents = Files.readString(outputFile);
    // Parsing the HTML without 3rd party libs is painful...
    assertTrue("Output file has generator tag set to pandoc",
        outputFileContents.contains("name=\"generator\" content=\"pandoc\""));
    assertTrue("Output file has text as a substring",
        outputFileContents.contains(sampleText));
  };
}
