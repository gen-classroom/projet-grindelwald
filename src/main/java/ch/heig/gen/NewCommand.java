package ch.heig.gen;

import picocli.CommandLine;

@CommandLine.Command(
        name = "new",
        description = "Create a new project in the current directory. This adds the necessary files required by the other commands",
        mixinStandardHelpOptions = true,
        version = "1")
public class NewCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("'new' command not implemented");
    }
}