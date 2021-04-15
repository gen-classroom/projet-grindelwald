package ch.heig.gen.commands;

import picocli.CommandLine;

@CommandLine.Command(
        name = "clean",
        description = "Not implemented",
        mixinStandardHelpOptions = true,
        version = "1")
public class CleanCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("'clean' command not implemented");
    }
}