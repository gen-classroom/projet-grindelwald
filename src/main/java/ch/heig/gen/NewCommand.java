package ch.heig.gen;

import picocli.CommandLine;

@CommandLine.Command(
        name = "new",
        description = "Not implemented",
        mixinStandardHelpOptions = true,
        version = "1")
public class NewCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("'new' command not implemented");
    }
}