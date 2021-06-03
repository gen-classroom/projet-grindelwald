package ch.heig.gen.commands;

import ch.heig.gen.Helper;
import ch.heig.gen.config.ConfigHelper;
import picocli.CommandLine;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@CommandLine.Command(
        name = "init",
        description = "Create a new site",
        mixinStandardHelpOptions = true,
        version = "1")
public class InitCommand implements Runnable {
    @CommandLine.Parameters(description = "The path to the new site")
    Path pathToSite;

    @Override
    public void run() {
        try {
            if (Files.isDirectory(pathToSite)) {
                Helper.deleteDirectory(pathToSite);
            }
            pathToSite = Files.createDirectories(pathToSite);
            Files.writeString(pathToSite.resolve("index.md"), "# Exemple de site", StandardOpenOption.CREATE);
            Files.writeString(pathToSite.resolve("config.xml"), ConfigHelper.defaultConfiguration(), StandardOpenOption.CREATE);
        } catch (IOException exception) {
            System.out.println("An error occured during the creation of the new site : " + exception);
        }
    }
}