package ch.heig.gen;

import picocli.CommandLine;

@CommandLine.Command(
        name = "serve",
        description = "Serve the static site so that the user can see what it would look like on a real web server",
        mixinStandardHelpOptions = true,
        version = "1")
public class ServeCommand implements Runnable {
    @Override
    public void run() {
        System.out.println("'serve' command not implemented");
    }
}