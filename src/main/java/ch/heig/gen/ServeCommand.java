package ch.heig.gen;

import picocli.CommandLine;

@CommandLine.Command(
        name = "serve",
        description = "Not implemented",
        mixinStandardHelpOptions = true,
        version = "1")
public class ServeCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("'serve' command not implemented");
    }
}