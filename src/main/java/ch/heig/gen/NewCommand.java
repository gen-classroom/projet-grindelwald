package ch.heig.gen;

import picocli.CommandLine;

@CommandLine.Command(
        name = "new",
        description = "Initializes the static site by adding a configuration file containing general information",
        mixinStandardHelpOptions = true,
        version = "1")
public class NewCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("'new' command not implemented");
    }
}