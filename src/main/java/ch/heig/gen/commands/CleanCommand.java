package ch.heig.gen.commands;

import ch.heig.gen.Helper;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@CommandLine.Command(
        name = "clean",
        description = "Delete a site",
        mixinStandardHelpOptions = true,
        version = "1")
public class CleanCommand implements Runnable {
    @CommandLine.Parameters(description = "The path to the site to clean")
    Path pathToSite;

    @Override
    public void run() {
        try {
            if (Files.isDirectory(pathToSite)) {
                Helper.deleteDirectory(pathToSite);
            }
        } catch (IOException exception) {
            System.out.println("An error occured during the deletion of the site : " + exception);
        }
    }
}