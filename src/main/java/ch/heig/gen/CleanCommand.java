package ch.heig.gen;

import picocli.CommandLine;

@CommandLine.Command(
        name = "clean",
        description = "Cleans up the static site",
        mixinStandardHelpOptions = true,
        version = "1")
public class CleanCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("'clean' command not implemented");
    }
}