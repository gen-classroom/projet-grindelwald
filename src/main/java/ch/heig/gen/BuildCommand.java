package ch.heig.gen;

import picocli.CommandLine;

@CommandLine.Command(
        name = "build",
        description = "Not implemented",
        mixinStandardHelpOptions = true,
        version = "1")
public class BuildCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("'build' command not implemented");
    }
}